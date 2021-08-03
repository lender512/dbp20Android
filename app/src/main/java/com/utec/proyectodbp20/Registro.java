package com.utec.proyectodbp20;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button buttonregister = (Button)findViewById(R.id.btnregistrar);
        buttonregister.setOnClickListener(this);
    }

        public void showMessage(String message){
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

        public void gotoHomeActivity(String Email, int id){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Email", Email);
            intent.putExtra("id",id);
            startActivity(intent);
        }

        @Override
        public void onClick(View view){

            EditText Fulllname = (EditText)findViewById(R.id.txtnombre_r);
            EditText Email = (EditText)findViewById(R.id.email_r);
            EditText Password = (EditText)findViewById(R.id.txtclave_r);
            EditText Checkpassword = (EditText)findViewById(R.id.checkpassword);

            String fullname = Fulllname.getText().toString();
            String email = Email.getText().toString();
            String password = Password.getText().toString();
            String checkpassword = Checkpassword.getText().toString();

            showMessage("llegue");
            if (!password.equals(checkpassword)){
                showMessage("Las contraseñas deben ser iguales");
                Log.d(getApplication().getPackageName(), "Las contraseñas deben ser iguales");

            } else {
                Log.d(getApplication().getPackageName(), "Usuario Creado");
                Intent a=new Intent(Registro.this,MainActivity.class);
                startActivity(a);
                Map<String, String> message = new HashMap<>();
                message.put("password", password);
                message.put("email", email);
                message.put("name", fullname);
                message.put("checkPassword", checkpassword);
                final JSONObject jsonMessage = new JSONObject(message);
                JsonObjectRequest stringRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://10.0.2.2:8080/signIn",
                        jsonMessage,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                showMessage("Usuario Creado");
                                try {
                                    String Email = response.getString("email");
                                    int id = response.getInt("id");
                                    String usu = Email + Integer.toString(id);
                                    showMessage(usu);
                                    gotoHomeActivity(Email, id);
                                } catch (JSONException e) {
                                    showMessage("ffff");
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                showMessage("Usuario no creado");

                            }
                        });
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(stringRequest);

            }

        }


        /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

}