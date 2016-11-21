package com.transport_kh.transport_kh.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.transport_kh.transport_kh.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double[] arrLat = new Double[]{49.984614, 49.984905, 49.981250, 49.978994, 49.977203, 49.972666,49.967388,
            49.961448, 49.956158, 49.951179, 49.945923, 49.940149, 49.935358, 49.930830, 49.930460, 49.930873,
            49.934648, 49.936107, 49.934349, 49.931377, 49.930176, 49.931453, 49.936465, 49.940449, 49.947068,
            49.953085, 49.957423, 49.962097, 49.968201, 49.973281, 49.978210, 49.979946, 49.982032, 49.985791,
            49.984614
    };
    Double[] arrLng = new Double[]{36.231342, 36.235333, 36.242183, 36.247494, 36.251374, 36.257776, 36.258952,
            36.258786, 36.259384, 36.259906, 36.259645, 36.260479,  36.261195, 36.261151, 36.256375, 36.250805,
            36.243981, 36.240887, 36.244091, 36.250155, 36.253683, 36.262590, 36.261529, 36.260870, 36.260514,
            36.260215, 36.259811, 36.259107, 36.259482, 36.257552, 36.249858, 36.245874, 36.241014, 36.235596,
            36.231342
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = null;
        for (int i = 0; i < arrLng.length; i++) {
            sydney = new LatLng(arrLat[i], arrLng[i]);
            mMap.addMarker(new MarkerOptions().position(sydney));
            //map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
