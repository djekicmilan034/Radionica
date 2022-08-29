package com.example.radionica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class pretraga extends AppCompatActivity {

    DatabaseHelper myDb;
    Button pretraga;
    EditText pretragaGrad, pretragaUsluga, pretragaCena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretraga);
        myDb = new DatabaseHelper(this);
        pretraga = (Button) findViewById(R.id.pretraga);
        pretragaGrad=(EditText)findViewById(R.id.pretragaGradova);
        pretragaUsluga=(EditText)findViewById(R.id.pretragaUsluga);
        pretragaCena=(EditText)findViewById(R.id.pretragaCena);
        viewAll1();

    }

    public void viewAll1() {
        pretraga.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuffer buffer;
                        try (Cursor res = myDb.pretraga(pretragaGrad.getText().toString(),pretragaUsluga.getText().toString(),pretragaCena.getText().toString())) {
                            if (res.getCount() == 0) {
                                // show message
                                showMessage("", "Doslo je do greske molimo Vas pokusajte ponovo!");
                                return;
                            }

                            buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Ime: " + res.getString(1) + "\n");
                                buffer.append("Rodjenje: " + res.getString(2) + "\n");
                                buffer.append("Telefon:+381 " + res.getString(3) + "\n");
                                buffer.append("Ocena: " + res.getDouble(4)/res.getDouble(8) + "\n");
                                buffer.append("Grad: " + res.getString(5) + "\n");
                                buffer.append("Delatnost: " + res.getString(6) + "\n");
                                buffer.append("Cena rada: " + res.getString(7) + "\n\n");

                            }
                        }

                        showMessage("Majstori koje ste trazili:", buffer.toString());
                    }
                }
        );


    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
