package com.commons.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EventForAdlDao {
    @Query("SELECT * FROM EventForAdlEntity")
    List<EventForAdlEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<EventForAdlEntity> eventsforAdls);

    @Query("DELETE FROM EventForAdlEntity")
    void deleteAll();
}