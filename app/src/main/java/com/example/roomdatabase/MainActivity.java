 package com.example.roomdatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roomdatabase.db.AppDatabase;
import com.example.roomdatabase.db.User;

import java.util.List;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mbtn = findViewById(R.id.addUser);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddNewUserActivity.class),100);
            }
        });
        initRecycler();
    }

     private void initRecycler() {
         RecyclerView recyclerView = findViewById(R.id.recyclerview);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));

         DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
         recyclerView.addItemDecoration(dividerItemDecoration);
         recyclerView.setAdapter();
     }

     public void LoadData(){
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        List<User> userList = db.userDao().getAllUsers();
        
    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100){
            LoadData();
        }
         super.onActivityResult(requestCode, resultCode, data);
     }
 }