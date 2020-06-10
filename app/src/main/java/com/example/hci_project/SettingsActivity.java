package com.example.hci_project;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;

public class SettingsActivity extends Activity {

    private Button personalInfo;
    private Button changeModeBtn;
    private RelativeLayout settingsWindow;
    private ImageView menu;
    private User user;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        activity = this;
        setIds();
        setOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(settingsWindow);
    }

    private void setIds() {

        settingsWindow = findViewById(R.id.settings_window);
        changeModeBtn = findViewById(R.id.change_mode_btn);
        personalInfo = findViewById(R.id.personal_info_btn);
        menu = findViewById(R.id.menu);

    }

    private void goToSideMenuActivity(){
        Intent intent = null;
        if(user.getRole().toLowerCase().equals(Finals.VISITOR)) {
             intent = new Intent(this, VisitorSideMenuActivity.class);
        } else if(user.getRole().toLowerCase().equals(Finals.PLACE_OWNER)){
            intent = new Intent(this, OwnerSideMenuActivity.class);
        } else if(user.getRole().toLowerCase().equals(Finals.MINISTRY_OF_HEALTH)){
            intent = new Intent(this, MinistryOfHealthSideMenuActivity.class);
        } else if(user.getRole().toLowerCase().equals(Finals.POLICE_OFFICER)){
            intent = new Intent(this, PoliceSideMenuActivity.class);
        }
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    private void setOnClickListeners() {
        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPersonalInfoActivity();
            }
        });

        changeModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               WaitingActivity.isDarkModeOn = !WaitingActivity.isDarkModeOn;
               Util.changeBackgroundColor(settingsWindow);
               if(WaitingActivity.isDarkModeOn){
                   changeModeBtn.setText("Change to Regular Mode");
               } else {
                   changeModeBtn.setText("Change to Dark Mode");
               }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });
    }

    private void goToPersonalInfoActivity(){
        Intent intent = new Intent(this,PersonalInfoActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
    }
}