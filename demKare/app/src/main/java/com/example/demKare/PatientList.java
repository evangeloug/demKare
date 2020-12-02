package com.example.demKare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PatientList extends AppCompatActivity {

    int LAUNCH_SECOND_ACTIVITY =1;
    public static ArrayList<String> patients = new ArrayList<String>();
    public static ArrayList<String> phone = new ArrayList<String>();
    boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        patients.add("blank");
        patients.add("alois2");
        patients.add("cpro");

        phone.add("blank");
        phone.add("95254867");
        phone.add("95785367");
    }

    public void logOut(View view){

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_logout, null);



        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        popupWindow.showAtLocation(view, Gravity.CENTER,  0, 0);



    }

    public void logOutConfirm(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }



    public void addPatients(View view) {

        LAUNCH_SECOND_ACTIVITY = 1;
        Intent i = new Intent(this, EditPatientList.class);

        startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);

    }
    @Override
    public void onResume(){
        super.onResume();

        LinearLayout linear = (LinearLayout) findViewById(R.id.patientListLinear);
        linear.removeAllViews();
        for(int i = 0; i< PatientList.patients.size(); i++) {

            final int c=i;
            final View view = getLayoutInflater().inflate(R.layout.patient_list_row, null);
            String displayname = "";
            if(PatientList.patients.get(i).equals("george01")) {
                displayname = "Giorgos Mixail";
                ((FloatingActionButton) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_DIAL, null);
                        i.setData(Uri.parse("tel:" + phone.get(c)));
                        startActivity(i);
                    }
                });
            }
            else if(PatientList.patients.get(i).equals("petros33")) {
                displayname = "Petros Georgiou";
                ((FloatingActionButton) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_DIAL, null);
                        i.setData(Uri.parse("tel:" + phone.get(c)));
                        startActivity(i);
                    }
                });
            }
            else if(PatientList.patients.get(i).equals("kostas88")) {
                displayname = "Konstantinos Kyriakou";
                ((FloatingActionButton) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_DIAL, null);
                        i.setData(Uri.parse("tel:" + phone.get(c)));
                        startActivity(i);
                    }
                });
            }
            else if(PatientList.patients.get(i).equals("alois2")) {
                displayname = "Andreas Loizou";
                ((FloatingActionButton) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_DIAL, null);
                        i.setData(Uri.parse("tel:" + phone.get(c)));
                        startActivity(i);
                    }
                });
            }
            else if(PatientList.patients.get(i).equals("cpro")) {
                displayname = "Constantinos Prokopiou";
                ((FloatingActionButton) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_DIAL, null);
                        i.setData(Uri.parse("tel:" + phone.get(c)));
                        startActivity(i);
                    }
                });
            }
            else {
                displayname = "Marios Petrou";
                ((FloatingActionButton) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_DIAL, null);
                        i.setData(Uri.parse("tel:" + phone.get(c)));
                        startActivity(i);
                    }
                });
            }
            ((TextView) view.findViewById(R.id.patientNameList)).setText(displayname);
            ((TextView) view.findViewById(R.id.patientNameList)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PatientList.this, MainMenu.class);
                    startActivity(intent);
                }
            });

            if(PatientList.patients.get(i).equals("blank"));
            else
                linear.addView(view);
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                String name = data.getStringExtra("name");
                patients.add(name);
                 if(name.equals("george01"))
                    phone.add("96486523");
                else if(name.equals("petros33")){
                    phone.add("95816658");
                }
                else if(name.equals("kostas88")){
                    phone.add("99425123");
                }
                else if(name.equals("alois2")){
                    phone.add("97625033");
                }
                else if(name.equals("cpro")){
                    phone.add("99563154");
                }
                else
                    phone.add("99856231");


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

}
