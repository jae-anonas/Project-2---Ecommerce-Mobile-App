package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class BasketActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView mRecyclerView;
    Button btnBack, btnCheckOut;
    BasketListRecyclerViewAdapter adapter;
    UserBasket basket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        setTitle("Basket");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        basket = UserBasket.getInstance();

        btnBack = (Button) findViewById(R.id.btnGoBack);
        btnCheckOut = (Button) findViewById(R.id.btnCheckout);

        btnBack.setOnClickListener(this);
        btnCheckOut.setOnClickListener(this);
        btnCheckOut.setText(String.format(Locale.ENGLISH, "CHECKOUT($%d)",
                UserBasket.getInstance().getTotalBill()));

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewBasket);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.addOnItemTouchListener(getSwipeListener());

        adapter = new BasketListRecyclerViewAdapter(basket.mFoodItemList);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            default:
            case R.id.btnGoBack:
                finish();
                break;
            case R.id.btnCheckout:
                if (basket.mFoodItemList.size() > 0){
                    //TODO ask user if he/she is certain of purchase
                    getCheckoutCheckDialog().create().show();

                }
                break;

        }
    }

    public SwipeableRecyclerViewTouchListener getSwipeListener(){

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {

                            FoodInBasket food;

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    food = basket.mFoodItemList.get(position);
                                    basket.mFoodItemList.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();

                                Snackbar.make(recyclerView,"Removed item from basket.", Snackbar.LENGTH_LONG).
                                        setAction("Undo", new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                basket.mFoodItemList.add(food);
                                                adapter.notifyDataSetChanged();
                                            }

                                        }).show();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {

                                for (int position : reverseSortedPositions) {
                                    food = basket.mFoodItemList.get(position);
                                    basket.mFoodItemList.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();

                                Snackbar.make(recyclerView,"Removed item from basket.", Snackbar.LENGTH_LONG).
                                        setAction("Undo", new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                basket.mFoodItemList.add(food);
                                                adapter.notifyDataSetChanged();
                                            }

                                        }).show();
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }
                        });
        return swipeTouchListener;

    }

    public static class CheckoutCheckDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.dialog_message_checkout + UserBasket.getInstance().getTotalBill() + ".00")
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    })
                    .setNegativeButton(R.string.not_yet, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    private AlertDialog.Builder getCheckoutCheckDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.dialog_message_checkout) +
                UserBasket.getInstance().getTotalBill() + ".00")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FoodDBHelper dbHelper = FoodDBHelper.getInstance(BasketActivity.this);
                        // TODO save all items
                        for (FoodInBasket foodInBasket : basket.mFoodItemList) {
                            dbHelper.newFoodItem(foodInBasket.getAsFoodItem());

                            //TODO update Food.count for each item bought
                            dbHelper.changeFoodCount(foodInBasket,
                                    foodInBasket.getCount() - foodInBasket.getQuantity());

                        }
                        Toast.makeText(BasketActivity.this,
                                "YOU HAVE PURCHASED ALL ITEMS IN YOUR BASKET",
                                Toast.LENGTH_SHORT).show();
                        //TODO delete all items in the list, maybe add some animation
                        basket.mFoodItemList.clear();
                        adapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton(R.string.not_yet, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO nothing
                    }
                });
        // Create the AlertDialog object and return it
        return builder;

    }

}

