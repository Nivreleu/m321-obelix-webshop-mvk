package ch.bbw.obelix.webshop.controller;

import ch.bbw.obelix.webshop.dto.MenhirDto;
import ch.bbw.obelix.webshop.entity.MenhirEntity;
import ch.bbw.obelix.webshop.repository.MenhirRepository;
import ch.bbw.obelix.webshop.service.QuarryService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
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
    public MenhirDto getMenhirById(@PathVariable UUID menhirId) {
        return quarryService.getMenhirById(menhirId);
    }

    @DeleteMapping("/api/quarry/{menhirId}")
    public void deleteById(@PathVariable UUID menhirId) {
        quarryService.deleteById(menhirId);
    }

    @StandardException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class UnknownMenhirException extends RuntimeException {}
}