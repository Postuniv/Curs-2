package com.ubb.postuniv.domain;

import com.ubb.postuniv.repository.IRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranzactieValidator {

    public void validate(Tranzactie  tranzactie, IRepository<Prajitura> prajituraRepository) {
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

        // data sa fie in format ZZ.LL.AAAA
        Pattern pattern = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(tranzactie.getData());
        if (!matcher.find()) {
            errors += "Data trebuie sa fie in formatul ZZ.LL.AAAA!\n";
        }

        if (!errors.isEmpty()) {
            throw new RuntimeException(errors);
        }
    }
}
