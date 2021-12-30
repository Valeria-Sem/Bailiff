package com.uni.bailiff.entity;

import java.io.Serializable;
import java.util.Objects;

public class ClientEntity implements Serializable {
    private long id;
    private String fio;

    public ClientEntity() {
    }

    public ClientEntity(String fio) {
        this.fio = fio;
    }

    public ClientEntity(long id, String fio) {
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
        ClientEntity that = (ClientEntity) o;
        return id == that.id && fio.equals(that.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                '}';
    }
}
