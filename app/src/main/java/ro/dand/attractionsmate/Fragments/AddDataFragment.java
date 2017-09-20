package ro.dand.attractionsmate.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import ro.dand.attractionsmate.Data.MarkerInfoRepository;
import ro.dand.attractionsmate.Models.MarkerInfo;
import ro.dand.attractionsmate.R;

public class AddDataFragment extends Fragment {
    MarkerInfoRepository markerInfoRepository = new MarkerInfoRepository();
    ArrayList<MarkerInfo> markerInfo = markerInfoRepository.getAllMarkerInfo();


    public AddDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        markerInfo.add(new MarkerInfo("TestMarker", "Test", new LatLng(44.450951,26.235211), "Teeeest", R.drawable.vacaresti));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_data, container, false);
    }


}
