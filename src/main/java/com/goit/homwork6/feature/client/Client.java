package com.goit.homwork6.feature.client;

import lombok.Data;

@Data
public class Client {
    private long id;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName(){
        return name;
    }
}
