package com.myproject.cartviewactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.cartviewactivity.R;
import com.myproject.cartviewactivity.db.Cart;
import com.myproject.cartviewactivity.interfaces.OnAdditionCallback;
import com.myproject.cartviewactivity.interfaces.OnDeleteCallback;
import com.myproject.cartviewactivity.interfaces.OnSubtractCallback;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Cart> list;
    private Context context;
    private OnSubtractCallback onSubtractCallback;
    private OnAdditionCallback onAdditionCallback;
    private OnDeleteCallback onDeleteCallback;

    public CartAdapter(Context context, List<Cart> list, OnSubtractCallback onSubtractCallback, OnAdditionCallback onAdditionCallback, OnDeleteCallback onDeleteCallback) {
        this.list = list;
        this.context = context;
        this.onSubtractCallback = onSubtractCallback;
        this.onAdditionCallback = onAdditionCallback;
        this.onDeleteCallback = onDeleteCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Cart cart = list.get(position);
        holder.txtName.setText(cart.getName());
        holder.txtPrice.setText(cart.getPrice());
        holder.txtQty.setText(cart.getQuantity());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtPrice, txtQty;
        ImageView imgAddition, imgSubtract, imgDelete;


        public ViewHolder(final View view) {
            super(view);
            txtName = view.findViewById(R.id.txt_name);
            txtPrice= view.findViewById(R.id.txt_price);
            txtQty= view.findViewById(R.id.txt_quantity);

            imgAddition= view.findViewById(R.id.img_addition_icon);
            imgSubtract= view.findViewById(R.id.img_subtract_icon);
            imgDelete= view.findViewById(R.id.img_delete_icon);

            imgSubtract.setOnClickListener(this);
            imgAddition.setOnClickListener(this);
            imgDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(v==imgSubtract)
            {
                onSubtractCallback.onSubtractSuccess(getAdapterPosition());
            }

            if(v==imgAddition)
            {
                onAdditionCallback.onAdditionSuccess(getAdapterPosition());
            }

            if(v==imgDelete)
            {
                onDeleteCallback.onDeleteSuccess(getAdapterPosition());
            }

        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
