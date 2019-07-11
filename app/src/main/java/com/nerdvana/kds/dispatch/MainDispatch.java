package com.nerdvana.kds.dispatch;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.nerdvana.kds.R;
import com.nerdvana.kds.bases.BaseActivity;

public class MainDispatch extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayout(R.layout.activity_dispatch);

        setTitle("Dispatch");
    }
}
