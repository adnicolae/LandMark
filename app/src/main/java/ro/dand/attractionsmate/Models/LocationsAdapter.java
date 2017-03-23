package ro.dand.attractionsmate.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import java.util.ArrayList;

import ro.dand.attractionsmate.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Andrew on 3/23/2017.
 */

public class LocationsAdapter extends ArrayAdapter<TitleDescriptionPair> {
    private ArrayList<TitleDescriptionPair> items;
    private LocationViewHolder locationViewHolder;

    private class LocationViewHolder{
        TextView title;
        TextView description;
    }

    public LocationsAdapter(Context context, int id, ArrayList<TitleDescriptionPair> items){
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


        TitleDescriptionPair titleDescriptionPair = items.get(pos);
        if (titleDescriptionPair != null) {
            locationViewHolder.description.setText(titleDescriptionPair.getLocationShortDescription());
            locationViewHolder.title.setText(titleDescriptionPair.getLocationTitle());
        }

        return v;
    }
}
