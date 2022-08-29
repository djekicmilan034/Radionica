package com.example.radionica;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button pretrazi;
    private Button dodaj;
    private Button oceni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pretrazi=(Button)findViewById(R.id.pretragaP);
        dodaj=(Button)findViewById(R.id.dodelaP);
        oceni=(Button)findViewById(R.id.ocenaP);

        pretrazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, pretraga.class);
                startActivity(i);

            }
        });

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, dodavanje.class);
                startActivity(i);

            }
        });
    oceni.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(MainActivity.this, ocena.class);
            startActivity(i);

        }
    });
    }




}
