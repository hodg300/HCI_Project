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
    private int maxVisitors;
    private String openHour;
    private String cuisines;
    private int index;

    public Place(String name, int image, String description, LatLng latlng, int numOfVisitors,
                 boolean isFull, int maxVisitors, String openHour, String cuisines, int index) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.latlng = latlng;
        this.numOfVisitors = numOfVisitors;
        this.isFull = isFull;
        this.maxVisitors = maxVisitors;
        this.openHour= openHour;
        this.cuisines = cuisines;
        this.index = index;
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

    public int getMaxVisitors() {
        return maxVisitors;
    }

    public void setMaxVisitors(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
