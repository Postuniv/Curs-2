package com.ubb.postuniv.repository;

import com.ubb.postuniv.domain.Prajitura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrajituraRepository {

    private Map<String, Prajitura> storage = new HashMap<>();

    public void create(Prajitura prajitura) {

    }

    public List<Prajitura> read() {
        return new ArrayList<>();
    }

    public Prajitura read(String idPrajitura) {
        return null;
    }

    public void update(Prajitura prajitura) {

    }

    public void delete(String idPrajitura) {

    }
}
