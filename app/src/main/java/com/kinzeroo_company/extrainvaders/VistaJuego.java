package com.kinzeroo_company.extrainvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class VistaJuego extends View {
    // //// NAVE //////
    private Grafico nave;
    private Canvas canvas;
    private Grafico misil;
    private static int PASO_VELOCIDAD_MISIL = 50;
    private boolean misilActivo = false;
    private int tiempoMisil;
    private boolean musica;
    private ThreadJuego thread = new ThreadJuego();

    public VistaJuego(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableBase,  drawableMisil;
        //nave dibujo
        drawableBase = ContextCompat.getDrawable(context, R.drawable.torre1);
        nave = new Grafico(this, drawableBase);
        //misil dibujo
        drawableMisil =  ContextCompat.getDrawable(context, R.drawable.misil);
        misil = new Grafico(this, drawableMisil);
        
    }
    @Override protected void onSizeChanged(int ancho, int alto, int ancho_anterior, int alto_anterior) {
        /*
        nave.setAncho(ancho);
        nave.setAlto(alto);*/
        nave.setAncho(200);
        nave.setAlto(200);
    }
    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        nave.DibujaGrafico(canvas);
        this.canvas = canvas;
        if (misilActivo) {
            misil.DibujaGrafico(canvas);
        }
    }
    public void mover(int x){
        nave.setIncX(x);
        nave.moverImagen();
    }
    public void ActivaMisil() {
        misil.setPosX(nave.getPosX()+nave.getAncho()/2-misil.getAncho()/2);
        misil.setPosY(nave.getPosY()+nave.getAlto()/2-misil.getAlto()/2);
        misil.setAngulo(nave.getAngulo());
        misil.setIncX(Math.cos(Math.toRadians(misil.getAngulo()))*PASO_VELOCIDAD_MISIL);
        misil.setIncY(Math.sin(Math.toRadians(misil.getAngulo()))*PASO_VELOCIDAD_MISIL);
        tiempoMisil = (int) Math.min(this.getWidth()/Math.abs(misil.getIncX()),
                this.getWidth()/Math.abs(misil.getIncY()))-2;
        Log.i("TIEMPO MISIL", "" + tiempoMisil);
        misilActivo=true;
        ThreadJuego hilo = new ThreadJuego();

        hilo.start();
    }
    public void ActualizarFisica(){
        if(misilActivo)
        {
            misil.incrementaPos(1);
            tiempoMisil--;
            Log.i("TIEMPO MISIL", "" + tiempoMisil);
            if(tiempoMisil<0){
                misilActivo=false;
            }

        }
    }
    class ThreadJuego extends Thread{
        private boolean pausa;
        private boolean corriendo=true;

        @Override
        public void run()
        {
            corriendo=true;
            while(corriendo)
            {
                ActualizarFisica();
                synchronized (this) {
                    try {
                        wait(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while(pausa){
                        try{
                            wait(500);
                        }
                        catch(Exception ex)
                        {
                            Log.e("Asteroides","Error en threadJuego.Run:"+Log.getStackTraceString(ex));
                        }
                    }
                }
            }
        }

        public void pausar() {
            pausa=true;

        }

        public synchronized void reanudar() {
            pausa=false;
            notify();

        }

        public void detener() {
            corriendo=false;
            if(pausa) reanudar();

        }
    }

}
