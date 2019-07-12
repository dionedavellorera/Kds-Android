package com.nerdvana.kds.dispatch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nerdvana.kds.R;
import com.nerdvana.kds.model.UserModel;

import java.util.List;

public class RoomBoyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserModel> userList;
    private Context context;
    private UserModel model;

    public RoomBoyListAdapter(List<UserModel> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RoomBoyListAdapter.ViewHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.list_item_users, viewGroup, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView header;;
        private TextView subHeader;;

        private RecyclerView listOrderDetails;;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            header = itemView.findViewById(R.id.tvHeader);
            subHeader = itemView.findViewById(R.id.tvSubHeader);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {
        model = userList.get(holder.getAdapterPosition());
        if(holder instanceof RoomBoyListAdapter.ViewHolder){


            ((ViewHolder) holder)
                    .header
                    .setText(
                            String.format(model.getName())
                    );

            ((ViewHolder) holder)
                    .subHeader
                    .setText(
                            String.format(model.getRole())
                    );



        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
