package com.example.fashion_commerce.order.responses;

import com.example.fashion_commerce.order.Order;

import java.util.Objects;

public class OrderDetails {
    private Order order;
    private double subTotal;
    private double shippingCost;
    private double tax;
    private double totalPrice;

    public OrderDetails(Order order, double subTotal, double shippingCost, double tax, double totalPrice) {
        this.order = order;
        this.subTotal = subTotal;
        this.shippingCost = shippingCost;
        this.tax = tax;
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
        return Double.compare(subTotal, that.subTotal) == 0 && Double.compare(shippingCost, that.shippingCost) == 0 && Double.compare(tax, that.tax) == 0 && Double.compare(totalPrice, that.totalPrice) == 0 && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, subTotal, shippingCost, tax, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "order=" + order +
                ", subTotal=" + subTotal +
                ", shippingCost=" + shippingCost +
                ", tax=" + tax +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
