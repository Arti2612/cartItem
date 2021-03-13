package com.myproject.cartviewactivity.model;

import java.util.List;

public class CatProductList {

    private String id;
    private String product_name;
    private String price;
    private String special_price;
    private String category_id;
    private String images;
    private List<String> defaultWeight;
    private List<String> originalPriceList;
    private List<String> specialPriceList;


    public CatProductList(String id, String product_name, String price, String special_price, String category_id, String images, List<String> defaultWeight, List<String> originalPriceList, List<String>specialPriceList) {
        this.id = id;
        this.product_name = product_name;
        this.price = price;
        this.special_price = special_price;
        this.category_id = category_id;
        this.images = images;
        this.defaultWeight = defaultWeight;
        this.originalPriceList = originalPriceList;
        this.specialPriceList = specialPriceList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecial_price() {
        return special_price;
    }

    public void setSpecial_price(String special_price) {
        this.special_price = special_price;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<String> getDefaultWeight() {
        return defaultWeight;
    }

    public void setDefaultWeight(List<String> defaultWeight) {
        this.defaultWeight = defaultWeight;
    }

    public List<String> getOriginalPriceList() {
        return originalPriceList;
    }

    public void setOriginalPriceList(List<String> originalPriceList) {
        this.originalPriceList = originalPriceList;
    }

    public List<String> getSpecialPriceList() {
        return specialPriceList;
    }

    public void setSpecialPriceList(List<String> specialPriceList) {
        this.specialPriceList = specialPriceList;
    }
}