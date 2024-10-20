package com.example.fashion_commerce.order.requests;

import java.util.List;
import java.util.Objects;

public class CreateOrder {
    private String email;
    private String phone;
    private String firstname;
    private String lastname;
    private String country;
    private String region;
    private String address;
    private String city;
    private String postalCode;
    private double shippingCost;
    private double tax;
    private List<OrderProductsIds> productIDs;

    public CreateOrder(String email, String phone, String firstname, String lastname, String country, String region, String address, String city, String postalCode, double shippingCost, double tax, List<OrderProductsIds> productIDs) {
        this.email = email;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.region = region;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.shippingCost = shippingCost;
        this.tax = tax;
        this.productIDs = productIDs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public List<OrderProductsIds> getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(List<OrderProductsIds> productIDs) {
        this.productIDs = productIDs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrder that = (CreateOrder) o;
        return Double.compare(shippingCost, that.shippingCost) == 0 && Double.compare(tax, that.tax) == 0 && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(country, that.country) && Objects.equals(region, that.region) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode) && Objects.equals(productIDs, that.productIDs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone, firstname, lastname, country, region, address, city, postalCode, shippingCost, tax, productIDs);
    }

    @Override
    public String toString() {
        return "CreateOrder{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", shippingCost=" + shippingCost +
                ", tax=" + tax +
                ", productIDs=" + productIDs +
                '}';
    }
}
