package com.commons.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BluetoothDeviceDao {
    @Query("SELECT * FROM BluetoothDeviceEntity WHERE address = :arg0")
    BluetoothDeviceEntity getByAddress(String arg0);

    @Query("SELECT * FROM BluetoothDeviceEntity")
    List<BluetoothDeviceEntity> getAll();

    @Query("SELECT * FROM BluetoothDeviceEntity WHERE address = :arg0 ")
    BluetoothDeviceEntity findByAddress(String arg0);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BluetoothDeviceEntity device);

    @Update()
    void updateDevice(BluetoothDeviceEntity device);
}