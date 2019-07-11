package com.nerdvana.kds.bases;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.nerdvana.kds.R;

public class BaseActivity extends AppCompatActivity {
    private View rootView;

    protected void setLayout(int layout) {
        setContentView(layout);
        rootView = findViewById(R.id.rootView);
        Animation animRightIn = AnimationUtils.loadAnimation(this, R.anim.from_right_in);
        rootView.startAnimation(animRightIn);
        rootView.requestFocus();
    }


    protected void openFragment(Fragment fragment, int container) {
        if (!fragment.isAdded()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(container, fragment);
            fragmentTransaction.commit();
        } else {
            Toast.makeText(getApplicationContext(), "Fragment already added", Toast.LENGTH_SHORT).show();
        }
    }


}
