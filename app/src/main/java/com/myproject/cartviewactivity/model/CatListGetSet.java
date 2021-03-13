package com.myproject.cartviewactivity.model;

public class CatListGetSet {
    private String id;
    private String parent_id;
    private String enable_category;
    private String category_name;
    private String category_image;
    private String url_key;

    public CatListGetSet(){

    }

    public CatListGetSet(String id, String parent_id, String enable_category, String category_name, String category_image, String url_key) {
        this.id = id;
        this.parent_id = parent_id;
        this.enable_category = enable_category;
        this.category_name = category_name;
        this.category_image = category_image;
        this.url_key = url_key;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getEnable_category() {
        return enable_category;
    }

    public void setEnable_category(String enable_category) {
        this.enable_category = enable_category;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getUrl_key() {
        return url_key;
    }

    public void setUrl_key(String url_key) {
        this.url_key = url_key;
    }
}
