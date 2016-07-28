package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.content.ContentValues;

/**
 * Created by roosevelt on 7/27/16.
 */
public class FoodInBasket extends Food{

    long mUserId;
    int mQuantity;

    public FoodInBasket(Food food, int quantity, long userId) {
        super(food.getId(), food.getCount(), food.getImgResId(),food.getName(), food.getDesc(), food.getCategory(), food.getTags(), food.getPrice());
        this.mUserId = userId;
        this.mQuantity = quantity;
    }

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long mUserId) {
        this.mUserId = mUserId;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public void addToQuantity(){
        this.mQuantity += 1;
    }

    //TODO use this in storing to database
    public FoodItem getAsFoodItem(){
        return new FoodItem(
                getQuantity(),
                getId(),
                getUserId()
        );
    }
}
