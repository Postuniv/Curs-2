package com.ubb.postuniv.repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.ubb.postuniv.domain.Entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFileRepository<TEntity extends Entity> implements IRepository<TEntity> {

    private String filename;
    private Class<TEntity> classType;

    public JsonFileRepository(String filename, Class<TEntity> classType) {
        this.filename = filename;
        this.classType = classType;
    }

    private Map<String, TEntity> readAll() {
        Gson gson = new Gson();
        try (JsonReader reader = new JsonReader(new FileReader(this.filename))) {
//            Type listType = new TypeToken<ArrayList<TEntity>>() {}.getType();
            // https://stackoverflow.com/questions/14139437/java-type-generic-as-argument-for-gson

            Type listType = TypeToken.getParameterized(List.class, this.classType).getType();
            List<TEntity> entities = gson.fromJson(reader, listType);

            if (entities == null) {
                return new HashMap<>();
            }

            Map<String, TEntity> mapEntites = new HashMap<>();
            for (TEntity entity : entities) {
                mapEntites.put(entity.getId(), entity);
            }
            return mapEntites;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    private void saveAll(Map<String, TEntity> storage) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (JsonWriter writer = new JsonWriter(new FileWriter(this.filename))) {
            gson.toJson(new ArrayList<>(storage.values()), List.class, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(TEntity entity) {
        Map<String, TEntity> storage = this.readAll();
        if (storage.containsKey(entity.getId())) {
            throw new RuntimeException("Exista deja o entitate cu id-ul " + entity.getId());
        }

        storage.put(entity.getId(), entity);
        this.saveAll(storage);
    }

    @Override
    public List<TEntity> read() {
        return new ArrayList<>(this.readAll().values());
    }

    @Override
    public TEntity read(String idEntity) {
        return this.readAll().get(idEntity);
    }

    @Override
    public void update(TEntity entity) {
        Map<String, TEntity> storage = this.readAll();
        if (!storage.containsKey(entity.getId())) {
            throw new RuntimeException("Nu exista nicio entitate cu id-ul " + entity.getId());
        }

        storage.put(entity.getId(), entity);
        this.saveAll(storage);

    }

    @Override
    public void delete(String idEntity) {
        Map<String, TEntity> storage = this.readAll();
        if (!storage.containsKey(idEntity)) {
            throw new RuntimeException("Nu exista nicio entitate cu id-ul " + idEntity);
        }

        storage.remove(idEntity);
        this.saveAll(storage);
    }
}
