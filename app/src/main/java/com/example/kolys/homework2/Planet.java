package com.example.kolys.homework2;

public class Planet {
    private int photoResId;
    private String name;
    private String discription;
    private String weight;
    private String temp;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    private String distance;

    public Planet(int photoResId, String name, String discription, String weight, String temp, String distance) {
        this.photoResId = photoResId;
        this.name = name;
        this.discription = discription;
        this.weight = weight;
        this.temp = temp;
        this.distance = distance;
    }

    public int getPhotoResId() {
        return photoResId;
    }

    public void setPhotoResId(int photoResId) {
        this.photoResId = photoResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
