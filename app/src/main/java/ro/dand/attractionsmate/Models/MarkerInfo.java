package ro.dand.attractionsmate.Models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Andrei Nicolae on 9/19/2017.
 */

public class MarkerInfo {
    private String markerTitle;
    private String markerShortDescription;
    private LatLng coordinates;
    private String markerLongDescription;
    private int markerImageAddress;

    public MarkerInfo() {
    }

    public MarkerInfo(String markerTitle, String markerShortDescription, LatLng coordinates, String markerLongDescription, int markerImageAddress) {
        this.markerTitle = markerTitle;
        this.markerShortDescription = markerShortDescription;
        this.coordinates = coordinates;
        this.markerLongDescription = markerLongDescription;
        this.markerImageAddress = markerImageAddress;
    }

    public String getMarkerTitle() {
        return markerTitle;
    }

    public String getMarkerShortDescription() {
        return markerShortDescription;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public String getMarkerLongDescription() {
        return markerLongDescription;
    }

    public int getMarkerImageAddress() {
        return markerImageAddress;
    }
}
