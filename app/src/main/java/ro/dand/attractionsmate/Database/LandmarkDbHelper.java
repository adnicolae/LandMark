package ro.dand.attractionsmate.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ro.dand.attractionsmate.Data.MarkerInfoRepository;
import ro.dand.attractionsmate.Models.MarkerInfo;

/**
 * Created by Andrei Nicolae on 9/20/2017.
 */

public class LandmarkDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Landmark.db";
    private MarkerInfoRepository _markerInfoRepository = null;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LandmarkDbContract.MarkerInfoEntry.TABLE_NAME + " (" +
            LandmarkDbContract.MarkerInfoEntry._ID + " INTEGER PRIMARY KEY," +
            LandmarkDbContract.MarkerInfoEntry.COL_TITLE + " VARCHAR2(30)," +
            LandmarkDbContract.MarkerInfoEntry.COL_SHORT_DESCRIPTION + " VARCHAR2(30)," +
            LandmarkDbContract.MarkerInfoEntry.COL_LATITUDE + " DOUBLE," +
            LandmarkDbContract.MarkerInfoEntry.COL_LONGITUDE + " DOUBLE," +
            LandmarkDbContract.MarkerInfoEntry.COL_LONG_DESCRIPTION + " TEXT," +
            LandmarkDbContract.MarkerInfoEntry.COL_IMAGE_ADDRESS + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DELETE TABLE IF EXISTS " + LandmarkDbContract.MarkerInfoEntry.TABLE_NAME;

    public LandmarkDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _markerInfoRepository = new MarkerInfoRepository();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

        // Insert dummy data from repository
        for (MarkerInfo markerInfo : _markerInfoRepository.getAllMarkerInfo()) {
            db.execSQL(
                    "INSERT INTO " + LandmarkDbContract.MarkerInfoEntry.TABLE_NAME +
                    " VALUES (null, \"" + markerInfo.getMarkerTitle() +"\", \"" +
                    markerInfo.getMarkerShortDescription() + "\"," +
                    markerInfo.getCoordinates().latitude + ", " +
                    markerInfo.getCoordinates().longitude + ", \"" +
                    markerInfo.getMarkerLongDescription() + "\", " +
                    markerInfo.getMarkerImageAddress() + ")"

            );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * Facilitates entire quote data retrieval.
     * @return A cursor containing the results statements.
     */
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + LandmarkDbContract.MarkerInfoEntry.TABLE_NAME, null);
        return cursor;
    }
}
