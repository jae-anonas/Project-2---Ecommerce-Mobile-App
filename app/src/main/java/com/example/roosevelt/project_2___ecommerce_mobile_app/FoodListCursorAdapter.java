package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by skyfishjy on 10/31/14.
 */
public class FoodListCursorAdapter extends CursorRecyclerViewAdapter<FoodCardHolder>{

    public FoodListCursorAdapter(Context context, Cursor cursor) {

        super(context, cursor);
    }

    @Override
    public FoodCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemCardView = inflater.inflate(R.layout.food_item_card_layout_basic, parent, false);

        return new FoodCardHolder(itemCardView);
    }

    @Override
    public void onBindViewHolder(final FoodCardHolder viewHolder, final Cursor cursor) {

        final long id = cursor.getLong(cursor.getColumnIndex(FoodDBHelper.COL_ID_FOOD));

        viewHolder.txtPrice
                .setText("$ " + String.valueOf(cursor.getInt(cursor.getColumnIndex(FoodDBHelper.COL_PRICE_FOOD))));
        viewHolder.txtName
                .setText(cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_NAME_FOOD)));
        viewHolder.imgView
                .setImageResource(cursor.getInt(cursor.getColumnIndex(FoodDBHelper.COL_IMG_RES_ID_FOOD)));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(view.getContext(), "onBindView " + String.valueOf(id), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), FoodDetailActivity.class);
                intent.putExtra(FoodDBHelper.COL_ID_FOOD, id);
                view.getContext().startActivity(intent);
            }
        });

    }

}