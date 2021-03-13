package com.myproject.cartviewactivity.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Cart.class},version = 1,exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {

    private static final String DB_NAME ="CartDb";
    private static CartDatabase instance;
    public abstract CartDao cartDao();

    public synchronized static CartDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    CartDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
