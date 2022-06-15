package com.cocodrilo.sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
//hosa
    EditText edtUser,edtPass;
    Button btnLogin; ///
    String usuario, password;

    private RequestQueue queue;
    String layPassword="";

    private static final String URL_GETUSER = "https://app.holoscomm.com/holos/app/AndroidServices/LoginGetUserApp.php?user=";

//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser=(EditText) findViewById(R.id.edtUsuario);
        edtPass=(EditText) findViewById(R.id.edtPassword);
        queue = Volley.newRequestQueue(this);
        btnLogin=findViewById(R.id.btnLogin);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                requestUser(getText(edtUser));
                layPassword = getText(edtPass);

            }
        });

    }


    public void requestUser(String user){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                URL_GETUSER+user, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String estado = response.getString("resultado");

                    if("CC".equals(estado)){
                        userVerify(response);
                    }else{
                        toastPrint(estado);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastPrint("Ocurrio un error: Verifique su conexion a internet");
            }
        });
        queue.add(request);


    }


    public void userVerify(JSONObject response) throws JSONException {

        JSONArray data = response.getJSONArray("datos");
        JSONObject datos = data.getJSONObject(0);
        String requestpassword = datos.getString("PasswordNew");

        if(layPassword.equals(requestpassword)){
            //acción exitosa de la validacion del correo
            //qui pondremos la logica de a donde nos tendria que nvia la app

            Preferences.setId(LoginActivity.this,datos.getString("idPromotor"));
            Preferences.setUserName(LoginActivity.this,datos.getString("Usuario"));

           // Intent intent = new Intent(LoginActivity.this,activity_home_SW.class);
           // startActivity(intent);
           // finish();

        }else {
            toastPrint("La contraseña no coincide, favor de verificarla");
        }

    }


    public void toastPrint(String estado){
        Toast.makeText(this, estado, Toast.LENGTH_LONG).show();

    }

    public String getText(EditText text){
        return text.getText().toString().trim();
    }





}