package com.example.demKare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageView img =  (ImageView) findViewById(R.id.appIconSignUp);
        int appImg = getResources().getIdentifier("@drawable/ic_launcher_foreground", null, getPackageName());
        img.setImageDrawable(getResources().getDrawable(appImg));
        //getSupportActionBar().hide();
    }

    public void donemethod(View view) {

        TextView test1 = (TextView)findViewById(R.id.editTextTextPersonName);
        String testA = test1.getText().toString();

        TextView test2 = (TextView)findViewById(R.id.editTextTextPersonName2);
        String testB = test2.getText().toString();

        TextView test3 = (TextView)findViewById(R.id.editTextPhone);
        String testc = test3.getText().toString();

        TextView test4 = (TextView)findViewById(R.id.editTextTextPassword);
        String testd = test4.getText().toString();

        TextView test5 = (TextView)findViewById(R.id.editTextTextEmailAddress);
        String teste = test5.getText().toString();

        TextView test6 = (TextView)findViewById(R.id.editTextDate);
        String testf = test6.getText().toString();

        TextView test7 = (TextView)findViewById(R.id.username_signup);
        String testg = test7.getText().toString();

        if(testA.equals("") || testB.equals("")  || testc.equals("")|| testd.equals("")|| teste.equals("")|| testf.equals("")|| testg.equals("")){
            Toast.makeText(getApplicationContext(), "Please fill all fields",
                    Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Sign up successful. Signing in.",
                    Toast.LENGTH_SHORT).show();

            //finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
