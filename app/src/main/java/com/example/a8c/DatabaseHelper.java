package com.example.a8c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDB";
    private static final String TABLE_NAME = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_FULL_NAME + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT"
                + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FULL_NAME, user.getFullName());
        values.put(KEY_USERNAME, user.getUserName());
        values.put(KEY_PASSWORD, user.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public User getUserById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_FULL_NAME, KEY_USERNAME, KEY_PASSWORD},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int fullNameIndex = cursor.getColumnIndex(KEY_FULL_NAME);
            int usernameIndex = cursor.getColumnIndex(KEY_USERNAME);
            int passwordIndex = cursor.getColumnIndex(KEY_PASSWORD);

            user = new User(cursor.getInt(idIndex),
                    cursor.getString(fullNameIndex),
                    cursor.getString(usernameIndex),
                    cursor.getString(passwordIndex));

            cursor.close();
        }

        db.close();
        return user;
    }

    public User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_FULL_NAME, KEY_USERNAME, KEY_PASSWORD},
                KEY_USERNAME + "=?", new String[]{username}, null, null, null, null);

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int fullNameIndex = cursor.getColumnIndex(KEY_FULL_NAME);
            int usernameIndex = cursor.getColumnIndex(KEY_USERNAME);
            int passwordIndex = cursor.getColumnIndex(KEY_PASSWORD);

            user = new User(cursor.getInt(idIndex),
                    cursor.getString(fullNameIndex),
                    cursor.getString(usernameIndex),
                    cursor.getString(passwordIndex));

            cursor.close();
        }

        db.close();
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int fullNameIndex = cursor.getColumnIndex(KEY_FULL_NAME);
            int usernameIndex = cursor.getColumnIndex(KEY_USERNAME);
            int passwordIndex = cursor.getColumnIndex(KEY_PASSWORD);

            do {
                User user = new User(cursor.getInt(idIndex),
                        cursor.getString(fullNameIndex),
                        cursor.getString(usernameIndex),
                        cursor.getString(passwordIndex));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }
}
