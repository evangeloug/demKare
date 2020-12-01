package com.example.demKare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //getSupportActionBar().hide();
    }

    public void donemethod(View view) {
        Toast.makeText(getApplicationContext(), "sign up succcessful",
                Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}