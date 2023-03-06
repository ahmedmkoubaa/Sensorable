package com.commons.database;

<<<<<<< HEAD

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
=======
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ActivityDao {
    @Query("SELECT * FROM ActivityEntity")
    List<ActivityEntity> getAll();

<<<<<<< HEAD
    @Query("SELECT COUNT(*) FROM ActivityEntity")
    int size();
=======
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<ActivityEntity> activities);

<<<<<<< HEAD
    @Delete
    void delete(List<ActivityEntity> activities);

    @Update
    void update(List<ActivityEntity> activities);
=======
    @Query("DELETE FROM ActivityEntity")
    void deleteAll();
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
}