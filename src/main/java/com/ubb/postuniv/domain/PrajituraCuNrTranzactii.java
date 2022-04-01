package com.ubb.postuniv.domain;

public class PrajituraCuNrTranzactii {
    public Prajitura prajitura;
    public int nrTranzactii;

    public PrajituraCuNrTranzactii(Prajitura prajitura, int nrTranzactii) {
        this.prajitura = prajitura;
        this.nrTranzactii = nrTranzactii;
    }

    @Override
    public String toString() {
        return "PrajituraCuNrTranzactii{" +
                "prajitura=" + prajitura +
                ", nrTranzactii=" + nrTranzactii +
                '}';
    }
}
