package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by roosevelt on 7/26/16.
 */
public class FoodDetailFragment extends Fragment implements View.OnClickListener{
    ImageView imgView, btnAddToBasket;
    TextView txtName, txtDescription, txtCategory, txtQuantityInCart;
    long foodId;

    FoodDBHelper dbHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_detail_layout, container, false);

        imgView = (ImageView) view.findViewById(R.id.imgViewDetail);
        btnAddToBasket = (ImageView) view.findViewById(R.id.imgBtnAddToCart);
        txtName = (TextView) view.findViewById(R.id.txtName);
        txtCategory = (TextView) view.findViewById(R.id.txtCategory);
        txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        txtQuantityInCart = (TextView) view.findViewById(R.id.txtQuantityInBasket);

        foodId = getArguments().getLong(FoodDBHelper.COL_ID_FOOD, 1);
        dbHelper = FoodDBHelper.getInstance(getActivity().getApplicationContext());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_page_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Food food = dbHelper.getFoodById(foodId);

//        FoodItem item = dbHelper.getBasketedFoodItemFromUser(Constants.user.getId(), foodId);

        FoodInBasket foodInBasket = UserBasket.getInstance().getFoodItemInBasketListById(foodId);

        imgView.setImageResource(food.getImgResId());
        txtName.setText(food.getName());
        txtCategory.setText(food.getCategory());
        txtDescription.setText(food.getDesc());

        if (foodInBasket != null)
            txtQuantityInCart.setText(String.valueOf(foodInBasket.getQuantity()));
        else
            txtQuantityInCart.setText(String.valueOf(0));


//        Toast.makeText(view.getContext(), food.getDesc(), Toast.LENGTH_SHORT).show();



        btnAddToBasket.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imgBtnAddToCart:
                /**TODO check if item is in the basket
                 * if it is in, update quantity
                 *
                 * if it's not, add it with 1 as quantity
                 *
                 */

                List<FoodInBasket> basketList = UserBasket.getInstance().mFoodItemList;

                Food food = dbHelper.getFoodById(foodId);

                int indexInList = -1;
                for (int i = 0; i < basketList.size(); i++) {
                    if (foodId == basketList.get(i).getId()){
                        indexInList = i;
                    }
                }
                FoodInBasket foodInBasket;

                if (indexInList < 0){
                    //not in list, add to list
                    foodInBasket = new FoodInBasket(food, 1, Constants.user.getId());
                    UserBasket.getInstance().addToBasket(foodInBasket);

                }

                else {
                    foodInBasket = basketList.get(indexInList);
                    foodInBasket.addToQuantity();
                }

                txtQuantityInCart.setText(String.valueOf(foodInBasket.getQuantity()));

/*
                FoodItem item = new FoodItem(foodId);
                if (!dbHelper.isInRecord(Constants.user.getId(), foodId)){
                    dbHelper.newFoodItem(item);
                    Log.i("iiiiiii", "not in record " + foodId);

                }
                else {
                    item = dbHelper.getBasketedFoodItemFromUser(Constants.user.getId(), foodId);
                    dbHelper.addQuantityToItemInBasket(foodId, Constants.user.getId());
                    Log.i("iiiiiii", "In record " + foodId + " Item ID: " + item.getId() + " Count: " + item.getQuantity());
                }

                String quantity = String.valueOf(item.getQuantity());
                Log.i("iiiiiii", "Quantity: " + quantity);
                txtQuantityInCart.setText(quantity);
                break;
                */
        }
    }
}

/**
 *
 *
 FoodItem item = new FoodItem(foodId);
 dbHelper.changeQuantity(item);
 txtQuantityInCart.setText(String.valueOf(dbHelper.getItemQuantityInBasket(item)));
 //                Toast.makeText(view.getContext(), "Added an item to cart", Toast.LENGTH_SHORT).show();

 Toast.makeText(view.getContext(), String.valueOf(dbHelper.getBasketItems().getCount()), Toast.LENGTH_SHORT).show();

 */
