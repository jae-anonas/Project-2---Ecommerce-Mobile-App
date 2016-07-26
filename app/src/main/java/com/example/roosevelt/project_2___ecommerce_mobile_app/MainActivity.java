package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FoodDBHelper mFoodDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFoodDBHelper = FoodDBHelper.getInstance(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        Cursor cursor = mFoodDBHelper.getAllFoodBasicInfo();

        final FoodListCursorAdapter foodListCursorAdapter = new FoodListCursorAdapter(this, cursor);

        mRecyclerView.setAdapter(foodListCursorAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insertFoodData();
                //foodListCursorAdapter.notifyDataSetChanged();
                Snackbar.make(view, "Cursor count: " + mFoodDBHelper.getAllFoodBasicInfo().getCount(),
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO method for inserting Food items in the database
    private void insertFoodData(){

        Food food1 = new Food(
                1,
                500,
                R.drawable.bacon_burger,
                "Bacon and Cheese Burger",
                "not your regular burger, with cheese and bacon plus fries on the side",
                "sandwich",
                " burger sandwich ",
                20);

        Food food2 = new Food(
                2,
                500,
                R.drawable.bread_basket,
                "Basket of Bread",
                "an old-school basket of freshly-baked bread",
                "bread",
                " bread sandwich ",
                7);

        Food food3 = new Food(
                3,
                500,
                R.drawable.bread_food_salad_sandwich,
                "Veggie Sandwich",
                "not your regular sandwich meal, with alfalfa sprouts and pomelo juice on the side",
                "sandwich",
                " sandwich vegetarian vegan bread ",
                10);

        Food food4 = new Food(
                4,
                500,
                R.drawable.broccoli_sandwich,
                "Broccoli Sandwich",
                "broccoli, tomatoes and cucumbers on a sesame seed bun",
                "sandwich",
                " vegan vegetarian sandwich ",
                10);

        Food food5 = new Food(
                5,
                500,
                R.drawable.bunch_of_carrots,
                "Bunch of Carrots",
                "just a bunch of carrots with one white and one red onion on the side",
                "vegetable",
                " vegan vegetarian vegetables ",
                5);

        Food food6 = new Food(
                6,
                500,
                R.drawable.bunch_of_tomatoes,
                "Bunch of Tomatoes",
                "a bunch of six Campari tomatoes",
                "vegetable",
                " vegan vegetarian sandwich ",
                5);

        Food food7 = new Food(
                7,
                500,
                R.drawable.burger_with_fries,
                "Hamburger with Potato Wedges",
                "100% Angus beef burger with Spinach and onions on a sesame seed bun",
                "sandwich",
                " burger sandwich potato potatoes ",
                12);

        Food food8 = new Food(
                8,
                500,
                R.drawable.food_colorful_dessert_sweet,
                "Chocolate Cupcakes",
                "12 chocolate cupcakes on a tray with assorted sprinkles",
                "dessert",
                " dessert sweets cupcakes chocolate ",
                18);

        Food food9 = new Food(
                9,
                500,
                R.drawable.food_dinner_grilled_shashlik,
                "Shashlik",
                "3 skewers of shashlik goodness, with onions and capsicum",
                "grilled",
                " skewer vegetarian ",
                10);

        Food food10 = new Food(
                10,
                500,
                R.drawable.food_pasta_tomato,
                "Tomato Linguine",
                "a gorgeous plate of linguine with chunky tomato sauce and olives",
                "pasta",
                " pasta tomato linguine ",
                12);

        Food food11 = new Food(
                11,
                500,
                R.drawable.meat_vegetables_gemuesepiess_mushrooms_111131,
                "Gemuesepiess",
                "3 Skewers of vegetable goodness",
                "grilled",
                " vegetarian vegan cucumber portobello mushroom ",
                10);

        Food food12 = new Food(
                12,
                500,
                R.drawable.steak_juicy,
                "12oz Steak",
                "12oz Angus Steak cooked to your liking, served with boiled and seasoned marble potatoes",
                "sandwich",
                " meat steak angus 12oz ",
                10);

        FoodDBHelper foodDBHelper = FoodDBHelper.getInstance(this);

        foodDBHelper.newFood(food1);
        foodDBHelper.newFood(food2);
        foodDBHelper.newFood(food3);
        foodDBHelper.newFood(food4);
        foodDBHelper.newFood(food5);
        foodDBHelper.newFood(food6);
        foodDBHelper.newFood(food7);
        foodDBHelper.newFood(food8);
        foodDBHelper.newFood(food9);
        foodDBHelper.newFood(food10);
        foodDBHelper.newFood(food11);
        foodDBHelper.newFood(food12);



    }
}
