package com.example.hci_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Utils.Finals;
import com.example.hci_project.Utils.Util;
import com.example.hci_project.Views.InvitationView;

import java.util.ArrayList;

public class OwnerInvitations extends Activity {

    public LinearLayout invitationsHolder;
    private ArrayList<Invitation> placeInvitations;
    private ImageView menu;
    private DynamicXML dynamicXML;
    private User user;
    private RelativeLayout ownerInvitationWindow;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placeInvitations = new ArrayList<>();
        user = (User) getIntent().getSerializableExtra(Finals.USER);
        place = OwnerHomeActivity.place;
        dynamicXML = new DynamicXML();
        setContentView(R.layout.activity_owner_invitations);
        setIds();
        setOnClickListeners();
        createInvitations();
        showInvitations();
    }

    private void createInvitations() {

        placeInvitations.add(new Invitation(
                "Mor Soferian",
                place.getName(),
                "15/2/20",
                "17:00",
                "3",
                place.getImage()
                ));

        placeInvitations.add(new Invitation(
                "Dina Bar Goren",
                place.getName(),
                "20/2/20",
                "20:00",
                "2",
                place.getImage()
        ));
        placeInvitations.add(new Invitation(
                "Sarel Micha",
                place.getName(),
                "22/2/20",
                "19:30",
                "10",
                place.getImage()
        ));
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
        Intent intent = new Intent(this, OwnerSideMenuActivity.class);
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        this.overridePendingTransition(R.anim.left_to_right,
                R.anim.right_to_left);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.changeBackgroundColor(ownerInvitationWindow);
    }

    private void setIds() {

        invitationsHolder = findViewById(R.id.invitations_holder);
        menu = findViewById(R.id.menu);
        ownerInvitationWindow = findViewById(R.id.owner_invitation_window);
    }

    private void showInvitations() {

        invitationsHolder.removeAllViews();

        getPlaceInvitations();

        if(placeInvitations.size() == 0){
            TextView noResultTV = dynamicXML.createTextView(this,"You don't have any invitations yet.","sans-serif-condensed",
                    13, Color.BLACK, Gravity.CENTER_HORIZONTAL,0,50,0,0);
            invitationsHolder.addView(noResultTV);
            return;
        }

        for(Invitation invitation : placeInvitations){

            String invitationInfoString = invitation.getDate() + "\nOrder for " + invitation.getNumOfGuests() + " at " + invitation.getHour();

            InvitationView invitationView = new InvitationView(
                    this,
                    dynamicXML.createTextView(this,invitation.getNameOfVisitor(),"sans-serif-condensed",20,Color.BLACK,Gravity.TOP,0,0,0,0),
                    dynamicXML.createImageView(this,invitation.getImage(),Gravity.CENTER,0,5,0,5,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT),
                    dynamicXML.createTextView(this,invitationInfoString,"sans-serif-condensed",15,Color.BLACK,Gravity.CENTER,0,0,0,0),invitationsHolder,
                    placeInvitations, place.getName()
            );

            invitationsHolder.addView(invitationView.getCard());
        }

    }

    private void getPlaceInvitations() {

        for(Invitation invitation : MapActivity.invitations){
            if(invitation.getNameOfPlace().toLowerCase().equals(place.getName())){
                placeInvitations.add(invitation);
            }
        }


    }
}