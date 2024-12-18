package com.example.fashion_commerce.order;

import com.example.fashion_commerce.admin.responses.GraphOrderAnalytics;
import com.example.fashion_commerce.admin.responses.OrderAnalytics;
import com.example.fashion_commerce.exception.Error;
import com.example.fashion_commerce.exception.FashionCommerceException;
import com.example.fashion_commerce.exception.Message;
import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.mail.MailSender;
import com.example.fashion_commerce.order.checkout.ContactInfo;
import com.example.fashion_commerce.order.checkout.ShippingAddress;
import com.example.fashion_commerce.order.requests.*;
import com.example.fashion_commerce.order.responses.OrderDetails;
import com.example.fashion_commerce.product.Product;
import com.example.fashion_commerce.product.ProductRepository;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MailSender mailSender;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, MailSender mailSender) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.mailSender = mailSender;
    }



    @Override
    public ResponseEntity<Response<Order>> createOrder(CreateOrder createOrder) {

        Order order = createdOrder(createOrder);

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String username = order.getShippingAddress().getFirstname() + " " + order.getShippingAddress().getLastname(); // combine the username

            List<Double> tempSubtotalPrice = new ArrayList<>();

            for (OrderProducts product : order.getProducts()) {
                double price = product.getProduct().getPrice();
                int quantity = product.getQuantity();

                tempSubtotalPrice.add((price * quantity));
            }

            // calculate the costs
            double shippingCost = order.getShippingAddress().getShippingCost();
            double tax = order.getShippingAddress().getTax();
            double tempTotalPrice = shippingCost + tax;
            double subtotalPrice = tempSubtotalPrice.stream().mapToDouble(Double::doubleValue).sum();
            double totalPrice = (subtotalPrice + tempTotalPrice);

            Map<String, Object> variables = Map.ofEntries(
                    Map.entry("username", username),
                    Map.entry("address", order.getShippingAddress().getAddress()),
                    Map.entry("phone", order.getContactInfo().getPhone()),
                    Map.entry("email", order.getContactInfo().getEmail()),
                    Map.entry("date", formatter.format(order.getDateCreated())),
                    Map.entry("orderId", order.getId()),
                    Map.entry("products", order.getProducts()),
                    Map.entry("subTotal", subtotalPrice),
                    Map.entry("shippingCost", shippingCost),
                    Map.entry("tax", tax),
                    Map.entry("total", totalPrice)
            );

            mailSender.sendMail(
                    order.getContactInfo().getEmail(),
                    "Thank you for your order 🌹🌹",
                    variables,
                    "orderConfirmation"
            );

            notifyAdminOfNewOrders(order, subtotalPrice, totalPrice);

            Response<Order> orderResponse = new Response<>(
                    HttpStatus.CREATED.value(),
                    "order created successfully",
                    order
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);

        } catch (Exception failedException) {
            throw new FashionCommerceException(Error.ERROR_CREATING_ORDER, new Throwable(failedException.getCause().getMessage()));
        }
    }

    private void notifyAdminOfNewOrders(Order order, double subtotal, double totalCost) throws MessagingException {
        Map<String, Object> newOrderVariables = Map.of(
                "customerName", order.getShippingAddress().getLastname() + " " + order.getShippingAddress().getFirstname(),
                "orderId", order.getId(),
                "date", order.getDateCreated(),
                "products", order.getProducts(),
                "subTotal", subtotal,
                "shippingCost", order.getShippingAddress().getShippingCost(),
                "tax", order.getShippingAddress().getTax(),
                "total", totalCost
        );

        mailSender.sendMail(
                "nusetor.setsofia.kojo@gmail.com",
                "New Order Received: " + order.getId(),
                newOrderVariables,
                "newOrderAdminNotification"
        );
    }

    private Order createdOrder(CreateOrder createOrder) {

        List<String> ids = createOrder.getProductIDs().stream().map(OrderProductsIds::getId).toList();
        List<String> validIDs = validateIDs(ids);
        if (validIDs.isEmpty()) {
            throw new FashionCommerceException(Error.INVALID_PRODUCT_IDS, new Throwable(Message.THE_REQUESTED_PRODUCT_ID_IS_INCORRECT.label));
        }

        List<OrderProducts> products = new ArrayList<>();
        for (String id : validIDs) {
            Product product = productRepository.findProductById(id);

            for (OrderProductsIds orderProducts : createOrder.getProductIDs()) {
                if (id.equals(orderProducts.getId())) {
                    products.add(
                            new OrderProducts(product, orderProducts.getQuantity())
                    );
                }
            }
        }

        ContactInfo contactInfo = createContactInfo(createOrder);
        ShippingAddress shippingAddress = createShippingAddress(createOrder);

        Order order = new Order(
                contactInfo,
                shippingAddress,
                products,
                OrderStatus.PENDING,
                "",
                LocalDateTime.now(),
                LocalDateTime.now()
        );


        try {
            return orderRepository.save(order);
        } catch (Exception exception) {
            throw new FashionCommerceException(Error.ERROR_SAVING_DATA, new Throwable(Message.CANNOT_PLACE_ORDER.label));
        }
    }

    private List<String> validateIDs(List<String> ids) {
        List<String> productIDs = productRepository.findAll().stream().map(Product::getId).toList();

        // Ensure ids is modifiable by creating a new ArrayList
        ids = new ArrayList<>(ids);

        ids.retainAll(productIDs);

        return ids;
    }

    private ContactInfo createContactInfo(CreateOrder createOrder) {
        return new ContactInfo(
                createOrder.getEmail(),
                createOrder.getPhone()
        );
    }

    private ShippingAddress createShippingAddress(CreateOrder order) {
        return new ShippingAddress(
                order.getFirstname(),
                order.getLastname(),
                order.getCountry(),
                order.getRegion(),
                order.getAddress(),
                order.getCity(),
                order.getPostalCode(),
                order.getShippingCost(),
                order.getTax()
        );
    }



    @Override
    public ResponseEntity<Response<List<Order>>> getOrders(boolean all, RequestOrderStatus status) {
        List<Order> orders;

        if (all) {
            orders = orderRepository.findAll();
        } else {
            if (!isValidOrderStatus(status.getStatus())) {
                throw new FashionCommerceException(Error.INVALID_ORDER_STATUS, new Throwable(Message.THE_REQUESTED_ORDER_STATUS_IS_INVALID.label));
            }

            orders = orderRepository.findOrderByStatus(status); //get the orders with the specified status
        }

        Response<List<Order>> orderResponse = new Response<>(
                HttpStatus.OK.value(),
                "all orders",
                orders,
                String.valueOf(orders.size())
        );

        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    private boolean isValidOrderStatus(String status) {
        return Arrays.stream(OrderStatus.values()).anyMatch(orderStatus -> orderStatus.name().equals(status.toUpperCase()));
    }



    @Override
    public ResponseEntity<Response<OrderDetails>> getOrder(String id) {

        Order order = verifyOrder(id);

        List<Double> tempSubtotalPrice = new ArrayList<>();
        for (OrderProducts product : order.getProducts()) {
            double price = product.getProduct().getPrice();
            int quantity = product.getQuantity();

            tempSubtotalPrice.add((price * quantity));
        }

        // calculate the costs
        double shippingCost = order.getShippingAddress().getShippingCost();
        double tax = order.getShippingAddress().getTax();
        double tempTotalPrice = shippingCost + tax;
        double subtotalPrice = tempSubtotalPrice.stream().mapToDouble(Double::doubleValue).sum();
        double totalPrice = (subtotalPrice + tempTotalPrice);

        Response<OrderDetails> orderResponse = new Response<>(
                HttpStatus.OK.value(),
                "order",
                new OrderDetails(
                        order,
                        subtotalPrice,
                        shippingCost,
                        tax,
                        totalPrice
                )
        );
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }


    @Override
    public ResponseEntity<Response<Order>> updateOrder(String id, String status, UpdateOrder notes) {

        Order order = verifyOrder(id);
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());

        // confirm that the supplied status is correct
        if (!Arrays.stream(OrderStatus.values()).toList().contains(orderStatus)) {
            throw new FashionCommerceException(Error.INVALID_ORDER_STATUS, new Throwable(Message.THE_REQUESTED_ORDER_STATUS_IS_INVALID.label));
        }

        // update the order status
        try {
            order.setOrderStatus(orderStatus);
            order.setNotes(notes.notes());
            Order updatedOrder = orderRepository.save(order);

            orderStatusUpdateNotification(order);

            Response<Order> orderResponse = new Response<>(
                    HttpStatus.CREATED.value(),
                    "order status updated successfully",
                    updatedOrder
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
        } catch (Exception e) {
            throw new FashionCommerceException(Error.ERROR_SAVING_DATA, new Throwable(Message.CANNOT_SAVE_THE_DATA.label));
        }
    }

    private void orderStatusUpdateNotification(Order order) throws MessagingException {
        List<Double> tempSubtotalPrice = new ArrayList<>();

        for (OrderProducts product : order.getProducts()) {
            double price = product.getProduct().getPrice();
            int quantity = product.getQuantity();

            tempSubtotalPrice.add((price * quantity));
        }

        // calculate the costs
        double shippingCost = order.getShippingAddress().getShippingCost();
        double tax = order.getShippingAddress().getTax();
        double tempTotalPrice = shippingCost + tax;
        double subtotalPrice = tempSubtotalPrice.stream().mapToDouble(Double::doubleValue).sum();
        double totalPrice = (subtotalPrice + tempTotalPrice);

        Map<String, Object> variables = Map.ofEntries(
                Map.entry("orderId", order.getId()),
                Map.entry("status", order.getOrderStatus()),
                Map.entry("notes", order.getNotes()),
                Map.entry("products", order.getProducts()),
                Map.entry("subTotal", subtotalPrice),
                Map.entry("shippingCost", shippingCost),
                Map.entry("tax", tax),
                Map.entry("total", totalPrice)
        );

        mailSender.sendMail(
                order.getContactInfo().getEmail(),
                "Your Order Status Has Been Updated - Order #" + order.getId(),
                variables,
                "orderStatusUpdateNotification"
        );
    }

    private Order verifyOrder(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new FashionCommerceException(
                Error.INVALID_ORDER_ID, new Throwable(Message.THE_REQUESTED_ORDER_ID_IS_INCORRECT.label)
        ));
    }

    @Override
    public ResponseEntity<Response<OrderAnalytics>> getOrderAnalytics() {
        double orders = getAllOrders("all");
        double pendingOrders = getAllOrders("PENDING");
        double deliveredOrders = getAllOrders("DELIVERED");
        double cancelledOrders = getAllOrders("CANCELLED");

        Response<OrderAnalytics> orderResponse = new Response<>(
          HttpStatus.OK.value(),
          "order analytics",
          new OrderAnalytics(
                  orders,
                  pendingOrders,
                  deliveredOrders,
                  cancelledOrders
          )
        );

        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    private double getAllOrders(String status) {
        LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);

        List<Order> orders = orderRepository.findOrderByDateAndStatus(lastMonth, status);

        return orders.stream()
                .map(Order::getProducts)
                .flatMap(Collection::stream)
                .map(orderProduct -> orderProduct.getProduct().getPrice() * orderProduct.getQuantity())
                .reduce((double) 0, Double::sum);
    }



    @Override
    public ResponseEntity<Response<?>> getGraphOrderAnalytics() {
        LinkedHashMap<String, Double> graph = new LinkedHashMap<>();

        double jan = getTotalMonthlySales(1);
        double feb = getTotalMonthlySales(2);
        double mar = getTotalMonthlySales(3);
        double apr = getTotalMonthlySales(4);
        double may = getTotalMonthlySales(5);
        double jun = getTotalMonthlySales(6);
        double jul = getTotalMonthlySales(7);
        double aug = getTotalMonthlySales(8);
        double sep = getTotalMonthlySales(9);
        double oct = getTotalMonthlySales(10);
        double nov = getTotalMonthlySales(11);
        double dec = getTotalMonthlySales(12);

        graph.put("Jan", jan);
        graph.put("Feb", feb);
        graph.put("Mar", mar);
        graph.put("Apr", apr);
        graph.put("May", may);
        graph.put("Jun", jun);
        graph.put("Jul", jul);
        graph.put("Aug", aug);
        graph.put("Sep", sep);
        graph.put("Oct", oct);
        graph.put("Nov", nov);
        graph.put("Dec", dec);

        Response<GraphOrderAnalytics> response = getGraphOrderAnalyticsResponse(graph);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private static Response<GraphOrderAnalytics> getGraphOrderAnalyticsResponse(LinkedHashMap<String, Double> graph) {
        List<String> months = new ArrayList<>();
        List<Double> sales = new ArrayList<>();

        for (Map.Entry<String, Double> data : graph.entrySet()) {
            months.add(data.getKey());
            sales.add(data.getValue());
        }

        return new Response<>(
                HttpStatus.OK.value(),
                "order graph analytics",
                new GraphOrderAnalytics(
                        months,
                        sales
                )
        );
    }

    private double getTotalMonthlySales(int month) {
        List<List<OrderProducts>> orders = orderRepository.findOrdersByMonth(month).stream().map(Order::getProducts).toList();
        double finalPrice = 0;

        for (var order : orders) {
            for (OrderProducts orderProducts : order) {

                double quantity = orderProducts.getQuantity();
                double productPrice = orderProducts.getProduct().getPrice();

                double tempPrice = quantity * productPrice;
                finalPrice += tempPrice;
            }
        }

        return finalPrice;
    }
}
