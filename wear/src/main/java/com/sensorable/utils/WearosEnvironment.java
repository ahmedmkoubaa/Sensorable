package com.sensorable.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.commons.utils.DeviceType;

public class WearosEnvironment {
    private static final String dataName = "hand";
    private static int DEVICE_TYPE = DeviceType.WEAROS_LEFT_HAND;

    public static void setDeviceType(Context context, final int type) {
        DEVICE_TYPE = type;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(dataName, type);
        editor.commit();
    }

    public static int getDeviceType(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return DEVICE_TYPE = sharedPreferences.getInt(dataName, 1);
    }

    public static int getDeviceType() {
        return DEVICE_TYPE;
    }
}
