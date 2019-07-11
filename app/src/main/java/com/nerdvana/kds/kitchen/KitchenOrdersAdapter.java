package com.nerdvana.kds.kitchen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nerdvana.kds.R;
import com.nerdvana.kds.model.OrderDetailsModel;
import com.nerdvana.kds.model.OrderListModel;

import java.util.List;

public class KitchenOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderListModel> orderList;
    private Context context;
    KitchenOrderDetailsAdapter kitchenOrderDetailsAdapter;
    RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    OrderListModel model;
    private OrderListAction orderListAction;

    public KitchenOrdersAdapter(List<OrderListModel> orderList, Context context,
                                OrderListAction orderListAction) {
        this.orderList = orderList;
        this.context = context;
        this.orderListAction = orderListAction;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new KitchenOrdersAdapter.ViewHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.list_item_orders, viewGroup, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView roomName;;
        private TextView serviceChallengeTime;;
        private TextView runningTime;;
        private TextView dateTime;;
        private TextView notes;;
        private TextView cashierName;;
        private Button bumpOrder;;

        private RecyclerView listOrderDetails;;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomName = itemView.findViewById(R.id.roomName);
            serviceChallengeTime = itemView.findViewById(R.id.serviceChallengeTime);
            runningTime = itemView.findViewById(R.id.runningTime);
            dateTime = itemView.findViewById(R.id.dateTime);
            notes = itemView.findViewById(R.id.notes);
            cashierName = itemView.findViewById(R.id.cashierName);

            listOrderDetails = itemView.findViewById(R.id.listOrderDetails);
            bumpOrder = itemView.findViewById(R.id.btnBump);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {
        model = orderList.get(holder.getAdapterPosition());
        if(holder instanceof KitchenOrdersAdapter.ViewHolder){


            ((ViewHolder) holder)
                    .roomName
                    .setText(
                            String.format("Room # : %s", model.getRoomName())
                    );

            ((ViewHolder) holder)
                    .serviceChallengeTime
                    .setText(
                            String.format("SC time: %s", model.getServiceChallengeTime())
                    );

            ((ViewHolder) holder)
                    .runningTime
                    .setText(
                            String.format("Running time: %s", model.getRunningTime())
                    );

            ((ViewHolder)holder)
                    .dateTime
                    .setText(
                            String.format("Time ordered: %s", model.getDateTimeOrdered())
                    );

            ((ViewHolder)holder)
                    .notes
                    .setText(
                            String.format("Notes: \n%s", model.getNotes())
                    );

            ((ViewHolder)holder)
                    .cashierName
                    .setText(
                            String.format("Cashier: \n%s", model.getCashierName())
                    );


            final OrderDetailsAction orderDetailsAction = new OrderDetailsAction() {
                @Override
                public void clicked(OrderDetailsModel orderDetailsModel, int position) {

                }

                @Override
                public void longClicked(OrderDetailsModel orderDetailsModel, int position) {
                    orderListAction.itemLongClicked(orderDetailsModel);
                }
            };
            kitchenOrderDetailsAdapter =
                    new KitchenOrderDetailsAdapter(model.getOrderDetailsList(), orderDetailsAction,
                            context);
            ((ViewHolder)holder)
                    .listOrderDetails
                    .setLayoutManager(new LinearLayoutManager(context));
            ((ViewHolder)holder)
                    .listOrderDetails
                    .setAdapter(kitchenOrderDetailsAdapter);
            ((ViewHolder)holder)
                    .listOrderDetails
                    .setRecycledViewPool(recycledViewPool);
            kitchenOrderDetailsAdapter.notifyDataSetChanged();
            ((ViewHolder)holder)
                    .bumpOrder
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            orderListAction.bumpClicked(holder.getAdapterPosition());
                        }
                    });



        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
