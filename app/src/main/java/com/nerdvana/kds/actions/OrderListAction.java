package com.nerdvana.kds.actions;

import com.nerdvana.kds.model.OrderDetailsModel;

public interface OrderListAction {
    void bumpClicked(int position);
    void itemLongClicked(OrderDetailsModel odm);
}
