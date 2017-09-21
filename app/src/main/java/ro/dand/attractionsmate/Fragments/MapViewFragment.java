package ro.dand.attractionsmate.Fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

import ro.dand.attractionsmate.Data.MarkerInfoRepository;
import ro.dand.attractionsmate.Database.LandmarkDbHelper;
import ro.dand.attractionsmate.Models.MarkerInfo;
import ro.dand.attractionsmate.R;
import ro.dand.attractionsmate.Activities.ScrollingActivity;

/**
 * Class to model the Map Fragment.
 */

public class MapViewFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMyLocationButtonClickListener {

    MapView mMapView;
    private GoogleMap googleMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    LandmarkDbHelper mDbHelper;
    ArrayList<MarkerInfo> updatedMarkerInfo = new ArrayList<>();
    ArrayList<LatLng> allCoordinates = new ArrayList<>();

    private final static HashMap<Marker, MarkerInfo> mMarkerInfoMap = new HashMap<>();
    private MarkerInfoRepository markerInfoRepository = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_fragment, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        markerInfoRepository = new MarkerInfoRepository();
        final ArrayList<MarkerInfo> mMarkerInfo = markerInfoRepository.getAllMarkerInfo();
        mDbHelper = new LandmarkDbHelper(getActivity());
        retrieveDataFromDb();
        retrieveCoordinates();


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.getUiSettings().setMapToolbarEnabled(true);

                enableMyLocation();

                int i = 0;
                for (LatLng coordinate : allCoordinates) {
                    Marker marker = googleMap.addMarker(new MarkerOptions()
                            .position(coordinate)
                            .title(updatedMarkerInfo.get(i).getMarkerTitle())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .snippet(updatedMarkerInfo.get(i).getMarkerShortDescription())

                    );
                    googleMap.setOnInfoWindowClickListener(MapViewFragment.this);

                    mMarkerInfoMap.put(marker, updatedMarkerInfo.get(i));
                    i++;
                }


                // Add a marker in Bucharest and move the camera
                LatLng bucharest = new LatLng(44.426767, 26.102538);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bucharest, 12));
            }
        });

        return rootView;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this.getActivity(), ScrollingActivity.class);
        Bundle extras = new Bundle();
        extras.putString("MARKER_TITLE", marker.getTitle());
        extras.putString("MARKER_DESCRIPTION", mMarkerInfoMap.get(marker).getMarkerLongDescription());
        extras.putInt("MARKER_PHOTO", mMarkerInfoMap.get(marker).getMarkerImageAddress());
        extras.putDouble("MARKER_LATITUDE", marker.getPosition().latitude);
        extras.putDouble("MARKER_LONGITUDE", marker.getPosition().longitude);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            ActivityCompat.requestPermissions(this.getActivity(), new String[]
                    {android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE );
        } else if (googleMap != null) {
            // Access to the location has been granted to the app.
            googleMap.setMyLocationEnabled(true);
        }
    }

    public void retrieveDataFromDb() {
        Cursor cursor = mDbHelper.getAllData();

        // Check if data is available
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
        }

        if (updatedMarkerInfo.size() > 0) {
            cursor.moveToPosition(updatedMarkerInfo.size() - 1);
        }

        while (cursor.moveToNext()) {
            updatedMarkerInfo.add(
                    new MarkerInfo(
                            cursor.getString(1),
                            cursor.getString(2),
                            new LatLng(cursor.getDouble(3), cursor.getDouble(4)),
                            cursor.getString(5),
                            getResources().getIdentifier(cursor.getString(6), "drawable", "ro.dand.attractionsmate")
                    )
            );
        }
        cursor.close();
    }

    public void retrieveCoordinates() {
        Cursor cursor = mDbHelper.getAllData();

        // Check if data is available
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
        }

        if (allCoordinates.size() > 0) {
            cursor.moveToPosition(allCoordinates.size() - 1);
        }

        while (cursor.moveToNext()) {
            allCoordinates.add(
                    new LatLng(cursor.getDouble(3), cursor.getDouble(4))
            );
        }
        cursor.close();
    }



    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this.getActivity(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
