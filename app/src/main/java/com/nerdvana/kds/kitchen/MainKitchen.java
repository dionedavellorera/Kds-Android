package com.nerdvana.kds.kitchen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.FrameLayout;

import com.nerdvana.kds.R;
import com.nerdvana.kds.bases.BaseActivity;
import com.nerdvana.kds.custom.Helper;
import com.nerdvana.kds.dialog.RecipeDialog;
import com.nerdvana.kds.login.LoginFragment;
import com.nerdvana.kds.model.OrderDetailsModel;
import com.nerdvana.kds.model.OrderListModel;
import com.nerdvana.kds.viewmodel.OrdersViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainKitchen extends BaseActivity implements OrderListAction{


    private FrameLayout leftFrame;

    private LoginFragment loginFragment;

    private RecyclerView listOrders;

    private OrdersViewModel ordersViewModel;

    private KitchenOrdersAdapter kitchenOrdersAdapter;

    List<OrderListModel> orderList = new ArrayList<>();

    RecipeDialog recipeDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayout(R.layout.activity_kitchen);

        setTitle("Kitchen");

        initializeViews();

        if (isTwoPane()) {
            openFragment(loginFragment, R.id.leftFrame);
        }

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
        if (ordersViewModel.isOrdersValidForBump(orderList.get(position).getOrderDetailsList())) {
            Helper.showDialogMessage(MainKitchen.this, "Order submitted to dispatch", "Information");
            ordersViewModel.removeItemFromList(orderList, position);
            kitchenOrdersAdapter.notifyItemRemoved(position);

        } else {
            Helper.showDialogMessage(MainKitchen.this, "Order not yet ready for dispatch", "Information");
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
}
