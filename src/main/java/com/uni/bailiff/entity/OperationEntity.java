package com.uni.bailiff.entity;

import java.io.Serializable;
import java.util.Objects;

public class OperationEntity implements Serializable {
    private long id;
    private String name;

    public OperationEntity() {
    }

    public OperationEntity(String name) {
        this.name = name;
    }

    public OperationEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationEntity that = (OperationEntity) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "OperationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
