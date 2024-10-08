package com.example.fashion_commerce.order;

import com.querydsl.core.types.Predicate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.example.fashion_commerce.order.requests.RequestOrderStatus;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String>, QuerydslPredicateExecutor<Order> {

    default List<Order> findOrderByStatus(RequestOrderStatus orderStatus) {
        QOrder qOrder = new QOrder("orders");
        Predicate predicate = qOrder.orderStatus.eq(OrderStatus.valueOf(orderStatus.getStatus().toUpperCase())) ;

        return (List<Order>) findAll(predicate);
    }
}
