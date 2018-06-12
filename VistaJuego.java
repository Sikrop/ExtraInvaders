package com.kinzeroo_company.extrainvaders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class VistaJuego extends View {

    private int puntuacion=0;
    private Activity padre;
    ImageView ovni;


    private Grafico nave;
    private boolean disparo=false;
    private ThreadJuego thread = new ThreadJuego();
    private static int PERIODO_PROCESO = 50;
    private long ultimoProceso=0;

    private Grafico misil;
    private static int PASO_VELOCIDAD_MISIL = 12;
    private boolean misilActivo=false;
    private int tiempoMisil;




    public VistaJuego(Context context, AttributeSet attrs) {

        super(context, attrs);
        Drawable drawableNave,  drawableMisil;
        SharedPreferences pref = context.getSharedPreferences(	   "org.example.asteroides_preferences", Context.MODE_PRIVATE);
        //trae la nave
        drawableNave = ContextCompat.getDrawable(context, R.drawable.torre1);
        nave = new Grafico(this,drawableNave);
        //trae el misil
        drawableMisil = ContextCompat.getDrawable(context, R.drawable.misil);
        misil=new Grafico(this, drawableMisil);
        //trae ovni
        ovni = (ImageView) findViewById(R.id.ovni_png);
    }

    protected void actualizaFisica() {

        long ahora = System.currentTimeMillis();

        if(ultimoProceso + PERIODO_PROCESO > ahora) {
            return;
        }
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        ultimoProceso = ahora;
        nave.incrementaPos(retardo);
        if(misilActivo){
            misil.incrementaPos(retardo);
            tiempoMisil--;
            if(tiempoMisil<0){
                misilActivo=false;
            }
          }
        }

    private void salir() {
        Intent intent =new Intent();
        intent.putExtra("puntuacion", puntuacion);
        padre.setResult(Activity.RESULT_OK,intent);
        padre.finish();

    }
    public void mover(int x){
        nave.setIncX(x);
        nave.moverImagen();
    }

    public void activaMisil() {
        misil.setPosX(nave.getPosX()+ nave.getAncho()/2-misil.getAncho()/2);
        misil.setPosY(nave.getPosY()+ nave.getAlto()/2-misil.getAlto()/2);
        //misil.setAngulo(nave.getAngulo());
        //misil.setIncX(Math.cos(Math.toRadians(misil.getAngulo()))*PASO_VELOCIDAD_MISIL);
        //misil.setIncY(Math.sin(Math.toRadians(misil.getAngulo()))*PASO_VELOCIDAD_MISIL);
        misil.setIncY(Math.cos(Math.toRadians(misil.getAngulo()))*PASO_VELOCIDAD_MISIL);
        tiempoMisil = (int) Math.min(this.getWidth() / Math.abs( misil.getIncX()), this.getHeight() / Math.abs(misil.getIncY())) - 2;
        misilActivo = true;
    }

    @Override protected void onSizeChanged(int ancho, int alto, int ancho_anter, int alto_anter) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);

        nave.setPosX(ancho/2-nave.getAncho()/2); //posicion torre
        nave.setPosY(alto/2-nave.getAlto()/9);

        nave.setAlto(200);//tamaño torre
        nave.setAncho(200);

        misil.setAncho(70); //tamaño laser
        misil.setAlto(100);

        ultimoProceso = System.currentTimeMillis();
        thread.start();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        nave.dibujaGrafico(canvas);
        if(misilActivo)
            misil.dibujaGrafico(canvas);
    }


    class ThreadJuego extends Thread {
        private boolean pausa;
        private boolean corriendo;

        @Override
        public void run() {
            corriendo=true;
            while(corriendo) {
                actualizaFisica();
                synchronized (this) {
                    while(pausa){
                        try{
                            wait();
                        }
                        catch(Exception ex) {
                            Log.e("VistaJuego","Error en threadJuego.Run:"+Log.getStackTraceString(ex));
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

    public void setPadre(Activity activity)
    {
        this.padre=activity;
    }
    public ThreadJuego getThread() {
        return thread;
    }
}
