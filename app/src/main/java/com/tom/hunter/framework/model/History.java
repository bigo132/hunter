package com.tom.hunter.framework.model;

/**
 * Created by txu1 on 9/9/2016.
 */
public class History {

    private String queryCriteria;

    private int type;

    private int id;

    public History(int id, String queryCriteria, int type) {
        this.id = id;
        this.queryCriteria = queryCriteria;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQueryCriteria() {
        return queryCriteria;
    }

    public void setQueryCriteria(String queryCriteria) {
        this.queryCriteria = queryCriteria;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
