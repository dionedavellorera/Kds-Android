package com.nerdvana.kds.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.nerdvana.kds.model.OrderDetailsModel;
import com.nerdvana.kds.model.OrderListModel;
import com.nerdvana.kds.repository.OrdersRepository;

import java.util.List;

public class OrdersViewModel extends AndroidViewModel {

    private OrdersRepository ordersRepository = OrdersRepository.getInstance();

    @NonNull
    private MutableLiveData<List<OrderListModel>> orderListLiveData;


    public OrdersViewModel(@NonNull Application application) {
        super(application);

        orderListLiveData = ordersRepository.getOrdersLiveData();
    }

    @NonNull
    public MutableLiveData<List<OrderListModel>> getOrderListLiveData() {
        return orderListLiveData;
    }

    public boolean isOrdersValidForBump(List<OrderDetailsModel> ordersList) {
        boolean isValid = true;
        for (OrderDetailsModel item : ordersList) {
            if (!item.isChecked()) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public void removeItemFromList(List<OrderListModel> ordersList , int position) {
        ordersList.remove(position);
    }
}
