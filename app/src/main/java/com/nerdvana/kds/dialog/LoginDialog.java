package com.nerdvana.kds.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nerdvana.kds.R;
import com.nerdvana.kds.bases.BaseDialog;
import com.nerdvana.kds.kitchen.ChefListAdapter;
import com.nerdvana.kds.actions.LoginAction;
import com.nerdvana.kds.model.UserModel;

import java.util.List;

public class LoginDialog extends BaseDialog {

    private RecyclerView rvUserList;

    private List<UserModel> userList;

    private LoginAction loginAction;
    public LoginDialog(@NonNull Context context, List<UserModel> userList,
                       LoginAction loginAction) {
        super(context);
        this.userList = userList;
        this.loginAction = loginAction;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDialogLayout(R.layout.dialog_login, "Login", false);

        initializeViews();

        setAdapter();
    }

    private void initializeViews() {
        rvUserList = findViewById(R.id.rvUserList);
    }

    private void setAdapter() {

        ChefListAdapter chefListAdapter = new ChefListAdapter(userList, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvUserList.setLayoutManager(linearLayoutManager);
        rvUserList.setAdapter(chefListAdapter);
    }
}
