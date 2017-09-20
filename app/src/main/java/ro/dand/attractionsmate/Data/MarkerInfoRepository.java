package ro.dand.attractionsmate.Data;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;

import ro.dand.attractionsmate.Models.MarkerInfo;

import ro.dand.attractionsmate.R;

/**
 * Created by Andrei Nicolae on 9/19/2017.
 */

public class MarkerInfoRepository {

    private final static ArrayList<MarkerInfo> mMarkerInfo =
            new ArrayList<>(Arrays.asList(
                    new MarkerInfo("Palace of the Parliament", "Palace and architecture", new LatLng(44.427504, 26.087351), "The world’s second largest administrative building is The Palace of the " +
                            "Parliament. It measures 330,000 m2 (~3,550,000 sq ft) and is regarded as former dictator’s, " +
                            "Nicolae Ceaușescu, most cherished jewel. By definition is one of the " +
                            "most significant sightseeing objectives in Bucharest, the capital of Romania. " +
                            "At the border of reason, kitsch and neoclassicism, the building reflects " +
                            "best what Romania underwent in the communist period. Built in a time when the people of Romania have been destitute, " +
                            "it has been regarded with reticence. It was named The People’s House, because it was built by the folk " +
                            "(and by no means for the folk). (http://travelguideromania.com/palace-parliament/)", "palat_popor"),
                    new MarkerInfo("Romanian Athenaeum", "Concert hall, culture and theatre", new LatLng(44.441381, 26.097396), "The Romanian Athenaeum, dubbed the Romanian temple of arts, is an architectural jewel the uniqueness and merits of which have been acknowledged by the fact the building was classified a historical monument in 2004 and part of the European patrimony in 2007. Located on the Victory Avenue, the Romanian Athenaeum is a tourist sight the importance of which does not come down exclusively to its being one of the most eye-catching architectural marvels of the capital, but also to the fact it is home to one of the oldest and most important cultural institutions in Bucharest, a major contributor to the artistic scene of Bucharest: the George Enescu Philharmonic Orchestra.",
                            "ateneul"),
                    new MarkerInfo("Stavropoleos Monastery", "Church and history", new LatLng(44.431803, 26.09887), "The Stavropoleos Monastery is an Eastern Orthodox monastery for nuns in central Bucharest, Romania. Its church is built in Brâncovenesc style. The patrons of the church (the saints to whom the church is dedicated) are St. Archangels Michael and Gabriel. The name Stavropoleos is a Romanian rendition of a Greek word, Stauropolis, meaning 'The city of the Cross'. One of the monastery's constant interests is Byzantine music, expressed through its choir and the largest collection of Byzantine music books in Romania.",
                            "stavropoleos"),
                    new MarkerInfo("Arch of Triumph", "History and architecture", new LatLng(44.467066, 26.077823), "Arcul de Triumf is a triumphal arch located in the northern part of Bucharest, on the Kiseleff Road." +
                            "The first, wooden, triumphal arch was built hurriedly, after Romania gained its independence (1878), so that the victorious troops could march under it. Another temporary arch was built on the same site, in 1922, after World War I, which was demolished in 1935 to make way for the current triumphal arch, which was inaugurated in September 1936." +
                            "The current arch has a height of 27 metres and was built after the plans of the architect Petre Antonescu. It has as its foundation a 25 x 11.50 metres rectangle. The sculptures with which the facades are decorated were created by famous Romanian sculptors such as Ion Jalea and Dimitrie Paciurea. Nowadays, military parades are held beneath the arch each 1 December, with the occasion of Romania's national holiday.",
                            "arcul_triumf"),
                    new MarkerInfo("Herăstrău Park", "Park", new LatLng(44.468469, 26.080531), "Herăstrău Park is a large park on the northern side of Bucharest, Romania, around Lake Herăstrău, one of the lakes formed by the Colentina River.",
                            "herastrau"),
                    new MarkerInfo("Old Princely Court", "Museum", new LatLng(44.430119, 26.101326), "The Old Princely Court, built as a palace or residence during the rule of Vlad III Dracula in 1459. Archaeological excavations started in 1953, and now the site is operated by the Muzeul Municipiului Bucuresti in the historic centre of Bucharest, Romania. Vlad the Impaler's reign was dominated by conflicts with the Turks, hence \"The obligation to permanently watch over and protect the southern boundary, the Danube, made him stay in the fortified town on the Dimbovita banks\". He issued a Latin document on 13 June 1458 from the area of current Bucharest. Then, on 20 September 1459, he issued a document in Slavonic, specifically referring to the \"fortress\" in Bucharest, his \"princely residence\". Other documents were issued in 1460 and 1461. Vlad would have been accompanied by his family, courtiers, and an army corps.",
                            "curtea_veche"),
                    new MarkerInfo("Tineretului Park", "Park", new LatLng(44.405718, 26.106061), "Tineretului Park is a large public park in southern Bucharest. Aside from green areas, the park contains a number of playgrounds as well as a navigable lake, utilised by leisure boats in summer. Tineretului Park contains the Sala Polivalentă, one of Bucharest's largest multi-purpose halls, used for concerts and indoor sporting events.",
                            "tineret"),
                    new MarkerInfo("National Museum of Romanian History", "Museum, history", new LatLng(44.431447, 26.097454), "The National Museum of Romanian History is a museum located on Calea Victoriei in Bucharest, Romania, which contains Romanian historical artifacts from prehistoric times up to modern times. The museum is located inside the former Postal Services Palace, which also houses a philatelic museum. With a surface of over 8,000 square meters, the museum has approx. 60 valuable exhibition rooms. The permanent displays include a plaster cast of the entirety of Trajan's Column, the Romanian Crown Jewels, and the Pietroasele treasure.",
                            "muzeu_istorie"),
                    new MarkerInfo("Biblioteca Nationala", "National Library", new LatLng(44.425594, 26.11017), "The National Library of Romania is the national library of Romania. It is intended to be the ... After 1859 Union, the library has reach the national statute",
                            "biblioteca"),
                    new MarkerInfo("Văcărești Nature Park", "Park", new LatLng(44.401017, 26.133877), "Văcărești Nature Park (Romanian: Parcul Natural Văcărești) is a nature park in Bucharest, Romania, containing the wetlands surrounding Lake Văcărești.",
                            "vacaresti")
                    )
            );

    public MarkerInfoRepository() {
    }

    public ArrayList<MarkerInfo> getAllMarkerInfo() {
        return mMarkerInfo;
    }

    public ArrayList<LatLng> getAllMarkerPointsCoordinates() {
        ArrayList<LatLng> points = new ArrayList<>();

        for (MarkerInfo markerInfo : mMarkerInfo) {
            points.add(markerInfo.getCoordinates());
        }

        return points;
    }
}
