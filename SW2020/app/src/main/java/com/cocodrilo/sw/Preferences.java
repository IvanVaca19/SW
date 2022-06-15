package com.cocodrilo.sw;


import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String PREFERENCES = "APP";

    public static final String KEY_ID = "idPromotor";
    public static final String KEY_ID_STORE = "idTienda";
    public static final String KEY_ID_DEPARTAMENT = "idDepartamento";
    public static  final String KEY_ID_RUTA = "idRuta";
    public static  final String KEY_ID_FORMATO = "idFormato";
    public static  final  String KEY_ID_PRODUCTO = "idProducto";
    public static final String KEY_ID_DEPARTAMENTO_EXHIBICION = "idDepartamentoExhibicion";
    public static final String KEY_ID_PLAZA = "idPlaza";
    public static  final String KEY_ID_CADENA = "idCadena";
    public static final String KEY_USER_NAME = "Usuario";
    public static final String KEY_ID_MARCA = "idMarca";
    public static final String KEY_ID_TIPOEVIDENCIA = "idTipoEvidencia";




    public static void setIdTipoEvidencia(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_TIPOEVIDENCIA,id).apply();


    }

    public static String getIdTipoEvidencia(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_TIPOEVIDENCIA,"");
    }






    public static void setIdMarca(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_MARCA,id).apply();


    }

    public static String getIdMarca(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_MARCA,"");
    }




    public static void setIdFormato(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_FORMATO,id).apply();


    }

    public static String getIdFormato(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_FORMATO,"");
    }



    public static void setIdProducto(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_PRODUCTO,id).apply();


    }

    public static String getIdProducto(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_PRODUCTO,"");
    }



    public static void setIdDepartamentoExhibicion(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_DEPARTAMENTO_EXHIBICION,id).apply();


    }

    public static String getIdDepartamentoExhibicion(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_DEPARTAMENTO_EXHIBICION,"");
    }





    public static void setIdPlaza(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_PLAZA,id).apply();


    }

    public static String getIdPlaza(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_PLAZA,"");
    }



    public static void setIdCadena(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_CADENA,id).apply();


    }

    public static String getIdCadena(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_CADENA,"");
    }



    public static void setId(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID,id).apply();


    }

    public static String getId(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID,"");
    }



    public static void setUserName(Context c , String userName){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_USER_NAME,userName).apply();
    }

    public static String getUserName(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_USER_NAME,"");
    }



    public static void setIdStore(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_STORE,id).apply();


    }

    public static String getIdStore(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_STORE,"");
    }



    public static void setIdDepartament(Context c,String id){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_DEPARTAMENT,id).apply();


    }

    public static String getIdDepartament(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_DEPARTAMENT,"");
    }


    public static void setIdRuta(Context c,String id){


        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ID_RUTA,id).apply();


    }

    public static String getIdRuta(Context c){
        SharedPreferences preferences = c.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        return preferences.getString(KEY_ID_RUTA,"");
    }






}
