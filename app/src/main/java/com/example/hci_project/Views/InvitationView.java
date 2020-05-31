package com.example.hci_project.Views;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.hci_project.R;
import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.MapActivity;

import static com.example.hci_project.MyInvitationsActivity.invitationsHolder;


public class InvitationView {

    private TextView nameOfPlace;
    private TextView invitationInfo;
    private ImageView image;
    private ImageView delete;
    private LinearLayout card;
    private DynamicXML dynamicallyXML;
    private Context context;



    public InvitationView(Context context,TextView nameOfPlace,ImageView image,TextView invitationInfo){
        dynamicallyXML = new DynamicXML();
        this.context = context;
        this.nameOfPlace = nameOfPlace;
        this.image = image;
        this.invitationInfo = invitationInfo;
        this.card = new LinearLayout(context);
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

        placeDescriptionLayout.addView(nameOfPlace);
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


        nameOfPlace.setTypeface(nameOfPlace.getTypeface(), Typeface.BOLD);

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
        for(int i = 0; i < MapActivity.invitations.size(); i++){
            if(MapActivity.invitations.get(i).getNameOfPlace().equals(nameOfPlace.getText().toString())){
                MapActivity.invitations.remove(i);
            }
        }

        //Remove from UI
        invitationsHolder.removeView(this.getCard());

        if(MapActivity.invitations.size() == 0){
            TextView noResultTV = dynamicallyXML.createTextView(context,"You don't have any invitations yet.","sans-serif-condensed",
                    13, Color.BLACK, Gravity.CENTER_HORIZONTAL,0,50,0,0);
            invitationsHolder.addView(noResultTV);
            return;
        }
    }
}
