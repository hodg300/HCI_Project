package com.example.hci_project;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

public class PlaceDialog extends DialogFragment {

    private RelativeLayout restaurantContainer;
    private TextView nameOfRestaurantTV;
    private TextView currentVisitorsTV;
    private ImageView restaurantImg;
    private ImageView infoImg;
    private Button inviteButton;
    private String nameOfRestaurant;
    private int imgSrc;
    private int numOfVisitors;
    private boolean isFull;
    private final String INVITE = "invite";
    private final String FULL = "this place is full";



    public PlaceDialog(String nameOfRestaurant, int imgSrc, int numOfVisitors, boolean isFull) {

        this.nameOfRestaurant = nameOfRestaurant;
        this.imgSrc = imgSrc;
        this.numOfVisitors = numOfVisitors;
        this.isFull = isFull;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_place,container, false);

        setIds(view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setRestaurant();


        return view;
    }

    private void setRestaurant() {

        if(isFull){
            restaurantContainer.setBackgroundResource(R.drawable.full_dialog_rounded_bg);
            inviteButton.setBackgroundResource(R.drawable.denied_button_shape);
            inviteButton.setText(FULL);
        } else {
            restaurantContainer.setBackgroundResource(R.drawable.not_full_dialog_rounded_bg);
            inviteButton.setBackgroundResource(R.drawable.accept_button_shape);
            inviteButton.setText(INVITE);
        }

        nameOfRestaurantTV.setText(nameOfRestaurant);
        restaurantImg.setImageResource(imgSrc);
        currentVisitorsTV.setText("Current Visitors : " + numOfVisitors);


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
