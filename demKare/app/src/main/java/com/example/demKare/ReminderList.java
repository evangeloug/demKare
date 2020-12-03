package com.example.demKare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * This class is for reminders.
 *
 * It shows reminders as list with the opportunity to edit, delete or insert new items on the list.
 */
public class ReminderList extends AppCompatActivity {

    private LinearLayout layout;
    private static final int ADD_REMINDER = 1004;
    private static final int EDIT_REMINDER = 1005;
    private View SelectedForEdit;
    private int video_nuumber;

    /**
     * This method creates the reminder list based on xml.
     *
     * @param savedInstanceState the bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminder();
            }
        });
        newReminder("Doctor appointment, General, 10/12/2020, 09:30",1);
        newReminder("Exercises, Daily, 04/12/2020, 07:20",2);
    }

    public void newReminder(String rem, int video){
        layout = (LinearLayout) findViewById(R.id.linear);

        View reminder = getLayoutInflater().inflate(R.layout.activity_reminder_row, null, false);

        TextView title = (TextView) reminder.findViewById(R.id.textView2);
        title.setText(rem);
        FloatingActionButton del = (FloatingActionButton) reminder.findViewById(R.id.floatingActionButton2);
        FloatingActionButton edit = (FloatingActionButton) reminder.findViewById(R.id.floatingActionButton3);
        VideoView vid = (VideoView) reminder.findViewById(R.id.videoView3);
        MediaController med = new MediaController(this);
        vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vid.setMediaController(med);
                med.setAnchorView(vid);
                if(video==1)
                vid.setVideoURI(Uri.parse("android.resource://" + getApplicationInfo().packageName + "/"+R.raw.doctor));
                else if (video==2)
                vid.setVideoURI(Uri.parse("android.resource://" + getApplicationInfo().packageName + "/"+R.raw.dementia));
                if (vid.isPlaying())
                    vid.start();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove_Reminder(reminder);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editReminder(reminder,video);
                SelectedForEdit = reminder;
                video_nuumber=video;
            }
        });
        layout.addView(reminder);
    }
    /**
     * Adds an item in reminders.
     * <p>
     * Adds an item based on user input, which was given from edit reminder activity.
     * Assigns fields with corresponding input from user (for the video chooseFromGallery method is called)
     */
    public void addReminder() {
        Intent sendIntent = new Intent(this, EditReminder.class);
        startActivityForResult(sendIntent, ADD_REMINDER);
        onActivityResult(ADD_REMINDER, 1, sendIntent);
    }

    /**
     * Adds an item in reminders.
     * <p>
     * Edits an item based on user input, which was given from edit reminder activity.
     * Assigns fields with corresponding input from user (for the video chooseFromGallery method is called).
     *
     * @param v the reminder
     */
    public void editReminder(View v,int video_name) {
        TextView title = v.findViewById(R.id.textView2);
        VideoView video = v.findViewById(R.id.videoView3);

        Intent sendIntent = new Intent(this, EditReminder.class);
        sendIntent.putExtra("name", title.getText().toString());
        if(video_name==1)
            video.setVideoURI(Uri.parse("android.resource://" + getApplicationInfo().packageName + "/"+R.raw.doctor));
        else if (video_name==2)
            video.setVideoURI(Uri.parse("android.resource://" + getApplicationInfo().packageName + "/"+R.raw.dementia));

        sendIntent.putExtra("requestCode", EDIT_REMINDER);
        startActivityForResult(sendIntent, EDIT_REMINDER);
        onActivityResult(EDIT_REMINDER, 1, sendIntent);
    }

    /**
     * Removes an item from reminders.
     *
     * @param v the reminder
     */
    public void remove_Reminder(View v) {
        layout.removeView(v);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        View reminder = getLayoutInflater().inflate(R.layout.activity_reminder_row, null, false);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADD_REMINDER) {
                String name = data.getStringExtra("name");
                String video_path = data.getStringExtra("video");
                TextView title = (TextView) reminder.findViewById(R.id.textView2);
                VideoView vid = (VideoView) reminder.findViewById(R.id.videoView3);
                title.setText(name);
                MediaController med = new MediaController(this);
                vid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setBackground(null);
                        med.setAnchorView(vid);
                        vid.setVideoURI(Uri.parse(video_path));
                        vid.setMediaController(med);
                        if (vid.isPlaying())
                            vid.start();
                    }
                });
                FloatingActionButton del = (FloatingActionButton) reminder.findViewById(R.id.floatingActionButton2);
                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        remove_Reminder(reminder);
                    }
                });
                FloatingActionButton edit = (FloatingActionButton) reminder.findViewById(R.id.floatingActionButton3);
                AppCompatActivity app = this;
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editReminder(v,video_nuumber);
                        SelectedForEdit=reminder;
                    }
                });
                layout.addView(reminder);
            }
            if (requestCode == EDIT_REMINDER) {
                String name = data.getStringExtra("name");
                String video_path = data.getStringExtra("video");
                TextView title = (TextView) SelectedForEdit.findViewById(R.id.textView2);
                VideoView vid = (VideoView) SelectedForEdit.findViewById(R.id.videoView3);
                title.setText(name);
                MediaController med = new MediaController(this);
                vid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        med.setAnchorView(vid);
                        vid.setVideoURI(Uri.parse(video_path));
                        vid.setMediaController(med);
                        if (vid.isPlaying())
                            vid.start();
                    }
                });
            }
        }
    }//onActivityResult
}