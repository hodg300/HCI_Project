package com.example.hci_project.Views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci_project.MinistryOfHealthAboutPlaceActivity;
import com.example.hci_project.Place;
import com.example.hci_project.PoliceAboutPlaceActivity;
import com.example.hci_project.R;
import com.example.hci_project.User;
import com.example.hci_project.Utils.DynamicXML;
import com.example.hci_project.Utils.Finals;


public class PlaceView {

    private TextView nameOfPlace;
    private TextView description;
    private ImageView image;
    private LinearLayout card;
    private DynamicXML dynamicallyXML;
    private Context context;
    private Place place;
    private User user;


    public PlaceView(Context context,TextView nameOfPlace,ImageView image,TextView description,Place place,User user){
        dynamicallyXML = new DynamicXML();
        this.context = context;
        this.nameOfPlace = nameOfPlace;
        this.image = image;
        this.description = description;
        this.card = new LinearLayout(context);
        this.place = place;
        this.user = user;
        createCard();


    }

    private void createCard() {

        Log.d("GUGU", "crate card!!!!");
        Log.d("GUGU", "user.gerRole: " + user.getRole());
        Log.d("GUGU", "final is  " + Finals.POLICE_OFFICER);

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
        placeDescriptionLayout.addView(description);

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

    }





    public LinearLayout getCard() {
        return card;
    }


}
