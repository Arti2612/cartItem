package com.myproject.cartviewactivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.cartviewactivity.adapter.CartAdapter;
import com.myproject.cartviewactivity.db.Cart;
import com.myproject.cartviewactivity.db.CartDao;
import com.myproject.cartviewactivity.db.CartDatabase;
import com.myproject.cartviewactivity.interfaces.OnAdditionCallback;
import com.myproject.cartviewactivity.interfaces.OnDeleteCallback;
import com.myproject.cartviewactivity.interfaces.OnSubtractCallback;

import java.util.List;


public class CartActivity extends AppCompatActivity implements OnAdditionCallback, OnSubtractCallback, OnDeleteCallback {
    private RecyclerView recyclerView;
    private TextView totalPriceTxt;
    private CartDatabase userDatabase;
    private CartDao dao;
    private List<Cart> responseList;
    private int totalPrice = 0;
    private CartAdapter adapter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setTitle("My Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = com.myproject.cartviewactivity.CartActivity.this;

        recyclerView = findViewById(R.id.recyclerView);
        totalPriceTxt = findViewById(R.id.price);

        userDatabase = CartDatabase.getInstance(context);

        //init dao and perform operation
        dao = userDatabase.cartDao();
        responseList = dao.getAllProducts();

        Log.d("users", "" + responseList);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new CartAdapter(context, responseList, this, this, this);

        recyclerView.setAdapter(adapter);

        for (Cart user : responseList) {
            totalPrice = totalPrice + (Integer.parseInt(user.getPrice()) * Integer.parseInt(user.getQuantity()));
        }


        if (responseList.size() <= 0) {
            totalPriceTxt.setText("No Items in Cart!!");
        } else {
            totalPriceTxt.setText(responseList.size() + " items - " + "Rs. " + totalPrice);
        }


    }

    @Override
    public void onAdditionSuccess(int adapterId) {
        TextView i = recyclerView.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
        int qty = Integer.parseInt(i.getText().toString());
        TextView j = recyclerView.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
        long rate = Integer.parseInt(j.getText().toString());
        if (qty >= 1) {
            qty = qty + 1;
            totalPrice += rate;
            i.setText(String.valueOf(qty));
            //totalPriceTxt.setText(String.valueOf(totalPrice));
            totalPriceTxt.setText(responseList.size() + " items - " + "Rs. " + totalPrice);

            Cart c = new Cart();
            c.setId(responseList.get(adapterId).getId());
            c.setProductid(responseList.get(adapterId).getProductid());
            c.setName(responseList.get(adapterId).getName());
            c.setPrice(responseList.get(adapterId).getPrice());
            c.setQuantity(String.valueOf(qty));
            responseList.set(adapterId, c);
            adapter.notifyItemChanged(adapterId);
        }

    }

    @Override
    public void onSubtractSuccess(int adapterId) {
        TextView i = recyclerView.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
        int qty = Integer.parseInt(i.getText().toString());
        TextView j = recyclerView.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
        long rate = Integer.parseInt(j.getText().toString());
        if (qty > 1) {
            qty = qty - 1;
            totalPrice -= rate;
            i.setText(String.valueOf(qty));
            //totalPriceTxt.setText(String.valueOf(totalPrice));
            totalPriceTxt.setText(responseList.size() + " items - " + "Rs. " + totalPrice);


            Cart c = new Cart();
            c.setId(responseList.get(adapterId).getId());
            c.setProductid(responseList.get(adapterId).getProductid());
            c.setName(responseList.get(adapterId).getName());
            c.setPrice(responseList.get(adapterId).getPrice());
            c.setQuantity(String.valueOf(qty));
            responseList.set(adapterId, c);
            adapter.notifyItemChanged(adapterId);
        }

    }

    @Override
    public void onDeleteSuccess(int adapterId) {

        int id = responseList.get(adapterId).getId();

        CartDatabase ud = CartDatabase.getInstance(context);
        Cart u = new Cart();
        u.setId(id);
        CartDao dao = ud.cartDao();
        dao.delete(u);

        responseList.remove(adapterId);
        adapter.notifyItemRemoved(adapterId);

        totalPrice = 0;

        for (Cart user : responseList) {
            totalPrice = totalPrice + (Integer.parseInt(user.getPrice()) * Integer.parseInt(user.getQuantity()));
        }


        if (responseList.size() <= 0) {
            totalPriceTxt.setText("No Items in Cart!!");
        } else {
            totalPriceTxt.setText(responseList.size() + " items - " + "Rs. " + totalPrice);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
