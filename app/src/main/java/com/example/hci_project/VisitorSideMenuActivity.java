package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hci_project.Utils.Finals;

public class VisitorSideMenuActivity extends Activity {

    private TextView nameOfUser;
    private TextView myInvitations;
    private TextView settings;
    private TextView home;
    private TextView logOut;
    private ImageView back;
    private de.hdodenhof.circleimageview.CircleImageView image;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_side_menu);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        setIds();
        setDetails();
        setOnClickListeners();
    }

    private void setDetails() {
        nameOfUser.setText(user.getFirstName() + " " + user.getLastName());
        image.setImageResource(user.getProfileImage());
    }

    private void setOnClickListeners() {
        myInvitations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMyInvitationsActivity();
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
                goToMapActivity();
            }
        });

    }

    private void goToMapActivity() {

        Intent intent = new Intent(this,MapActivity.class);
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

    private void goToMyInvitationsActivity(){
        Intent intent = new Intent(this,MyInvitationsActivity.class);
        intent.putExtra(Finals.USER, user);
        startActivity(intent);
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
        finish();
    }
    private void setIds() {
        myInvitations = findViewById(R.id.my_invitations_title);
        settings = findViewById(R.id.settings_title);
        logOut = findViewById(R.id.logout_title);
        back = findViewById(R.id.back);
        nameOfUser = findViewById(R.id.name_of_user);
        image = findViewById(R.id.profile_image);
        home = findViewById(R.id.home);

    }
}