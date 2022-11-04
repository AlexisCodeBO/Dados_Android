package com.example.dados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //Se obtiene el Intent que inició esta actividad
        Intent i=getIntent();
        //Se obtienen los datos enviados
        boolean resultado= i.getBooleanExtra(DadosActivity.RESULTADO, false);
        int dado1= i.getIntExtra(DadosActivity.DADO1, 0);
        int dado2= i.getIntExtra(DadosActivity.DADO2, 0);





    }//onCreate
}//ResultadoActivity
