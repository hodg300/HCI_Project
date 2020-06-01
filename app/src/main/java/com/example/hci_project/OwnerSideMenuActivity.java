package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hci_project.Utils.Finals;

public class OwnerSideMenuActivity extends Activity {

    private TextView nameOfUser;
    private TextView stateOfPlace;
    private TextView settings;
    private TextView logOut;
    private TextView allInvitations;
    private TextView about;
    private ImageView back;
    private de.hdodenhof.circleimageview.CircleImageView image;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_side_menu);
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

        stateOfPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPlaceStateActivity();
            }
        });

        allInvitations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAllInvitationsActivity();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAboutActivity();
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

    }

    private void goToAboutActivity() {

        Intent intent = new Intent(this,OwnerAboutPlaceActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        finish();

    }

    private void goToPlaceStateActivity() {

        Intent intent = new Intent(this,PlaceStateActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        finish();
    }

    private void goToAllInvitationsActivity() {
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
        finish();
    }
    private void setIds() {

        allInvitations = findViewById(R.id.all_invitations);
        about = findViewById(R.id.about);
        stateOfPlace = findViewById(R.id.state_of_place);
        settings = findViewById(R.id.settings);
        logOut = findViewById(R.id.logout);
        back = findViewById(R.id.back);
        nameOfUser = findViewById(R.id.name_of_user);
        image = findViewById(R.id.profile_image);

    }
}