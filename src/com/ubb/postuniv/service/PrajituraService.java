package com.ubb.postuniv.service;

import com.ubb.postuniv.domain.Prajitura;
import com.ubb.postuniv.domain.Tranzactie;
import com.ubb.postuniv.repository.IRepository;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PrajituraService {

    private IRepository<Prajitura> prajituraRepository;

    public PrajituraService(IRepository<Prajitura> prajituraRepository) {
        this.prajituraRepository = prajituraRepository;
    }

    public void addPrajitura(String id, String nume, String ingrediente,
                             float calorii, float pret, boolean faraZahar) {
        Prajitura prajitura = new Prajitura(id, nume, ingrediente, calorii, pret, faraZahar);
        this.prajituraRepository.create(prajitura);
    }

    private String getRandomString(int maxLength, Random r) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 1 + r.nextInt(maxLength); ++i) {
            result.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return result.toString();
    }

    public void generateRandom(int number) {
        Random r = new Random();
        final int maxStringLength = 10;

        for (int i = 0; i < number; ++i) {
            Prajitura prajitura = new Prajitura(
                    UUID.randomUUID().toString(),
                    this.getRandomString(maxStringLength, r),
                    this.getRandomString(maxStringLength, r),
                    r.nextFloat(),
                    r.nextFloat(),
                    r.nextBoolean()
            );

            this.prajituraRepository.create(prajitura);
        }
    }

    public List<Prajitura> getAll() {
        return this.prajituraRepository.read();
    }
}
