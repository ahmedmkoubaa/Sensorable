package com.sensorable;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.commons.utils.Actions;
import com.commons.utils.SensorablePermissions;
import com.sensorable.activities.ConfigActivity;
import com.sensorable.services.WearForegroundService;

public class MainActivity extends AppCompatActivity {
    private static LinearLayout mainBackground;
    private Button configButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // request all necessary permissions
        SensorablePermissions.requestAll(this);

        initializeUIElements();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeForegroundService();
    }

    private void initializeForegroundService() {
        final Actions action = Actions.START;

        Intent intent = new Intent(this, WearForegroundService.class);
        intent.setAction(action.name()); // Set the action to be performed (Start or Stop)
        startForegroundService(intent); // Start a foreground service

//        if (ServiceStatePreferences.getServiceState(this) != ServiceState.STARTED) {
//          // to avoid to launch twice the same service even when is already started
//        }
    }

    // this is pure front end and can stay right here
    private void initializeUIElements() {
        configButton = findViewById(R.id.configuButton);
        configButton.setOnClickListener(v -> startActivity(new Intent(this, ConfigActivity.class)));

        mainBackground = findViewById(R.id.mainBackground);
        mainBackground.setBackgroundColor(Color.BLACK);
    }

    public static class MainFeedback {
        private static void setColorState(int color) {
            if (mainBackground != null) {
                mainBackground.setBackgroundColor(color);
            } else {
                Log.e("Wear OS MAIN ACTIVITY", "Unintialized main background");
            }
        }

        public static void setSuccess() {
            setColorState(Color.GREEN);
        }

        public static void setFailure() {
            setColorState(Color.RED);
        }
    }
}