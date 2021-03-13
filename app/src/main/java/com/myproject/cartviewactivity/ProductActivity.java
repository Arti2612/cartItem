package com.myproject.cartviewactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.cartviewactivity.adapter.ProductAdapter;
import com.myproject.cartviewactivity.db.Cart;
import com.myproject.cartviewactivity.db.CartDao;
import com.myproject.cartviewactivity.db.CartDatabase;

import java.util.ArrayList;
import java.util.List;


public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Cart> cartList;
    private CartDatabase userDatabase;
    private CartDao dao;
    private List<Cart> responseList;
    private int count = 0;
    private TextView tv_cart_count;
    private FrameLayout cart_frame;
    private ProductAdapter catProadapter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        context = com.myproject.cartviewactivity.ProductActivity.this;

        tv_cart_count = findViewById(R.id.cart_count_tv2);
        cart_frame = findViewById(R.id.cart_frame2);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(catProadapter);

        cartList = new ArrayList<>();

        // CartDao
        userDatabase = CartDatabase.getInstance(context);
        dao = userDatabase.cartDao();
        responseList = dao.getAllProducts();
        count = dao.getProductCount();

        tv_cart_count.setText(String.valueOf(count));

        cart_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CartActivity.class);
                startActivity(i);
            }
        });

        loadData();

    }


    private void addProduct(String name, String price, String productid) {

        Cart cart = new Cart();
        cart.setProductid(productid);
        cart.setName(name);
        cart.setPrice(price);
        cart.setQuantity("1");

        cartList.add(cart);

    }


    private void loadData() {

        addProduct("Samsung Galaxy M31", "120", "1");
        addProduct("Apple iPhone X", "410", "2");
        addProduct("OnePlus 8 pro", "330", "3");
        addProduct("Oppo Reno 3", "186", "4");
        addProduct("Samsung galaxy S21", "256", "5");
        addProduct("Redmi Note 10", "199", "6");
        addProduct("Realme 9 pro", "163", "7");
        addProduct("OnePlus Nord", "452", "8");

        catProadapter = new ProductAdapter(cartList, context, tv_cart_count);

        recyclerView.setAdapter(catProadapter);

    }


    @Override
    protected void onResume() {
        super.onResume();

        userDatabase = CartDatabase.getInstance(context);
        dao = userDatabase.cartDao();
        responseList = dao.getAllProducts();
        count = dao.getProductCount();

        tv_cart_count.setText(String.valueOf(count));

    }

}
