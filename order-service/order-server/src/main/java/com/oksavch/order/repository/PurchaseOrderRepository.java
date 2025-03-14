package com.oksavch.order.repository;

import com.oksavch.order.entity.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends ReactiveCrudRepository<Order, UUID> {
}
