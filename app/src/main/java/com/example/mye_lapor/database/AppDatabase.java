package com.example.mye_lapor.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mye_lapor.dao.DatabaseDao;
import com.example.mye_lapor.model.ModelDatabase;

@Database(entities = {ModelDatabase.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}
