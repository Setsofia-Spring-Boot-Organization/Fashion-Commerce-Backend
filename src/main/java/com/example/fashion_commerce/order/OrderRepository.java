package com.example.fashion_commerce.order;

import com.querydsl.core.types.Predicate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.example.fashion_commerce.order.requests.RequestOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String>, QuerydslPredicateExecutor<Order> {

    default List<Order> findOrderByStatus(RequestOrderStatus orderStatus) {
        QOrder qOrder = new QOrder("orders");
        Predicate predicate = qOrder.orderStatus.eq(OrderStatus.valueOf(orderStatus.getStatus().toUpperCase())) ;

        return (List<Order>) findAll(predicate);
    }

    default List<Order> findOrderByDateAndStatus(LocalDateTime date, String status) {
        QOrder qOrder = new QOrder("orders");
        Predicate predicate;

        if (status.equals("all")) {
            predicate = qOrder.dateCreated.after(date);
        } else {
            predicate = qOrder.dateCreated.after(date).and(
                    qOrder.orderStatus.eq(OrderStatus.valueOf(status.toUpperCase()))
            );
        }

        assert predicate != null;
        return (List<Order>) findAll(predicate);
    }

    default List<Order> findOrdersByMonth(int month) {
        LocalDateTime startDate = LocalDateTime.now().withMonth(month).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDate = startDate.plusMonths(1);

        QOrder qOrder = new QOrder("Orders");
        Predicate predicate = qOrder.orderStatus.eq(OrderStatus.valueOf("DELIVERED")).and(
                qOrder.dateUpdated.goe(startDate)
        ).and(
                qOrder.dateUpdated.lt(endDate)
        );

        return (List<Order>) findAll(predicate);
    }
}
