package com.sensorable.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.commons.services.CsvSensorsSaverService;
import com.commons.utils.SensorableServicesManager;

public class ManagerService extends Service {
    private static Boolean INITIALIZED = false;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!INITIALIZED) {
            initializeWearOsTransmissionService();
            initializeEmpaticaTransmissionService();

//            initializeSensorsProviderService();
            initializeAdlDetectionService();
            initializeBackUpService();
            initializeCsvSaverService();


            // This isn't useful yet and eventually we'll remove it
            // initializeBluetoothDetectionService();

            initializeRegisterActivitiesService();

            INITIALIZED = true;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void initializeService(Class<?> serviceClass) {
        SensorableServicesManager.initializeService(this, serviceClass);
    }

    private void initializeService(Intent intent) {
        SensorableServicesManager.initializeService(this, intent);

    }

    private void initializeWearOsTransmissionService() {
        initializeService(WearTransmissionService.class);
    }

    private void initializeEmpaticaTransmissionService() {
        initializeService(EmpaticaTransmissionService.class);
    }

    private void initializeSensorsProviderService() {
        Intent intent = new Intent(this, SensorsProviderService.class);
        initializeService(intent);
    }

    private void initializeAdlDetectionService() {
        initializeService(AdlDetectionService.class);
    }

    private void initializeBackUpService() {
        initializeService(BackUpService.class);
    }

    private void initializeCsvSaverService() {
        initializeService(CsvSensorsSaverService.class);
    }

    private void initializeRegisterActivitiesService() {
        initializeService(RegisterActivitiesService.class);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}