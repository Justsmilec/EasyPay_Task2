package com.example.easypay_task;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Data_Table.class},version = 1)
public abstract class DatabaseClass extends RoomDatabase {

    public abstract DataAccess mydata();
}
