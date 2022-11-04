package com.example.dados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class DadosActivity extends AppCompatActivity {

    public static final String RESULTADO="com.example.dados.RESULTADO";
    public static final String DADO1= "com.example.dados.DADO1";
    public static final String DADO2= "com.example.dados.DADO2";
    public static final String PUNTO= "com.example.dados.PUNTO";

    private final Random rng= new Random();//Random Number Generator
    //definir posibles estados del juego

    private enum Estado {INICIAL, VICTORIA, DERROTA, CONTINUAR };
    private final int DOS=2;
    private final int TRES=3;
    private final int SIETE=7;
    private final int ONCE=11;
    private final int DOCE=12;

    private int dado1;
    private int dado2;
    private int punto=0;
    private Estado estadoJuego=Estado.INICIAL;
    private Button lanzarButton;

    private Animation animacion_dado1;
    private Animation animacion_dado2;
    private ImageView dado1imageView;
    private ImageView dado2imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);


        lanzarButton= findViewById(R.id.lanzarbutton);
        lanzarButton.setOnClickListener(lanzarButtonOyente);
        dado1imageView=findViewById(R.id.dado1imageView);
        dado2imageView= findViewById(R.id.dado2imageView);

        animacion_dado1= AnimationUtils.loadAnimation (this, R.anim.animacion_dado1);
        animacion_dado1.setAnimationListener(AnimacionDadoOyente);
        //no animaciones distintas
        animacion_dado2= AnimationUtils.loadAnimation (this, R.anim.animacion_dado2);
    }//oncreate

    private void animarDados(){

        lanzarButton.setEnabled(false);

        dado1imageView.startAnimation(animacion_dado1);
        dado2imageView.startAnimation(animacion_dado2);
    }// animarDados

    Animation.AnimationListener AnimacionDadoOyente=new Animation.AnimationListener() {
        @Override
        //Se llama cuando empieza la animacion
        public void onAnimationStart(Animation animation) {

        }//OnAnimationStart

        //Se llama cuando termina la animacion
        @Override
        public void onAnimationEnd(Animation animation) {
        lanzarButton.setEnabled(true);
        establecerEstado(lanzarDados());
        actualizarGui();
        }//onAnimationEnd

        //Se llama cuando se repite la animacion
        @Override
        public void onAnimationRepeat(Animation animation) {

        }//onAnimationRepeat
    };

    private void establecerEstado(int sumaDados){
        //Etapa inicial del juego
        if(estadoJuego== Estado.INICIAL){
            switch(sumaDados){
                case SIETE:
                case ONCE:
                    estadoJuego=Estado.VICTORIA;
                    break;
                case DOS:
                case TRES:
                case DOCE:
                    estadoJuego=Estado.DERROTA;
                    terminarJuego();
                    break;
                default:
                    estadoJuego=Estado.CONTINUAR;
                    punto= sumaDados;
            }
        }
        else{ //lanzamientos subsecuentes
            if(sumaDados== SIETE){
                estadoJuego=Estado.DERROTA;
                terminarJuego();
            }
            else if(sumaDados==punto) {
                estadoJuego=Estado.VICTORIA;
                terminarJuego();
            }

        }

    }// establecerEstado()

    private void terminarJuego(){

        lanzarButton.setEnabled(false);

        Handler manejador=new Handler();
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean resultado;

                if(estadoJuego== Estado.VICTORIA){
                    resultado= true;
                    dado1imageView.setImageResource(R.drawable.ganaste);

                }
                else{
                    resultado=false;
                    dado2imageView.setImageResource(R.drawable.perdiste);
                }

                Intent i=new Intent(DadosActivity.this, ResultadoActivity.class);

                // Se envian datos con el Intent
                i.putExtra(RESULTADO, resultado);
                i.putExtra(DADO1, dado1);
                i.putExtra(DADO2, dado2);

                startActivity(i);
                finish();

            }//run
        }, 3000);


    }

    private int lanzarDados(){
        dado1= rng.nextInt(6)+1;
        dado2= rng.nextInt(6)+1;

        return(dado1+ dado2);
    }//lanzarDados

    private void actualizarGui(){
        dado1imageView=findViewById(R.id.dado1imageView);
        dado2imageView= findViewById(R.id.dado2imageView);
        TextView resultadoTextView= findViewById(R.id.resultadotextView);
        TextView puntoTextView=findViewById(R.id.puntotextView);

        //Calcular la imagen
        switch(dado1){
            case 1:
                dado1imageView.setImageResource(R.drawable.cara1);
                break;
            case 2:
                dado1imageView.setImageResource(R.drawable.cara2);
                break;
            case 3:
                dado1imageView.setImageResource(R.drawable.cara3);
                break;
            case 4:
                dado1imageView.setImageResource(R.drawable.cara4);
                break;
            case 5:
                dado1imageView.setImageResource(R.drawable.cara5);
                break;
            case 6:
                dado1imageView.setImageResource(R.drawable.cara6);
                break;


        }//switchdado1


        switch(dado2){
            case 1:
                dado2imageView.setImageResource(R.drawable.cara1);
                break;
            case 2:
                dado2imageView.setImageResource(R.drawable.cara2);
                break;
            case 3:
                dado2imageView.setImageResource(R.drawable.cara3);
                break;
            case 4:
                dado2imageView.setImageResource(R.drawable.cara4);
                break;
            case 5:
                dado2imageView.setImageResource(R.drawable.cara5);
                break;
            case 6:
                dado2imageView.setImageResource(R.drawable.cara6);
                break;

        }//switchdado2

        //Cadena de caracteres parametrizadas en pantalla
        resultadoTextView.setText(getString(R.string.resultado, dado1, dado2, (dado1+dado2)));

        if(punto !=0){
            puntoTextView.setText(getString(R.string.punto, punto));
        }
    }//actualizarGui




    View.OnClickListener lanzarButtonOyente=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            animarDados();
        }//onclick
    };//lanzarButtonOyente

}//DadosActivity
