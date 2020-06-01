package com.example.hci_project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hci_project.Utils.Finals;

public class SettingsActivity extends Activity {

    private Button personalInfo;
    private ImageView menu;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setIds();
        setOnClickListeners();
    }

    private void setIds() {
        personalInfo = findViewById(R.id.personal_info_btn);
        menu = findViewById(R.id.menu);

    }

    private void goToSideMenuActivity(){
        Intent intent = new Intent(this, VisitorSideMenuActivity.class);
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