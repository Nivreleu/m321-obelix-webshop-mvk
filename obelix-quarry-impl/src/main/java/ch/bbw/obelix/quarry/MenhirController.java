package ch.bbw.obelix.quarry;

import ch.bbw.obelix.quarry.api.MenhirApi;
import ch.bbw.obelix.quarry.api.MenhirDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class MenhirController implements MenhirApi {

    @Override
    public List<MenhirDto> getAllMenhirs() {
        return null;
    }
}
