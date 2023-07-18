package com.example.friendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class FriendActivity extends AppCompatActivity {
    ArrayList<Friend> list_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        Button bt_exit = (Button)findViewById(R.id.bt_exit);
        Button bt_save = (Button)findViewById(R.id.bt_save);
        Button bt_select = (Button)findViewById(R.id.bt_select);
        Button bt_delete = (Button)findViewById(R.id.bt_delete);
        Button bt_update = (Button)findViewById(R.id.bt_update);

        EditText et_name = (EditText)findViewById(R.id.et_name);
        EditText et_phone = (EditText)findViewById(R.id.et_phone);
        EditText et_address = (EditText)findViewById(R.id.et_address);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friend friend = new Friend();
                friend.setName(et_name.getText().toString());
                friend.setPhone_number(Integer.parseInt(et_phone.getText().toString()));
                friend.setAddress(et_address.getText().toString());

                if (dbHelper.insertFriend(friend) > 0)
                    Toast.makeText(getApplicationContext(), "Save successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Save error!", Toast.LENGTH_SHORT).show();
            }
        });

        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Friend> list_friend;
                list_friend = dbHelper.getAllFriend();

                ArrayList<String> list_string = new ArrayList<>();
                for (Friend friend:list_friend){
                    list_string.add(friend.getId_friend()+"");
                    list_string.add(friend.getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(FriendActivity.this,
                        android.R.layout.simple_list_item_1, list_string);
                gridView.setAdapter(adapter);
            }
        });
        
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_name.getText().toString())){
                    Toast.makeText(FriendActivity.this, "Please chose friend of record that you want to delete!", Toast.LENGTH_SHORT).show();
                }
                else if (dbHelper.deleteFriend(et_name.getText().toString()) > 0)
                   Toast.makeText(getApplicationContext(), "Delete Successfully!", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(getApplicationContext(), "Delete error!", Toast.LENGTH_SHORT).show();

               ArrayList<Friend> list_friend;
               list_friend = dbHelper.getAllFriend();

               ArrayList<String> list_string = new ArrayList<>();
               for (Friend friend:list_friend){
                    list_string.add(friend.getId_friend()+"");
                    list_string.add(friend.getName());
               }

               ArrayAdapter<String> adapter = new ArrayAdapter<>(FriendActivity.this,
                        android.R.layout.simple_list_item_1, list_string);
               gridView.setAdapter(adapter);
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friend friend = new Friend();
                friend.setName(et_name.getText().toString());
                friend.setPhone_number(Integer.parseInt(et_phone.getText().toString()));
                friend.setAddress(et_address.getText().toString());

                if (dbHelper.updateFriend(friend) > 0)
                    Toast.makeText(getApplicationContext(), "Update Successfully!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Update error!", Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                list_friend = dbHelper.getAllFriend();
                Friend friend = list_friend.get(i/2);
                et_name.setText(friend.getName()+"");
                et_phone.setText(friend.getPhone_number()+"");
                et_address.setText(friend.getAddress()+"");
            }
        });

    }
}