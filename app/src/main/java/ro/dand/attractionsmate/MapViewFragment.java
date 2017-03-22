package ro.dand.attractionsmate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class to model the Map Fragment.
 */

public class MapViewFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener {

    MapView mMapView;
    private GoogleMap googleMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;
    protected LocationManager locationManager;
    public final static String EXTRA_MESSAGE = "";
    public final static String MARKER_TITLE = "";
//    public final static HashMap<Marker, String> mMarkerMap = new HashMap<>();
//    public final static HashMap<Marker, Integer> mMarkerDrawable = new HashMap<>();
    public final static HashMap<Integer, Marker> mMarkerPosition = new HashMap<>();
    public final static HashMap<Marker, DescriptionImagePair> mMarkerInfo = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_fragment, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

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
                ArrayList<LatLng> allPoints = new ArrayList<>(Arrays.asList(
//                Casa Poporului
                        new LatLng(44.427504, 26.087351),
//                Ateneul Roman
                        new LatLng(44.441381, 26.097396),
//                Biblioteca Nationala
                        new LatLng(44.425594, 26.11017),
//                Berceni
                        new LatLng(44.389222, 26.118203)
                ));

//                enableMyLocation();


                Resources res = getResources();
                String[] titles = res.getStringArray(R.array.Locations);
//                String[] titles = {"Casa Poporului", "Ateneul Roman", "Biblioteca Nationala", "Berceni"};
                String[] snippets = {"Place of the Parliament", "Concert Hall", "National Library", "Neighbourhood"};
                String[] descriptions = {
                        "The Palace of the Parliament (Romanian: Palatul Parlamentului) is the seat of the Parliament of Romania. Located on Dealul Arsenalului in central Bucharest (Sector 5), it is the second largest administrative building in the world,[1] after The Pentagon in the United States.",
                        "The Romanian Athenaeum (Romanian: Ateneul Român) is a concert hall in the center of Bucharest, Romania and a landmark of the Romanian capital city.",
                        "The National Library of Romania is the national library of Romania. It is intended to be the ... After 1859 Union, the library has reach the national statute",
                        "Berceni is a southern neighbourhood in Bucharest."
                };
                int[] myImageList = new int[]{R.drawable.casapop, R.drawable.ateneul, R.drawable.biblioteca, R.drawable.berceni};
//                ArrayList<Integer> myImages = new ArrayList<>(Arrays.asList(R.drawable.casapop, R.drawable.casa_poporuluii));
                int i = 0;
                for (LatLng coordinate : allPoints) {
                    Marker marker = googleMap.addMarker(new MarkerOptions()
                            .position(coordinate)
                            .title(titles[i])
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .snippet(snippets[i])

                    );
                    googleMap.setOnInfoWindowClickListener(MapViewFragment.this);
//                    mMarkerMap.put(marker, descriptions[i]);
//                    mMarkerDrawable.put(marker, myImageList[i]);
                    mMarkerInfo.put(marker, new DescriptionImagePair(descriptions[i], myImageList[i]));
                    mMarkerPosition.put(i, marker);
                    i++;
                }


                // Add a marker in Bucharest and move the camera
                LatLng bucharest = new LatLng(44.426767, 26.102538);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bucharest, 12));
//                googleMap = mMap;
//
//                // For dropping a marker at a point on the Map
//                LatLng sydney = new LatLng(-34, 151);
//                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//                // For zooming automatically to the location of the marker
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this.getActivity(), ScrollingActivity.class);
        Bundle extras = new Bundle();
        extras.putString("MARKER_TITLE", marker.getTitle());
        extras.putString("MARKER_DESCRIPTION", mMarkerInfo.get(marker).getDescription());
        extras.putInt("MARKER_PHOTO", mMarkerInfo.get(marker).getImageAddress());
//        extras.putParcelable("MARKER_POSITION", marker.getPosition());
        extras.putDouble("MARKER_LATITUDE", marker.getPosition().latitude);
        extras.putDouble("MARKER_LONGITUDE", marker.getPosition().longitude);
//        intent.putExtra(EXTRA_MESSAGE, marker.getSnippet());
//        intent.putExtra(MARKER_TITLE, marker.getTitle());
//        intent.pu
        intent.putExtras(extras);
        startActivity(intent);
        //Toast.makeText(getContext(), marker.getSnippet(), Toast.LENGTH_SHORT).show();
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


//    /**
//     * Enables the My Location layer if the fine location permission has been granted.
//     */
//    private void enableMyLocation() {
//        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Permission to access the location is missing.
//            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION, true);
//            PermissionUtils.requestPermission(this.startActivity());
//        } else if (googleMap != null) {
//            // Access to the location has been granted to the app.
//            googleMap.setMyLocationEnabled(true);
//        }
//    }
//
//    @Override
//    public boolean onMyLocationButtonClick() {
//        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
//        // Return false so that we don't consume the event and the default behavior still occurs
//        // (the camera animates to the user's current position).
//        return false;
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
//            return;
//        }
//
//        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
//                Manifest.permission.ACCESS_FINE_LOCATION)) {
//            // Enable the my location layer if the permission has been granted.
//            enableMyLocation();
//        } else {
//            // Display the missing permission error dialog when the fragments resume.
//            mPermissionDenied = true;
//        }
//    }
//
//    @Override
//    protected void onResumeFragments() {
//        super.onResumeFragments();
//        if (mPermissionDenied) {
//            // Permission was not granted, display error dialog.
//            showMissingPermissionError();
//            mPermissionDenied = false;
//        }
//    }
//
//    /**
//     * Displays a dialog with error message explaining that the location permission is missing.
//     */
//    private void showMissingPermissionError() {
//        PermissionUtils.PermissionDeniedDialog
//                .newInstance(true).show(getSupportFragmentManager(), "dialog");
//    }
}
