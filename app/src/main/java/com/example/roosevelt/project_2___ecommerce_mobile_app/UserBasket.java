package com.example.roosevelt.project_2___ecommerce_mobile_app;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by roosevelt on 7/27/16.
 */
public class UserBasket {
    List<FoodInBasket> mFoodItemList;

    private static UserBasket ourInstance = new UserBasket();

    public static UserBasket getInstance() {
        return ourInstance;
    }

    private UserBasket() {
        mFoodItemList = new LinkedList<>();

    }

    public void addToBasket(FoodInBasket foodItem){
        //if quantity is less than available items
        mFoodItemList.add(foodItem);
        //else, show toast
    }

    public List<FoodInBasket> getFoodItemInBasketList(long id){

        return mFoodItemList;
    }

    public FoodInBasket getFoodItemInBasketListById(long id){
        FoodInBasket foodInBasket = null;

        for (FoodInBasket food : mFoodItemList) {
            if (id == food.getId()){
                foodInBasket = food;
                break;
            }
        }
        return foodInBasket;
    }

    public int getTotalBill(){
        int total = 0;

        for (FoodInBasket foodInBasket : mFoodItemList) {
            total += foodInBasket.getQuantity() * foodInBasket.getPrice();
        }

        return total;
    }

}
