package ch.bbw.obelix.webshop.service;

import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuarryWebclientService {

    private final WebClient quarryWebClient;

    public List<MenhirDto> getAllMenhirs() {
        return quarryWebClient.get()
                .uri("/api/menhirs")
                .retrieve()
                .bodyToFlux(MenhirDto.class)
                .collectList()
                .block();
    }

    public MenhirDto getMenhirById(UUID menhirId) {
        return quarryWebClient.get()
                .uri("/api/menhirs/{id}", menhirId)
                .retrieve()
                .bodyToMono(MenhirDto.class)
                .block();
    }

    public void deleteById(UUID menhirId) {
        quarryWebClient.delete()
                .uri("/api/quarry/{id}", menhirId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
