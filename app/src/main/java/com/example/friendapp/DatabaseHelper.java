package com.example.friendapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "mydb.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Friends(" +
                "id_friend integer primary key," +
                "name text," +
                "phone_number integer," +
                "address text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Friends");
        onCreate(db);
    }

    public int insertFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", friend.getName());
        contentValues.put("phone_number", friend.getPhone_number());
        contentValues.put("address", friend.getAddress());

        int result = (int)db.insert("Friends", null, contentValues);
        db.close();
        return result;
    }

    public ArrayList<Friend> getAllFriend(){
        ArrayList<Friend> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Friends", null);
        if (cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Friend(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public int deleteFriend(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String whereClause = "name = ?";
        String whereArgs[] = {name + ""};
        int result = db.delete("Friends", whereClause, whereArgs);
        db.close();
        return result;
    }

    public int updateFriend(Friend friend){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", friend.getName());
        contentValues.put("phone_number", friend.getPhone_number());
        contentValues.put("address", friend.getAddress());

        String whereClause = "name=?";
        String whereArgs[] = {friend.getName()+""};
        int result =  db.update("Friends", contentValues, whereClause, whereArgs);
        db.close();
        return result;
    }

}


