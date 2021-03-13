package com.myproject.cartviewactivity.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface CartDao {
    @Insert
    void insert(Cart cart);

    @Query("select * from Cart")
    List<Cart> getAllProducts();

    @Query("Select Count(*) from Cart")
    int getProductCount();

    @Delete
    void delete(Cart u);

}
