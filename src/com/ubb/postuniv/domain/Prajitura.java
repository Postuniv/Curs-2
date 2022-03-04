package com.ubb.postuniv.domain;

public class Prajitura {
    // id, nume, ingrediente, calorii, preț, dacă e fără zahăr.
    private String id;
    private String nume;
    private String ingrediente;
    private float calorii;
    private float pret;
    private boolean faraZahar;

    public Prajitura(String id, String nume, String ingrediente, float calorii, float pret, boolean faraZahar) {
        this.id = id;
        this.nume = nume;
        this.ingrediente = ingrediente;
        this.calorii = calorii;
        this.pret = pret;
        this.faraZahar = faraZahar;
    }

    public String getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public float getCalorii() {
        return calorii;
    }

    public float getPret() {
        return pret;
    }

    public boolean isFaraZahar() {
        return faraZahar;
    }

    @Override
    public String toString() {
        return "Prajitura{" +
                "id='" + id + '\'' +
                ", nume='" + nume + '\'' +
                ", ingrediente='" + ingrediente + '\'' +
                ", calorii=" + calorii +
                ", pret=" + pret +
                ", faraZahar=" + faraZahar +
                '}';
    }
}
