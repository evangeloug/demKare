package com.example.demKare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img =  (ImageView) findViewById(R.id.appIcon);
        int appImg = getResources().getIdentifier("@drawable/ic_launcher_foreground", null, getPackageName());
        img.setImageDrawable(getResources().getDrawable(appImg));

        TextView mandText = (TextView) findViewById(R.id.accountMandatoryText4);
        mandText.setEnabled(false);
        //getSupportActionBar().hide();
    }

    public void signupmethod(View view) {
        TextView test1 = (TextView)findViewById(R.id.username_enter);
        String testA = test1.getText().toString();
        Intent intent = new Intent(this, Sign_up.class);
        //finish();
        startActivity(intent);
    }

    public void sign_inmethod(View view) {

        TextView test1 = (TextView)findViewById(R.id.username_enter);
        String testA = test1.getText().toString();
        if(testA.equals("caregiver")){
            Intent intent = new Intent(this, PatientList.class);
           //finish();
           startActivity(intent);
        }else{
                Intent intent = new Intent(this, MainMenu.class);
                //finish();
                startActivity(intent);
        }
    }
}
