package com.cocodrilo.sw.Ubicacion;

import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.maps.model.LatLng;

public class GeofencesHelper extends ContextWrapper {

    private static  final String TAG = "GeofencesHelper";
    PendingIntent pendingIntent;

    public GeofencesHelper(Context base) {
        super(base);
    }

    public GeofencingRequest getGeofencingRequest(Geofence geofence){
        return  new GeofencingRequest.Builder()
                .addGeofence(geofence)
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .build();



    }

    public Geofence getGeofence(String ID, LatLng latLng, float radius, int transistionTrypes){
        return new Geofence.Builder()
                .setCircularRegion(latLng.latitude, latLng.longitude, radius)
                .setRequestId(ID)
                .setTransitionTypes(transistionTrypes)
                .setLoiteringDelay(300)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
    }

    public PendingIntent getPendingIntent(){

        if(pendingIntent != null){
            return pendingIntent;
        }

        Intent intent = new Intent(this, GeonfenceBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 2607, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        return  pendingIntent;
    }

    public String getErrorString(Exception ex){
        if(ex instanceof ApiException){
            ApiException apiException = (ApiException) ex;
            switch (apiException.getStatusCode()){
                case  GeofenceStatusCodes
                        .GEOFENCE_NOT_AVAILABLE:
                    return "GEOFENCE_NOT_AVAILABLE";
                case  GeofenceStatusCodes
                        .GEOFENCE_TOO_MANY_GEOFENCES:
                    return "GEOFENCE_TOO_MANY_GEOFENCES";
                case  GeofenceStatusCodes
                        .GEOFENCE_TOO_MANY_PENDING_INTENTS:
                    return "GEOFENCE_TOO_MANY_PENDING_INTENTS";

            }
        }

        return ex.getLocalizedMessage();

    }

}
