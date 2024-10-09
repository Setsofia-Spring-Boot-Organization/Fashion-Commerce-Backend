package com.example.fashion_commerce.order;

import com.example.fashion_commerce.order.checkout.ContactInfo;
import com.example.fashion_commerce.order.checkout.ShippingAddress;
import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

@Document
@QueryEntity
public class Order {

    @MongoId
    private String id;

    private ContactInfo contactInfo;
    private ShippingAddress shippingAddress;
    private List<String> productIDs;

    public Order(String id, ContactInfo contactInfo, ShippingAddress shippingAddress, List<String> productIDs) {
        this.id = id;
        this.contactInfo = contactInfo;
        this.shippingAddress = shippingAddress;
        this.productIDs = productIDs;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(contactInfo, order.contactInfo) && Objects.equals(shippingAddress, order.shippingAddress) && Objects.equals(productIDs, order.productIDs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactInfo, shippingAddress, productIDs);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", contactInfo=" + contactInfo +
                ", shippingAddress=" + shippingAddress +
                ", productIDs=" + productIDs +
                '}';
    }
}
