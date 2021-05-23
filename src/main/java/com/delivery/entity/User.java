package com.delivery.entity;

import java.util.Date;

public class User {

    private int id;
    private String email;
    private String encrypted_password;
    private Date reset_password_token;
    private Date reset_password_sent_at;
    private String remember_created_at;
    private int sign_in_count;
    private String current_sign_in_at;
    private String name;
    private String uid;
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public Date getReset_password_token() {
        return reset_password_token;
    }

    public void setReset_password_token(Date reset_password_token) {
        this.reset_password_token = reset_password_token;
    }

    public Date getReset_password_sent_at() {
        return reset_password_sent_at;
    }

    public void setReset_password_sent_at(Date reset_password_sent_at) {
        this.reset_password_sent_at = reset_password_sent_at;
    }

    public String getRemember_created_at() {
        return remember_created_at;
    }

    public void setRemember_created_at(String remember_created_at) {
        this.remember_created_at = remember_created_at;
    }

    public int getSign_in_count() {
        return sign_in_count;
    }

    public void setSign_in_count(int sign_in_count) {
        this.sign_in_count = sign_in_count;
    }

    public String getCurrent_sign_in_at() {
        return current_sign_in_at;
    }

    public void setCurrent_sign_in_at(String current_sign_in_at) {
        this.current_sign_in_at = current_sign_in_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
