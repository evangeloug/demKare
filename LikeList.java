package com.example.demKare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labtest.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * This class is for like list.
 *
 * It shows like list items with the opportunity to edit, delete or insert new items on the list.
 */
public class LikeList extends AppCompatActivity {

    private static final int START_EDIT_ACTIVITY=0;
    private static final int START_INSERT_ACTIVITY=1;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private FloatingActionButton insertButton;
    private LinearLayout linearVerticalLayout;
    private boolean isInsertPressed;
    private View lastViewPressed;
    private ImageView lastImagePressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_list);

        isInsertPressed = false;
        lastViewPressed = null;
        insertButton = (FloatingActionButton) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInsertPressed = true;
                insertInLikeList();
            }
        });
        //layout for different items
        linearVerticalLayout = (LinearLayout) findViewById(R.id.linearVerticalLayout);

        //hardcode 2 items
        addLikedItem("Sheftalies 1","Food","des",null,"@drawable/sheftalies");
        addLikedItem("Ferrari","Cars","This is a ferrari car!",null, "@drawable/ferrari");
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK && requestCode== IMAGE_PICK_CODE) {
            //set image to image view
            lastImagePressed.setImageURI(data.getData());
        } else {
            String name = null;
            String category = null;
            String description = null;
            Bitmap bitmap = null;
            boolean cameToEdit;
            if (resultCode == RESULT_OK) {
                name = (String) data.getExtras().getSerializable("name");
                category = (String) data.getExtras().getSerializable("category");
                description = (String) data.getExtras().getSerializable("description");
                bitmap = (Bitmap) data.getExtras().getParcelable("image");
                cameToEdit = data.getExtras().getBoolean("cameToEdit");
                if (requestCode == START_EDIT_ACTIVITY) {
                    editItem(name, category, description, null);

                } else if (requestCode == START_INSERT_ACTIVITY) {
                    addLikedItem(name, category, description, bitmap, null);
                }
            }
            isInsertPressed = false;
            lastViewPressed = null;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra("requestCode", requestCode);
        super.startActivityForResult(intent, requestCode);
    }

    /**
     * Adds an item in like list.
     *
     * Edits an item based on user input, which was given from edit like list item activity.
     * Assigns fields with corresponding input from user (for the image setPicture method is called)
     *
     * @param titleText text for name text view
     * @param categoryText text for category text view
     * @param descriptionText text for description popup
     * @param picture image to be displayed for this item
     */
    private void editItem(String titleText, String categoryText, String descriptionText, Bitmap picture) {
        TextView title = (TextView) lastViewPressed.findViewById(R.id.likeItemName);
        title.setText(titleText);
        TextView category = (TextView) lastViewPressed.findViewById(R.id.likeItemCategoryText);
        category.setText(categoryText);
        ImageView image = (ImageView) lastViewPressed.findViewById(R.id.likeItemImage);
        setPicture(image, picture, null);
        Button description = (Button) lastViewPressed.findViewById(R.id.likeItemDescriptionButton);
        description.setContentDescription(descriptionText);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDescriptionPopup(description.getContentDescription().toString());
            }
        });
    }

    /**
     * Adds an item in like list.
     *
     * Create a new item with user input, which was given from edit like list item activity.
     * Assigns fields with corresponding input from user (for the image setPicture method is called)
     *
     * @param titleText text for name text view
     * @param categoryText text for category text view
     * @param descriptionText text for description popup
     * @param picture image to be displayed for this item
     * @param path path of the image to be displayed for this item
     */
    public void addLikedItem(String titleText, String categoryText, String descriptionText, Bitmap picture, String path){
        View item = getLayoutInflater().inflate(R.layout.activity_liked_item_row,null,false);

        TextView title = (TextView) item.findViewById(R.id.likeItemName);
        title.setText(titleText);
        TextView category = (TextView) item.findViewById(R.id.likeItemCategoryText);
        category.setText(categoryText);
        ImageView image = (ImageView) item.findViewById(R.id.likeItemImage);
        setPicture(image, picture, path);
        FloatingActionButton delete = (FloatingActionButton) item.findViewById(R.id.likeItemDeleteFAButton);
        FloatingActionButton edit = (FloatingActionButton) item.findViewById(R.id.likeItemEditFAButton);
        Button description = (Button) item.findViewById(R.id.likeItemDescriptionButton);
        description.setContentDescription(descriptionText);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLikedItem(item);
            }
        });
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDescriptionPopup(description.getContentDescription().toString());
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastViewPressed = item;
                isInsertPressed = false;
                moveToEdit(item);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastImagePressed=image;
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
        linearVerticalLayout.addView(item);
    }

    /**
     * Sets a picture to an item.
     *
     * Gets an imageView of the corresponding like list item, which will get the image, and a bitmap
     * -the new iamge and a path. If image is not defined, we check to find the image from tis given
     * path. If not found, a default image will display instead.
     *
     * @param image  like list item image view
     * @param picture the actual picture to be displayed
     * @param path path of the picture
     */
    private void setPicture(ImageView image, Bitmap picture, String path){
        if ( (picture==null) || (path!=null) && (path.equals("purple")) ) {
            if (path != null){
                String uri = (path.equals("purple")) ? "@drawable/purple" : path;

                int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                Drawable res = getResources().getDrawable(imageResource);
                image.setImageDrawable(res);
            }
        }else{
            image.setImageBitmap(picture);
        }
    }
    private void moveToEdit(View view) {
        isInsertPressed = false;
        TextView title = (TextView) view.findViewById(R.id.likeItemName);
        TextView category = (TextView) view.findViewById(R.id.likeItemCategoryText);
        ImageView image = (ImageView) view.findViewById(R.id.likeItemImage);
        Button description = (Button) view.findViewById(R.id.likeItemDescriptionButton);
        image.buildDrawingCache();
        Bitmap bitmap = image.getDrawingCache();

        Intent intent = new Intent(LikeList.this, EditLikeListItem.class);
     /*   Bundle bundle = new Bundle();
        bundle.putParcelable("image", bitmap);
       // intent.putExtras(bundle);
        //bundle.putBoolean("isInsertPressed", isInsertPressed);
        bundle.putSerializable("name", title.getText().toString());
        bundle.putSerializable("category", category.getText().toString());
        bundle.putSerializable("description", description.getContentDescription().toString()); */
        intent.putExtra("name", title.getText());
        intent.putExtra("category", category.getText());
        intent.putExtra("description", description.getContentDescription().toString());
        //intent.putExtra("image", bitmap);
        startActivityForResult(intent, START_EDIT_ACTIVITY);
    }

    /**
     * Removes an item from like list.
     *
     * @param v item to be deleted
     */
    private void removeLikedItem(View v){
        linearVerticalLayout.removeView(v);
    }

    /**
     * Creates a popup with the description of an item.
     *
     * Gets the text to be displayed in the popup (the description of an item)
     *
     * @param text to be displayed
     */
    private void createDescriptionPopup(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LikeList.this);

        builder.setTitle("Description");
        builder.setMessage( (text==null) || (text.length()==0) ? "No Description for this item" : text);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    /**
     * Open insert like list activity to insert an new like list item.
     */
    private void insertInLikeList() {
        isInsertPressed = true;
        Intent intent = new Intent(LikeList.this, EditLikeListItem.class);
        startActivityForResult(intent, START_INSERT_ACTIVITY);
    }

    /**
     * Opens gallery to select an image (changes activity) for a like list item.
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
                else
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}