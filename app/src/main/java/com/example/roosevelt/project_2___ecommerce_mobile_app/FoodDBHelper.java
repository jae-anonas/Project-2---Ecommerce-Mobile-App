package com.example.roosevelt.project_2___ecommerce_mobile_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by roosevelt on 7/23/16.
 */
public class FoodDBHelper extends SQLiteOpenHelper {

    public static final String TAG = "INSIDE DATABASEHELPER";

    public static final String DB_NAME = "foodshop.db";
    public static final int DB_VERSION = 1;

    public static final String FOOD_TABLE_NAME = "food";
    public static final String COL_ID_FOOD = "_id";
    public static final String COL_NAME_FOOD = "food_name";
    public static final String COL_CATEGORY_FOOD = "category";
    public static final String COL_DESCRIPTION_FOOD = "description";
    public static final String COL_COUNT_FOOD = "count";
    public static final String COL_TAGS_FOOD = "tags";
    public static final String COL_IMG_RES_ID_FOOD = "img_res_id";
    public static final String COL_PRICE_FOOD = "price";

    public static final String USER_TABLE_NAME = "user";
    public static final String COL_ID_USER = "_id";
    public static final String COL_NAME_USER = "user_name";
    public static final String COL_PW_USER = "password";
    public static final String COL_DATE_REGISTERED_USER = "date_since_registration";


    public static final String ITEM_TABLE_NAME = "basket_item";
    public static final String COL_ID_ITEM = "_id";
    public static final String COL_QUANTITY_ITEM = "quantity";
    public static final String COL_PURCHASED_ITEM = "is_purchased";
    public static final String COL_DATE_PURCHASED_ITEM = "date_purchased";
    public static final String COL_FK_USER_ID = "user_id";
    public static final String COL_FK_FOOD_ID = "food_id";


    private static final String CREATE_FOOD_TABLE = "CREATE TABLE " + FOOD_TABLE_NAME + " (" +
            COL_ID_FOOD + " INTEGER PRIMARY KEY, " +
            COL_NAME_FOOD + " TEXT, " +
            COL_DESCRIPTION_FOOD + " TEXT," +
            COL_CATEGORY_FOOD + " TEXT, " +
            COL_COUNT_FOOD + " INT, " +
            COL_TAGS_FOOD + " TEXT, " +
            COL_IMG_RES_ID_FOOD + " INT, " +
            COL_PRICE_FOOD + " INT " +
            ")";

    private static final String[] COLS_FOOD_BASIC_INFO = {COL_ID_FOOD, COL_NAME_FOOD, COL_PRICE_FOOD, COL_IMG_RES_ID_FOOD};

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE_NAME + " (" +
            COL_ID_USER + " INTEGER PRIMARY KEY, " +
            COL_NAME_USER + " TEXT, " +
            COL_PW_USER + " TEXT, " +
            COL_DATE_REGISTERED_USER + " TEXT " +
            ")";

    private static final String CREATE_ITEM_TABLE = "CREATE TABLE " + ITEM_TABLE_NAME + " (" +
            COL_ID_ITEM + " INTEGER PRIMARY KEY, " +
            COL_QUANTITY_ITEM + " INT, " +
            COL_PURCHASED_ITEM + " INT, " +
            COL_DATE_PURCHASED_ITEM + " TEXT, " +
            COL_FK_USER_ID + " INTEGER, " +
            COL_FK_FOOD_ID + " INTEGER, " +
            "FOREIGN KEY(" + COL_FK_USER_ID + ") " +
                "REFERENCES " + USER_TABLE_NAME + "(" +
                    COL_ID_USER + ") " +
            "FOREIGN KEY(" + COL_FK_FOOD_ID + ") " +
                "REFERENCES " + FOOD_TABLE_NAME + "(" +
                    COL_ID_FOOD + ") " +
            ")";

    private static FoodDBHelper sInstance;

    private FoodDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static FoodDBHelper getInstance(Context context){
        if (sInstance == null)
            sInstance = new FoodDBHelper(context);
        return sInstance;
    }

