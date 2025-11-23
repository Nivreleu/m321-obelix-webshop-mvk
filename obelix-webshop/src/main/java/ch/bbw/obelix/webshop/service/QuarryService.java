package ch.bbw.obelix.webshop.service;

import ch.bbw.obelix.webshop.controller.ObelixWebshopController;
import ch.bbw.obelix.webshop.dto.MenhirDto;
import ch.bbw.obelix.webshop.entity.MenhirEntity;
import ch.bbw.obelix.webshop.repository.MenhirRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class QuarryService {

    private final MenhirRepository menhirRepository;

    public List<MenhirDto> getAllMenhirs() {
        return menhirRepository.findAll()
                .stream()
                .map(MenhirEntity::toDto)
                .toList();
    }

    public MenhirDto getMenhirById(UUID menhirId) {
        return menhirRepository.findById(menhirId)
                .map(MenhirEntity::toDto)
                .orElseThrow(() -> new ObelixWebshopController.UnknownMenhirException(
                        "unknown menhir with id " + menhirId));
    }

    public void deleteById(UUID menhirId) {
        menhirRepository.deleteById(menhirId);
    }

    // --- Initial-Daten (aus ObelixWebshopService kopieren) ---

    @PostConstruct
    public void initializeMenhirs() {
        if (menhirRepository.count() == 0) {
            createDefaultMenhirs();
        }
    }

    public void createDefaultMenhirs() {
        menhirRepository.deleteAll();

        var obelixSpecial = new MenhirEntity();
        obelixSpecial.setWeight(2.5);
        obelixSpecial.setStoneType("Granite Gaulois");
        obelixSpecial.setDecorativeness(MenhirEntity.Decorativeness.DECORATED);
        obelixSpecial.setDescription("Obelix's personal favorite! Perfect for throwing at Romans. ");
        menhirRepository.save(obelixSpecial);

        var getafixMasterpiece = new MenhirEntity();
        getafixMasterpiece.setWeight(4.2);
        getafixMasterpiece.setStoneType("Mystical Dolmen Stone");
        getafixMasterpiece.setDecorativeness(MenhirEntity.Decorativeness.MASTERWORK);
        getafixMasterpiece.setDescription("Blessed by Getafix himself! ...");
        menhirRepository.save(getafixMasterpiece);

        var touristTrap = new MenhirEntity();
        touristTrap.setWeight(1.0);
        touristTrap.setStoneType("Imported Roman Marble");
        touristTrap.setDecorativeness(MenhirEntity.Decorativeness.PLAIN);
        touristTrap.setDescription("Budget-friendly option! ...");
        menhirRepository.save(touristTrap);
    }
}
