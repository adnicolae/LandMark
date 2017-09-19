package ro.dand.attractionsmate.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import ro.dand.attractionsmate.Data.MarkerInfoRepository;
import ro.dand.attractionsmate.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Andrew on 3/23/2017.
 */

public class LocationsAdapter extends ArrayAdapter<MarkerInfo> {
    private ArrayList<MarkerInfo> items;
    private LocationViewHolder locationViewHolder;

    private class LocationViewHolder{
        TextView title;
        TextView description;
    }

    public LocationsAdapter(Context context, int id, ArrayList<MarkerInfo> items){
        super(context, id, items);
        this.items = items;
    }

    @Override
    public View getView (int pos, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(android.R.layout.simple_list_item_2, null);
            locationViewHolder = new LocationViewHolder();
            locationViewHolder.description = (TextView) v.findViewById(android.R.id.text2);
            locationViewHolder.title = (TextView) v.findViewById(android.R.id.text1);
            v.setTag(locationViewHolder);
        } else {
            locationViewHolder = (LocationViewHolder) v.getTag();
        }


        MarkerInfo markerInfo = items.get(pos);
        if (markerInfo != null) {
            locationViewHolder.description.setText(markerInfo.getMarkerShortDescription());
            locationViewHolder.title.setText(markerInfo.getMarkerTitle());
        }

        return v;
    }
}
