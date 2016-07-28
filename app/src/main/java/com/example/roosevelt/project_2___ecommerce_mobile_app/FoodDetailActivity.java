package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FoodDetailActivity extends AppCompatActivity {
    Toolbar mToolbar;
    long foodId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        foodId = getIntent().getLongExtra(FoodDBHelper.COL_ID_FOOD, 1);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.detail_page_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home:
                finish();
                break;
            case R.id.btnGoToBasket:
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
                break;

            default:
        }
        return super.onOptionsItemSelected(item);
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
