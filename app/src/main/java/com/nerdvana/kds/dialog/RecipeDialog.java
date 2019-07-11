package com.nerdvana.kds.dialog;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nerdvana.kds.R;
import com.nerdvana.kds.bases.BaseDialog;
import com.nerdvana.kds.custom.ImageLoader;
import com.nerdvana.kds.model.OrderDetailsModel;
import com.nerdvana.kds.model.OrderListModel;
import com.nerdvana.kds.viewmodel.OrdersViewModel;

import java.util.List;

public class RecipeDialog extends BaseDialog implements View.OnClickListener {

    private OrderDetailsModel orderDetailsModel;

    private TextView itemName;

    private ImageView image;

    private TextView itemRecipe;

    private ImageView playVideo;

    private PlayVideoDialog playVideoDialog;

    public RecipeDialog(@NonNull Context context, OrderDetailsModel orderDetailsModel) {
        super(context);
        this.orderDetailsModel = orderDetailsModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDialogLayout(R.layout.dialog_recipe, "Recipe", false);
        initializeViews();

        itemName.setText(orderDetailsModel.getName());
        ImageLoader.load(getContext(), orderDetailsModel.getImageUrl(),
                image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itemRecipe.setText(Html.fromHtml("Ingredients<br><ul><li>Bawang</li><li>Sibuyas</li></ul> <p>1.First put the oil</p><br><p>2. Serve while hot!</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            itemRecipe.setText(Html.fromHtml("Ingredients<br><ul><li>Bawang</li><li>Sibuyas</li></ul> <p>1.First put the oil</p><br><p>2. Serve while hot!</p>"));
        }
    }

    private void initializeViews() {
        itemName = findViewById(R.id.itemName);

        image = findViewById(R.id.image);

        itemRecipe = findViewById(R.id.itemRecipe);

        playVideo = findViewById(R.id.playVideo);
        playVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playVideo:

                if (playVideoDialog == null) {

                    playVideoDialog = new PlayVideoDialog(getContext(), orderDetailsModel.getVideoUrl());

                    playVideoDialog.setOnDismissListener(new OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            playVideoDialog = null;
                        }
                    });

                    playVideoDialog.setOnCancelListener(new OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            playVideoDialog = null;
                        }
                    });

                    playVideoDialog.show();
                }



                break;
        }
    }
}
