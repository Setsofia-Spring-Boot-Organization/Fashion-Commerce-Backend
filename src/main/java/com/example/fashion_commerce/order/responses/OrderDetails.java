package com.example.fashion_commerce.order.responses;

import com.example.fashion_commerce.order.OrderStatus;
import com.example.fashion_commerce.order.checkout.ContactInfo;
import com.example.fashion_commerce.order.checkout.ShippingAddress;
import com.example.fashion_commerce.order.requests.OrderProducts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderDetails {
    private String id;
    private ContactInfo contactInfo;
    private ShippingAddress shippingAddress;
    private List<OrderProducts> products;
    private OrderStatus orderStatus;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private double subTotal;
    private double shippingCost;
    private double tax;
    private double totalPrice;

    public OrderDetails(String id, ContactInfo contactInfo, ShippingAddress shippingAddress, List<OrderProducts> products, OrderStatus orderStatus, LocalDateTime dateCreated, LocalDateTime dateUpdated, double subTotal, double shippingCost, double tax, double totalPrice) {
        this.id = id;
        this.contactInfo = contactInfo;
        this.shippingAddress = shippingAddress;
        this.products = products;
        this.orderStatus = orderStatus;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.subTotal = subTotal;
        this.shippingCost = shippingCost;
        this.tax = tax;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderProducts> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProducts> products) {
        this.products = products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return Double.compare(subTotal, that.subTotal) == 0 && Double.compare(shippingCost, that.shippingCost) == 0 && Double.compare(tax, that.tax) == 0 && Double.compare(totalPrice, that.totalPrice) == 0 && Objects.equals(id, that.id) && Objects.equals(contactInfo, that.contactInfo) && Objects.equals(shippingAddress, that.shippingAddress) && Objects.equals(products, that.products) && orderStatus == that.orderStatus && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(dateUpdated, that.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactInfo, shippingAddress, products, orderStatus, dateCreated, dateUpdated, subTotal, shippingCost, tax, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id='" + id + '\'' +
                ", contactInfo=" + contactInfo +
                ", shippingAddress=" + shippingAddress +
                ", products=" + products +
                ", orderStatus=" + orderStatus +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", subTotal=" + subTotal +
                ", shippingCost=" + shippingCost +
                ", tax=" + tax +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
