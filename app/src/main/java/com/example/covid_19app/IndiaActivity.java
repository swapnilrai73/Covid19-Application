package com.example.covid_19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

public class IndiaActivity extends AppCompatActivity {

    ImageView backButton2,refresh2;
    TextView tvIndiaCase, tvIndiaDeath, tvIndiaActive, tvIndiaRecover,tvState,tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india);

        backButton2 = (ImageView) findViewById(R.id.backImage14);
        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IndiaActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });
        refresh2 = (ImageView) findViewById(R.id.refreshView4);
        refresh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                Toast.makeText(IndiaActivity.this, "Updated Successfully!", Toast.LENGTH_LONG).show();
            }
        });

        tvIndiaCase = (TextView) findViewById(R.id.textTotalCasesIndia);
        tvIndiaDeath = (TextView) findViewById(R.id.textDeathIndia);
        tvIndiaActive = (TextView) findViewById(R.id.textActiveIndia);
        tvIndiaRecover = (TextView) findViewById(R.id.textRecoveredIndia);
        tvState = (TextView) findViewById(R.id.textState);
        tvTime = (TextView) findViewById(R.id.textTime);

    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(IndiaActivity.this);
        String url = "https://api.covid19india.org/data.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("statewise");
                            for(int k=0;k<jsonArray.length();k++) {
                               JSONObject jsonObject=jsonArray.getJSONObject(0);
                                tvIndiaCase.setText(jsonObject.getString("confirmed"));
                                tvIndiaDeath.setText(jsonObject.getString("deaths"));
                                tvIndiaActive.setText(jsonObject.getString("active"));
                                tvIndiaRecover.setText(jsonObject.getString("recovered"));
                                tvState.setText(jsonObject.getString("state"));
                                tvTime.setText(jsonObject.getString("lastupdatedtime"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error response", error.toString());
            }
        });
        queue.add(request);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }
}
