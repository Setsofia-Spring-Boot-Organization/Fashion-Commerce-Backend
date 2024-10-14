package com.example.fashion_commerce.order;

import com.example.fashion_commerce.order.checkout.ContactInfo;
import com.example.fashion_commerce.order.checkout.ShippingAddress;
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
    private List<String> productIDs;
    private OrderStatus orderStatus;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public Order() {}

    public Order(ContactInfo contactInfo, ShippingAddress shippingAddress, List<String> productIDs, OrderStatus orderStatus, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.contactInfo = contactInfo;
        this.shippingAddress = shippingAddress;
        this.productIDs = productIDs;
        this.orderStatus = orderStatus;
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

    public List<String> getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(List<String> productIDs) {
        this.productIDs = productIDs;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(contactInfo, order.contactInfo) && Objects.equals(shippingAddress, order.shippingAddress) && Objects.equals(productIDs, order.productIDs) && orderStatus == order.orderStatus && Objects.equals(dateCreated, order.dateCreated) && Objects.equals(dateUpdated, order.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactInfo, shippingAddress, productIDs, orderStatus, dateCreated, dateUpdated);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", contactInfo=" + contactInfo +
                ", shippingAddress=" + shippingAddress +
                ", productIDs=" + productIDs +
                ", orderStatus=" + orderStatus +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }


}
