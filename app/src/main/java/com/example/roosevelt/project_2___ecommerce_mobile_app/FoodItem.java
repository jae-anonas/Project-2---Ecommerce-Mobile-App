package com.example.roosevelt.project_2___ecommerce_mobile_app;

/**
 * Created by roosevelt on 7/23/16.
 */
public class FoodItem {
    private int quantity, id, userId, foodId;
    private boolean purchased;


    public FoodItem(boolean purchased, int id, int quantity, int foodId) {
        this.purchased = purchased;
        this.id = id;
        this.quantity = quantity;
        this.foodId = foodId;
    }

    public FoodItem(int quantity, boolean purchased, int foodId) {
        this.quantity = quantity;
        this.purchased = purchased;
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
