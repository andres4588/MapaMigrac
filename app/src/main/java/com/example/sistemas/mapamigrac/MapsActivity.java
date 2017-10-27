package com.example.sistemas.mapamigrac;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.sistemas.mapamigrac.api.ApiDatos;
import com.example.sistemas.mapamigrac.models.Oficina;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Retrofit retrofit;
    public final static String TAG = "Datos colombia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        retrofit = new Retrofit.Builder().baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create()).build();
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

    }

    public void cambiarMapa(View v) {
        switch (v.getId()) {
            case R.id.norm:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


                break;

            case R.id.sate:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;


            case R.id.gp2:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
                break;

            case R.id.mark:
                ApiDatos service = retrofit.create(ApiDatos.class);
                Call<List<Oficina>> municipioCall = service.obtenerListaOficinas();


                municipioCall.enqueue(new Callback<List<Oficina>>() {
                    @Override
                    public void onResponse(Call<List<Oficina>> call, Response<List<Oficina>> response) {
                        if (response.isSuccessful()) {
                            List milista = response.body();
                            for (int i = 0; i < milista.size(); i++) {
                                Oficina m = (Oficina) milista.get(i);


                                // Add a marker in Sydney and move the camera
                                LatLng sydney = new LatLng(m.getLatitud(), m.getLongitud());
                                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.avion)).position(sydney).title(m.getNombre()).snippet("Telefono: "+m.getTelefono()));
                                mMap.getUiSettings().setZoomControlsEnabled(true);

                                mMap.getUiSettings().setCompassEnabled(true);
                                mMap.addCircle(new CircleOptions()
                                        .center(new LatLng(m.getLatitud(), m.getLongitud()))
                                        .radius(500)
                                        .strokeColor(Color.RED));


                            }
                        } else {
                            Log.e(TAG, "OnResponse" + response.errorBody());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Oficina>> call, Throwable t) {
                        Log.e(TAG, "OnFailure" + t.getMessage());//mensaje en rojo
                    }
                });//En cola


        }


    }



}
