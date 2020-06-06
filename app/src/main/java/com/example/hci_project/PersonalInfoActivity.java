package com.example.hci_project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hci_project.Utils.Finals;

public class PersonalInfoActivity extends Activity {
    private ImageView menu;
    private User user;
    private Button submitBtn;
    private RelativeLayout personalInfoWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setIds();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_SHORT).show();
                goToSettingsActivity();
            }
        });
    }

    private void goToSettingsActivity() {

        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
    }


    private void goToSideMenuActivity(){
        Intent intent = new Intent(this, VisitorSideMenuActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    private void setIds() {
        menu = findViewById(R.id.menu);
        submitBtn = findViewById(R.id.submit_btn);
        personalInfoWindow = findViewById(R.id.personal_info_window);

    }
}