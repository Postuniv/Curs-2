package com.ubb.postuniv.repository;

import com.ubb.postuniv.domain.Tranzactie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranzactieRepository {
    private Map<String, Tranzactie> storage = new HashMap<>();

    public void create(Tranzactie tranzactie) {
        if (storage.containsKey(tranzactie.getId())) {
            throw new RuntimeException("Exista deja o tranzactie cu id-ul " + tranzactie.getId());
        }

        this.storage.put(tranzactie.getId(), tranzactie);
    }

    public List<Tranzactie> read() {
        return new ArrayList<>(storage.values());
    }

    public Tranzactie read(String idTranzactie) {
        return storage.get(idTranzactie);
    }

    public void update(Tranzactie tranzactie) {
        if (!storage.containsKey(tranzactie.getId())) {
            throw new RuntimeException("Nu exista nicio tranzactie cu id-ul " + tranzactie.getId());
        }

        this.storage.put(tranzactie.getId(), tranzactie);
    }

    public void delete(String idTranzactie) {
        if (!storage.containsKey(idTranzactie)) {
            throw new RuntimeException("Nu exista nicio idTranzactie cu id-ul " + idTranzactie);
        }

        this.storage.remove(idTranzactie);
    }
}
