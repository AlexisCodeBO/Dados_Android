package com.example.dados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button EntendidoButton= findViewById(R.id.EntendidoButton);
        EntendidoButton.setOnClickListener(EntendidoButtonOyente);



    }//onCreate

    View.OnClickListener EntendidoButtonOyente= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Origen y para que actividad
            //Se nombra ambas clases
            Intent i =new Intent(MainActivity.this, DadosActivity.class);
            startActivity(i);
            finish();

        }//onClick
    };//EntendidoButtonOyente

}//MainActivity
