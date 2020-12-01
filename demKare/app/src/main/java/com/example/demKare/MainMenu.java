package com.example.demKare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    private GridLayout mainMenuGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainMenuGrid = (GridLayout) findViewById(R.id.MainMenuGrid);

        setSingleEvent(mainMenuGrid);
    }

    public void logOut(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
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

    /**
     * Changes screen on main menu
     *
     * @param grid Main menu grid
     */
    private void setSingleEvent(GridLayout grid) {
        for (int i = 0; i < grid.getChildCount(); i++) {
            // Button i
            CardView cardView = (CardView) grid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Contacts
                    if (finalI == 0) {
                        Intent intent = new Intent(MainMenu.this, Contacts.class);
                        startActivity(intent);
                    }
                    // People to remember
                    if (finalI == 1) {
                        Intent intent = new Intent(MainMenu.this, PeopleToRemember.class);
                        startActivity(intent);
                    }
                    // Reminders
                    if (finalI == 2) {
                        Intent intent = new Intent(MainMenu.this, ReminderList.class);
                        startActivity(intent);
                    }
                    // Things to remember
                    if (finalI == 3) {
                        Intent intent = new Intent(MainMenu.this, ThingsList.class);
                        startActivity(intent);
                    }
                    // Things to remember
                    if (finalI == 4) {
                        Intent intent = new Intent(MainMenu.this, ReadAboutDementia.class);
                        startActivity(intent);
                    }
                    // Like list
                    else if (finalI == 5) {
                        Intent intent = new Intent(MainMenu.this, LikeList.class);
                        startActivity(intent);
                    }
                    // Like list
                    else if (finalI == 6) {
                        Intent intent = new Intent(MainMenu.this, TestEvaluation.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}