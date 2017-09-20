package ro.dand.attractionsmate.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import ro.dand.attractionsmate.Data.MarkerInfoRepository;
import ro.dand.attractionsmate.Database.LandmarkDbHelper;
import ro.dand.attractionsmate.Models.MarkerInfo;
import ro.dand.attractionsmate.R;

public class AddDataFragment extends Fragment {
    MarkerInfoRepository markerInfoRepository = new MarkerInfoRepository();
    ArrayList<MarkerInfo> markerInfo = markerInfoRepository.getAllMarkerInfo();
    EditText locationTitle, locationShortDescription, locationLat, locationLng, locationLongDescription;
    Button insertBtn;
    FloatingActionButton geocoderFAB;
    LandmarkDbHelper mDbHelper;

    public AddDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_data, container, false);

        mDbHelper = new LandmarkDbHelper(getActivity());

        locationTitle = (EditText) view.findViewById(R.id.locationName);
        locationShortDescription = (EditText) view.findViewById(R.id.shortDescription);
        locationLat = (EditText) view.findViewById(R.id.locationLat);
        locationLng = (EditText) view.findViewById(R.id.locationLng);
        locationLongDescription = (EditText) view.findViewById(R.id.longDescription);
        geocoderFAB = (FloatingActionButton) view.findViewById(R.id.geocoderFAB);
        insertBtn = (Button) view.findViewById(R.id.insertBtn);
        insertLocation();
        openLinkOnFabClick();

        return view;
    }

    public void insertLocation() {
        try {
            insertBtn.setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            boolean isInserted = mDbHelper.insertData(
                                    locationTitle.getText().toString(),
                                    locationShortDescription.getText().toString(),
                                    Double.parseDouble(locationLat.getText().toString()),
                                    Double.parseDouble(locationLng.getText().toString()),
                                    locationLongDescription.getText().toString(),
                                    "no_preview"
                            );

                            if (isInserted) {
                                Toast.makeText(getActivity(), "Data inserted.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), "Data NOT inserted.", Toast.LENGTH_LONG).show();
                            }
//                            markerInfo.add(
//                                    new MarkerInfo(
//                                            locationTitle.getText().toString(),
//                                            locationShortDescription.getText().toString(),
//                                            new LatLng(Double.parseDouble(locationLat.getText().toString()), Double.parseDouble(locationLng.getText().toString())),
//                                            locationLongDescription.getText().toString(),
//                                            R.drawable.no_preview
//                                    ));

                            // toast here
                        }
                    }
            );
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void openLinkOnFabClick() {
        geocoderFAB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("https://google-developers.appspot.com/maps/documentation/utils/geocoder/"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
        );
    }

}
