package com.nerdvana.kds.dispatch;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.nerdvana.kds.R;
import com.nerdvana.kds.bases.BaseActivity;
import com.nerdvana.kds.dialog.LoginDialog;
import com.nerdvana.kds.dialog.RecipeDialog;
import com.nerdvana.kds.kitchen.KitchenOrdersAdapter;
import com.nerdvana.kds.login.LoginFragment;
import com.nerdvana.kds.model.OrderListModel;
import com.nerdvana.kds.model.UserModel;
import com.nerdvana.kds.viewmodel.OrdersViewModel;
import com.nerdvana.kds.viewmodel.UsersViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainDispatch extends BaseActivity {

    private FrameLayout leftFrame;

    private LoginFragment loginFragment;

    private RecyclerView listOrders;

    private OrdersViewModel ordersViewModel;

    private KitchenOrdersAdapter kitchenOrdersAdapter;

    List<OrderListModel> orderList = new ArrayList<>();

    private LoginDialog loginDialog;

    RecipeDialog recipeDialog;

    private UsersViewModel usersViewModel;

    private List<UserModel> userList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayout(R.layout.activity_dispatch);

        setTitle("Dispatch");


    }
}
