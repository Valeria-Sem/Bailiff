package com.uni.bailiff.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderEntity implements Serializable {
    private long id;
    private String client;
    private String operation;
    private String bailiff;

    public OrderEntity() {
    }

    public OrderEntity(String client, String operation, String bailiff) {
        this.client = client;
        this.operation = operation;
        this.bailiff = bailiff;
    }

    public OrderEntity(long id, String client, String operation, String bailiff) {
        this.id = id;
        this.client = client;
        this.operation = operation;
        this.bailiff = bailiff;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getBailiff() {
        return bailiff;
    }

    public void setBailiff(String bailiff) {
        this.bailiff = bailiff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id && client.equals(that.client) && operation.equals(that.operation) && bailiff.equals(that.bailiff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, operation, bailiff);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", operation='" + operation + '\'' +
                ", bailiff='" + bailiff + '\'' +
                '}';
    }
}

