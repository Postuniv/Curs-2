package com.ubb.postuniv.service;

import com.ubb.postuniv.domain.Prajitura;
import com.ubb.postuniv.domain.PrajituraCuNrTranzactii;
import com.ubb.postuniv.domain.Tranzactie;
import com.ubb.postuniv.domain.TranzactieValidator;
import com.ubb.postuniv.repository.IRepository;


import java.util.*;


public class TranzactieService {

    private IRepository<Tranzactie> tranzactieRepository;
    private IRepository<Prajitura> prajituraRepository;
    private TranzactieValidator tranzactieValidator;

    public TranzactieService(IRepository<Tranzactie> tranzactieRepository,
                             IRepository<Prajitura> prajituraRepository,
                             TranzactieValidator tranzactieValidator) {
        this.tranzactieRepository = tranzactieRepository;
        this.prajituraRepository = prajituraRepository;
        this.tranzactieValidator = tranzactieValidator;
    }

    public void addTranzactie(String id, String idPrajitura,
                              int cardClient, String data,
                              String ora, int numarBucati) {
        Tranzactie tranzactie = new Tranzactie(id, idPrajitura, cardClient, data, ora, numarBucati);
        this.tranzactieValidator.validate(tranzactie, prajituraRepository);
        this.tranzactieRepository.create(tranzactie);
    }

    public List<PrajituraCuNrTranzactii> getPrajituriOrdonateDescDupaNrTranzactii() {
        Map<String, Integer> groupings = new HashMap<>();
        for (Tranzactie tranzactie : this.getAll()) {
            String idPrajitura = tranzactie.getIdPrajitura();
            if (groupings.containsKey(idPrajitura)) {
                groupings.put(idPrajitura, groupings.get(idPrajitura) + 1); // + tranzactie.getNrBucati()
            } else {
                groupings.put(idPrajitura, 1); // tranzactie.getNrBucati()
            }
        }

        List<PrajituraCuNrTranzactii> result = new ArrayList<>();
        for (String idPrajitura : groupings.keySet()) {
            result.add(new PrajituraCuNrTranzactii(
                    this.prajituraRepository.read(idPrajitura),
                    groupings.get(idPrajitura)
            ));
        }

        /*
            result.sort(new Comparator<PrajituraCuNrTranzactii>() {
                @Override
                public int compare(PrajituraCuNrTranzactii o1, PrajituraCuNrTranzactii o2) {
                    return -Integer.compare(o1.nrTranzactii, o2.nrTranzactii);
                }
            });
         */

        result.sort((o1, o2) -> -Integer.compare(o1.nrTranzactii, o2.nrTranzactii));

        return result;
    }

    private String getRandomDate(Random r) {
        return String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10)) + "." +
                String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10)) + "." +
                String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10)) + String.valueOf(r.nextInt(10));
    }

    public void generateRandom(int number) {
        Random r = new Random();
        final int maxStringLength = 10;

        List<Prajitura> prajituri = this.prajituraRepository.read();
        for (int i = 0; i < number; ++i) {
            Prajitura prajituraTranzactionata = prajituri.get(r.nextInt(prajituri.size()));
            Tranzactie tranzactie = new Tranzactie(
                    UUID.randomUUID().toString(),
                    prajituraTranzactionata.getId(),
                    r.nextInt(),
                    this.getRandomDate(r),
                    this.getRandomDate(r), // TODO: implement a getRandomTime
                    r.nextInt(Math.max(10000 / ((int)prajituraTranzactionata.getCalorii() + 1) - 1, 0))
            );

            this.tranzactieValidator.validate(tranzactie, prajituraRepository);
            this.tranzactieRepository.create(tranzactie);
        }
    }

    public List<Tranzactie> getAll() {
        return this.tranzactieRepository.read();
    }
}
