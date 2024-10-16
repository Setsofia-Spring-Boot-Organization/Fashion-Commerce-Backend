package com.example.fashion_commerce.order;

import com.example.fashion_commerce.exception.Error;
import com.example.fashion_commerce.exception.FashionCommerceException;
import com.example.fashion_commerce.exception.Message;
import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.mail.MailSender;
import com.example.fashion_commerce.order.checkout.ContactInfo;
import com.example.fashion_commerce.order.checkout.ShippingAddress;
import com.example.fashion_commerce.order.requests.CreateOrder;
import com.example.fashion_commerce.product.Product;
import com.example.fashion_commerce.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.fashion_commerce.order.requests.RequestOrderStatus;

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
            //TODO:
            // 1. replace the hardcoded texts with variables
            // 2. create a template for the variables
            // 3. set a sensible email subject
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String username = order.getShippingAddress().getFirstname() + " " + order.getShippingAddress().getLastname(); // combine the username
            List<Double> prices = order.getProducts().stream().map(Product::getPrice).toList();

            // calculate the costs
            double price = prices.stream().mapToDouble(Double::doubleValue).sum();
            double shippingCost = 0.0;
            double tax = 0.0;
            double tempTotalPrice = shippingCost + tax;
            double totalPrice = price + tempTotalPrice;

            Map<String, Object> variables = Map.ofEntries(
                    Map.entry("username", username),
                    Map.entry("address", order.getShippingAddress().getAddress()),
                    Map.entry("phone", order.getContactInfo().getPhone()),
                    Map.entry("email", order.getContactInfo().getEmail()),
                    Map.entry("date", formatter.format(order.getDateCreated())),
                    Map.entry("orderId", order.getId()),
                    Map.entry("products", order.getProducts()),
                    Map.entry("subTotal", price),
                    Map.entry("shippingCost", shippingCost),
                    Map.entry("tax", tax),
                    Map.entry("totalPrice", totalPrice)
            );

            mailSender.sendMail(
                    order.getContactInfo().getEmail(),
                    "Thank you for your order 🌹🌹",
                    variables,
                    "orderConfirmation"
            );

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

    private Order createdOrder(CreateOrder createOrder) {

        List<String> validIDs = validateIDs(createOrder.getProductIDs());
        if (validIDs.isEmpty()) {
            throw new FashionCommerceException(Error.INVALID_PRODUCT_IDS, new Throwable(Message.THE_REQUESTED_PRODUCT_ID_IS_INCORRECT.label));
        }

        List<Product> products = new ArrayList<>();
        for (String id : validIDs) {
            Product product = productRepository.findProductById(id);
            products.add(product);
        }

        ContactInfo contactInfo = createContactInfo(createOrder);
        ShippingAddress shippingAddress = createShippingAddress(createOrder);

        Order order = new Order(
                contactInfo,
                shippingAddress,
                products,
                OrderStatus.PENDING,
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
        ids.retainAll(productIDs);
        return ids;
    }

    private ContactInfo createContactInfo(CreateOrder createOrder) {
        return new ContactInfo(
                createOrder.getEmail(),
                createOrder.getPhone()
        );
    }

    private ShippingAddress createShippingAddress(CreateOrder createOrder) {
        return new ShippingAddress(
                createOrder.getFirstname(),
                createOrder.getLastname(),
                createOrder.getCountry(),
                createOrder.getRegion(),
                createOrder.getAddress(),
                createOrder.getCity(),
                createOrder.getPostalCode()
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
    public ResponseEntity<Response<Order>> getOrder(String id) {

        Order order = verifyOrder(id);

        Response<Order> orderResponse = new Response<>(
                HttpStatus.OK.value(),
                "order",
                order
        );
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }


    @Override
    public ResponseEntity<Response<Order>> updateOrder(String id, String status) {

        Order order = verifyOrder(id);
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());

        // confirm that the supplied status is correct
        if (!Arrays.stream(OrderStatus.values()).toList().contains(orderStatus)) {
            throw new FashionCommerceException(Error.INVALID_ORDER_STATUS, new Throwable(Message.THE_REQUESTED_ORDER_STATUS_IS_INVALID.label));
        }

        // update the order status
        try {
            order.setOrderStatus(orderStatus);
            Order updatedOrder = orderRepository.save(order);

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

    private Order verifyOrder(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new FashionCommerceException(
                Error.INVALID_ORDER_ID, new Throwable(Message.THE_REQUESTED_ORDER_ID_IS_INCORRECT.label)
        ));
    }
}
