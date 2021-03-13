package com.myproject.cartviewactivity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.myproject.cartviewactivity.R;
import com.myproject.cartviewactivity.db.Cart;
import com.myproject.cartviewactivity.db.CartDao;
import com.myproject.cartviewactivity.db.CartDatabase;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Cart> listItems;
    private Context context;
    private TextView CartCount;


    public ProductAdapter(List<Cart> listItems, Context context, TextView cartCount) {
        this.listItems = listItems;
        this.context = context;
        this.CartCount = cartCount;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_product, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Cart listItem = listItems.get(position);

        holder.txtName.setText(listItem.getName());
        holder.txtPrice.setText(listItem.getPrice());

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtPrice;
        AppCompatButton addBtn;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            txtPrice= itemView.findViewById(R.id.txt_price);
            addBtn= itemView.findViewById(R.id.add_Btn);

            addBtn.setOnClickListener(this);

            }

        @Override
        public void onClick(View v) {

            CartDatabase ud = CartDatabase.getInstance(v.getContext());
            Cart cart = new Cart();
            int position = getAdapterPosition();
            cart.setProductid(listItems.get(position).getProductid());
            cart.setName(listItems.get(position).getName());
            cart.setPrice(listItems.get(position).getPrice());
            cart.setQuantity(listItems.get(position).getQuantity());

            CartDao dao = ud.cartDao();
            dao.insert(cart);

            Snackbar snackbar;
            snackbar = Snackbar.make(v, listItems.get(position).getName()+" added to cart", Snackbar.LENGTH_SHORT);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(Color.parseColor("#FF03DAC5"));
            snackbar.show();


            CartCount.setText(""+dao.getProductCount());


        }
    }

}
