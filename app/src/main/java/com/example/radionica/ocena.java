package com.example.radionica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ocena extends AppCompatActivity {

    DatabaseHelper myDb=new DatabaseHelper(this);
    EditText idM,ocenaM;
    Button oceni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocena);


        idM=(EditText)findViewById(R.id.sifraMAJSTORA);
        ocenaM=(EditText)findViewById(R.id.ocenaMajstora);

        oceni=(Button)findViewById(R.id.ocenjivanjeM);

        updatest();

    }
    private void updatest() {
        oceni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novaocena=ocenaM.getText().toString();
                String potvrdasifre=idM.getText().toString();
                if(Integer.parseInt(novaocena)>5 || Integer.parseInt(novaocena)<0){
                    Toast.makeText(ocena.this, "Ocena mora biti od 0 do 5!", Toast.LENGTH_LONG).show();
                }
                else {
                    int res = myDb.updateOcena(Integer.parseInt(potvrdasifre), novaocena);
                    Toast.makeText(ocena.this, "Uspesno ste ocenili majstora!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}
