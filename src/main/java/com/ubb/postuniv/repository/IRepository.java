package com.ubb.postuniv.repository;

import com.ubb.postuniv.domain.Entity;
import com.ubb.postuniv.domain.Prajitura;

import java.util.List;

public interface IRepository<TEntity extends Entity> {
    void create(TEntity entity);
    List<TEntity> read();
    TEntity read(String idEntity);
    void update(TEntity entity);
    void delete(String idEntity);
}
