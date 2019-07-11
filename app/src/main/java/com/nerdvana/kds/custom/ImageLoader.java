package com.nerdvana.kds.custom;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nerdvana.kds.R;

public class ImageLoader {

    public static void load(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.sample_image)
                .error(R.drawable.sample_image)
                .fallback(R.drawable.sample_image)
                .into(view);
    }
}
