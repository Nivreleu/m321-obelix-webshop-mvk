package ch.bbw.obelix.quarry;

import ch.bbw.obelix.quarry.api.DecorativenessDto;
import ch.bbw.obelix.webshop.controller.ObelixWebshopController;
import ch.bbw.obelix.webshop.dto.BasketDto;
import ch.bbw.obelix.webshop.service.ObelixWebshopService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuarryService {

    @Lazy
    private final QuarryController quarryController;

    private BasketDto basket;

    public void exchange(UUID menhirId) {
        var menhir = quarryController.getMenhirById(menhirId);
        var decorativeness = menhir.decorativeness();
        if (!isGoodOffer(decorativeness)) {
            throw new ObelixWebshopService.BadOfferException("Bad Offer: That won't even feed Idefix!");
        }
        quarryController.deleteById(menhirId);
        leave();
    }

    public boolean isGoodOffer(DecorativenessDto decorativeness) {
        var stoneWorth = decorativeness.ordinal();
        var basketWorth = basket.items()
                .stream().map(x -> switch (x.name().toLowerCase(Locale.ROOT)) {
                    case "boar" -> 5; // oh boy, oh boy!
                    case "honey" -> 2;
                    case "magic potion" -> 0; // not allowed to drink this!
                    default -> 1; // everything is worth something
                } * x.count()).reduce(0, Integer::sum);
        log.info("basket worth {} vs menhir worth {} ({})", basketWorth, decorativeness, stoneWorth);
        return basketWorth >= stoneWorth;
    }
}
