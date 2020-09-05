package com.example.easypay_task;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data")
public class Data_Table {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "name_data")
    private String name;
    @ColumnInfo(name = "password_data")
    private String password;



    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public void setId(int newid) {
        this.id = newid;

    }
    public void setName(String newName){
        this.name = newName;
    }
    public void setPassword(String newPassword){
        this.password = newPassword;
    }


}
