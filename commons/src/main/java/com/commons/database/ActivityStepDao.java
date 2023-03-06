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
public interface ActivityStepDao {
    @Query("SELECT * FROM ActivityStepEntity")
    List<ActivityStepEntity> getAll();

<<<<<<< HEAD
    @Query("SELECT COUNT(*) FROM ActivityStepEntity")
    int size();
=======
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<ActivityStepEntity> steps);

<<<<<<< HEAD
=======
    @Query("DELETE FROM ActivityStepEntity")
    void deleteAll();

>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
    @Query("SELECT * FROM ActivityStepEntity WHERE id = :arg0")
    ActivityStepEntity getStepById(int arg0);

    @Query("SELECT * FROM ActivityStepEntity " +
            "WHERE id IN " +
            "(SELECT id_step FROM StepsForActivitiesEntity WHERE id_activity = :arg0 )")
    List<ActivityStepEntity> getStepsOfActivity(long arg0);

<<<<<<< HEAD
    @Update
    void update(ArrayList<ActivityStepEntity> stepsEntities);

    @Delete
    void delete(List<ActivityStepEntity> collect);
=======
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
}