package com.kinzeroo_company.extrainvaders;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Juego extends AppCompatActivity {
    VistaJuego vistaJuego;
    ImageView ovni;
    Button btnA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_juego);

        vistaJuego = (VistaJuego) findViewById(R.id.VistaJuego);

       /* btnA = (Button) findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ovni = (ImageView) findViewById(R.id.ovni_png);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.movimiento);
                ovni.startAnimation(animation);
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        ovni = (ImageView) findViewById(R.id.ovni_png);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.movimiento);
        ovni.startAnimation(animation);
    }

    public void btnIz(View view) {
        Log.e("BtnIz", "Aumenta");
        vistaJuego.mover(-100);
    }

    public void btnDr(View view) {
        Log.e("BtnDr", "Disminuye");
        vistaJuego.mover(100);
    }
    public void  btnHit(View view){
        Log.e("BtnHit","Golpe");
        vistaJuego.ActivaMisil();
    }

}
