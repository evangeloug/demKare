package com.example.demKare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditPatientList extends AppCompatActivity {

    private EditText editName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient_list);

        editName = findViewById(R.id.editListName);
        TextView myAwesomeTextView = (TextView)findViewById(R.id.patientUserEdit);

        LinearLayout linear = (LinearLayout) findViewById(R.id.editPatientListLinear);
        for(int i = 0; i< PatientList.patients.size(); i++){

            final int finalI = i;

            final View view = getLayoutInflater().inflate(R.layout.patient_item, null);
            ((TextView) view.findViewById(R.id.patientUserEdit)).setText(PatientList.patients.get(i));
            ((Button) view.findViewById(R.id.patientDeleteButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.setVisibility(View.GONE);
                    PatientList.patients.remove(finalI);
                }
            });

            ((Button) view.findViewById(R.id.patientEditButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Edit Patient
                }
            });


            linear.addView(view);
        }

    }

    public void saveChanges(View view) {

        String name = editName.getText().toString();

        Intent returnIntent = new Intent();

        returnIntent.putExtra("name",name);
        if(name.matches("")){
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        }
        else {
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String name = data.getStringExtra("test");
        Log.v("sss",name);

    }


}
