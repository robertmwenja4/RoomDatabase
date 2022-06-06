package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatabase.db.AppDatabase;
import com.example.roomdatabase.db.User;

public class AddNewUserActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mfirst, mlast;
    Button msave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        mfirst = findViewById(R.id.firstname);
        mlast = findViewById(R.id.lastname);
        msave = findViewById(R.id.save);
        msave.setOnClickListener(this);

    }

    public void SaveUser(String first, String last){

        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        User user = new User();
        user.firstName = first;
        user.lastName = last;
        db.userDao().insertUser(user);
        finish();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.save:
                SaveUser(mfirst.getText().toString(), mlast.getText().toString());
                break;
        }
    }
}