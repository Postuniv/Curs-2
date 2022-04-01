package com.ubb.tests;

import com.ubb.postuniv.domain.Prajitura;

import static org.junit.jupiter.api.Assertions.*;

class PrajituraTest {

    @org.junit.jupiter.api.Test
    void getId() {
        Prajitura prajitura = new Prajitura("1", "ecler", "zahar, frisca", 200, 20, false);
        assertEquals("1", prajitura.getId(), "getId a returnat " + prajitura.getId() + " in loc de 1.");
    }

    @org.junit.jupiter.api.Test
    void getNume() {

    }

    @org.junit.jupiter.api.Test
    void getIngrediente() {
    }

    @org.junit.jupiter.api.Test
    void getCalorii() {
    }

    @org.junit.jupiter.api.Test
    void getPret() {
    }

    @org.junit.jupiter.api.Test
    void isFaraZahar() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }
}