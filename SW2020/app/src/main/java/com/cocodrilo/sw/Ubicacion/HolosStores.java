package com.cocodrilo.sw.Ubicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.cocodrilo.sw.Modules;
import com.cocodrilo.sw.Preferences;
import com.cocodrilo.sw.R;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class HolosStores extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {




    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;
    private GeofencingClient geofencingClient;
    private GeofencesHelper geofenceHelper;
    public static final String URL_MAP = "https://app.holoscomm.com/holos/app/AndroidServices/GetAllRutas.php?idUser=";
    private RequestQueue queue;
    TextView titleToolbar;

    public Button checkins;

    private float GEOFENCE_RADIUS = 300;//15;
    private String GEOFENCE_ID = "SOME_GEOFENCE_ID";

    private int FINE_LOCATION_ACCESS_REQUEST_CODE = 10001;
    private int BACKGROUND_LOCATION_ACCESS_REQUEST_CODE = 10002;

    public static HolosStores aHolosStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holos_stores);

        checkins = (Button) findViewById(R.id.btn_checkin);

        //Toolbar
        titleToolbar = (TextView)findViewById(R.id.toolbar_title);
        titleToolbar.setText("Rutas");
        titleToolbar.setTextSize(20);
        titleToolbar.setTextColor(titleToolbar.getContext().getResources().getColor(R.color.white));



        queue = Volley.newRequestQueue(this);

        aHolosStore = this;

        //pasar variables por bundle


       ///fin de pasar variables


        //  loadUserView(Preferences.getId(this));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        geofencingClient = LocationServices.getGeofencingClient(this);
        geofenceHelper = new GeofencesHelper(this);




    }







    @Override
    public void onMapLongClick(LatLng latLng) {

        if (Build.VERSION.SDK_INT >= 29) {
            //We need background permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                handleMapLongClick(latLng);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                    //We show a dialog and ask for permission
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, BACKGROUND_LOCATION_ACCESS_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, BACKGROUND_LOCATION_ACCESS_REQUEST_CODE);
                }
            }

        } else {
            handleMapLongClick(latLng);
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng home = new LatLng(19.432689, -99.133208);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 16));

        loadUserMap(Preferences.getId(this));


        enableUserLocation();

        mMap.setOnMapLongClickListener(this);



    }

    private void loadUserMap(String id) {




    }

    private void enableUserLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            //Ask for permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                //We need to show user a dialog for displaying why the permission is needed and then ask for the permission...
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == FINE_LOCATION_ACCESS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //We have the permission
                mMap.setMyLocationEnabled(true);
            } else {
                //We do not have the permission..

            }
        }

        if (requestCode == BACKGROUND_LOCATION_ACCESS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //We have the permission
                Toast.makeText(this, "You can add geofences...", Toast.LENGTH_SHORT).show();
            } else {
                //We do not have the permission..
                Toast.makeText(this, "Background location access is neccessary for geofences to trigger...", Toast.LENGTH_SHORT).show();
            }
        }
    }




    private void handleMapLongClick(LatLng latLng) {
        mMap.clear();
        addMarker(latLng);
        addCircle(latLng, GEOFENCE_RADIUS);
        addGeofence(latLng, GEOFENCE_RADIUS);
    }



    private void addMarker(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        mMap.addMarker(markerOptions);
    }

    private void addCircle(LatLng latLng, float radius) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.argb(255, 255, 0, 0));
        circleOptions.fillColor(Color.argb(64, 255, 0, 0));
        circleOptions.strokeWidth(4);
        mMap.addCircle(circleOptions);
    }


    @SuppressLint("MissingPermission")
    private void addGeofence(LatLng latLng, float radius) {

        Geofence geofence = geofenceHelper.getGeofence(GEOFENCE_ID, latLng, radius, Geofence.GEOFENCE_TRANSITION_ENTER |
                Geofence.GEOFENCE_TRANSITION_DWELL | Geofence.GEOFENCE_TRANSITION_EXIT);
        GeofencingRequest geofencingRequest = geofenceHelper.getGeofencingRequest(geofence);
        PendingIntent pendingIntent = geofenceHelper.getPendingIntent();

        geofencingClient.addGeofences(geofencingRequest, pendingIntent)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.e(TAG, "onSuccess: Geofence Added...");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String errorMessage = geofenceHelper.getErrorString(e);
                        Log.e(TAG, "onFailure: " + errorMessage);
                    }
                });
    }




    public void toastPrint(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public String getText(EditText text) {
        return text.getText().toString().trim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        aHolosStore = null;
    }

    public void exitLocation(){
        checkins.setEnabled(false);

    }

    public void enterLocation() {
        checkins.setEnabled(true);
        Intent modules = new Intent(this, Modules.class);

        final TextView textViewid = findViewById(R.id.idpromotor);
        final TextView textViewName = findViewById(R.id.nomtienda);
        final TextView textViewcadena = findViewById(R.id.cadena);
        final TextView textViewidformatos = findViewById(R.id.idformatoimpr);
        final TextView textVieworden = findViewById(R.id.orden);
        final TextView textViewidplaza = findViewById(R.id.idplaza);
        final TextView textViewidcadena = findViewById(R.id.idcadena);
        final TextView textViewidtienda = findViewById(R.id.idtienda);
        final TextView textViewfecharuta = findViewById(R.id.fecharutas);
        final TextView textViewdte = findViewById(R.id.dte);
        final TextView textViewplaza = findViewById(R.id.plaza);
        final TextView textViewpromotor = findViewById(R.id.promotor);

        String id = textViewid.getText().toString();
        int idpromotor = Integer.parseInt(id);

        String nametienda = textViewName.getText().toString();
        String cadena = textViewcadena.getText().toString();
        String idf = textViewidformatos.getText().toString();
        int idformato = Integer.parseInt(idf);
        String orden = textVieworden.getText().toString();
        String idp = textViewidplaza.getText().toString();
        int idplaza = Integer.parseInt(idp);
        String idc = textViewidcadena.getText().toString();
        int idCadena = Integer.parseInt(idc);
        String idt = textViewidtienda.getText().toString();
        int idtienda = Integer.parseInt(idt);
        String fecharut = textViewfecharuta.getText().toString();
        String dte = textViewdte.getText().toString();
        String plaza = textViewplaza.getText().toString();
        String promotor = textViewpromotor.getText().toString();


        modules.putExtra("idPromotor", idpromotor);
        modules.putExtra("Tienda", nametienda);
        modules.putExtra("Cadena", cadena);
        modules.putExtra("idFormato", idformato);
        modules.putExtra("Orden", orden);
        modules.putExtra("idPlaza", idplaza);
        modules.putExtra("idCadena", idCadena);
        modules.putExtra("idTienda", idtienda);
        modules.putExtra("FechaRuta", fecharut);
        modules.putExtra("DTE", dte);
        modules.putExtra("Plaza", plaza);
        modules.putExtra("Promotor", promotor);
        startActivity(modules);

    }





}