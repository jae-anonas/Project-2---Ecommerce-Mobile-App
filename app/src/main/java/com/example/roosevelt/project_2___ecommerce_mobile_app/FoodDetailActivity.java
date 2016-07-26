package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class FoodDetailActivity extends AppCompatActivity {
    long foodId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        foodId = getIntent().getLongExtra(FoodDBHelper.COL_ID_FOOD, 1);

//        Toast.makeText(this, String.valueOf(foodId), Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FoodDetailFragment fragment = new FoodDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(FoodDBHelper.COL_ID_FOOD, foodId);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}

/**
 *
 static OnFoodSelectedListener mListener;

 public interface OnFoodSelectedListener {
 void onFoodSelected(long id);
 }

 public static FoodDetailFragment newInstance(Bundle bundle, OnFoodSelectedListener listener) {
 FoodDetailFragment fragment = new FoodDetailFragment();
 fragment.setArguments(bundle);
 mListener = listener;
 return fragment;
 }
 */
