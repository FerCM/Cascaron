package com.fiscaliageneralags.fiscalia.Models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by ERodriguezF on 09/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class BuildingClusterMarkerItem implements ClusterItem {

    private LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private Object mTag;

    public BuildingClusterMarkerItem(LatLng mPosition, String mTitle, String mSnippet, Object mTag) {

        this.mPosition = mPosition;
        this.mTitle = mTitle;
        this.mSnippet = mSnippet;
        this.mTag = mTag;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    public Object getTag() {
        return mTag;
    }

}
