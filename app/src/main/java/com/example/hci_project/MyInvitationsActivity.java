package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Views.InvitationView;

public class MyInvitationsActivity extends Activity {

    public static LinearLayout invitationsHolder;
    private ImageView menu;
    private DynamicXML dynamicXML;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        dynamicXML = new DynamicXML();
        setContentView(R.layout.activity_my_invitations);
        setIds();
        setOnClickListeners();
        showPlaces();
    }

    private void setOnClickListeners() {
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSideMenuActivity();
            }
        });
    }

    private void goToSideMenuActivity(){
        Intent intent = new Intent(this,SideMenuActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
        finish();
    }

    private void setIds() {

        invitationsHolder = findViewById(R.id.invitations_holder);
        menu = findViewById(R.id.menu);
    }

    private void showPlaces() {

        invitationsHolder.removeAllViews();

        if(MapActivity.invitations.size() == 0){
            TextView noResultTV = dynamicXML.createTextView(this,"You don't have any invitations yet.","sans-serif-condensed",
                    13, Color.BLACK, Gravity.CENTER_HORIZONTAL,0,50,0,0);
            invitationsHolder.addView(noResultTV);
            return;
        }

        for(Invitation invitation : MapActivity.invitations){

            String invitationInfoString = invitation.getDate() + "\nOrder for " + invitation.getNumOfGuests() + " at " + invitation.getHour();

            InvitationView invitationView = new InvitationView(
                    this,
                    dynamicXML.createTextView(this,invitation.getNameOfPlace(),"sans-serif-condensed",20,Color.BLACK,Gravity.TOP,0,0,0,0),
                    dynamicXML.createImageView(this,invitation.getImage(),Gravity.CENTER,0,5,0,5,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT),
                    dynamicXML.createTextView(this,invitationInfoString,"sans-serif-condensed",15,Color.BLACK,Gravity.CENTER,0,0,0,0)
            );

            invitationsHolder.addView(invitationView.getCard());

        }

    }
}