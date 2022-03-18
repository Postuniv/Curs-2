package com.ubb.postuniv.repository;

import com.ubb.postuniv.domain.Entity;

import java.util.List;

public interface IUpsertRepository<TEntity extends Entity> {
    void upsert(TEntity entity);
    List<TEntity> read();
    TEntity read(String idEntity);
    void delete(String idEntity);
}
