package com.example.demKare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This class handles like list items.
 *
 * It basically offers the opportunity to create an item with given input from user.
 * Also, it offeres the opportunity to edit name, category and description fields.
 */
public class EditThingsItem extends AppCompatActivity {

    private Button saveButton, setPicButton;
    private TextView nameText, descriptionText;
    private Uri imagePath;
    private ImageView imageView;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int START_EDIT_ACTIVITY=0;
    private static final int START_INSERT_ACTIVITY=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_things_item);

        TextView mandText = (TextView) findViewById(R.id.accountMandatoryText3);
        mandText.setEnabled(false);
        saveButton = (Button) findViewById(R.id.saveThingsButton);
        setPicButton = (Button) findViewById(R.id.setThingsPicButton);
        nameText = (TextView) findViewById(R.id.nameThingsText);
        descriptionText = (TextView) findViewById(R.id.descriptionThingsText);
        imageView = (ImageView) findViewById(R.id.imageThingsView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditThingsItem.this,
                android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.likeListCategory)); //simple_list_item_1
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });
        setPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check permissions
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //permission not granted request it
                        String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //permission popup
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        //permission already granted
                        pickImageFromGallery();
                    }
                } else{
                    pickImageFromGallery();
                }
                pickImageFromGallery();
            }
        });

        //set image to default
        int imageResource = getResources().getIdentifier("@drawable/purple", null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);
        //check if we came for edit or insert
        handleEditOrInsert();
    }

    /**
     * Sets text views, spinner equal to values from the selected item, otherwise they are empty.
     */
    private void handleEditOrInsert(){
        Intent intent = getIntent();
        if (intent.getIntExtra("requestCode",-1)==START_EDIT_ACTIVITY){
            nameText.setText(intent.getStringExtra("name"));
            descriptionText.setText(intent.getStringExtra("description"));
            imageView.setVisibility(View.INVISIBLE);
            setPicButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Save input from user and returns it to parent activity to create a like list item
     */
    private void saveItem() {
        if (!constraintsViolation()) {
            boolean cameToEdit = getIntent().getIntExtra("requestCode",-1)==START_EDIT_ACTIVITY ;
            String name = nameText.getText().toString();
            String description = descriptionText.getText().toString();
            imageView.buildDrawingCache();
            Bitmap bitmap = imageView.getDrawingCache();

            //make a bundle for objects
            Bundle bundle = new Bundle();
            bundle.putSerializable("name", name);
            bundle.putSerializable("description", description);
            bundle.putParcelable("image", bitmap);
            bundle.putBoolean("cameToEdit", cameToEdit);

            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     * Checks if any constraints where violated.
     *
     * It checks if the name text view was left empty and creates a popup alert message to the user
     * to fill it.
     *
     * @return true if name was empty, otherwise false
     */
    private boolean constraintsViolation() {
        boolean isNameEmpty = nameText.getText() == null || nameText.getText().length() == 0;
        if (isNameEmpty)
            Toast.makeText(EditThingsItem.this, "Please fill all mandatory fields", Toast.LENGTH_SHORT).show();
        return isNameEmpty;
    }

    /**
     * Opens gallery to select an image (changes activity)
     */
    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    pickImageFromGallery(); // permission was granted
            }
        }
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode==RESULT_OK && requestCode== IMAGE_PICK_CODE) {
            //set image to image view
            imageView.setImageURI(data.getData());
        }
    }
}