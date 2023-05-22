package com.fiscaliageneralags.fiscalia.Utils;

import android.content.Context;

import com.fiscaliageneralags.fiscalia.Models.BuildingClusterMarkerItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by ERodriguezF on 09/03/2018.
 * @author ERodriguezF
 * @version 1.3
 */

public class CustomClusterRenderer extends DefaultClusterRenderer<BuildingClusterMarkerItem> {



    /**
     * The index for changing colors.
     */
    private int indexColor = 1;

    /**
     * Override the super Constructor of {@link DefaultClusterRenderer}
     * @param context The context of the application.
     * @param map The Google Map instance.
     * @param clusterManager The Cluster manager of the map.
     */
    public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<BuildingClusterMarkerItem> clusterManager) {
        super(context, map, clusterManager);
    }

    /**
     * Gets the color for the pin
     * @return The color for the next pin.
     */
    private float getColorForPin(){
        float color;
        switch (indexColor){
            case 1:
                color = 87.75f;//95.73f;//86;
                indexColor = 2;
                break;
            case 2:
                color = 32.25f;//87.75f;//25;
                indexColor = 3;
                break;
            case 3:
                color = 12.87f;//122.3f;//39;
                indexColor = 4;
                break;
            case 4:
                color = 176.5f;//154.7f;//198;
                indexColor = 1;
                break;
            default:
                color = 87.75f;//95.73f;//86;
                indexColor = 2;
                break;
        }
        return color;
    }

    /**
     * Replace the default icon color using {@link BitmapDescriptorFactory#defaultMarker(float)} and {@link MarkerOptions#icon(BitmapDescriptor)}
     * @param item The Current Item for the Marker
     * @param markerOptions The Marker Options for the Item.
     */
    @Override
    protected void onBeforeClusterItemRendered(BuildingClusterMarkerItem item, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(getColorForPin()));
    }

}
