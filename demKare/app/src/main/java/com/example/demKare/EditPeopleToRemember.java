package com.example.demKare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Editing and inserting people class
 */
public class EditPeopleToRemember extends AppCompatActivity {

    private Button saveButton, setPicButton;
    private TextView nameText, surnameText, relationshipText;

    private static final int CHOOSE_EDIT = 0;
    private static final int GET_PICTURE = 5;
    private static final int GET_PERMISSION = 6;

    private Uri imagePath;
    private ImageView personImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load layout
        setContentView(R.layout.activity_people_list_edit);

        // Choose viewables
        personImgView = (ImageView) findViewById(R.id.rememberedPersonImageEdit);
        nameText = (TextView) findViewById(R.id.rememberedPersonEditName);
        surnameText = (TextView) findViewById(R.id.rememberedPersonEditSurname);
        relationshipText = (TextView) findViewById(R.id.rememberedPersonEditRelationship);
        saveButton = (Button) findViewById(R.id.rememberedPersonSaveButton);
        setPicButton = (Button) findViewById(R.id.rememberedPersonSetPicButton);

        // Action for save
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePerson();
            }
        });

        // Action for set
        setPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // PERMISSION DENIED NEED TO GET PERMISSIONS
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, GET_PERMISSION);
                    }
                }
                getImageFromGallery();
            }
        });

        // Set an example default image
        int defaultImage = getResources().getIdentifier("@drawable/purple", null, getPackageName());
        personImgView.setImageDrawable(getResources().getDrawable(defaultImage));

        // Setting the editing view if user is editing
        Intent intent = getIntent();
        if (intent.getIntExtra("requestCode",-1) == CHOOSE_EDIT) {
            nameText.setText(intent.getStringExtra("name"));
            surnameText.setText(intent.getStringExtra("surname"));
            relationshipText.setText(intent.getStringExtra("relationship"));
            personImgView.setVisibility(View.INVISIBLE);
            setPicButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Saves person to list
     */
    private void savePerson() {
        boolean errorName = nameText.getText() == null || nameText.getText().length() == 0;
        boolean errorSurname = surnameText.getText() == null || surnameText.getText().length() == 0;
        boolean errorRelationship = relationshipText.getText() == null || relationshipText.getText().length() == 0;

        if (errorName || errorSurname || errorRelationship) {
            Toast.makeText(EditPeopleToRemember.this, "Please fill all mandatory fields", Toast.LENGTH_SHORT).show();
        }
        // Can only save if there are no errors
        else {
            // Bundling up results to send to list activity
            Bundle bundle = new Bundle();
            bundle.putSerializable("name", nameText.getText().toString());
            bundle.putSerializable("surname", surnameText.getText().toString());
            bundle.putSerializable("relationship", relationshipText.getText().toString());
            personImgView.buildDrawingCache();
            bundle.putParcelable("image", personImgView.getDrawingCache());

            // Return bundle
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     * Starts gallery to get image
     */
    private void getImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GET_PICTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == GET_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                getImageFromGallery();
        }
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Set image
        if (resultCode == RESULT_OK && requestCode== GET_PICTURE)
            personImgView.setImageURI(data.getData());
    }
}