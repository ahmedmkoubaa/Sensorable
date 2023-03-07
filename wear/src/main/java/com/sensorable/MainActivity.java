package com.sensorable;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.widget.Button;
import android.widget.ListView;

import com.commons.utils.Actions;
import com.commons.utils.SensorableConstants;
import com.commons.utils.SensorablePermissions;
import com.commons.utils.ServiceState;
import com.commons.utils.ServiceStatePreferences;
import com.sensorable.activities.ConfigActivity;
import com.sensorable.services.WearForegroundService;
import com.sensorable.utils.LoggerAdapter;
import com.sensorable.utils.SensorableLogger;

public class MainActivity extends WearableActivity {
    private LoggerAdapter loggerAdapter;
    private ListView loggerList;
    private Button configButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // request all necessary permissions
        SensorablePermissions.requestAll(this);

        initializeForegroundService();
        initializeUIElements();
        initializeReminders();

    }

    private void initializeForegroundService() {
        final Actions action = Actions.START;
        if (ServiceStatePreferences.getServiceState(this) == ServiceState.STOPPED && action == Actions.STOP) {
            return;
        }

        Intent intent = new Intent(this, WearForegroundService.class);
        intent.setAction(action.name()); // Set the action to be performed (Start or Stop)
        startForegroundService(intent); // Start a foreground service
    }

    // remind to show the logging messages when sent some information data
    private void initializeReminders() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // do something
                loggerAdapter.notifyDataSetChanged();
                handler.postDelayed(this, SensorableConstants.SCHEDULE_LOGGER_REFRESH);  // 1 second delay
            }
        };
        handler.post(runnable);
    }

    // this is pure front end and can stay right here
    private void initializeUIElements() {
        loggerAdapter = new LoggerAdapter(getBaseContext(), R.layout.logger_message_layout, SensorableLogger.getLoggedData());
        loggerAdapter.setNotifyOnChange(true);

        loggerList = findViewById(R.id.loggerList);
        loggerList.setAdapter(loggerAdapter);

        configButton = findViewById(R.id.configuButton);
        configButton.setOnClickListener(v -> startActivity(new Intent(this, ConfigActivity.class)));
    }
}