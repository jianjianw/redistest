package org.opengg.bean;

import java.io.Serializable;

public class Role implements Serializable{

    private static final long serialVersionUID = -4026078624030803175L;
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
