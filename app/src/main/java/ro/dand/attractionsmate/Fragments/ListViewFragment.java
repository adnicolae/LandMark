package ro.dand.attractionsmate.Fragments;

/*
 * Class to model the List fragment.
 * Outputs a list of locations.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import ro.dand.attractionsmate.Data.MarkerInfoRepository;
import ro.dand.attractionsmate.Database.LandmarkDbContract;
import ro.dand.attractionsmate.Database.LandmarkDbHelper;
import ro.dand.attractionsmate.Models.LocationsAdapter;
import ro.dand.attractionsmate.Models.MarkerInfo;
import ro.dand.attractionsmate.R;
import ro.dand.attractionsmate.Activities.ScrollingActivity;

public class ListViewFragment extends android.support.v4.app.ListFragment implements OnItemClickListener {
    LandmarkDbHelper mDbHelper;
    MarkerInfoRepository markerInfoRepository = new MarkerInfoRepository();
    ArrayList<MarkerInfo> markerInfo = markerInfoRepository.getAllMarkerInfo();
    ArrayList<MarkerInfo> updatedMarkerInfo = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        mDbHelper = new LandmarkDbHelper(getActivity());
        retrieveDataFromDb();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new LocationsAdapter(getActivity(), android.R.layout.simple_list_item_2, updatedMarkerInfo));
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Intent intent = new Intent(this.getActivity(), ScrollingActivity.class);

        Bundle extras = new Bundle();
        extras.putString("MARKER_TITLE", updatedMarkerInfo.get(position).getMarkerTitle());
        extras.putString("MARKER_DESCRIPTION", updatedMarkerInfo.get(position).getMarkerLongDescription());
        extras.putInt("MARKER_PHOTO", updatedMarkerInfo.get(position).getMarkerImageAddress());
        extras.putDouble("MARKER_LATITUDE", updatedMarkerInfo.get(position).getCoordinates().latitude);
        extras.putDouble("MARKER_LONGITUDE", updatedMarkerInfo.get(position).getCoordinates().longitude);
        intent.putExtras(extras);
        startActivity(intent);
    }
    // NOTE: issue parsing/storing R.drawable both as string and int
    public void retrieveDataFromDb() {
        Cursor cursor = mDbHelper.getAllData();

        // Check if data is available
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
        }

        while (cursor.moveToNext()) {
            cursor.getString(0);
            updatedMarkerInfo.add(
                    new MarkerInfo(
                            cursor.getString(1),
                            cursor.getString(2),
                            new LatLng(cursor.getDouble(3), cursor.getDouble(4)),
                            cursor.getString(5),
                            R.drawable.no_preview
                    )
            );
        cursor.getString(6);
        }
        cursor.close();
    }
}
