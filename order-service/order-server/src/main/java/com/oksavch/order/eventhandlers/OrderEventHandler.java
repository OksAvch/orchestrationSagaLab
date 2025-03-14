package com.oksavch.order.eventhandlers;

import com.oksavch.order.service.OrderEventUpdateService;
import com.oksavch.orderclient.dto.OrchestratorRequestDTO;
import com.oksavch.orderclient.dto.OrchestratorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Log4j2
@Configuration
@AllArgsConstructor
public class OrderEventHandler {

    private Flux<OrchestratorRequestDTO> flux;
    private OrderEventUpdateService service;

    @Bean
    public Supplier<Flux<OrchestratorRequestDTO>> supplier(){
        return () -> flux;
    }

    @Bean
    public Consumer<Flux<OrchestratorResponseDTO>> consumer(){
        return f -> f
                .doOnNext(c -> log.info("Consuming :: {}", c))
                .flatMap(responseDTO -> this.service.updateOrder(responseDTO))
                .subscribe();
    }

}
