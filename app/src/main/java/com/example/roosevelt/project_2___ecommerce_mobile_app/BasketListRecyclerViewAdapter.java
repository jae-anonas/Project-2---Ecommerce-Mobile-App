package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Dimension;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by roosevelt on 7/27/16.
 */
public class BasketListRecyclerViewAdapter extends RecyclerView.Adapter<BasketListCardHolder>{

    private Context mContext;
    private List<FoodInBasket> mFoodItemList;
//    FoodDBHelper dbHelper;
    private OnChangeQuantityListener mListener;

    public void setFoodItemList(List<FoodInBasket> mFoodItemList) {
        this.mFoodItemList = mFoodItemList;
    }

    public interface OnChangeQuantityListener{
        void onChangeQuantity(int total);
    }

    public BasketListRecyclerViewAdapter(List<FoodInBasket> basketList, Context context, OnChangeQuantityListener listener){
        mFoodItemList = basketList;
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public BasketListCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.basket_item_layout, parent, false);
        BasketListCardHolder viewHolder = new BasketListCardHolder(view);
//        dbHelper = FoodDBHelper.getInstance(parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BasketListCardHolder holder, int position) {
        final FoodInBasket foodItem = mFoodItemList.get(position);
        final int pos = position;

        holder.imgView.setImageResource(foodItem.getImgResId());
        holder.txtName.setText(foodItem.getName());
        holder.txtPrice.setText(String.format(Locale.ENGLISH, "$ %d.00", foodItem.getPrice()));
        holder.txtQuantity.setText(String.valueOf(foodItem.getQuantity()));
        holder.txtSubTotal.setText(String.format(Locale.ENGLISH, "$ %d.00",
                (foodItem.getPrice() * foodItem.getQuantity())));
        //TODO add onclicklistener to txtBtnChange...
        holder.txtBtnChangeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(foodItem, pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodItemList.size();
    }

    public void show(final FoodInBasket food, final int position)
    {
        final Dialog d = new Dialog(mContext);
        d.setTitle("Change quantity");
        d.setContentView(R.layout.dialog_get_quantity);
        Button b2 = (Button) d.findViewById(R.id.btnDialogCancel);
        Button b1 = (Button) d.findViewById(R.id.btnDialogOkay);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numQuantity);
        np.setMaxValue(food.getCount()); // max value 100
        np.setMinValue(0);   // min value 0
        np.setWrapSelectorWheel(false);
        np.setValue(food.getQuantity());
//        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (np.getValue() == 0) {
                    mFoodItemList.remove(position);
                    notifyDataSetChanged();
                }
                else{
                    mFoodItemList.get(position).setQuantity(np.getValue());
                    notifyDataSetChanged();
                }
                mListener.onChangeQuantity(UserBasket.getInstance().getTotalBill());
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();

    }


}
