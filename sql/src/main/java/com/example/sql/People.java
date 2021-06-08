package com.example.sql;

public class People {
    public int ID = -1;
    public String name;
    public String password;
    public float height;

    @Override
    public String toString() {
        return "ID: " + ID + ", 姓名; " + name + ", 密码: " + password + ", 身高: " + height;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
