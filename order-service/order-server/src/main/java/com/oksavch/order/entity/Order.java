package com.oksavch.order.entity;

import com.oksavch.orderclient.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@ToString
@Table(name = "orders")
public class Order {
    @Id
    private UUID id;

    @Column("user_id")
    private Integer userId;
    @Column("product_id")
    private Integer productId;
    private Double price;
    private OrderStatus status;
}
