package com.commons.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collection;
=======
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
import java.util.List;

@Dao
public interface StepsForActivitiesRegistryDao {
    @Query("SELECT * FROM StepsForActivitiesRegistryEntity")
    List<StepsForActivitiesRegistryEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<StepsForActivitiesRegistryEntity> stepsRegistries);

    @Query("DELETE FROM StepsForActivitiesRegistryEntity")
    void deleteAll();

<<<<<<< HEAD
    @Query("DELETE FROM StepsForActivitiesRegistryEntity WHERE id_activity = :arg0")
    void delete(Long arg0);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(StepsForActivitiesRegistryEntity stepsRegistries);

    @Query("SELECT id_step FROM StepsForActivitiesRegistryEntity WHERE id_activity = :arg0 AND clicked = 1")
    List<Integer> getClickedStepsByActivityId(long arg0);
=======
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(StepsForActivitiesRegistryEntity stepsRegistries);
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
}