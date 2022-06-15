package com.cocodrilo.sw.Ubicacion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeonfenceBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "GeofenceBroadcastReceiv";


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        NotificationHelper notificationHelper = new NotificationHelper(context);


        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if(geofencingEvent.hasError()){
            Log.d(TAG, "onReceive: Error receiving Geofences event");
            return;
        }



        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
        //   Location location = geofencingEvent.getTriggeringLocation();
        int transitionType = geofencingEvent.getGeofenceTransition();


        switch (transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                notificationHelper.sendHighPriorityNotification("Usted esta entrando a la Tienda","", HolosStores.class);
                if (HolosStores.aHolosStore != null) HolosStores.aHolosStore.enterLocation();

                break;
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                notificationHelper.sendHighPriorityNotification("Usted esta sobre el area de la tienda","", HolosStores.class);
                if (HolosStores.aHolosStore != null) HolosStores.aHolosStore.enterLocation();

                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                notificationHelper.sendHighPriorityNotification("Usted esta saliendo del area de la Tienda","", HolosStores.class);
                if (HolosStores.aHolosStore != null) HolosStores.aHolosStore.exitLocation();
                break;

        }


    }


}