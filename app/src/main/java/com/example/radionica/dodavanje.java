package com.example.radionica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dodavanje extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText ime,rodjenje,telefon,grad,usluga,cena;
    Button btnAddData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje);
        myDb = new DatabaseHelper(this);

        ime = (EditText)findViewById(R.id.imeD);
        rodjenje = (EditText)findViewById(R.id.rodjenD);
        telefon = (EditText)findViewById(R.id.telefonD);
        grad = (EditText)findViewById(R.id.gradD);
        usluga = (EditText)findViewById(R.id.uslugaD);
        cena = (EditText)findViewById(R.id.cenaD);


        btnAddData = (Button)findViewById(R.id.dodajD);


        AddData();


    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!ime.getText().toString().equals("") && !rodjenje.getText().toString().equals("")
                                && !telefon.getText().toString().equals("")
                                && !grad.getText().toString().equals("")
                                && !usluga.getText().toString().equals("")
                                && !cena.getText().toString().equals(""))
                        {
                            boolean isInserted = myDb.insertData(ime.getText().toString(),
                                    rodjenje.getText().toString(), telefon.getText().toString(), grad.getText().toString(), usluga.getText().toString(), cena.getText().toString());
                            if (isInserted == true) {
                                String idd="";
                                Cursor id=myDb.getMaxId();
                                while (id.moveToNext()) {
                                    idd = id.getString(0);
                                }
                                Toast.makeText(dodavanje.this, "Vas ID je: " +idd, Toast.LENGTH_LONG).show();
                            }else
                                Toast.makeText(dodavanje.this, "Pokusajte ponovo", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(dodavanje.this, "Morate uneti sve podatke prilikom dodavanja majstora!", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }




    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
