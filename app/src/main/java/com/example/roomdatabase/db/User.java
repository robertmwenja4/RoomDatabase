package com.example.roomdatabase.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "First_Name")
    public String firstName;
    @ColumnInfo(name = "Last_Name")
    public String lastName;
}
