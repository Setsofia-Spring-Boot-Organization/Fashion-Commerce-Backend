package com.example.fashion_commerce.order.checkout;

import java.util.Objects;

public class ContactInfo {
    private String email;
    private String phone;

    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone);
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
