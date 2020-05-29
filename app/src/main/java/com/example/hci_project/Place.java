package com.example.hci_project;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class Place {

    private String name;
    private String description;
    private int image;
    private LatLng latlng;
    private Marker marker;
    private int numOfVisitors;
    private boolean isFull;

    public Place(String name,int image,String description, LatLng latlng, int numOfVisitors,boolean isFull) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.latlng = latlng;
        this.numOfVisitors = numOfVisitors;
        this.isFull = isFull;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public int getNumOfVisitors() {
        return numOfVisitors;
    }

    public void setNumOfVisitors(int numOfVisitors) {
        this.numOfVisitors = numOfVisitors;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
