package ro.dand.attractionsmate.Database;

import android.provider.BaseColumns;

/**
 * Created by Andrei Nicolae on 9/20/2017.
 */

public final class LandmarkDbContract {
    public LandmarkDbContract(){}

    public static class MarkerInfoEntry implements BaseColumns {
        public static final String TABLE_NAME =  "marker_info";
        public static final String COL_TITLE = "Title";
        public static final String COL_SHORT_DESCRIPTION = "ShortDescription";
        public static final String COL_LATITUDE = "Latitude";
        public static final String COL_LONGITUDE = "Longitude";
        public static final String COL_LONG_DESCRIPTION = "LongDescription";
        public static final String COL_IMAGE_ADDRESS = "ImageAddress";
    }
}
