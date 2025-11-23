package ch.bbw.obelix.webshop.service;

import ch.bbw.obelix.quarry.api.QuarryApi;
import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuarryWebclientService implements QuarryApi {

    private final WebClient quarryWebClient;

    @Override
    public List<MenhirDto> getAllMenhirs() {
        return quarryWebClient.get()
                .uri("/api/menhirs")
                .retrieve()
                .bodyToFlux(MenhirDto.class)
                .collectList()
                .block();
    }

    @Override
    public MenhirDto getMenhirById(UUID menhirId) {
        return quarryWebClient.get()
                .uri("/api/menhirs/{id}", menhirId)
                .retrieve()
                .bodyToMono(MenhirDto.class)
                .block();
    }

    @Override
    public void deleteById(UUID menhirId) {
        quarryWebClient.delete()
                .uri("/api/quarry/{id}", menhirId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
