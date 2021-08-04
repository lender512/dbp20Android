package com.utec.proyectodbp20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class home extends AppCompatActivity implements View.OnClickListener {

    //PostsList
    private Button mBtnAñadir;
    private ListView mListView;
    private EditText mEditText;
    private List<String> mLista = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBtnAñadir = findViewById(R.id.btnRegPost);
        mBtnAñadir.setOnClickListener(this);
        mListView = findViewById(R.id.listView);
        mEditText = findViewById(R.id.post);

        Button buttonRegApartment = (Button)findViewById(R.id.btnRegApartment);
        buttonRegApartment.setOnClickListener(this);

        /*Button buttonRegPost = (Button)findViewById(R.id.btnRegPost);
        buttonRegPost.setOnClickListener(this::onClick2);*/

        Button buttonRegPost2 = (Button)findViewById(R.id.btnRegPost);
        buttonRegPost2.setOnClickListener(this::onClick3);


    }

    //Mostrar Posts
    /*public void onClick2(View view) {
        switch (view.getId()){
            case R.id.btnRegPost:
                String texto = mEditText.getText().toString();
                mLista.add(texto);
                mEditText.getText().clear();
                mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, mLista);
                mListView.setAdapter(mAdapter);
        }
    }*/

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void gotoHomeActivity(String District, int id){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("District", District);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onClick(View view){
        Log.d(getApplication().getPackageName(), "Button click error!");

        EditText District = (EditText)findViewById(R.id.distrito);
        EditText Address = (EditText)findViewById(R.id.direccion);

        String distrito = District.getText().toString();
        String address = Address.getText().toString();

        showMessage("Añadido");
        if (address.length() == 0 || distrito.length() == 0) {
            Toast.makeText(getApplicationContext(), "Revisa las entradas.", Toast.LENGTH_LONG).show();
            return;
        } else {
            Log.d(getApplication().getPackageName(), "Casa Creada");
            Map<String, String> message = new HashMap<>();
            message.put("district", distrito);
            message.put("address", address);
            message.put("id", this.getIntent().getExtras().getString("id"));
            final JSONObject jsonMessage = new JSONObject(message);
            JsonObjectRequest stringRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "http://10.0.2.2:8080/apartmentsM/create",
                    jsonMessage,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            showMessage("Dirección Creada");
                            try {
                                if(response.getBoolean("error")){
                                    Toast.makeText(home.this,"Error",Toast.LENGTH_SHORT).show();}
                                String District = response.getString("district");
                                int id = response.getInt("id");
                                String usu = District + Integer.toString(id);
                                showMessage(usu);
                                gotoHomeActivity(District, id);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            showMessage("Depa no creado");

                        }
                    });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);

        }

    }

    public void onClick3(View view){
        Log.d(getApplication().getPackageName(), "Button click error!");

        EditText Comment = (EditText)findViewById(R.id.post);
        EditText Address = (EditText)findViewById(R.id.direc2);
        EditText District = (EditText)findViewById(R.id.district);

        String comment = Comment.getText().toString();
        String address = Address.getText().toString();
        String district = District.getText().toString();

        showMessage("Added");
        if (comment.length() == 0 || district.length() == 0 || address.length() == 0) {
            Toast.makeText(getApplicationContext(), "Revisa los datos.", Toast.LENGTH_LONG).show();
            return;
        } else {
            Log.d(getApplication().getPackageName(), "Post Creado");
            Map<String, String> message = new HashMap<>();
            message.put("comment", comment);
            message.put("address", address);
            message.put("district", district);
            message.put("id", this.getIntent().getExtras().getString("id"));
            final JSONObject jsonMessage = new JSONObject(message);
            JsonObjectRequest stringRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    "http://10.0.2.2:8080/postM/create",
                    jsonMessage,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            showMessage("Post Creado");
                            try {
                                String Comment = response.getString("comment");
                                int id = response.getInt("id");
                                String usu = Comment + Integer.toString(id);
                                showMessage(usu);
                                gotoHomeActivity(Comment, id);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            showMessage("Post no creado");

                        }
                    });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);

        }

    }


}