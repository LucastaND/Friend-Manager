package com.example.friendapp;

//Declare variables and data types for class Friend
public class Friend {
    private int id_friend;
    private String name;
    private int phone_number;
    private String address;

    public Friend(int id_friend, String name, int phone_number, String address) {
        this.id_friend = id_friend;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }

    public Friend() {
        this.id_friend = 0;
        this.name = null;
        this.address = null;
        this.phone_number = 0;
    }

    public int getId_friend() {
        return id_friend;
    }

    public void setId_friend(int id_friend) {
        this.id_friend = id_friend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}