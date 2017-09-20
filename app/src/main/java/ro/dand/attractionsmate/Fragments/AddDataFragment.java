package ro.dand.attractionsmate.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import ro.dand.attractionsmate.Data.MarkerInfoRepository;
import ro.dand.attractionsmate.Models.MarkerInfo;
import ro.dand.attractionsmate.R;

public class AddDataFragment extends Fragment {
    MarkerInfoRepository markerInfoRepository = new MarkerInfoRepository();
    ArrayList<MarkerInfo> markerInfo = markerInfoRepository.getAllMarkerInfo();
    EditText locationTitle, locationShortDescription, locationLat, locationLng, locationLongDescription;
    Button insertBtn;

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

        locationTitle = (EditText) view.findViewById(R.id.locationName);
        locationShortDescription = (EditText) view.findViewById(R.id.shortDescription);
        locationLat = (EditText) view.findViewById(R.id.locationLat);
        locationLng = (EditText) view.findViewById(R.id.locationLng);
        locationLongDescription = (EditText) view.findViewById(R.id.longDescription);
        insertBtn = (Button) view.findViewById(R.id.insertBtn);
        insertLocation();

        return view;
    }

    public void insertLocation() {
        try {
            insertBtn.setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            markerInfo.add(
                                    new MarkerInfo(
                                            locationTitle.getText().toString(),
                                            locationShortDescription.getText().toString(),
                                            new LatLng(Double.parseDouble(locationLat.getText().toString()), Double.parseDouble(locationLng.getText().toString())),
                                            locationLongDescription.getText().toString(),
                                            R.drawable.no_preview
                                    ));
                        }
                    }
            );
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
