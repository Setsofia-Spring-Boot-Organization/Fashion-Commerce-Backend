package com.example.fashion_commerce.order.requests;

import java.util.Objects;

public class UpdateOrder {
    private String notes;

    public UpdateOrder(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateOrder that = (UpdateOrder) o;
        return Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(notes);
    }

    @Override
    public String toString() {
        return "UpdateOrder{" +
                "notes='" + notes + '\'' +
                '}';
    }
}
