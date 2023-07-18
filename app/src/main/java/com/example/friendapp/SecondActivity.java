package com.example.friendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button bt_newf = (Button)findViewById(R.id.bt_newf);
        Button bt_exit = (Button)findViewById(R.id.bt_exit);
        GridView gridView = (GridView)findViewById(R.id.gridview);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_newf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(SecondActivity.this, FriendActivity.class);
                startActivity(intent3);
            }
        });

        ArrayList<Friend> list_friend;
        list_friend = dbHelper.getAllFriend();

        ArrayList<String> list_string = new ArrayList<>();
        for (Friend friend:list_friend){
            list_string.add(friend.getId_friend()+"");
            list_string.add(friend.getName());
            list_string.add(friend.getPhone_number()+"");
            list_string.add(friend.getAddress());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SecondActivity.this,
                android.R.layout.simple_list_item_1, list_string);
        gridView.setAdapter(adapter);
    }
}