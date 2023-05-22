package com.fiscaliageneralags.fiscalia.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fiscaliageneralags.fiscalia.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by ERodriguezF on 21/02/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class AgencyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private View popup=null;
    private LayoutInflater inflater;

    public AgencyInfoWindowAdapter(LayoutInflater inflater) {
        this.inflater=inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     * Inflate the Custom window to pop up when marker is pressed.
     * @param marker
     * @return
     */
    @Override
    public View getInfoContents(Marker marker) {
        if (popup == null) {
            popup=inflater.inflate(R.layout.agency_info_window, null);
        }
        TextView tv= popup.findViewById(R.id.title);
        tv.setText(marker.getTitle());
        tv= popup.findViewById(R.id.snippet);
        if(marker.getSnippet() == null){
            tv.setVisibility(View.GONE);
        }
        else {
            tv.setText(marker.getSnippet());
        }
        return(popup);
    }
}
