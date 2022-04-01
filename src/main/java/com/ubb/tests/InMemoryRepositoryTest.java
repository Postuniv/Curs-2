package com.ubb.tests;

import com.ubb.postuniv.domain.Prajitura;
import com.ubb.postuniv.repository.IRepository;
import com.ubb.postuniv.repository.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {

    IRepository<Prajitura> getTestRepository() {
        IRepository<Prajitura> repository = new InMemoryRepository<>();
        repository.create(new Prajitura("1", "ecler", "zahar, frisca", 200, 20, false));
        repository.create(new Prajitura("2", "gdfisoa", "fms nm,sf", 500, 30, false));
        repository.create(new Prajitura("6", "fsm,n,m", "fekdhjsafbnmsd", 300, 27, true));

        return repository;
    }

    @Test
    void create() {
        IRepository<Prajitura> repository = getTestRepository();
        assertEquals(3, repository.read().size());
        assertNotEquals(null, repository.read("1"));
        assertNotEquals(null, repository.read("2"));
        assertNotEquals(null, repository.read("6"));


        Prajitura toAddSameId = new Prajitura("6", "vxvcd ", "432seda", 424, 24, false);
        try {
            repository.create(toAddSameId);
            fail();
        } catch (RuntimeException rex) {
            assertNotEquals("", rex.getMessage());
        }

        Prajitura toAddNewId = new Prajitura("100", "vxvcd ", "432seda", 424, 24, false);
        repository.create(toAddNewId);
        assertEquals(4, repository.read().size());
        assertNotEquals(null, repository.read("100"));
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}