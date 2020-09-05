package com.example.easypay_task;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DataAccess {
    //insertion
    @Insert
    public void addUser(Data_Table user);

    @Query("select * from data")
    public List<Data_Table> getUsers();

}
