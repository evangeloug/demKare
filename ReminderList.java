package com.example.reminders;

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
 * <p>
 * It shows reminders as list with the opportunity to edit, delete or insert new items on the list.
 */
public class ReminderList extends AppCompatActivity {

    private LinearLayout layout;
    private static final int ADD_REMINDER = 1004;
    private static final int EDIT_REMINDER = 1005;
    private View SelectedForEdit;

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

        layout = (LinearLayout) findViewById(R.id.linear);
        for (int i = 0; i < 2; i++) {
            View reminder = getLayoutInflater().inflate(R.layout.activity_reminder_row, null, false);

            TextView title = (TextView) reminder.findViewById(R.id.textView2);
            title.setText("Reminder" + (i + 1) + ", 13/12/1943" + ", 05:35");
            FloatingActionButton del = (FloatingActionButton) reminder.findViewById(R.id.floatingActionButton2);
            FloatingActionButton edit = (FloatingActionButton) reminder.findViewById(R.id.floatingActionButton3);
            VideoView vid = (VideoView) reminder.findViewById(R.id.videoView3);
            MediaController med = new MediaController(this);
            vid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setBackground(null);
                    vid.setMediaController(med);
                    med.setAnchorView(vid);
                    vid.setVideoURI(Uri.parse("android.resource://" + getApplicationInfo().packageName + "/" + R.raw.video));
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
                    editReminder(reminder);
//                    remove_Reminder(reminder);
                    SelectedForEdit = reminder;
                }
            });

            layout.addView(reminder);
        }
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
    public void editReminder(View v) {
        TextView title = v.findViewById(R.id.textView2);
        VideoView video = v.findViewById(R.id.videoView3);

        Intent sendIntent = new Intent(this, EditReminder.class);
        sendIntent.putExtra("name", title.getText().toString());
        sendIntent.putExtra("video", "android.resource://" + getApplicationInfo().packageName + "/" + R.raw.video);

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
                        editReminder(v);
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
                        v.setBackground(null);
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