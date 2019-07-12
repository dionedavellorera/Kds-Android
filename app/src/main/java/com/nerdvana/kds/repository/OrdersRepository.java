package com.nerdvana.kds.repository;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.nerdvana.kds.model.OrderListModel;

import java.util.List;

public class OrdersRepository {

    private static OrdersRepository INSTANCE;

    @NonNull
    private MutableLiveData<List<OrderListModel>> ordersLiveData = new MutableLiveData<>();

    public static OrdersRepository getInstance() {
        if(INSTANCE == null) {
            synchronized (OrdersRepository.class) {
                if(INSTANCE == null) {
                    INSTANCE = new OrdersRepository();
                }
            }
        }
        return INSTANCE;
    }


    @NonNull
    public MutableLiveData<List<OrderListModel>> getOrdersLiveData() {
        return ordersLiveData;
    }
}
