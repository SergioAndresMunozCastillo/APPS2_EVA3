package com.example.eva3_1_conexion_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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
        new MiClaseConexion().execute("http://192.168.1.75:3000/actors");
    }

    class MiClaseConexion extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            String sRuta = strings[0];
            String sResu = null;
            try{
                URL ruta = new URL(sRuta);
                HttpURLConnection http = (HttpURLConnection)ruta.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                http.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                http.connect();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("first_name", "Jose");
                jsonObject.put("last_name", "Jolote");

                DataOutputStream dataOutput = new DataOutputStream(http.getOutputStream());
                dataOutput.write(jsonObject.toString().getBytes());
                dataOutput.flush();
                dataOutput.close();
                
                if(http.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader inputStreamReader = new InputStreamReader(http.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                }
            }catch(MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null){
                try{
                    JSONArray jsonArray = new JSONArray(s);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        miListaJSON.add(jsonObject);
                    }
                    lstVwActors.setAdapter(new MiAdaptador(MainActivity.this,
                            R.layout.layout_actors,
                            miListaJSON));
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
    }
}