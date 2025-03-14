package com.oksavch.order.controller;

import com.oksavch.order.entity.Order;
import com.oksavch.order.service.OrderService;
import com.oksavch.orderclient.dto.OrderRequestDTO;
import com.oksavch.orderclient.dto.OrderResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
@AllArgsConstructor
public class OrderController {

    private OrderService service;

    @PostMapping("/create")
    public Mono<Order> createOrder(@RequestBody Mono<OrderRequestDTO> mono) {
        return mono.flatMap(this.service::createOrder);
    }

    @GetMapping("/all")
    public Flux<OrderResponseDTO> getOrders() {
        return this.service.getAll();
    }

}
