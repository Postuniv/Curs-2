package com.ubb.postuniv.service;

import com.ubb.postuniv.domain.Entity;
import com.ubb.postuniv.repository.IRepository;
import com.ubb.postuniv.repository.IUpsertRepository;

import java.util.List;

public class UpsertRepositoryForClassicalRepositoryAdapter<TEntity extends Entity> implements IRepository<TEntity> {

    IUpsertRepository<TEntity> adaptee;

    public UpsertRepositoryForClassicalRepositoryAdapter(IUpsertRepository<TEntity> adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void create(TEntity entity) {
        if (this.adaptee.read(entity.getId()) != null) {
            throw new RuntimeException("Exista deja o entitate cu id-ul " + entity.getId());
        }

        this.adaptee.upsert(entity);
    }

    @Override
    public List<TEntity> read() {
        return this.adaptee.read();
    }

    @Override
    public TEntity read(String idEntity) {
        return this.adaptee.read(idEntity);
    }

    @Override
    public void update(TEntity entity) {
        if (this.adaptee.read(entity.getId()) == null) {
            throw new RuntimeException("Nu exista o entitate cu id-ul " + entity.getId());
        }

        this.adaptee.upsert(entity);
    }

    @Override
    public void delete(String idEntity) {
        this.adaptee.delete(idEntity);
    }
}
