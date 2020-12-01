package com.example.reminders;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_info);

        VideoView vid = (VideoView) findViewById(R.id.video_play);
        MediaController med = new MediaController(this);
        vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vid.setMediaController(med);
                med.setAnchorView(vid);
                    vid.setVideoURI(Uri.parse("android.resource://" + getApplicationInfo().packageName + "/"+R.raw.dementia));
                if (vid.isPlaying())
                    vid.start();
            }
        });
    }
}