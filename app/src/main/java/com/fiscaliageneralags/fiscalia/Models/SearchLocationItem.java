package com.fiscaliageneralags.fiscalia.Models;

/**
 * Created by ERodriguezF on 22/12/2017.
 * @author ERodriguezF
 * @version 1.18
 */

public class SearchLocationItem {

    private int id;
    private String etiqueta;
    private double latitude;
    private double longitud;
    private float zoom;

    public SearchLocationItem(int id, String etiqueta, float zoom, double latitude, double longitud) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.latitude = latitude;
        this.longitud = longitud;
        this.zoom = zoom;
    }

    public int getId() {
        return id;
    }

    public String getEtiqueta() { return etiqueta; }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public float getZoom() { return zoom; }

    @Override
    public String toString() { return this.etiqueta; }
}
