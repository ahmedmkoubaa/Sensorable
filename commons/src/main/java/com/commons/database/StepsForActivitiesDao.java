package com.commons.database;

import androidx.room.Dao;
<<<<<<< HEAD
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
=======
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f

import java.util.ArrayList;
import java.util.List;

@Dao
public interface StepsForActivitiesDao {
    @Query("SELECT * FROM StepsForActivitiesEntity")
    List<StepsForActivitiesEntity> getAll();

<<<<<<< HEAD
    @Query("SELECT COUNT(*) FROM StepsForActivitiesEntity")
    int size();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<StepsForActivitiesEntity> stepsForActivities);

    @Query("SELECT id FROM StepsForActivitiesEntity WHERE id_activity = :arg0 AND id_step = :arg1")
    int getIdByActivityAndStep(long arg0, int arg1);

    @Update
    void update(ArrayList<StepsForActivitiesEntity> stepsForActivityEntities);

    @Delete
    void delete(List<StepsForActivitiesEntity> collect);
=======
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<StepsForActivitiesEntity> stepsForActivities);

    @Query("DELETE FROM StepsForActivitiesEntity")
    void deleteAll();

    @Query("SELECT id FROM StepsForActivitiesEntity WHERE id_activity = :arg0 AND id_step = :arg1")
    int getIdByActivityAndStep(long arg0, int arg1);
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
}