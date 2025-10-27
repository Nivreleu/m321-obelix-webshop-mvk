package ch.bbw.obelix.quarry.api;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

public interface MenhirApi {
    @GetMapping("/api/menhirs")
    public List<MenhirDto> getAllMenhirs();
}
