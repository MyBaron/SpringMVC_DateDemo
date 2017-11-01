package com.Entity;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/11/1.
 */
@Component
public class TestEntity {

    private String name;

    private String Data;
    private Date date;
    private Timestamp timestamp;

    public TestEntity() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "name='" + name + '\'' +
                ", Data='" + Data + '\'' +
                ", date=" + date +
                ", timestamp=" + timestamp +
                '}';
    }
}
