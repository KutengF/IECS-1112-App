package com.example.breakfastorderingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private final List<MenuItem> menuItems;
    private final OnItemClickListener itemClickListener;

    public MenuAdapter(List<MenuItem> menuItems, OnItemClickListener itemClickListener) {
        this.menuItems = menuItems;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);

        holder.name.setText(menuItem.getName());
        holder.description.setText(menuItem.getDescription());
        holder.price.setText(String.format("$%.2f", menuItem.getPrice()));
        Picasso.get().load(menuItem.getImageUrl()).into(holder.image);

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onAddToCartClick(menuItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public interface OnItemClickListener {
        void onAddToCartClick(MenuItem menuItem);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name;
        private final TextView description;
        private final TextView price;
        private final Button addToCartButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.menu_item_image);
            name = itemView.findViewById(R.id.menu_item_name);
            description = itemView.findViewById(R.id.menu_item_description);
            price = itemView.findViewById(R.id.menu_item_price);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}

