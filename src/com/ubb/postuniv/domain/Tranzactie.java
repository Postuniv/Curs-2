package com.ubb.postuniv.domain;

public class Tranzactie extends Entity {
    // id, id prăjitură (trebuie să existe), nr. card client (întreg), data, ora, numărul de bucăți

    private String idPrajitura;
    private int cardClient;
    private String data;
    private String ora;
    private int numarBucati;

    public Tranzactie(String id, String idPrajitura, int cardClient, String data, String ora, int numarBucati) {
        super(id);
        this.idPrajitura = idPrajitura;
        this.cardClient = cardClient;
        this.data = data;
        this.ora = ora;
        this.numarBucati = numarBucati;
    }

    public String getIdPrajitura() {
        return idPrajitura;
    }

    public int getCardClient() {
        return cardClient;
    }

    public String getData() {
        return data;
    }

    public String getOra() {
        return ora;
    }

    public int getNumarBucati() {
        return numarBucati;
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "id='" + id + '\'' +
                ", idPrajitura='" + idPrajitura + '\'' +
                ", cardClient=" + cardClient +
                ", data='" + data + '\'' +
                ", ora='" + ora + '\'' +
                ", numarBucati=" + numarBucati +
                '}';
    }
}
