package com.example.covid_19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
ImageView back;
TextView newCase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        back = (ImageView)findViewById(R.id.imageView14);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(i);
            }
        });
        newCase=(TextView)findViewById(R.id.textView6);
        newCase.setText(getIntent().getStringExtra("NewCase"));
    }
}
