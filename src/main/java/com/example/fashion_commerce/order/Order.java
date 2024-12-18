package com.example.fashion_commerce.order;

import com.example.fashion_commerce.order.checkout.ContactInfo;
import com.example.fashion_commerce.order.checkout.ShippingAddress;
import com.example.fashion_commerce.order.requests.OrderProducts;
import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@QueryEntity
@Document(collection = "orders")
public class Order {

    @MongoId
    private String id;

    private ContactInfo contactInfo;
    private ShippingAddress shippingAddress;
    private List<OrderProducts> products;
    private OrderStatus orderStatus;
    private String notes;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public Order(ContactInfo contactInfo, ShippingAddress shippingAddress, List<OrderProducts> products, OrderStatus orderStatus, String notes, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.contactInfo = contactInfo;
        this.shippingAddress = shippingAddress;
        this.products = products;
        this.orderStatus = orderStatus;
        this.notes = notes;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(contactInfo, order.contactInfo) && Objects.equals(shippingAddress, order.shippingAddress) && Objects.equals(products, order.products) && orderStatus == order.orderStatus && Objects.equals(notes, order.notes) && Objects.equals(dateCreated, order.dateCreated) && Objects.equals(dateUpdated, order.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactInfo, shippingAddress, products, orderStatus, notes, dateCreated, dateUpdated);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", contactInfo=" + contactInfo +
                ", shippingAddress=" + shippingAddress +
                ", products=" + products +
                ", orderStatus=" + orderStatus +
                ", notes=" + notes +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
