package com.orkestra.paycars.domain;

public class DomainItem {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String title;
    private String description;
    private String picture;
    private int review;
    private double score;
    private int cart;
    private double price;

    public DomainItem(String title, String description, String picture, int review, double score, int cart, double price) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.review = review;
        this.score = score;
        this.cart = cart;
        this.price = price;
    }
}
