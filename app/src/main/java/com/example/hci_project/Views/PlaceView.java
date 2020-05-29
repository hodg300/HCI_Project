package com.example.hci_project.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci_project.Place;
import com.example.hci_project.R;
import com.example.hci_project.Utils.DynamicXML;


public class PlaceView {

    private TextView nameOfPlace;
    private TextView description;
    private ImageView image;
    private LinearLayout card;
    private DynamicXML dynamicallyXML;
    private Context context;
    private Place place;

    public PlaceView(Context context,TextView nameOfPlace,ImageView image,TextView description,Place place){

        dynamicallyXML = new DynamicXML();
        this.context = context;
        this.nameOfPlace = nameOfPlace;
        this.image = image;
        this.description = description;
        this.card = new LinearLayout(context);
        this.place = place;
        createCard();


    }

    private void createCard() {
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

        setClickListeners(card);
    }

    void setClickListeners(LinearLayout cardView){


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public LinearLayout getCard() {
        return card;
    }
}
