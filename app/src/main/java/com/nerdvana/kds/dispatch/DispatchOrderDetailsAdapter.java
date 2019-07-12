package com.nerdvana.kds.dispatch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nerdvana.kds.R;
import com.nerdvana.kds.actions.OrderDetailsAction;
import com.nerdvana.kds.model.OrderDetailsModel;

import java.util.List;

public class DispatchOrderDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OrderDetailsModel> orderDetailsList;

    private OrderDetailsAction orderDetailsAction;

    private Context ctx;

    private SparseBooleanArray itemStateArray= new SparseBooleanArray();

    public DispatchOrderDetailsAdapter(List<OrderDetailsModel> orderDetailsList,
                                       OrderDetailsAction orderDetailsAction,
                                       Context context) {
        this.orderDetailsList = orderDetailsList;
        this.orderDetailsAction = orderDetailsAction;
        this.ctx = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DispatchOrderDetailsAdapter.ViewHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.list_item_order_details, viewGroup, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private TextView itemQty;
        private LinearLayout rootView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            itemQty = itemView.findViewById(R.id.itemQty);
            rootView = itemView.findViewById(R.id.rootView);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {
        final OrderDetailsModel model = orderDetailsList.get(i);
        if(holder instanceof DispatchOrderDetailsAdapter.ViewHolder){

            if (!model.isChecked()) {

                ((ViewHolder)holder)
                        .rootView
                        .setBackgroundColor(ctx.getResources().getColor(R.color.colorWhite));

            } else  {

                ((ViewHolder)holder)
                        .rootView
                        .setBackgroundColor(ctx.getResources().getColor(R.color.colorLtGrey));

            }


            ((ViewHolder) holder)
                    .itemName
                    .setText(
                            model.getName()
                    );

            ((ViewHolder) holder)
                    .itemQty
                    .setText(
                            model.getQty()
                    );

            ((ViewHolder)holder)
                    .rootView
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            model.setChecked(model.isChecked() ? false : true);
                            notifyDataSetChanged();

                        }
                    });

            ((ViewHolder)holder)
                    .rootView
                    .setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            orderDetailsAction.longClicked(model, holder.getAdapterPosition());
                            return true;
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }
}
