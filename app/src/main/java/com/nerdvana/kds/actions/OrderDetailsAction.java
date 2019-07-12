package com.nerdvana.kds.actions;

import com.nerdvana.kds.model.OrderDetailsModel;

public interface OrderDetailsAction {
    void clicked(OrderDetailsModel orderDetailsModel, int position);
    void longClicked(OrderDetailsModel orderDetailsModel, int position);
}
