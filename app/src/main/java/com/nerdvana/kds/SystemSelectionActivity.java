package com.nerdvana.kds;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.nerdvana.kds.bases.BaseActivity;
import com.nerdvana.kds.custom.ApplicationConstants;
import com.nerdvana.kds.custom.SharedPreferenceManager;
import com.nerdvana.kds.dispatch.MainDispatch;
import com.nerdvana.kds.kitchen.MainKitchen;

public class SystemSelectionActivity extends BaseActivity implements View.OnClickListener {

    private Button btnEnterKitchen;
    private Button btnEnterDispatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayout(R.layout.activity_system_selection);

        setTitle("KDS");

        initializeViews();

        detectSystem();
    }

    private void initializeViews() {
        btnEnterKitchen = findViewById(R.id.btnEnterKitchen);
        btnEnterKitchen.setOnClickListener(this);
        btnEnterDispatch = findViewById(R.id.btnEnterDispatch);
        btnEnterDispatch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEnterKitchen:
                openKitchenActivity();
                SharedPreferenceManager.saveString("kitchen", ApplicationConstants.SYSTEM_TYPE);
            break;

            case R.id.btnEnterDispatch:
                openDispatchActivity();
                SharedPreferenceManager.saveString("dispatch", ApplicationConstants.SYSTEM_TYPE);
            break;
        }
    }

    private void openKitchenActivity() {
        Intent mainKitchen= new Intent(
                SystemSelectionActivity.this,
                MainKitchen.class);
        mainKitchen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mainKitchen);
    }

    private void openDispatchActivity() {
        Intent mainDispatch= new Intent(
                SystemSelectionActivity.this,
                MainDispatch.class);
        mainDispatch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mainDispatch);
    }

    private void detectSystem() {
        if (!TextUtils.isEmpty(SharedPreferenceManager.getString(ApplicationConstants.SYSTEM_TYPE))) {
            switch (SharedPreferenceManager.getString(ApplicationConstants.SYSTEM_TYPE)) {
                case "kitchen":
                    openKitchenActivity();
                    break;
                case "dispatch":
                    openDispatchActivity();
                    break;
            }
        }
    }
}
