package btsio.com.sointoumap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import btsio.com.sointoumap.objects.Pharmacie;

public class GoogleMapPharmacie extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng currentLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_pharmacie);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Get id pharm
        double lat = getIntent().getDoubleExtra("lat", -34f);
        double longi = getIntent().getDoubleExtra("long", 151);
        currentLatLng = new LatLng(lat, longi);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(currentLatLng.latitude, currentLatLng.longitude);
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        // Set pharmacies marker
        setPharmacieLocation(mMap);

        // move camera to selected pharmacie
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15f));
    }

    public void setPharmacieLocation(GoogleMap googleMap){
        for (Pharmacie pharmacie : Repository.getPharmacies()) {
            LatLng latLng = new LatLng(pharmacie.getLocation().getLatitude(), pharmacie.getLocation().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(latLng).title(pharmacie.getNom())
                    .snippet("Distance : " + pharmacie.getDistanceString() + " km")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pharmacie)));
        }
    }
}
