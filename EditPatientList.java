

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
            ((FloatingActionButton) view.findViewById(R.id.patientDeleteButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.setVisibility(View.GONE);
                    PatientList.patients.remove(finalI);
                    PatientList.phone.remove(finalI);
                }
            });

            final int finalI1 = i;
            ((FloatingActionButton) view.findViewById(R.id.patientEditButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(PatientList.patients.get(finalI1).equals("george01")) {
                        Intent intent = new Intent(view.getContext(), EditAccount.class);
                        intent.putExtra("fullname", "Giorgos Mixail");
                        intent.putExtra("email", "george01@gmail.com");
                        intent.putExtra("phone", PatientList.phone.get(finalI1));
                        intent.putExtra("username", "george01");
                        intent.putExtra("password", "dafdSA3f");
                        intent.putExtra("index", finalI1);

                        startActivityForResult(intent, 99);
                    }

                    if(PatientList.patients.get(finalI1).equals("petros33")) {
                        Intent intent = new Intent(view.getContext(), EditAccount.class);
                        intent.putExtra("fullname", "Petros Georgiou");
                        intent.putExtra("email", "petros33@gmail.com");
                        intent.putExtra("phone", PatientList.phone.get(finalI1));
                        intent.putExtra("username", "petros33");
                        intent.putExtra("password", "F32gDadasd");
                        intent.putExtra("index", finalI1);

                        startActivityForResult(intent, 99);
                    }

                    if(PatientList.patients.get(finalI1).equals("kostas88")) {
                        Intent intent = new Intent(view.getContext(), EditAccount.class);
                        intent.putExtra("fullname", "Konstantinos Kyriakou");
                        intent.putExtra("email", "kostas88@gmail.com");
                        intent.putExtra("phone", PatientList.phone.get(finalI1));
                        intent.putExtra("username", "kostas88");
                        intent.putExtra("password", "£FASsd231");
                        intent.putExtra("index", finalI1);

                        startActivityForResult(intent, 99);
                    }


                    if(PatientList.patients.get(finalI1).equals("alois2")) {
                        Intent intent = new Intent(view.getContext(), EditAccount.class);
                        intent.putExtra("fullname", "Andreas Loizou");
                        intent.putExtra("email", "alois2@gmail.com");
                        intent.putExtra("phone", PatientList.phone.get(finalI1));
                        intent.putExtra("username", "alois2");
                        intent.putExtra("password", "FSavsdfe2");
                        intent.putExtra("index", finalI1);

                        startActivityForResult(intent, 99);
                    }


                    if(PatientList.patients.get(finalI1).equals("cpro")) {
                        Intent intent = new Intent(view.getContext(), EditAccount.class);
                        intent.putExtra("fullname", "Constantinos Prokopiou");
                        intent.putExtra("email", "cpro@gmail.com");
                        intent.putExtra("phone", PatientList.phone.get(finalI1));
                        intent.putExtra("username", "cpro");
                        intent.putExtra("password", "2g45241");
                        intent.putExtra("index", finalI1);
                        startActivityForResult(intent, 99);

                        startActivityForResult(intent, 99);
                    }


                    else {
                        String email = PatientList.patients.get(finalI1) + "gmail.com";
                        Intent intent = new Intent(view.getContext(), EditAccount.class);
                        intent.putExtra("fullname", "Marios Petrou");
                        intent.putExtra("email", email);
                        intent.putExtra("phone", PatientList.phone.get(finalI1));
                        intent.putExtra("username", PatientList.patients.get(finalI1));
                        intent.putExtra("password", "213fsSfdD");
                        intent.putExtra("index", finalI1);


                        startActivityForResult(intent, 99);
                    }
                }
            });


            if(PatientList.patients.get(i).equals("blank"));
            else
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

}
