package ch.bbw.obelix.quarry.api;

import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.*;
import java.util.List;
import java.util.UUID;

@HttpExchange("/api")
public interface QuarryApi {

    @GetExchange("/menhirs")
    List<MenhirDto> getAllMenhirs();

    @GetExchange("/menhirs/{menhirId}")
    MenhirDto getMenhirById(@PathVariable UUID menhirId);

    @DeleteExchange("/quarry/{menhirId}")
    void deleteById(@PathVariable UUID menhirId);
}