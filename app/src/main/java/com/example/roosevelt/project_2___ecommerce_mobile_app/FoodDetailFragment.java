package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by roosevelt on 7/26/16.
 */
public class FoodDetailFragment extends Fragment implements View.OnClickListener{
    ImageView imgView, btnAddToBasket;
    TextView txtName, txtDescription, txtCategory;
    long foodId;

    FoodDBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_detail_layout, container, false);

        imgView = (ImageView) view.findViewById(R.id.imgViewDetail);
        btnAddToBasket = (ImageView) view.findViewById(R.id.imgBtnAddToCart);
        txtName = (TextView) view.findViewById(R.id.txtName);
        txtCategory = (TextView) view.findViewById(R.id.txtCategory);
        txtDescription = (TextView) view.findViewById(R.id.txtDescription);

        foodId = getArguments().getLong(FoodDBHelper.COL_ID_FOOD, 1);
        dbHelper = FoodDBHelper.getInstance(getActivity().getApplicationContext());

        return view;
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setRetainInstance(true);
//
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Food food = dbHelper.getFoodById(foodId);

        Toast.makeText(view.getContext(), food.getDesc(), Toast.LENGTH_SHORT).show();

        imgView.setImageResource(food.getImgResId());
        txtName.setText(food.getName());
        txtCategory.setText(food.getCategory());
        txtDescription.setText(food.getDesc());

        btnAddToBasket.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imgBtnAddToCart:
                Toast.makeText(view.getContext(), "Added an item to cart", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
