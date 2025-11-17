package ch.bbw.obelix.quarry;

import ch.bbw.obelix.quarry.api.MenhirDto;
import ch.bbw.obelix.quarry.api.QuarryApi;
import ch.bbw.obelix.webshop.controller.ObelixWebshopController;
import ch.bbw.obelix.webshop.entity.MenhirEntity;
import ch.bbw.obelix.webshop.repository.MenhirRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuarryController implements QuarryApi {

    private final MenhirRepository menhirRepository;

    @Override
    public List<MenhirDto> getAllMenhirs() {
        return menhirRepository.findAll()
                .stream().map(MenhirEntity::toDto).toList();
    }

    @Override
    public MenhirDto getMenhirById(@PathVariable UUID menhirId) {
        return menhirRepository.findById(menhirId)
                .map(MenhirEntity::toDto)
                .orElseThrow(() -> new ObelixWebshopController.UnknownMenhirException("unknwon menhir with id " + menhirId));
    }

    /**
     * Note that this should only be called by Asterix himself.
     * Hopefully, no customer will ever find this endpoint...
     */
    @Override
    public void deleteById(@PathVariable UUID menhirId) {
        menhirRepository.deleteById(menhirId);
    }

    @StandardException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class UnknownMenhirException extends RuntimeException {}
}
