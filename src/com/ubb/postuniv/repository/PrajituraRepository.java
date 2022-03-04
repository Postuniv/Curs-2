package com.ubb.postuniv.repository;

import com.ubb.postuniv.domain.Prajitura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrajituraRepository {

    private Map<String, Prajitura> storage = new HashMap<>();

    public void create(Prajitura prajitura) {
        if (storage.containsKey(prajitura.getId())) {
            throw new RuntimeException("Exista deja o prajitura cu id-ul " + prajitura.getId());
        }

        this.storage.put(prajitura.getId(), prajitura);
    }

    public List<Prajitura> read() {
        return new ArrayList<>(storage.values());
    }

    public Prajitura read(String idPrajitura) {
        return storage.get(idPrajitura);
    }

    public void update(Prajitura prajitura) {
        if (!storage.containsKey(prajitura.getId())) {
            throw new RuntimeException("Nu exista nicio prajitura cu id-ul " + prajitura.getId());
        }

        this.storage.put(prajitura.getId(), prajitura);
    }

    public void delete(String idPrajitura) {
        if (!storage.containsKey(idPrajitura)) {
            throw new RuntimeException("Nu exista nicio idPrajitura cu id-ul " + idPrajitura);
        }

        this.storage.remove(idPrajitura);
    }
}
