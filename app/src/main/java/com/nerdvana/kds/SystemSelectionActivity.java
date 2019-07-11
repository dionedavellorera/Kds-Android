package com.nerdvana.kds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nerdvana.kds.bases.BaseActivity;
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
                Intent mainKitchen= new Intent(
                        SystemSelectionActivity.this,
                        MainKitchen.class);
                mainKitchen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(mainKitchen);
            break;

            case R.id.btnEnterDispatch:
                Intent mainDispatch= new Intent(
                        SystemSelectionActivity.this,
                        MainDispatch.class);
                mainDispatch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(mainDispatch);
            break;

        }
    }
}
