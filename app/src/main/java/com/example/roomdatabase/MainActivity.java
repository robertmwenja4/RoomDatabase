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

import com.example.roomdatabase.databinding.ActivityMainBinding;
import com.example.roomdatabase.db.AppDatabase;
import com.example.roomdatabase.db.User;
import com.example.roomdatabase.db.UserListAdapter;

import java.util.List;

 public class MainActivity extends AppCompatActivity {
     private UserListAdapter userListAdapter;
     ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        activityMainBinding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Main.class));
                finish();
            }
        });
        activityMainBinding.addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddNewUserActivity.class),100);
            }
        });
//        setContentView(R.layout.activity_main);
//        Button mbtn = findViewById(R.id.addUser);
//        mbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivityForResult(new Intent(MainActivity.this, AddNewUserActivity.class),100);
//            }
//        });
        initRecycler();

        LoadData();
    }

     private void initRecycler() {
//         RecyclerView recyclerView = findViewById(R.id.recyclerview);
//         recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//         DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//         recyclerView.addItemDecoration(dividerItemDecoration);
//         userListAdapter = new UserListAdapter(this);
//         recyclerView.setAdapter(userListAdapter);
         activityMainBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
         DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
         activityMainBinding.recyclerview.addItemDecoration(dividerItemDecoration);
         userListAdapter = new UserListAdapter(this);
         activityMainBinding.recyclerview.setAdapter(userListAdapter);
     }

     public void LoadData(){
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        List<User> userList = db.userDao().getAllUsers();
        userListAdapter.setUserList(userList);
        
    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100){
            LoadData();
        }
         super.onActivityResult(requestCode, resultCode, data);
     }
 }