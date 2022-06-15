package com.cocodrilo.sw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cocodrilo.sw.Adapter.AdapterRutasPromotor;
import com.cocodrilo.sw.Constructs.Rutas;
import com.cocodrilo.sw.Ubicacion.HolosStores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeSW extends AppCompatActivity {

    private static final String TAG = "Home";
    public static final String EXTRA_PROMOTOR = "idPromotor";
    public static final String EXTRA_TIENDA = "Tienda";
    public static final String EXTRA_CADENA= "FormatoCadena";
    public static final String EXTRA_FORMATOS = "idFormato";
    public static final String EXTRA_ORDEN= "Orden";
    public static final String EXTRA_IMAGEN= "imagen";
    public static final String EXTRA_PLAZA = "idPlaza";
    public static final String EXTRA_CADENAS = "idCadena";
    public static final String EXTRA_TIENDAS = "idTienda";
    public static final String EXTRA_FECHARUTA = "FechaRuta";
    public static final String EXTRA_DTE = "DTE";
    public static final String EXTRA_plaza = "Plaza";
    public static final String EXTRA_PROMOTORES = "Promotor";
    public static final String EXTRA_cadena = "FormatoCadena";





    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_RUTAS_SW = "https://app.holoscomm.com/holos/app/AndroidServices/GetAllStoresPromotor.php?user=";
    ImageView user_info;
    TextView rutas;
    ImageView exit;
    String one;



    //a list to store all the products
    List<Rutas> productRutas;
    private AdapterRutasPromotor adapterRoutes;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_s_w);
        user_info = (ImageView)findViewById(R.id.btn_user);
        exit = (ImageView)findViewById(R.id.btn_exit);
        rutas  = (TextView)findViewById(R.id.btn_routes);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.rv_rutas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        user_info = (ImageView)findViewById(R.id.btn_user);
        exit = (ImageView)findViewById(R.id.btn_exit);
        rutas  = (TextView)findViewById(R.id.btn_routes);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.rv_rutas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productRutas = new ArrayList<>();



        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts(Preferences.getId(this));

        user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i = new Intent(getApplicationContext(), UserInfoSW.class);
                //startActivity(i);
            }
        });

        rutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent i = new Intent(getApplicationContext(), HolosStores.class);
                //startActivity(i);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });


    }

    private void loadProducts(String id) {

        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Cargando Sucursales espere un momento...");
        progressDialog.show();


        JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.GET,
                URL_RUTAS_SW+id,null, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject s) {

                // s=s.replace("healthy");
                progressDialog.dismiss();
                try {


                    JSONArray array =s.getJSONArray("datos");

                    for (int i=0; i<array.length();i++){

                        JSONObject object=array.getJSONObject(i);

                        Rutas item=new Rutas(
                                object.getInt("idPromotor"),
                                object.getString("Tienda"),
                                object.getString("FormatoCadena"),
                                object.getInt("idFormato"),
                                object.getString("Orden"),
                                object.getString("imagen"),
                                object.getInt("idPlaza"),
                                object.getInt("idCadena"),
                                object.getInt("idTienda"),
                                object.getString("FechaRuta"),
                                object.getString("DTE"),
                                object.getString("Plaza"),
                                object.getString("Promotor"));

                        productRutas.add(item);

                    }

                    adapterRoutes=new AdapterRutasPromotor(HomeSW.this, productRutas);
                    recyclerView.setAdapter(adapterRoutes);
                    adapterRoutes.setOnItemClickListener(HomeSW.this);
                    //adapterRoutes =new AdapterRutasPromotor(getApplicationContext(), productRutas);
                    //recyclerView.setAdapter(adapterRoutes);



                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this) ;
        requestQueue.add(stringRequest);



    }

    public void onItemClick(int position) {

        Intent modules =new Intent(this, HolosStores.class);
        Rutas clickItem=productRutas.get(position);
        modules.putExtra(EXTRA_PROMOTOR, clickItem.getId());
        modules.putExtra(EXTRA_TIENDA, clickItem.getMall());
        modules.putExtra(EXTRA_CADENA, clickItem.getFormato());
        modules.putExtra(EXTRA_FORMATOS, clickItem.getIdFormatos());
        modules.putExtra(EXTRA_ORDEN, clickItem.getOrden());
        modules.putExtra(EXTRA_IMAGEN, clickItem.getImage());
        modules.putExtra(EXTRA_PLAZA, clickItem.getIdPlazas());
        modules.putExtra(EXTRA_CADENAS, clickItem.getIdCadenas());
        modules.putExtra(EXTRA_TIENDAS, clickItem.getIdTiendas());
        modules.putExtra(EXTRA_FECHARUTA, clickItem.getFechaRuta());
        modules.putExtra(EXTRA_DTE, clickItem.getDte());
        modules.putExtra(EXTRA_plaza, clickItem.getPlaza());
        modules.putExtra(EXTRA_PROMOTORES, clickItem.getPromotor());

        startActivity(modules);


    }
}