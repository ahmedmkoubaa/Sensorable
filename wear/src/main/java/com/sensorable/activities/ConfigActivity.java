package com.sensorable.activities;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.commons.utils.DeviceType;
import com.commons.utils.LoginHelper;
import com.sensorable.R;
import com.sensorable.utils.WearosEnvironment;

public class ConfigActivity extends AppCompatActivity {
    private TextView userCode;
    private Button saveUserButton;
    private ToggleButton usedHandtoggle;
    private static final String leftHand = "IZQUIERDA";
    private static final String rightHand = "DERECHA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        userCode = (TextView) findViewById(R.id.userIdText);
        userCode.setText(getUserCode());

        saveUserButton = (Button) findViewById(R.id.saveUserIdButton);
        saveUserButton.setOnClickListener(view -> {
            LoginHelper.saveLogin(this, userCode.getText().toString());
            finish();
        });

        usedHandtoggle = findViewById(R.id.usedHandToggleButton);

        // Set the default values
        usedHandtoggle.setTextOff(leftHand);
        usedHandtoggle.setTextOn(rightHand);

        // retrieve the stored data
        usedHandtoggle.setChecked(WearosEnvironment.getDeviceType(this) == DeviceType.WEAROS_RIGHT_HAND);

        usedHandtoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WearosEnvironment.setDeviceType(ConfigActivity.this, isChecked ? DeviceType.WEAROS_RIGHT_HAND : DeviceType.WEAROS_LEFT_HAND);
            }
        });

        usedHandtoggle.setOnClickListener(v -> {
            // revert the click
            usedHandtoggle.setChecked(!usedHandtoggle.isChecked());

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat_Dialog)); // Change "this" to `getActivity()` if you're using this on a fragment
            builder.setMessage("Cambiar de mano?")
                    .setPositiveButton("Sí", (dialogInterface, i) -> {
                        usedHandtoggle.setChecked(!usedHandtoggle.isChecked());
                    })
                    .setNegativeButton("No", null)
                    .show();


        });
    }

    private String getUserCode() {
        return LoginHelper.getUserCode(this) == null ? "" : LoginHelper.getUserCode(this);
    }
}