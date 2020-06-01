package com.example.hci_project.Views;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.hci_project.Invitation;
import com.example.hci_project.R;
import com.example.hci_project.Utils.DynamicXML;

import java.util.ArrayList;

public class InvitationView {

    private String nameOfPlace;
    private TextView nameOfVisitor;
    private TextView invitationInfo;
    private ImageView image;
    private ImageView delete;
    private LinearLayout card;
    private DynamicXML dynamicallyXML;
    private Context context;
    private LinearLayout container;
    private ArrayList<Invitation> invitations;



    public InvitationView(Context context,TextView name,ImageView image,TextView invitationInfo,LinearLayout container,ArrayList<Invitation> invitations,String nameOfPlace){
        dynamicallyXML = new DynamicXML();
        this.context = context;
        this.nameOfVisitor = name;
        this.image = image;
        this.invitationInfo = invitationInfo;
        this.card = new LinearLayout(context);
        this.container = container;
        this.invitations = invitations;
        this.nameOfPlace = nameOfPlace;
        createCard();


    }

    private void createCard() {

        delete = dynamicallyXML.createImageView(context,R.drawable.deleteicon,Gravity.BOTTOM,0, 0,0,0,80,80);
        card.setBackgroundResource(R.drawable.card_view_shape);
        LinearLayout.LayoutParams cardParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                300
        );
        cardParam.topMargin = 15;
        card.setLayoutParams(cardParam);
        //Set card sum
        card.setWeightSum(2f);
        card.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout placeDescriptionLayout = new LinearLayout(context);
        LinearLayout.LayoutParams placeParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
        );
        placeParam.leftMargin = 20;
        placeDescriptionLayout.setOrientation(LinearLayout.VERTICAL);
        placeDescriptionLayout.setLayoutParams(placeParam);

        placeDescriptionLayout.addView(nameOfVisitor);
        placeDescriptionLayout.addView(invitationInfo);
        placeDescriptionLayout.addView(delete);

        LinearLayout imageLayout = new LinearLayout(context);
        imageLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
        );
        imageLayout.setLayoutParams(imageParams);
        imageLayout.addView(image);


        nameOfVisitor.setTypeface(nameOfVisitor.getTypeface(), Typeface.BOLD);

        //Create Params
        card.addView(placeDescriptionLayout);
        card.addView(imageLayout);

        setClickListeners(card);
    }

    void setClickListeners(LinearLayout cardView){


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpAlertDialog();
            }
        });
    }

    public LinearLayout getCard() {
        return card;
    }

    private void popUpAlertDialog() {

        TextView alertMessage = dynamicallyXML.createTextView(context,
                "After deletion this reservation will not be valid any more.",
                "sans-serif-condensed",15, Color.BLACK,Gravity.CENTER,
                0,0,0,0);
        alertMessage.setPadding(40,40,40,40);

        new AlertDialog.Builder(context)
                .setTitle("Are you sure?")
                .setView(alertMessage)
                .setIcon(R.drawable.ic_warning_sign)
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                deleteInvitation();
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).show();

    }

    public void deleteInvitation(){

        //Remove from list
        for(int i = 0; i < invitations.size(); i++){

            if(invitations.get(i).getNameOfPlace().toLowerCase().equals(nameOfPlace.toLowerCase()) &&
                    invitations.get(i).getNameOfVisitor().toLowerCase().equals(nameOfVisitor.getText().toString().toLowerCase())
            ){
                Log.d("SIZE", "size is : " + invitations.size());
                invitations.remove(i);
            }
        }

        //Remove from UI
        container.removeView(this.getCard());

        Log.d("SIZE", "size of invitations: " + invitations.size());

        if(invitations.size() == 0){
            TextView noResultTV = dynamicallyXML.createTextView(context,"You don't have any invitations yet.","sans-serif-condensed",
                    13, Color.BLACK, Gravity.CENTER_HORIZONTAL,0,50,0,0);
            container.addView(noResultTV);
            return;
        }
    }
}
