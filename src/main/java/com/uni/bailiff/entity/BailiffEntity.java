package com.uni.bailiff.entity;

import java.io.Serializable;
import java.util.Objects;

public class BailiffEntity implements Serializable {
    private long id;
    private String fio;

    public BailiffEntity() {
    }

    public BailiffEntity(String fio) {
        this.fio = fio;
    }

    public BailiffEntity(long id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BailiffEntity that = (BailiffEntity) o;
        return id == that.id && fio.equals(that.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio);
    }

    @Override
    public String toString() {
        return "BailiffEntity{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                '}';
    }
}
