package ch.bbw.obelix.webshop.controller;

import ch.bbw.obelix.webshop.dto.BasketDto;
import ch.bbw.obelix.webshop.service.BasketService;
import ch.bbw.obelix.webshop.service.ObelixWebshopService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    /**
     * Customer adds even more shinies in exchange for a beautiful menhir.
     */
    @PutMapping("/api/basket/offer")
    public BasketDto offer(@RequestBody BasketDto.BasketItem basketItem) {
        return basketService.offer(basketItem);
    }

    /**
     * In case the customer doesn't want to offer more and leaves.
     */
    @DeleteMapping("/api/basket")
    public void leave() {
        basketService.leave();
    }

    /**
     * Decide if the current basket is worthy enough for a beautiful menhir.
     *
     * @param menhirId the menhir to buy
     * @throws ObelixWebshopService.BadOfferException in case the basket is tiny
     */
    @PostMapping("/api/basket/buy/{menhirId}")
    public void exchangeFor(@PathVariable UUID menhirId) {
        basketService.exchange(menhirId);
    }
}
