package com.ubb.tests;

import com.ubb.postuniv.domain.Prajitura;
import com.ubb.postuniv.domain.Tranzactie;
import com.ubb.postuniv.domain.TranzactieValidator;
import com.ubb.postuniv.repository.IRepository;
import com.ubb.postuniv.repository.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranzactieValidatorTest {

    @Test
    void validate() {
        /// Look up: AAA - Arrange, Act, Assert
        // setup
        IRepository<Prajitura> prajituraRepository = new InMemoryRepository<>();
        prajituraRepository.create(new Prajitura("1", "ecler", "zahar, frisca", 200, 20, false));
        prajituraRepository.create(new Prajitura("2", "inghetata", "zahar, vanilie, ciocolata", 240, 26, false));
        prajituraRepository.create(new Prajitura("3", "amandina", "ciocolata", 250, 21, true));

        TranzactieValidator tranzactieValidator = new TranzactieValidator();

        // testare propriu-zisa
        Tranzactie t1 = new Tranzactie("8", "3", 343462, "gdf dfgfgds", "32 2", 9);
        try {
            tranzactieValidator.validate(t1, prajituraRepository);
            fail();
        } catch (RuntimeException rex) {
            assertEquals("Data trebuie sa fie in formatul ZZ.LL.AAAA!\n", rex.getMessage());
        }

        Tranzactie t2 = new Tranzactie("1", "3", 343462, "11.03.2022", "32 2", 9);
        try {
            tranzactieValidator.validate(t2, prajituraRepository);
        } catch (RuntimeException rex) {
            fail();
        }
    }
}