package com.example.demKare;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Class for people to remember list
 */
public class Contacts extends AppCompatActivity {

    private static final int CHOOSE_EDIT = 0;
    private static final int CHOOSE_INSERT = 1;
    private static final int GET_PICTURE = 5;
    private static final int GET_PERMISSION = 6;

    private FloatingActionButton insertButton;

    private View editingEntry;

    private LinearLayout peopleLinear;
    private ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        editingEntry = null;

        peopleLinear = (LinearLayout) findViewById(R.id.peopleCLinear);

        // inserting people for testing
        addPerson("Giorgos","Andreou","Friend", "77777777",null,"@drawable/person");

        insertButton = (FloatingActionButton) findViewById(R.id.peopleCInsertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPerson();
            }
        });
    }

    /**
     * Updates a person entry in list
     *
     * @param name name for person
     * @param surname surname for person
     * @param relationship relationship for person
     * @param phoneNum phone number for person
     * @param editingEntry current entry being updated
     *
     */
    private void updatePerson(String name, String surname, String relationship, String phoneNum, View editingEntry) {
        TextView fullName = (TextView) editingEntry.findViewById(R.id.PersonName);
        fullName.setText(name + " " + surname);

        TextView relation = (TextView) editingEntry.findViewById(R.id.PersonRelationship);
        relation.setText(relationship);

        TextView phone = (TextView) editingEntry.findViewById(R.id.PersonPhone);
        phone.setText(phoneNum);
    }

    /**
     * Inserts a person entry in list
     *
     * Person entry has title with name and surname, relationship, and image
     *
     * @param name name for person
     * @param surname surname for person
     * @param relationship relationship for person
     * @param phoneNum phone number for person
     * @param image image for person
     * @param imagePath image path for image selected
     */
    public void addPerson(String name, String surname, String relationship, String phoneNum, Bitmap image, String imagePath) {
        View person = getLayoutInflater().inflate(R.layout.activity_contact_list_row,null,false);

        // Add info to entry
        TextView fullName = (TextView) person.findViewById(R.id.PersonName);
        fullName.setText(name + " " + surname);

        TextView relation = (TextView) person.findViewById(R.id.PersonRelationship);
        relation.setText(relationship);

        TextView phone = (TextView) person.findViewById(R.id.PersonPhone);
        phone.setText(phoneNum);

        ImageView picture = (ImageView) person.findViewById(R.id.PersonImage);
        setPicture(picture, image, imagePath);

        // Generate buttons for entry
        FloatingActionButton edit = (FloatingActionButton) person.findViewById(R.id.PersonEditButton);
        FloatingActionButton delete = (FloatingActionButton) person.findViewById(R.id.PersonDeleteButton);
        FloatingActionButton call = (FloatingActionButton) person.findViewById(R.id.PersonCallButton);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editingEntry = person;
                editPerson(person);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePerson(person);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, null);
                i.setData(Uri.parse("tel:" + phoneNum));
                startActivity(i);
            }
        });

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImage = picture;
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

        peopleLinear.addView(person);
    }

    /**
     * Changes person entry's picture
     *
     * @param view  imageView for person's picture
     * @param picture bitmap picture
     * @param path path of picture
     */
    private void setPicture(ImageView view, Bitmap picture, String path){
        // Sent bitmap
        if (picture != null) {
            view.setImageBitmap(picture);
        }
        // Sent path
        else if (path != null) {
            int img = getResources().getIdentifier(path, null, getPackageName());
            view.setImageDrawable(getResources().getDrawable(img));
        }
    }

    /**
     * Move to editing  view
     *
     * @param p view with current entry info
     */
    private void editPerson(View p) {
        TextView fullName = (TextView) p.findViewById(R.id.PersonName);
        String[] fullNameSplit = fullName.getText().toString().split(" ");

        TextView relationship = (TextView) p.findViewById(R.id.PersonRelationship);
        TextView number = (TextView) p.findViewById(R.id.PersonPhone);

        Intent intent = new Intent(Contacts.this, EditContacts.class);
        intent.putExtra("name", fullNameSplit[0]);
        intent.putExtra("surname", fullNameSplit[1]);
        intent.putExtra("relationship", relationship.getText());
        intent.putExtra("number", number.getText());

        startActivityForResult(intent, CHOOSE_EDIT);
    }

    /**
     * Open edit activity to insert person
     */
    private void insertPerson() {
        Intent intent = new Intent(Contacts.this, EditContacts.class);
        startActivityForResult(intent, CHOOSE_INSERT);
    }

    /**
     * Removes person from list
     *
     * @param p person to be deleted
     */
    private void removePerson(View p){
        peopleLinear.removeView(p);
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
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        String name = null;
        String surname = null;
        String relationship = null;
        String number = null;
        Bitmap picture = null;

        if (resultCode == RESULT_OK) {
            if (requestCode == GET_PICTURE)
                selectedImage.setImageURI(data.getData());
            else {
                name = (String) data.getExtras().getSerializable("name");
                surname = (String) data.getExtras().getSerializable("surname");
                relationship = (String) data.getExtras().getSerializable("relationship");
                number = (String) data.getExtras().getSerializable("number");
                picture = (Bitmap) data.getExtras().getParcelable("image");

                // Inserting person
                if (requestCode == CHOOSE_INSERT)
                    addPerson(name, surname, relationship, number, picture, null);
                    // Editing person
                else if (requestCode == CHOOSE_EDIT)
                    updatePerson(name, surname, relationship, number, editingEntry);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra("requestCode", requestCode);
        super.startActivityForResult(intent, requestCode);
    }

}