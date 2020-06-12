package com.example.eva3_2_conexon_mysql_volley;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MiAdaptador extends ArrayAdapter<JSONObject> {
    Context context;
    int resource;
    List<JSONObject> objects;

    public MiAdaptador(@NonNull Context context, int resource, @NonNull List<JSONObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            convertView = layoutInflater.inflate(resource, parent, false);
        }
        TextView txtVwFirstN, txtVwLastN;
        txtVwFirstN = convertView.findViewById(R.id.txtVwFirstN);
        txtVwLastN = convertView.findViewById(R.id.txtVwLastN);

        //LLENAR DATOS DE LA LISTA
        try {
            txtVwFirstN.setText(objects.get(position).getString("first_name"));
            txtVwLastN.setText(objects.get(position).getString("last_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return super.getView(position, convertView, parent);
    }
}