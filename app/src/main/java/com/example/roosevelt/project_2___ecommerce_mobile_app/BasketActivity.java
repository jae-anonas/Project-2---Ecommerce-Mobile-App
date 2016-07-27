package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class BasketActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewBasket);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        BasketListRecyclerViewAdapter adapter = new BasketListRecyclerViewAdapter(UserBasket.getInstance().mFoodItemList);

        Toast.makeText(this, "Count: " + UserBasket.getInstance().mFoodItemList.size(), Toast.LENGTH_SHORT).show();
        mRecyclerView.setAdapter(adapter);
    }
}
