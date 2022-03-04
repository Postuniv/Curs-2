package com.ubb.postuniv.domain;

import com.ubb.postuniv.repository.PrajituraRepository;

public class TranzactieValidator {

    public void validate(Tranzactie  tranzactie, PrajituraRepository prajituraRepository) {
        String errors = "";
        Prajitura prajituraTranzactionata = prajituraRepository.read(tranzactie.getIdPrajitura());
        if (prajituraTranzactionata == null) {
            errors += "Nu exista nicio prajitura cu id-ul " + tranzactie.getIdPrajitura() + "\n";
        } else {
            // nr. de bucăți * caloriile < 10 000.
            float produs = tranzactie.getNumarBucati() * prajituraTranzactionata.getCalorii();
            if (produs >= 10000) {
                errors += "nr. de bucati * caloriile trebuie sa fie < 10000 (este: " + produs + ")\n";
            }
        }

        if (!errors.isEmpty()) {
            throw new RuntimeException(errors);
        }
    }
}
