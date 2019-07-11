package com.nerdvana.kds.model;

import java.util.List;

public class OrderListModel {
    private String roomName;
    private String cashierName;
    private String dateTimeOrdered;
    private String notes;
    private String serviceChallengeTime;
    private List<OrderDetailsModel> orderDetailsList;
    private String runningTime;

    public OrderListModel(String roomName, String cashierName,
                          String dateTimeOrdered, String notes,
                          String serviceChallengeTime, List<OrderDetailsModel> orderDetailsList,
                          String runningTime) {
        this.roomName = roomName;
        this.cashierName = cashierName;
        this.dateTimeOrdered = dateTimeOrdered;
        this.notes = notes;
        this.serviceChallengeTime = serviceChallengeTime;
        this.orderDetailsList = orderDetailsList;
        this.runningTime = runningTime;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public List<OrderDetailsModel> getOrderDetailsList() {
        return orderDetailsList;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public String getDateTimeOrdered() {
        return dateTimeOrdered;
    }

    public String getNotes() {
        return notes;
    }

    public String getServiceChallengeTime() {
        return serviceChallengeTime;
    }
}
