package com.ubb.postuniv.repository;

import com.ubb.postuniv.domain.Entity;
import com.ubb.postuniv.domain.Prajitura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<TEntity extends Entity> implements IRepository<TEntity> {
    private Map<String, TEntity> storage = new HashMap<>();

    @Override
    public void create(TEntity entity) {
        if (storage.containsKey(entity.getId())) {
            throw new RuntimeException("Exista deja o entitate cu id-ul " + entity.getId());
        }

        this.storage.put(entity.getId(), entity);
    }

    @Override
    public List<TEntity> read() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public TEntity read(String idEntity) {
        return storage.get(idEntity);
    }

    @Override
    public void update(TEntity entity) {
        if (!storage.containsKey(entity.getId())) {
            throw new RuntimeException("Nu exista nicio entitate cu id-ul " + entity.getId());
        }

        this.storage.put(entity.getId(), entity);
    }

    @Override
    public void delete(String idEntity) {
        if (!storage.containsKey(idEntity)) {
            throw new RuntimeException("Nu exista nicio entitate cu id-ul " + idEntity);
        }

        this.storage.remove(idEntity);
    }
}
