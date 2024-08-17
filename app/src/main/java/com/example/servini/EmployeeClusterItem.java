package com.example.servini;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class EmployeeClusterItem implements ClusterItem {

    private final LatLng position;
    private final String title;
    private final String snippet;
    private final String imageUri;

    public EmployeeClusterItem(LatLng position, String title, String snippet, String imageUri) {
        this.position = position;
        this.title = title;
        this.snippet = snippet;
        this.imageUri = imageUri;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return snippet;
    }

    public String getImageUri() {
        return imageUri;
    }
}


