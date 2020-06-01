package com.example.hci_project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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

import com.example.hci_project.Utils.Finals;

public class PlaceDialog extends DialogFragment {

    private RelativeLayout restaurantContainer;
    private TextView nameOfRestaurantTV;
    private TextView currentVisitorsTV;
    private ImageView restaurantImg;
    private ImageView infoImg;
    private Button inviteButton;
    private Button stateOfPlace;
    private Place place;
    private User user;
    private final String FULL = "The place if currently full";
    private final String NOT_FULL = "this place is currently open for X visitors";



    public PlaceDialog(Place place,User user) {

        this.place = place;
        this.user = user;
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

                goToAboutPlaceActivity();
            }
        });

        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToInvitationActivity();
            }
        });
    }

    private void goToInvitationActivity() {

        Log.d("TEST", place.getName());

        Intent intent = new Intent(getContext(),InvitationActivity.class);
        intent.putExtra(Finals.PLACE_INDEX, WaitingActivity.places.indexOf(place));
        startActivity(intent);
        getDialog().dismiss();
    }

    private void goToAboutPlaceActivity(){

        Log.d("TEST", "user name " + user.firstName);
        Intent intent = new Intent(getContext(), VisitorAboutPlaceActivity.class);
        intent.putExtra(Finals.PLACE, WaitingActivity.places.indexOf(place));
        intent.putExtra(Finals.USER,user);
        startActivity(intent);
        getDialog().dismiss();
    }

    private void setRestaurant() {

        if(place.isFull()){
            stateOfPlace.setBackgroundResource(R.drawable.denied_button_shape);
            restaurantContainer.setBackgroundResource(R.drawable.full_dialog_rounded_bg);
            inviteButton.setText(inviteButton.getText() + " for other time");
            stateOfPlace.setText("this place is currently full");

        } else {
            restaurantContainer.setBackgroundResource(R.drawable.not_full_dialog_rounded_bg);
            stateOfPlace.setBackgroundResource(R.drawable.accept_button_shape);
            stateOfPlace.setText("this place is currently open for " + (place.getMaxVisitors() - place.getNumOfVisitors()) + " visitors");

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
        stateOfPlace = view.findViewById(R.id.state_of_place);
    }

}
