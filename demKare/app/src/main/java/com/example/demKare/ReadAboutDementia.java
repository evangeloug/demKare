package com.example.demKare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReadAboutDementia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_about_dementia);

        TextView levels = (TextView) findViewById(R.id.textView5);
        levels.setMovementMethod(new ScrollingMovementMethod());

    }
}