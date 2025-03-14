package com.oksavch.order.service;

import com.oksavch.order.entity.Order;
import com.oksavch.order.repository.PurchaseOrderRepository;
import com.oksavch.orderclient.dto.OrchestratorRequestDTO;
import com.oksavch.orderclient.dto.OrderRequestDTO;
import com.oksavch.orderclient.dto.OrderResponseDTO;
import com.oksavch.orderclient.enums.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService {

    private static final Map<Integer, Double> PRODUCT_PRICE = Map.of(
            1, 100d,
            2, 200d,
            3, 300d
    );

    private PurchaseOrderRepository purchaseOrderRepository;
    private Sinks.Many<OrchestratorRequestDTO> sink;

    public Mono<Order> createOrder(OrderRequestDTO orderRequestDTO) {
        return this.purchaseOrderRepository.save(this.dtoToEntity(orderRequestDTO))
                .doOnNext(e -> orderRequestDTO.setOrderId(e.getId()))
                .doOnNext(e -> this.emitEvent(orderRequestDTO));
    }

    public Flux<OrderResponseDTO> getAll() {
        return this.purchaseOrderRepository.findAll()
                .map(this::entityToDto);
    }

    private void emitEvent(OrderRequestDTO orderRequestDTO) {
        this.sink.tryEmitNext(this.getOrchestratorRequestDTO(orderRequestDTO));
    }

    private Order dtoToEntity(final OrderRequestDTO dto) {
        Order order = new Order();
        order.setId(dto.getOrderId());
        order.setProductId(dto.getProductId());
        order.setUserId(dto.getUserId());
        order.setStatus(OrderStatus.ORDER_CREATED);
        order.setPrice(PRODUCT_PRICE.get(order.getProductId()));
        return order;
    }

    private OrderResponseDTO entityToDto(final Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setProductId(order.getProductId());
        dto.setUserId(order.getUserId());
        dto.setStatus(order.getStatus());
        dto.setAmount(order.getPrice());
        return dto;
    }

    public OrchestratorRequestDTO getOrchestratorRequestDTO(OrderRequestDTO orderRequestDTO) {
        OrchestratorRequestDTO requestDTO = new OrchestratorRequestDTO();
        requestDTO.setUserId(orderRequestDTO.getUserId());
        requestDTO.setAmount(PRODUCT_PRICE.get(orderRequestDTO.getProductId()));
        requestDTO.setOrderId(orderRequestDTO.getOrderId());
        requestDTO.setProductId(orderRequestDTO.getProductId());
        return requestDTO;
    }

}
