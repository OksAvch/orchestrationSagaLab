package com.oksavch.order.service;

import com.oksavch.order.repository.PurchaseOrderRepository;
import com.oksavch.orderclient.dto.OrchestratorResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OrderEventUpdateService {

    private PurchaseOrderRepository repository;

    public Mono<Void> updateOrder(final OrchestratorResponseDTO responseDTO){
        return this.repository.findById(responseDTO.getOrderId())
                .doOnNext(p -> p.setStatus(responseDTO.getStatus()))
                .flatMap(this.repository::save)
                .then();
    }

}
