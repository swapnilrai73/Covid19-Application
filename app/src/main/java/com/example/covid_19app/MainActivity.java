package com.example.covid_19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class MainActivity extends AppCompatActivity {
Button stats;
TextView tvConfirmed,tvDeath,tvRecovered,tvNewCases;
ImageView refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //button for more info
        findViewById(R.id.bginfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://www.who.int/emergencies/diseases/novel-coronavirus-2019");
            }
        });
        //button for statistics
        stats = findViewById(R.id.statsButton);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCaseValue = tvNewCases.getText().toString();
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("NewCase",newCaseValue);

                startActivity(i);
            }
        });
        //call view
        tvConfirmed=(TextView)findViewById(R.id.textConfirm);
        tvNewCases=(TextView)findViewById(R.id.textNewCase);
        tvDeath=(TextView)findViewById(R.id.textDeath);
        tvRecovered=(TextView)findViewById(R.id.textRecovered);
        //object to refresh data
        refresh=(ImageView)findViewById(R.id.imageView2);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call volley
                getData();
            }
        });

    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tvConfirmed.setText(jsonObject.getString("cases"));
                    tvDeath.setText(jsonObject.getString("deaths"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvNewCases.setText(jsonObject.getString("todayCases"));
                    Toast.makeText(MainActivity.this,"Updated Successfully!",Toast.LENGTH_LONG).show();

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
    public  void clicked_btn(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}

