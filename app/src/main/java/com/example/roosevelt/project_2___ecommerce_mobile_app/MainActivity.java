package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FoodDBHelper mFoodDBHelper;
    private Cursor cursor;
    private ProgressBar mProgressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressbar = (ProgressBar) findViewById(R.id.progressbar);

        insertFoodDataAsync();

        Intent intent = new Intent(Intent.ACTION_SEARCH);
        intent.putExtra(SearchManager.QUERY, "");
        onNewIntent(intent);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.trim().equals("")){

                    Intent intent = new Intent(Intent.ACTION_SEARCH);
                    intent.putExtra(SearchManager.QUERY, "");
                    onNewIntent(intent);
                }
                return false;
            }
        });

        ComponentName componentName = new ComponentName(this, this.getClass());

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);

            mFoodDBHelper = FoodDBHelper.getInstance(this);

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);

            //TODO run the next line on a separate thread
//            getFoodBySearchAsync(query);
            cursor = mFoodDBHelper.getFoodBySearch(query);
//            Cursor cursor = mFoodDBHelper.getAllFoodBasicInfo();

            if (!cursor.moveToFirst()){
                Toast.makeText(this, "0 Results Found", Toast.LENGTH_SHORT).show();
            }

            FoodListCursorAdapter foodListCursorAdapter = new FoodListCursorAdapter(this, cursor);
            mRecyclerView.setAdapter(foodListCursorAdapter);
        }
    }

    private void insertFoodDataAsync(){
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

        final List<Food> foods = new ArrayList<>(12);
        foods.add(food1);
        foods.add(food2);
        foods.add(food3);
        foods.add(food4);
        foods.add(food5);
        foods.add(food6);
        foods.add(food7);
        foods.add(food8);
        foods.add(food9);
        foods.add(food10);
        foods.add(food11);
        foods.add(food12);

        final FoodDBHelper foodDBHelper = FoodDBHelper.getInstance(this);


        foodDBHelper.newFood(food1);



        AsyncTask<Void, Integer, Void> task = new AsyncTask<Void, Integer, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressbar.setMax(12);
                mProgressbar.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                for(int i = 0; i < 12; i++){
                    foodDBHelper.newFood(foods.get(i));
                    publishProgress(i + 1);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                mProgressbar.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mProgressbar.setVisibility(View.INVISIBLE);
            }
        };


        User user = new User(1,"jaeanonas", "1234");

        Constants.user = user;

        foodDBHelper.newUser(Constants.user);

        task.execute();
    }

    /**
     *
     *
     private void addDatabaseItemsAsync(){

     task = new AsyncTask<Void, Integer, Cursor>() {

    @Override
    protected void onPreExecute() {
    super.onPreExecute();
    mProgressBar.setVisibility(View.VISIBLE);
    mProgressBar.setMax(3000);
    }

    @Override
    protected Cursor doInBackground(Void... params) {
    for (int i = 0; i < 3000; i++) {
    ExampleDBHelper.getInstance(getApplicationContext()).addName("John","Doe");
    publishProgress(i + 1);
    }

    return ExampleDBHelper.getInstance(getApplicationContext()).getFirstHundred();
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
    super.onPostExecute(cursor);
    int count = ExampleDBHelper.getInstance(getApplicationContext()).getItemCount();
    mProgressBar.setVisibility(View.INVISIBLE);
    mTextView.setText("All items have been added! Current item count " + count);

    mAdapter.swapCursor(cursor);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    super.onProgressUpdate(values);
    mTextView.setText("Adding items to database...." + values[0]);
    mProgressBar.setProgress(values[0]);

    }
    };

     task.execute();
     }
     *
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btnGoToBasket) {
            Intent intent = new Intent(this, BasketActivity.class);
            startActivity(intent);
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

        User user = new User(1,"jaeanonas", "1234");

        Constants.user = user;

        foodDBHelper.newUser(Constants.user);



    }
}
