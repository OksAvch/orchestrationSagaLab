package com.oksavch.orchestrator.config;

import com.oksavch.orchestrator.dto.OrchestratorRequestDTO;
import com.oksavch.orchestrator.dto.OrchestratorResponseDTO;
import com.oksavch.orchestrator.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class OrchestratorConfig {

    private final OrchestratorService orchestratorService;

    @Bean
    public Function<Flux<OrchestratorRequestDTO>, Flux<OrchestratorResponseDTO>> processorQueue(){
        return flux -> flux
                .flatMap(this.orchestratorService::orderProduct)
                .doOnNext(dto -> log.info("Status : {}", dto.getStatus()));
    }
}
