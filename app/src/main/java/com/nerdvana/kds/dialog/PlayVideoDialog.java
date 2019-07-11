package com.nerdvana.kds.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.nerdvana.kds.R;
import com.nerdvana.kds.bases.BaseDialog;

public class PlayVideoDialog extends BaseDialog {

    private String videoUrl;

    private VideoView videoView;

    private boolean isVideoPlaying = true;

    private MediaController mediaController;

    private int currentPosition = 0;
    public PlayVideoDialog(@NonNull Context context, String videoUrl) {
        super(context);
        this.videoUrl = videoUrl;

        mediaController = new MediaController(getContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDialogLayout(R.layout.dialog_play_video, "VIDEO", false);

        initializeViews();
        videoView.setMediaController(mediaController);
        videoView.setVideoPath(videoUrl);

        videoView.start();

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVideoPlaying) {
                    currentPosition = videoView.getCurrentPosition();
                    isVideoPlaying = false;
                    videoView.pause();
                } else {
                    videoView.seekTo(currentPosition);
                    isVideoPlaying = true;
                    videoView.start();
                }

            }
        });

    }

    private void initializeViews() {
        videoView = findViewById(R.id.videoView);
    }
}
