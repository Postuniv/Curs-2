package com.ubb.postuniv.repository;

import com.ubb.postuniv.domain.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUpsertRepository<TEntity extends Entity> implements IUpsertRepository<TEntity> {
    private Map<String, TEntity> storage = new HashMap<>();

    @Override
    public void upsert(TEntity entity) {
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
    public void delete(String idEntity) {
        if (!storage.containsKey(idEntity)) {
            throw new RuntimeException("Nu exista nicio entitate cu id-ul " + idEntity);
        }

        this.storage.remove(idEntity);
    }
}
