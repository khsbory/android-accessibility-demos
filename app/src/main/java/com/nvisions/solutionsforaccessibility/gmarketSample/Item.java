package com.nvisions.solutionsforaccessibility.gmarketSample;

public class Item {
    private String image;
    private String title;
    private String salePrice;
    private String price;
    private String grade;
    private String review;

    public Item(String image, String title, String salePrice, String price, String grade, String review) {
        this.image = image;
        this.title = title;
        this.salePrice = salePrice;
        this.price = price;
        this.grade = grade;
        this.review = review;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
