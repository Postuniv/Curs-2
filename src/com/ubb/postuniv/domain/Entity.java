package com.ubb.postuniv.domain;

public abstract class Entity {
    protected String id;

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id='" + id + '\'' +
                '}';
    }
}
