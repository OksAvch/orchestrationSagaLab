package com.oksavch.order.configuration;

import com.oksavch.order.service.OrderEventUpdateService;
import com.oksavch.orderclient.dto.OrchestratorRequestDTO;
import com.oksavch.orderclient.dto.OrchestratorResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Log4j2
@Component
@RequiredArgsConstructor
public class OrderEventHandler {

    private final Flux<OrchestratorRequestDTO> flux;
    private final OrderEventUpdateService service;

    @Bean
    public Supplier<Flux<OrchestratorRequestDTO>> supplierQueue() {
        return () -> flux;
    }

    @Bean
    public Consumer<Flux<OrchestratorResponseDTO>> consumerQueue() {
        return f -> f
                .doOnNext(c -> log.info("Consuming :: {}", c))
                .flatMap(responseDTO -> this.service.updateOrder(responseDTO))
                .subscribe();
    }

}
