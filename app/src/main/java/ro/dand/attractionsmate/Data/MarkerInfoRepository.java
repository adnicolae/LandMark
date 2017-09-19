package ro.dand.attractionsmate.Data;

import java.util.ArrayList;
import java.util.Arrays;

import ro.dand.attractionsmate.Models.TitleDescriptionPair;

/**
 * Created by Andrei Nicolae on 9/19/2017.
 */

public class MarkerInfoRepository {

    private final static ArrayList<TitleDescriptionPair> mTitleDescriptionPair =
            new ArrayList<>(Arrays.asList(
                    new TitleDescriptionPair("Palace of the Parliament", "Palace and architecture"),
                    new TitleDescriptionPair("Romanian Athenaeum", "Concert hall, culture and theatre"),
                    new TitleDescriptionPair("Stavropoleos Monastery", "Church and history"),
                    new TitleDescriptionPair("Arch of Triumph", "History and architecture"),
                    new TitleDescriptionPair("Herăstrău Park", "Park"),
                    new TitleDescriptionPair("Old Princely Court", "Museum"),
                    new TitleDescriptionPair("Tineretului Park", "Park"),
                    new TitleDescriptionPair("National Museum of Romanian History", "Museum, history"),
                    new TitleDescriptionPair("Biblioteca Nationala", "National Library"),
                    new TitleDescriptionPair("Văcărești Nature Park", "Park"))
            );

    public MarkerInfoRepository() {
    }

    public ArrayList<TitleDescriptionPair> getAllPairs() {
        return mTitleDescriptionPair;
    }
}
