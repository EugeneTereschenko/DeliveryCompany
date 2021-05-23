package com.delivery.entity;

import java.util.Date;

public class Cart {
    private int id;
    private double total_price;
    private int created_at;
    private int update_at;
    private int user_id;
    private double shipping_price;
    private int coupon;
    private String checkout_step;
    private String cityFrom;
    private String cityTo;
    private String typeDeliver;
    private int count;
    private int weight;
    private int length;
    private int width;
    private int height;
    private int distance;


    public static Builder newBuilder() {
        return new Cart.Builder();
    }

    public static class Builder {

        private Cart cart;

        public Builder() {
            cart = new Cart();
        }

        public Builder addId(int val) {
            cart.id = val;
            return this;
        }

        public Builder addTotal_price(double val) {
            cart.total_price = val;
            return this;
        }

        public Builder addCreated_at(int val) {
            cart.created_at = val;
            return this;
        }

        public Builder addUser_id(int val) {
            cart.user_id = val;
            return this;
        }

        public Builder addShipping_price(double val) {
            cart.shipping_price = val;
            return this;
        }

        public Builder addCoupon(int val) {
            cart.coupon = val;
            return this;
        }

        public Builder addCheckout_step(String val) {
            cart.checkout_step = val;
            return this;
        }

        public Builder addCityFrom(String val) {
            cart.cityFrom = val;
            return this;
        }

        public Builder addCityTo(String val) {
            cart.cityTo = val;
            return this;
        }

        public Builder addTypeDeliver(String val) {
            cart.typeDeliver = val;
            return this;
        }

        public Builder addCount(int val) {
            cart.count = val;
            return this;
        }

        public Builder addWeight(int val) {
            cart.weight = val;
            return this;
        }

        public Builder addLenght(int val) {
            cart.length = val;
            return this;
        }

        public Builder addWidth(int val) {
            cart.width = val;
            return this;
        }

        public Builder addHeight(int val) {
            cart.height = val;
            return this;
        }

        public Builder addDistance(int val) {
            cart.distance = val;
            return this;
        }

        public Cart build() {
            return cart;
        }
    }

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
