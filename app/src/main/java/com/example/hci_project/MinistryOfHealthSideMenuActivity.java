package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;

public class MinistryOfHealthSideMenuActivity extends Activity {

    private TextView nameOfUser;
    private TextView allReports;
    private TextView settings;
    private TextView home;
    private TextView logOut;
    private ImageView back;
    private de.hdodenhof.circleimageview.CircleImageView image;
    private User user;
    private RelativeLayout ministryOfHealthSideMenuActivity;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministry_of_health_side_menu);
        activity = this;
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setIds();
        setDetails();
        setOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(ministryOfHealthSideMenuActivity);
    }

    private void setDetails() {
        nameOfUser.setText(user.getFirstName() + " " + user.getLastName());
        image.setImageResource(user.getProfileImage());
    }

    private void setOnClickListeners() {
        allReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAllReportsActivity();
            }


        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSettingsActivity();
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLastActivity();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomeActivity();
            }
        });

    }

    private void goToAllReportsActivity() {

            Intent intent = new Intent(this,AllReportsActivity.class);
            intent.putExtra(Finals.USER, user);
            startActivity(intent);
            finish();
    }

    private void goToHomeActivity() {

        Intent intent = new Intent(this,SearchActivity.class);
        intent.putExtra(Finals.USER, user);
        startActivity(intent);
        finish();
    }

    private void goToLastActivity(){
        onBackPressed();
        this.overridePendingTransition(R.anim.left_out,
                R.anim.right_in);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.left_out,
                R.anim.right_in);
        finish();
    }



    private void goToSettingsActivity(){
        Intent intent = new Intent(this,SettingsActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        finish();
    }
    private void goToLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        Util.finishAllActivities();

    }
    private void setIds() {
        allReports = findViewById(R.id.all_reports_title);
        settings = findViewById(R.id.settings_title);
        logOut = findViewById(R.id.logout_title);
        back = findViewById(R.id.back);
        nameOfUser = findViewById(R.id.name_of_user);
        image = findViewById(R.id.profile_image);
        home = findViewById(R.id.home);
        ministryOfHealthSideMenuActivity = findViewById(R.id.ministryOfHealthSideMenuWindow);

    }
}