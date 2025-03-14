package com.oksavch.orderclient.dto;

import com.oksavch.orderclient.enums.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderResponseDTO {

    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private Double amount;
    private Double price;
    private OrderStatus status;

}
