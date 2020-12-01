package com.example.list;

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

import java.util.ArrayList;

public class PatientList extends AppCompatActivity {

    int LAUNCH_SECOND_ACTIVITY =1;
    public static ArrayList<String> patients = new ArrayList<String>();
    boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        patients.add("alois2");
        patients.add("cpro");
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


                final View view = getLayoutInflater().inflate(R.layout.patient_list_row, null);
                String displayname = "";
                if(PatientList.patients.get(i).equals("george01")) {
                    displayname = "Giorgos Mixail";
                    ((ImageView) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_DIAL, null);
                            i.setData(Uri.parse("tel:99854815"));
                            startActivity(i);
                        }
                    });
                }
                else if(PatientList.patients.get(i).equals("petros33")) {
                    displayname = "Petros Georgiou";
                    ((ImageView) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_DIAL, null);
                            i.setData(Uri.parse("tel:95254867"));
                            startActivity(i);
                        }
                    });
                }
                else if(PatientList.patients.get(i).equals("kostas88")) {
                    displayname = "Konstantinos Kyriakou";
                    ((ImageView) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_DIAL, null);
                            i.setData(Uri.parse("tel:99237467"));
                            startActivity(i);
                        }
                    });
                }
                else if(PatientList.patients.get(i).equals("alois2")) {
                    displayname = "Andreas Loizou";
                    ((ImageView) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_DIAL, null);
                            i.setData(Uri.parse("tel:95254867"));
                            startActivity(i);
                        }
                    });
                }
                else if(PatientList.patients.get(i).equals("cpro")) {
                    displayname = "Constantinos Prokopiou";
                    ((ImageView) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_DIAL, null);
                            i.setData(Uri.parse("tel:95254867"));
                            startActivity(i);
                        }
                    });
                }
                else {
                    displayname = "Marios Petrou";
                    ((ImageView) view.findViewById(R.id.callButtonCaregiver)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_DIAL, null);
                            i.setData(Uri.parse("tel:99356137"));
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


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    public void logOutCnfirm(View view) {
        //Log Out
    }
}
