package ro.dand.attractionsmate.Fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ro.dand.attractionsmate.Data.MarkerInfoRepository;
import ro.dand.attractionsmate.Models.DescriptionImagePair;
import ro.dand.attractionsmate.Models.TitleDescriptionPair;
import ro.dand.attractionsmate.R;
import ro.dand.attractionsmate.Activities.ScrollingActivity;

/**
 * Class to model the Map Fragment.
 */

public class MapViewFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMyLocationButtonClickListener {

    MapView mMapView;
    private GoogleMap googleMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    protected LocationManager locationManager;
    public final static String EXTRA_MESSAGE = "";
    public final static String MARKER_TITLE = "";

    public final static HashMap<Integer, Marker> mMarkerPosition = new HashMap<>();
    public final static HashMap<Marker, DescriptionImagePair> mMarkerInfo = new HashMap<>();
    public MarkerInfoRepository markerInfoRepository = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_fragment, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        markerInfoRepository = new MarkerInfoRepository();
        final ArrayList<TitleDescriptionPair> markerInfo = markerInfoRepository.getAllPairs();

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
//                Biserica Stravopoleus
                        new LatLng(44.431803, 26.09887),
//                Arch of Triumph
                        new LatLng(44.467066, 26.077823),
//                Parc Herastrau
                        new LatLng(44.468469, 26.080531),
//                        Old Court
                        new LatLng(44.430119, 26.101326),
//                        Tineretului Park
                        new LatLng(44.405718, 26.106061),
//                        Muzeul de Istorie
                        new LatLng(44.431447, 26.097454),
//                Biblioteca Nationala
                        new LatLng(44.425594, 26.11017),
//                Berceni
                        new LatLng(44.401017, 26.133877)
                ));

                enableMyLocation();

                String[] descriptions = {
                        "The world’s second largest administrative building is The Palace of the " +
                                "Parliament. It measures 330,000 m2 (~3,550,000 sq ft) and is regarded as former dictator’s, " +
                                "Nicolae Ceaușescu, most cherished jewel. By definition is one of the " +
                                "most significant sightseeing objectives in Bucharest, the capital of Romania. " +
                                "At the border of reason, kitsch and neoclassicism, the building reflects best what Romania underwent in the communist period. Built in a time when the people of Romania have been destitute, it has been regarded with reticence. It was named The People’s House, because it was built by the folk (and by no means for the folk). (http://travelguideromania.com/palace-parliament/)",
                        "The Romanian Athenaeum, dubbed the Romanian temple of arts, is an architectural jewel the uniqueness and merits of which have been acknowledged by the fact the building was classified a historical monument in 2004 and part of the European patrimony in 2007. Located on the Victory Avenue, the Romanian Athenaeum is a tourist sight the importance of which does not come down exclusively to its being one of the most eye-catching architectural marvels of the capital, but also to the fact it is home to one of the oldest and most important cultural institutions in Bucharest, a major contributor to the artistic scene of Bucharest: the George Enescu Philharmonic Orchestra.",
                        "The Stavropoleos Monastery is an Eastern Orthodox monastery for nuns in central Bucharest, Romania. Its church is built in Brâncovenesc style. The patrons of the church (the saints to whom the church is dedicated) are St. Archangels Michael and Gabriel. The name Stavropoleos is a Romanian rendition of a Greek word, Stauropolis, meaning 'The city of the Cross'. One of the monastery's constant interests is Byzantine music, expressed through its choir and the largest collection of Byzantine music books in Romania.",
                        "Arcul de Triumf is a triumphal arch located in the northern part of Bucharest, on the Kiseleff Road." +
                                "The first, wooden, triumphal arch was built hurriedly, after Romania gained its independence (1878), so that the victorious troops could march under it. Another temporary arch was built on the same site, in 1922, after World War I, which was demolished in 1935 to make way for the current triumphal arch, which was inaugurated in September 1936." +
                                "The current arch has a height of 27 metres and was built after the plans of the architect Petre Antonescu. It has as its foundation a 25 x 11.50 metres rectangle. The sculptures with which the facades are decorated were created by famous Romanian sculptors such as Ion Jalea and Dimitrie Paciurea. Nowadays, military parades are held beneath the arch each 1 December, with the occasion of Romania's national holiday.",
                        "Herăstrău Park is a large park on the northern side of Bucharest, Romania, around Lake Herăstrău, one of the lakes formed by the Colentina River.",
                        "The Old Princely Court, built as a palace or residence during the rule of Vlad III Dracula in 1459. Archaeological excavations started in 1953, and now the site is operated by the Muzeul Municipiului Bucuresti in the historic centre of Bucharest, Romania. Vlad the Impaler's reign was dominated by conflicts with the Turks, hence \"The obligation to permanently watch over and protect the southern boundary, the Danube, made him stay in the fortified town on the Dimbovita banks\". He issued a Latin document on 13 June 1458 from the area of current Bucharest. Then, on 20 September 1459, he issued a document in Slavonic, specifically referring to the \"fortress\" in Bucharest, his \"princely residence\". Other documents were issued in 1460 and 1461. Vlad would have been accompanied by his family, courtiers, and an army corps.",
                        "Tineretului Park is a large public park in southern Bucharest. Aside from green areas, the park contains a number of playgrounds as well as a navigable lake, utilised by leisure boats in summer. Tineretului Park contains the Sala Polivalentă, one of Bucharest's largest multi-purpose halls, used for concerts and indoor sporting events.",
                        "The National Museum of Romanian History is a museum located on Calea Victoriei in Bucharest, Romania, which contains Romanian historical artifacts from prehistoric times up to modern times. The museum is located inside the former Postal Services Palace, which also houses a philatelic museum. With a surface of over 8,000 square meters, the museum has approx. 60 valuable exhibition rooms. The permanent displays include a plaster cast of the entirety of Trajan's Column, the Romanian Crown Jewels, and the Pietroasele treasure.",
                        "The National Library of Romania is the national library of Romania. It is intended to be the ... After 1859 Union, the library has reach the national statute",
                        "Văcărești Nature Park (Romanian: Parcul Natural Văcărești) is a nature park in Bucharest, Romania, containing the wetlands surrounding Lake Văcărești."
                };

                int[] myImageList = new int[]{
//                        Casa poporului
                        R.drawable.palat_popor,
//                        Ateneul Roman
                        R.drawable.ateneul,
//                        Stravopoleus church
                        R.drawable.stavropoleos,
//                        Arcul de triumf
                        R.drawable.arcul_triumf,
//                        Parc Herastrau
                        R.drawable.herastrau,
//                        Curtea veche
                        R.drawable.curtea_veche,
//                        Parc tineretului
                        R.drawable.tineret,
//                        muzeul de istorie
                        R.drawable.muzeu_istorie,
//                        biblioteca nationala
                        R.drawable.biblioteca,
//                        berceni
                        R.drawable.vacaresti};

                int i = 0;
                for (LatLng coordinate : allPoints) {
                    Marker marker = googleMap.addMarker(new MarkerOptions()
                            .position(coordinate)
                            .title(markerInfo.get(i).getLocationTitle())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .snippet(markerInfo.get(i).getLocationShortDescription())

                    );
                    googleMap.setOnInfoWindowClickListener(MapViewFragment.this);

                    mMarkerInfo.put(marker, new DescriptionImagePair(descriptions[i], myImageList[i]));
                    mMarkerPosition.put(i, marker);
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
        extras.putString("MARKER_DESCRIPTION", mMarkerInfo.get(marker).getDescription());
        extras.putInt("MARKER_PHOTO", mMarkerInfo.get(marker).getImageAddress());
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
