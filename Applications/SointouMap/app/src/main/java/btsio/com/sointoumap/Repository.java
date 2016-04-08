package btsio.com.sointoumap;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import btsio.com.sointoumap.objects.Pharmacie;

/**
 * Created by dzak on 05/04/16.
 */
public class Repository {

    private static ArrayList<Pharmacie> pharmacies;

    public static void init(){
        pharmacies = new ArrayList<Pharmacie>();
    }

    public static void initFakePharmacies(){
        LatLng lng = new LatLng(-34, 151);
        Location myLocation = new Location("Moi");
        myLocation.setLatitude(lng.latitude);
        myLocation.setLongitude(lng.longitude);

        pharmacies.add(new Pharmacie("Pharmacie 1", new LatLng(-34.002, 151.02), myLocation));
        pharmacies.add(new Pharmacie("Pharmacie 2", new LatLng(-34.006, 151.008), myLocation));
        pharmacies.add(new Pharmacie("Pharmacie 3", new LatLng(-34.003, 151.002), myLocation));
        pharmacies.add(new Pharmacie("Pharmacie 4", new LatLng(-34.004, 151.004), myLocation));
        pharmacies.add(new Pharmacie("Pharmacie 5", new LatLng(-34.002, 151.04), myLocation));
    }

    public static ArrayList<Pharmacie> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(ArrayList<Pharmacie> newPharmacies){
        pharmacies = newPharmacies;
    }

}
