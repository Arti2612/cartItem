package com.myproject.cartviewactivity.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Cart")
public class Cart {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "productid")
    private String productid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "quantity")
    private String quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
