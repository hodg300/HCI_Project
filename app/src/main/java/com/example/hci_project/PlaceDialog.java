package com.example.hci_project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.AutoText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class PlaceDialog extends DialogFragment {
    final static String PLACE = "place";
    private RelativeLayout restaurantContainer;
    private TextView nameOfRestaurantTV;
    private TextView currentVisitorsTV;
    private ImageView restaurantImg;
    private ImageView infoImg;
    private Button inviteButton;
    private Place place;
    private final String INVITE = "invite";
    private final String FULL = "this place is full";



    public PlaceDialog(Place place) {

        this.place = place;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_place,container, false);

        setIds(view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setRestaurant();
        setOnClickListeners();


        return view;
    }

    private void setOnClickListeners() {
        infoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void goToAboutPlaceActivity(){
        Intent intent = new Intent(getContext(),AboutPlaceActivity.class);
        //
        startActivity(intent);
    }

    private void setRestaurant() {

        if(place.isFull()){
            restaurantContainer.setBackgroundResource(R.drawable.full_dialog_rounded_bg);
            inviteButton.setBackgroundResource(R.drawable.denied_button_shape);
            inviteButton.setText(FULL);
        } else {
            restaurantContainer.setBackgroundResource(R.drawable.not_full_dialog_rounded_bg);
            inviteButton.setBackgroundResource(R.drawable.accept_button_shape);
            inviteButton.setText(INVITE);
        }

        nameOfRestaurantTV.setText(place.getName());
        restaurantImg.setImageResource(place.getImage());
        currentVisitorsTV.setText("Current Visitors : " + place.getNumOfVisitors());


    }

    private void setIds(View view) {

        restaurantContainer = view.findViewById(R.id.restaurant_dialog_container);
        nameOfRestaurantTV = view.findViewById(R.id.name_of_restaurant_tv);
        currentVisitorsTV = view.findViewById(R.id.current_visitors_tv);
        restaurantImg = view.findViewById(R.id.img_of_restaurant);
        infoImg = view.findViewById(R.id.info);
        inviteButton = view.findViewById(R.id.invite_btn);
    }

}
