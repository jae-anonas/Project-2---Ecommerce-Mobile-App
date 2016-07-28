package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class BasketActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView mRecyclerView;
    Button btnBack, btnCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        setTitle("Items in Your Basket");

        btnBack = (Button) findViewById(R.id.btnGoBack);
        btnCheckOut = (Button) findViewById(R.id.btnCheckout);

        btnBack.setOnClickListener(this);
        btnCheckOut.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewBasket);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        BasketListRecyclerViewAdapter adapter = new BasketListRecyclerViewAdapter(UserBasket.getInstance().mFoodItemList);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
            case R.id.btnGoBack:
                finish();
                break;
            case R.id.btnCheckout:
                Toast.makeText(this, "YOU HAVE PURCHASED ALL ITEMS IN YOUR BASKET",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
