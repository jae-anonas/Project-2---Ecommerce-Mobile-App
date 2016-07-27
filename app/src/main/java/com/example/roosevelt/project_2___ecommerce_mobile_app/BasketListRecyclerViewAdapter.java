package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

/**
 * Created by roosevelt on 7/27/16.
 */
public class BasketListRecyclerViewAdapter extends RecyclerView.Adapter<BasketListCardHolder> {

    private List<FoodInBasket> mFoodItemList;
    FoodDBHelper dbHelper;

    public BasketListRecyclerViewAdapter(List<FoodInBasket> basketList){
        mFoodItemList = basketList;
    }

    @Override
    public BasketListCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.basket_item_layout, parent, false);
        BasketListCardHolder viewHolder = new BasketListCardHolder(view);
        dbHelper = FoodDBHelper.getInstance(parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BasketListCardHolder holder, int position) {
        FoodInBasket foodItem = mFoodItemList.get(position);

        holder.imgView.setImageResource(foodItem.getImgResId());
        holder.txtName.setText(foodItem.getName());
        holder.txtPrice.setText(String.format(Locale.ENGLISH, "$ %d.00", foodItem.getPrice()));
        holder.txtQuantity.setText(String.valueOf(foodItem.getQuantity()));
        holder.txtSubTotal.setText(String.format(Locale.ENGLISH, "$ %d.00",
                (foodItem.getPrice() * foodItem.getQuantity())));
    }

    @Override
    public int getItemCount() {
        return mFoodItemList.size();
    }
}
