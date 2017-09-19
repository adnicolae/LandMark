package ro.dand.attractionsmate.Fragments;

/*
 * Class to model the List fragment.
 * Outputs a list of locations.
 */

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.model.Marker;

import ro.dand.attractionsmate.Models.LocationsAdapter;
import ro.dand.attractionsmate.R;
import ro.dand.attractionsmate.Activities.ScrollingActivity;

public class ListViewFragment extends android.support.v4.app.ListFragment implements OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new LocationsAdapter(getActivity(), android.R.layout.simple_list_item_2, MapViewFragment.mTitleDescriptionPair));
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Intent intent = new Intent(this.getActivity(), ScrollingActivity.class);
        Marker myMarker = MapViewFragment.mMarkerPosition.get(position);

        Bundle extras = new Bundle();
        extras.putString("MARKER_TITLE", myMarker.getTitle());
        extras.putString("MARKER_DESCRIPTION", MapViewFragment.mMarkerInfo.get(myMarker).getDescription());
        extras.putInt("MARKER_PHOTO", MapViewFragment.mMarkerInfo.get(myMarker).getImageAddress());
        extras.putDouble("MARKER_LATITUDE", myMarker.getPosition().latitude);
        extras.putDouble("MARKER_LONGITUDE", myMarker.getPosition().longitude);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
