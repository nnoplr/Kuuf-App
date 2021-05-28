package com.novika.novikakuuf;

public class Products {

    private String name;
    private int minPlayer, maxPlayer, price;
    private double longitude, latitude;
    int IDprod;

    public Products(int IDprod, String name, int minPlayer, int maxPlayer, int price, double longitude, double latitude) {
        this.IDprod = IDprod;
        this.name = name;
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        this.price = price;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getIDprod() {
        return IDprod;
    }

    public void setIDprod(int IDprod) {
        this.IDprod = IDprod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPlayer() {
        return minPlayer;
    }

    public void setMinPlayer(int minPlayer) {
        this.minPlayer = minPlayer;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
