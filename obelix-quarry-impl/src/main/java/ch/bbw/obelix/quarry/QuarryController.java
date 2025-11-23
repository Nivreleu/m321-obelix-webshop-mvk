package ch.bbw.obelix.quarry;

import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import ch.bbw.obelix.webshop.service.QuarryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuarryController {

    private final QuarryService quarryService;

    @GetMapping("/api/menhirs")
    public List<MenhirDto> getAllMenhirs() {
        return quarryService.getAllMenhirs();
    }

    @GetMapping("/api/menhirs/{menhirId}")
    public MenhirDto getMenhirById(UUID menhirId) {
        return quarryService.getMenhirById(menhirId);
    }

    @DeleteMapping("/api/quarry/{menhirId}")
    public void deleteById(UUID menhirId) {
        quarryService.deleteById(menhirId);
    }
}