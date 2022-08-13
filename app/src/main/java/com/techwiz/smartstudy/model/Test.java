package com.techwiz.smartstudy.model;

public class Test {
    private int id;
    private String name;
    private int isTaken;

    public Test(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsTaken() {
        return isTaken;
    }

    public void setIs_taken(int isTaken) {
        this.isTaken = isTaken;
    }
}
