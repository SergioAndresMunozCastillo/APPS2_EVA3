package com.example.eva3_2_conexon_mysql_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lstVwActors;
    ArrayList<JSONObject> miListaJSON = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstVwActors = findViewById(R.id.lstVwActors);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "http://192.168.1.75:3000/actors",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = response.getJSONObject(i);
                                miListaJSON.add(jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lstVwActors.setAdapter(new MiAdaptador(MainActivity.this,
                                R.layout.layout_actors,
                                miListaJSON));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(this).add(jsonArrayRequest);

       /* JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                "http://192.168.1.75:3000/actors",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = response.getJSONObject(i);
                                miListaJSON.add(jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lstVwActors.setAdapter(new MiAdaptador(MainActivity.this,
                                R.layout.layout_actors,
                                miListaJSON));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(this).add(jsonArrayRequest);
        */
    }
}