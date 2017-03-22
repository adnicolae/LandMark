package ro.dand.attractionsmate;

/*
 * Class to model the List fragment.
 * Outputs a list of locations.
 */

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;

import java.util.List;

public class myListFragment extends android.support.v4.app.ListFragment implements OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Locations, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
//        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this.getActivity(), ScrollingActivity.class);
        Marker myMarker = MapViewFragment.mMarkerPosition.get(position);
        Bundle extras = new Bundle();
        extras.putString("MARKER_TITLE", myMarker.getTitle());
        extras.putString("MARKER_DESCRIPTION", MapViewFragment.mMarkerInfo.get(myMarker).getDescription());
        extras.putInt("MARKER_PHOTO", MapViewFragment.mMarkerInfo.get(myMarker).getImageAddress());
//        extras.putParcelable("MARKER_POSITION", marker.getPosition());
        extras.putDouble("MARKER_LATITUDE", myMarker.getPosition().latitude);
        extras.putDouble("MARKER_LONGITUDE", myMarker.getPosition().longitude);
//        intent.putExtra(EXTRA_MESSAGE, marker.getSnippet());
//        intent.putExtra(MARKER_TITLE, marker.getTitle());
//        intent.pu
        intent.putExtras(extras);
        startActivity(intent);
    }
}
