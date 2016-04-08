package btsio.com.sointoumap.objects;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;

/**
 * Created by dzak on 05/04/16.
 */
public class Pharmacie{

    private static DecimalFormat df = new DecimalFormat("#######0.00");

    private String nom;
    private Location location;
    private float distance;

    public Pharmacie(String m_nom, LatLng m_coord){
        this.nom = m_nom;
        this.location = new Location(m_nom);
        location.setLatitude(m_coord.latitude);
        location.setLongitude(m_coord.longitude);
    }

    public Pharmacie(String m_nom, LatLng m_coord, Location currentLocation){
        this(m_nom, m_coord);

        distance = location.distanceTo(currentLocation);
        // TO km
        distance /= 1000;
    }

    public String getNom() {
        return nom;
    }

    public Location getLocation() {
        return location;
    }

    public float getDistance() {
        return distance;
    }

    public String getDistanceString(){
        return "" + df.format(distance);
    }
}
