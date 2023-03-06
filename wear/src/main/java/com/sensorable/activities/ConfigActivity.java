package com.sensorable.activities;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.commons.utils.DeviceType;
import com.commons.utils.LoginHelper;
import com.sensorable.R;
import com.sensorable.utils.WearosEnvironment;

public class ConfigActivity extends WearableActivity {

    private TextView userCode;
    private Button confirmUsercode;
    private ToggleButton usedHandtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        userCode = (TextView) findViewById(R.id.userIdText);
        confirmUsercode = (Button) findViewById(R.id.saveUserIdButton);
        confirmUsercode.setOnClickListener(view -> LoginHelper.saveLogin(this, userCode.getText().toString()));

        usedHandtoggle = findViewById(R.id.usedHandToggleButton);
        usedHandtoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WearosEnvironment.setDeviceType(isChecked ? DeviceType.WEAROS_RIGHT_HAND : DeviceType.WEAROS_LEFT_HAND);
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }
}