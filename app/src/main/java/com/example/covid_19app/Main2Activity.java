package com.example.covid_19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
ImageView back,refresh4;
TextView tvActive, tvCritical,tvDeathPerMillion,tvCasePerMillion;
Button statsIndia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        refresh4=(ImageView)findViewById(R.id.refreshView4);
        refresh4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                Toast.makeText(Main2Activity.this,"Updated Successfully!",Toast.LENGTH_LONG).show();
            }
        });

        back = (ImageView)findViewById(R.id.imageView14);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(i);
            }
        });

        statsIndia=(Button)findViewById(R.id.statsButtonIndia);
        statsIndia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Main2Activity.this,IndiaActivity.class);
                startActivity(j);
            }
        });

        tvActive=(TextView)findViewById(R.id.textActive);
        tvCritical=(TextView)findViewById(R.id.textCritical);
        tvCasePerMillion=(TextView)findViewById(R.id.textCasePerMillion);
        tvDeathPerMillion=(TextView)findViewById(R.id.textDeathPerMillion);

    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(Main2Activity.this);
        String url = "https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvCasePerMillion.setText(jsonObject.getString("casesPerOneMillion"));
                    tvDeathPerMillion.setText(jsonObject.getString("deathsPerOneMillion"));
                    tvActive.setText(jsonObject.getString("active"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error response",error.toString());
            }
        });
        queue.add(stringRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }
}
