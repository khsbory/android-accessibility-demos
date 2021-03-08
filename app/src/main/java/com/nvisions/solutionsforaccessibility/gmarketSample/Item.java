package com.nvisions.solutionsforaccessibility.gmarketSample;

public class Item {
    private String image;
    private String title;
    private String salePrice;
    private String price;
    private String grade;
    private String review;
    private String couponSale;
    private String cardSale;

    public Item(String image, String title, String salePrice, String price, String grade, String review, String couponSale, String cardSale) {
        this.image = image;
        this.title = title;
        this.salePrice = salePrice;
        this.price = price;
        this.grade = grade;
        this.review = review;
        this.couponSale = couponSale;
        this.cardSale = cardSale;
    }

    public String getCouponSale() {
        return couponSale;
    }

    public void setCouponSale(String couponSale) {
        this.couponSale = couponSale;
    }

    public String getCardSale() {
        return cardSale;
    }

    public void setCardSale(String cardSale) {
        this.cardSale = cardSale;
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
