package com.delivery.entity;


import java.util.Date;

public class Rate {

    private int id;
    private double weight;
    private int distancefrom;
    private int distanceto;
    private double cost;
    private Date create_at;
    private Date update_at;

    public Rate(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getDistancefrom() {
        return distancefrom;
    }

    public void setDistancefrom(int distancefrom) {
        this.distancefrom = distancefrom;
    }

    public int getDistanceto() {
        return distanceto;
    }

    public void setDistanceto(int distanceto) {
        this.distanceto = distanceto;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}
