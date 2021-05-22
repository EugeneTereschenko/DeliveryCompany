package com.delivery.entity;

import java.util.Date;

public class Cart {
   int id;
   double total_price;
   int created_at;
   int update_at;
   int user_id;
   double shipping_price;
   int coupon;
   String checkout_step;
   String cityFrom;
   String cityTo;
   String typeDeliver;
   int count;
   int weight;
   int length;
   int width;
   int height;
   int distance;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(int update_at) {
        this.update_at = update_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(double shipping_price) {
        this.shipping_price = shipping_price;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public String getCheckout_step() {
        return checkout_step;
    }

    public void setCheckout_step(String checkout_step) {
        this.checkout_step = checkout_step;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getTypeDeliver() {
        return typeDeliver;
    }

    public void setTypeDeliver(String typeDeliver) {
        this.typeDeliver = typeDeliver;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", total_price=" + total_price +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                ", user_id=" + user_id +
                ", shipping_price=" + shipping_price +
                ", coupon=" + coupon +
                ", checkout_step='" + checkout_step + '\'' +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", typeDeliver='" + typeDeliver + '\'' +
                ", count=" + count +
                ", weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
