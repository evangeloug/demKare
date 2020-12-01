package com.example.demKare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * A class for account information editing.
 *
 * It contains fields to be filled from user in order to change his/her account details.
 *
 * @author Giorgos Evangelou
 * @since 22/11/2020
 */
public class EditAccount extends AppCompatActivity {

    private EditText email, phone, username, password, passwordVerification;
    private EditText nameSurname, emailLabel, phoneLabel, usernameLabel, passwordLabel, passwordVerificationLabel;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        //text boxes
        email = (EditText) findViewById(R.id.emailText);
        phone = (EditText) findViewById(R.id.phoneText);
        username = (EditText) findViewById(R.id.usernameText);
        password = (EditText) findViewById(R.id.passwordText);
        passwordVerification = (EditText) findViewById(R.id.passwordVerificationText);
        nameSurname = (EditText) findViewById(R.id.nameSurnameText);
        //nameSurname.setEnabled(false);

        //labels
        emailLabel = (EditText) findViewById(R.id.emailLabel);
        phoneLabel = (EditText) findViewById(R.id.phoneLabel);
        usernameLabel = (EditText) findViewById(R.id.usernameLabel);
        passwordLabel = (EditText) findViewById(R.id.passwordLabel);
        passwordVerificationLabel = (EditText) findViewById(R.id.passwordVerificationLabel);
        //set all labels as non editable
        emailLabel.setEnabled(false);
        phoneLabel.setEnabled(false);
        usernameLabel.setEnabled(false);
        passwordLabel.setEnabled(false);
        passwordVerificationLabel.setEnabled(false);

        save = (Button) findViewById(R.id.saveAccountInfo);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });

        assignInformation();
    }

    /**
     * Sets text views values according to the profile which is going to be edited
     */
    private void assignInformation(){
        Intent intent = getIntent();
        nameSurname.setText("Hello "+intent.getStringExtra("fullname")+"!");
        email.setText(intent.getStringExtra("email"));
        phone.setText(intent.getStringExtra("phone"));
        username.setText(intent.getStringExtra("username"));
        password.setText(intent.getStringExtra("password"));
        passwordVerification.setText(intent.getStringExtra("password"));
    }

    /**
     * Saves changed information and returns it to caller.
     */
    private void saveInfo() {
        if (okConstraints()) {
            String emailS = email.getText().toString();
            String phoneS = phone.getText().toString();
            String usernameS = username.getText().toString();
            String passwordS = password.getText().toString();

            //make a bundle for objects
            Bundle bundle = new Bundle();
            bundle.putSerializable("email", emailS);
            bundle.putSerializable("phone", phoneS);
            bundle.putSerializable("username", usernameS);
            bundle.putSerializable("password", passwordS);

            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     * Checks for violations.
     *
     * Checks if ant violations were held. If violations exist a call for a error message popup
     * method occur to display all the violation messages. At the end, return true if no violations
     * were held.
     * Violations: if any of the input fields is empty, or if phone doesn't contain only numbers
     * or if password and its verification don't match.
     *
     * @return true if no violations
     */
    private boolean okConstraints() {
        boolean emailEmpty = email.getText() == null || email.getText().length() == 0;
        boolean phoneEmpty = phone.getText() == null || phone.getText().length() == 0;
        boolean usernameEmpty = username.getText() == null || username.getText().length() == 0;
        boolean passwordEmpty = password.getText() == null || password.getText().length() == 0;
        boolean passwordVerificationEmpty = passwordVerification.getText() == null || passwordVerification.getText().length() == 0;
        boolean isPhoneNum = true;
        if (!phoneEmpty)
            isPhoneNum = phone.getText().toString().matches("[0-9]+");
        boolean passMatch = true;
        if (!passwordEmpty && !passwordVerificationEmpty)
            passMatch = password.getText().toString().equals(passwordVerification.getText().toString());

        String violations="";
        if (emailEmpty) violations+="Email field must be filled!\n";
        if (phoneEmpty) violations+="Phone field must be filled!\n";
        if (!isPhoneNum) violations+="Phone field must contain only numbers!\n";
        if (usernameEmpty) violations+="Username field must be filled!\n";
        if (passwordEmpty) violations+="Password field must be filled!\n";
        if (passwordVerificationEmpty) violations+="Password Verification field must be filled!\n";
        if (!passMatch) violations+="Password and Verification fields do not match!";

        boolean noneViolations = !emailEmpty && !phoneEmpty && !usernameEmpty && !passwordEmpty &&
                !passwordVerificationEmpty && isPhoneNum && passMatch;
        createPopupError(noneViolations,violations);
        return noneViolations;
    }

    /**
     * Creates a popup alert box if any violation of input was held.
     *
     * Violations: if any of the input fields is empty, or if phone doesn't contain only numbers
     * or if password and its verification don't match.
     *
     * @param noneViolations if none violations exist
     * @param violations errors-violations text to be displayed
     */
    private void createPopupError(boolean noneViolations, String violations){
        if(!noneViolations) {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditAccount.this);

            builder.setTitle("Input Error");
            builder.setMessage(violations);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
    }
}