    public static final String DELETE_FOOD_ITEM_TABLE = "DROP TABLE IF EXISTS " + ITEM_TABLE_NAME;
    public static final String DELETE_FOOD_TABLE = "DROP TABLE IF EXISTS " + FOOD_TABLE_NAME;
    public static final String DELETE_USER_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_FOOD_TABLE);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_FOOD_ITEM_TABLE);
        sqLiteDatabase.execSQL(DELETE_FOOD_TABLE);
        sqLiteDatabase.execSQL(DELETE_USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void newFood(Food food){
        ContentValues values = new ContentValues();
        values.put(COL_NAME_FOOD, food.getName());
        values.put(COL_CATEGORY_FOOD, food.getCategory());
        values.put(COL_DESCRIPTION_FOOD, food.getDesc());
        values.put(COL_COUNT_FOOD, food.getCount());
        values.put(COL_TAGS_FOOD, food.getTags());
        values.put(COL_IMG_RES_ID_FOOD, food.getImgResId());
        values.put(COL_PRICE_FOOD, food.getPrice());

        SQLiteDatabase db = getWritableDatabase();
        db.insertOrThrow(FOOD_TABLE_NAME, null, values);
        db.close();
    }

    public void newUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME_USER, user.getName());
        values.put(COL_PW_USER, user.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        db.insertOrThrow(USER_TABLE_NAME, null, values);
        db.close();
    }

    public void newFoodItem(FoodItem item) {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd mmmm yyyy", Locale.ENGLISH);

        ContentValues values = new ContentValues();
        values.put(COL_DATE_PURCHASED_ITEM, sdf.format(currentDate));
        values.put(COL_PURCHASED_ITEM, item.isPurchased());  //means not yet purchased
        values.put(COL_QUANTITY_ITEM, item.getQuantity());
        values.put(COL_FK_FOOD_ID, item.getFoodId());
        values.put(COL_FK_USER_ID, item.getUserId());

        SQLiteDatabase db = getWritableDatabase();
        db.insertOrThrow(ITEM_TABLE_NAME, null, values);
        db.close();
    }

    //TODO method for retrieving all food products
    public Cursor getAllFood(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                FOOD_TABLE_NAME,   //table name
                null,   //String[] projection
                null,   //String selection with " = ? " or " LIKE ? "
                null,   //String[] selection
                null,   //String groupBy
                null,   //String having
                null    //String orderBy
        );

        return cursor;
    }

    //TODO method for retrieving all food products
    public Cursor getAllFoodBasicInfo(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                FOOD_TABLE_NAME,   //table name
                COLS_FOOD_BASIC_INFO,   //String[] projection
                null,   //String selection with " = ? " or " LIKE ? "
                null,   //String[] selection
                null,   //String groupBy
                null,   //String having
                null    //String orderBy
        );

        return cursor;
    }

    //TODO method for retrieving all food products searched with a keyword/tag
    public Cursor getFoodBySearch(String search){
        SQLiteDatabase db = getReadableDatabase();
        String selection =
                COL_NAME_FOOD + " LIKE ? OR " +
                        COL_DESCRIPTION_FOOD + " LIKE ? OR " +
                        COL_TAGS_FOOD + " LIKE ? ";
//        String selection = COL_TAGS_FOOD + " LIKE ? ";
        String[] selectionArgs = new String[]{"%" + search + "%",
                "%" + search + "%", "%" + search + "%"};
//        String[] selectionArgs = new String[]{"%" + search + "%"};

        Cursor cursor;

        cursor = db.query(
                FOOD_TABLE_NAME,   //table name
                COLS_FOOD_BASIC_INFO,   //String[] projection
                selection,      //String selection
                selectionArgs,   //String[] selectionArgs
                null,   //String groupBy
                null,   //String having
                null    //String orderBy
        );

        return cursor;

    }

    //TODO method for retrieving Food details/object
    public Food getFoodById(long id){
        Food food = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                FOOD_TABLE_NAME,   //table name
                null,   //String[] projection (null for */all columns)
                COL_ID_FOOD + " = ? ",   //String selection with " = ? " or " LIKE ? "
                new String[]{String.valueOf(id)},   //String[] selection
                null,   //String groupBy
                null,   //String having
                null    //String orderBy
        );

        if(cursor.moveToFirst()){
            Log.i(TAG, "Creating then returning Food object");
            food = new Food(
                    cursor.getInt(cursor.getColumnIndex(COL_ID_FOOD)),
                    cursor.getInt(cursor.getColumnIndex(COL_COUNT_FOOD)),
                    cursor.getInt(cursor.getColumnIndex(COL_IMG_RES_ID_FOOD)),
                    cursor.getString(cursor.getColumnIndex(COL_NAME_FOOD)),
                    cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION_FOOD)),
                    cursor.getString(cursor.getColumnIndex(COL_CATEGORY_FOOD)),
                    cursor.getString(cursor.getColumnIndex(COL_TAGS_FOOD)),
                    cursor.getInt(cursor.getColumnIndex(COL_PRICE_FOOD))
            );
            Log.i("iiiiiiiiii", "some text"+cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION_FOOD)));
        }

        return food;

    }

    //TODO method for updating quantity of an item already in the list
    public void changeQuantity(long foodId, long userId, int quantity){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_QUANTITY_ITEM, quantity);

        int i = db.update(
                ITEM_TABLE_NAME,
                values,
                COL_FK_FOOD_ID + " = ? AND " +
                    COL_FK_USER_ID + " = ? AND " +
                    COL_PURCHASED_ITEM + " = ",
                new String[]{String.valueOf(foodId),
                    String.valueOf(userId),
                    String.valueOf(0)}
        );

        if (i != -1){
            Log.i(TAG, "Item quantity update successful");
        }

        db.close();

    }
}



/**
 Cursor cursor = db.query(
 null,   //table name
 null,   //String[] projection
 null,   //String selection with " = ? " or " LIKE ? "
 null,   //String[] selection
 null,   //String groupBy
 null,   //String having
 null    //String orderBy
 );
 */