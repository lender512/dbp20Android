package com.utec.proyectodbp20;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {

    Button registro,btniniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btniniciar = findViewById(R.id.btniniciar);


    }
    public void onRegisterClicked(View view){
        Intent intent=new Intent(MainActivity.this,Registro.class);
        startActivity(intent);
    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void goToHomeActivity(String name, String password){
       /* Intent intent = new Intent(this, home.class);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        startActivity(intent);*/
    }

    public void onLoginClicked(View view){
        Log.d(getApplication().getPackageName(), "Button click error!");
        EditText email=findViewById(R.id.loginEmail);
        EditText password =findViewById(R.id.loginclave);

        String Email = email.getText().toString();
        String Passsword = password.getText().toString();
        showMessage("se logro");

        Map<String, String> message = new HashMap<>();
        message.put("email", Email);
        message.put("password", Passsword);
        JSONObject jsonMessage = new JSONObject(message);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "http://10.0.2.2:8080/logIn", jsonMessage,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showMessage("welcome");

                        try {
                            if(response.getBoolean("error")){
                                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                            }
                            /*response.getString("email");
                            response.getString("password");
                            String email = response.getString("email");
                            String password = response.getString("password");
                            goToHomeActivity(email, password);*/
                            else{
                                Intent i=new Intent(MainActivity.this,home.class);
                                i.putExtra("id",response.getString("id"));
                                startActivity(i);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showMessage("Fallo");
            }
        }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}