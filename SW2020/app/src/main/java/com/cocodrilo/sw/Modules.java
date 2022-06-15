package com.cocodrilo.sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cocodrilo.sw.Constructs.Rutas;

import java.util.List;

import static com.cocodrilo.sw.HomeSW.EXTRA_CADENAS;
import static com.cocodrilo.sw.HomeSW.EXTRA_DTE;
import static com.cocodrilo.sw.HomeSW.EXTRA_FECHARUTA;
import static com.cocodrilo.sw.HomeSW.EXTRA_FORMATOS;
import static com.cocodrilo.sw.HomeSW.EXTRA_ORDEN;
import static com.cocodrilo.sw.HomeSW.EXTRA_PLAZA;
import static com.cocodrilo.sw.HomeSW.EXTRA_PROMOTOR;
import static com.cocodrilo.sw.HomeSW.EXTRA_PROMOTORES;
import static com.cocodrilo.sw.HomeSW.EXTRA_TIENDA;
import static com.cocodrilo.sw.HomeSW.EXTRA_TIENDAS;
import static com.cocodrilo.sw.HomeSW.EXTRA_plaza;

public class Modules extends AppCompatActivity {

    Button btnAbarrotesCompetencia, btnpropioAbarrotes, btnPropiosFrutasVerduras,
            btnDepartExh,btnEvidencias, btnPedido;

    List<Rutas> productRutas;
    TextView titleToolbar;
    TextView IDP, NOMTIENDA, CADENA, IDFORMATO, ORDEN, IDPLAZA,IDCADENA,
            IDTIENDA, FORMATOS, FECHRUT, DTES, PLAZA,PROMOTOR;
    ImageView imaUser, imaExit;


    private static final String TAG = "Home";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        //Toolbar
        imaUser = (ImageView) findViewById(R.id.btn_user);
        imaExit = (ImageView) findViewById(R.id.btn_exit);
        imaExit.setVisibility(View.VISIBLE);
        imaUser.setVisibility(View.VISIBLE);
        titleToolbar = (TextView)findViewById(R.id.toolbar_title);
        titleToolbar.setText("Modulos");
        titleToolbar.setTextSize(20);
        titleToolbar.setTextColor(titleToolbar.getContext().getResources().getColor(R.color.white));


        Intent intent=getIntent();
        Bundle ids = intent.getExtras();

        final int id = intent.getIntExtra(EXTRA_PROMOTOR,0);
        String tiendas = intent.getStringExtra(EXTRA_TIENDA);
        String ordenss = intent.getStringExtra(EXTRA_ORDEN);
        String idplazas = intent.getStringExtra(EXTRA_PLAZA);
        String idcade = intent.getStringExtra(EXTRA_CADENAS);
        String idtiendas = intent.getStringExtra(EXTRA_TIENDAS);
        String idformatos = intent.getStringExtra(EXTRA_FORMATOS);
        String fechar = intent.getStringExtra(EXTRA_FECHARUTA);
        String dtes = intent.getStringExtra(EXTRA_DTE);
        String plazas = intent.getStringExtra(EXTRA_plaza);
        String promotor = intent.getStringExtra(EXTRA_PROMOTORES);








        btnAbarrotesCompetencia = (Button)findViewById(R.id.btnCAbarrotes);
        btnpropioAbarrotes = (Button)findViewById(R.id.btnPRAbarrotes);
        btnPropiosFrutasVerduras = (Button)findViewById(R.id.btnPRfv);
        btnDepartExh = (Button)findViewById(R.id.btnDepExh);
        btnEvidencias = (Button)findViewById(R.id.btnEvid);
        btnPedido=(Button) findViewById(R.id.btn_Pedi);



        btnAbarrotesCompetencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext() ,CompetenciaAbarrotes.class);
                startActivity(i);

            }
        });



        btnpropioAbarrotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PropiosAbarrotes.class);
                startActivity(i);


            }
        });




        btnPropiosFrutasVerduras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PropiosFrutasVerduras.class);
                startActivity(i);

            }
        });




        btnDepartExh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DepartamentosExhibiciones.class);
                startActivity(i);

            }
        });


        btnEvidencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Evidencias.class);
                startActivity(i);

            }
        });


        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Pedidos.class);
                startActivity(i);
            }
        });


    }



    }
