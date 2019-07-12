package com.nerdvana.kds.kitchen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.nerdvana.kds.R;
import com.nerdvana.kds.actions.LoginAction;
import com.nerdvana.kds.actions.OrderListAction;
import com.nerdvana.kds.bases.BaseActivity;
import com.nerdvana.kds.custom.Helper;
import com.nerdvana.kds.custom.SharedPreferenceManager;
import com.nerdvana.kds.dialog.LoginDialog;
import com.nerdvana.kds.dialog.RecipeDialog;
import com.nerdvana.kds.login.LoginFragment;
import com.nerdvana.kds.model.OrderDetailsModel;
import com.nerdvana.kds.model.OrderListModel;
import com.nerdvana.kds.model.UserModel;
import com.nerdvana.kds.viewmodel.OrdersViewModel;
import com.nerdvana.kds.viewmodel.UsersViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainKitchen extends BaseActivity implements OrderListAction, LoginAction {


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

        setLayout(R.layout.activity_kitchen);

        setTitle("Kitchen");

        initializeViews();

        userList = new ArrayList<>();
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));
        userList.add(new UserModel(656, "10655",
                "Dione Dave Llorera", "Android Developer"));



        if (isTwoPane()) {
            openFragment(loginFragment, R.id.leftFrame);
        }

        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);


        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);

        ordersViewModel.getOrderListLiveData().observe(this, new Observer<List<OrderListModel>>() {
            @Override
            public void onChanged(@Nullable List<OrderListModel> orderListModels) {
                kitchenOrdersAdapter.notifyDataSetChanged();
            }
        });

        setOrdersAdapter();
    }

    private boolean isTwoPane() {
        boolean isTwoPane = false;
        if (leftFrame != null) {
            isTwoPane = true;
        }

        return isTwoPane;
    }

    private void initializeViews() {
        listOrders = findViewById(R.id.listOrders);
        leftFrame = findViewById(R.id.leftFrame);

        loginFragment = new LoginFragment();
    }

    private void setOrdersAdapter() {
        for (int i = 0; i < 25; i++) {

            List<OrderDetailsModel> orderDetailsList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                orderDetailsList.add(new OrderDetailsModel("PRODUCT " + j, String.valueOf(i),
                        false,
                        "http://clipart-library.com/img/2076303.png",
                        "Mix this \n Mix that, add magic sarap \n Done!",
                        "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"));
            }

            orderList.add(new OrderListModel("30"+i, "SHERYL AVENO",
                    "07-10-2019 1:23 PM", "PAKILUTO MAIGI",
                    "25:00:00", orderDetailsList,
                    "00:10:" + i));
        }



        kitchenOrdersAdapter = new KitchenOrdersAdapter(orderList, MainKitchen.this,
                this);

        int columnCount = 1;
        if (isTwoPane()) {
            columnCount = 2;
        }

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(columnCount,StaggeredGridLayoutManager.VERTICAL);
        listOrders.setLayoutManager(layoutManager);
        listOrders.setAdapter(kitchenOrdersAdapter);
        kitchenOrdersAdapter.notifyDataSetChanged();

    }

    @Override
    public void bumpClicked(int position) {

        if (usersViewModel.hasLoggedInUser(userList)) {
            if (ordersViewModel.isOrdersValidForBump(orderList.get(position).getOrderDetailsList())) {
                Helper.showDialogMessage(MainKitchen.this, "Order submitted to dispatch", "Information");
                ordersViewModel.removeItemFromList(orderList, position);
                kitchenOrdersAdapter.notifyItemRemoved(position);

            } else {
                Helper.showDialogMessage(MainKitchen.this, "Order not yet ready for dispatch", "Information");
            }
        } else {
            Helper.showDialogMessage(MainKitchen.this, "No Logged in users", "Information");
        }


    }

    @Override
    public void itemLongClicked(OrderDetailsModel odm) {
        if (recipeDialog == null) {
            recipeDialog = new RecipeDialog(MainKitchen.this, odm);

            recipeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    recipeDialog = null;
                }
            });

            recipeDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    recipeDialog = null;
                }
            });

            recipeDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferenceManager.clearPreference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                if (loginDialog == null) {

                    openLoginDialog();

                }
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    private void openLoginDialog() {


        loginDialog = new LoginDialog(MainKitchen.this, userList, this);

        loginDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                loginDialog = null;
            }
        });

        loginDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                loginDialog = null;
            }
        });

        loginDialog.show();
    }

    @Override
    public void userClicked(int position) {

    }
}
