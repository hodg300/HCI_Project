package com.example.hci_project.Utils;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.cardview.widget.CardView;

import com.example.hci_project.R;

public class DynamicXML {

    public ImageView createImageView(Context context,int image, int gravity, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(leftMargin,topMargin,rightMargin,bottomMargin);
        lp.gravity = gravity;
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(image);
        imageView.setLayoutParams(lp);

        return imageView;
    }

    public TextView createTextView(Context context,String text,String fontFamily, int size, int color, int gravity, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(leftMargin,topMargin,rightMargin,bottomMargin);
        lp.gravity = gravity;
        TextView textView = new TextView(context);
        textView.setTextSize(size);
        textView.setText(text);
        textView.setTypeface(Typeface.create(fontFamily, Typeface.NORMAL));
        textView.setTextColor(color);
        textView.setLayoutParams(lp);

        return textView;
    }

    public EditText createEditText(Context context,String hint, int type) {

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(700, 100);
        lp.setMargins(0,30,20,20);
        lp.gravity = Gravity.CENTER;
        EditText editText = new EditText(context);
        editText.setHint(hint);
        editText.setTextSize(13);
        editText.setPadding(40,0,0,0);
        editText.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
        editText.setHintTextColor(Color.BLACK);
        editText.setTextColor(Color.BLACK);
        editText.setBackgroundResource(R.drawable.edit_text_shape);
        editText.setInputType(type);
        editText.setLayoutParams(lp);

        return editText;

    }

    public CardView createCardView(Context context,int width, int height,int leftMargin,int topMargin, int rightMargin,int bottomMargin, int radius,int color){
        CardView cardView = new CardView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.setMargins(leftMargin,topMargin,rightMargin,bottomMargin);
        cardView.setLayoutParams(layoutParams);
        cardView.setRadius(radius);
        cardView.setPadding(25, 25, 25, 25);
        cardView.setCardBackgroundColor(color);
        cardView.setMaxCardElevation(30);
        cardView.setMaxCardElevation(6);

        return cardView;
    }

    public ProgressBar createProgressBar(Context context){

        ProgressBar progressBar = new ProgressBar(context,null,android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        return  progressBar;
    }

    //To be complete
    public Button createButton(Context context,String text, int color){
        Button button = new Button(context);
        button.setText(text);
        button.setBackgroundColor(color);

        return button;
    }



    public void addAllViewsLayout(LinearLayout layout, View...view) {

        for(View v: view){
            layout.addView(v);
        }
    }
}

