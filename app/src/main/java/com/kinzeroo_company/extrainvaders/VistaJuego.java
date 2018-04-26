package com.kinzeroo_company.extrainvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class VistaJuego extends View {
    // //// NAVE //////
    private Grafico nave;
    private int giroNave;
    private float aceleracionNave;
    private static final int PASO_GIRO_NAVE = 5;
    private static final float PASO_ACELERACION_NAVE = 0.5f;

    public VistaJuego(Context context) {
        super(context);
        Drawable drawableBase,  drawableLaser;
        drawableBase = ContextCompat.getDrawable(context, R.drawable.torre1);
        nave = new Grafico(this, drawableBase);
    }
    @Override protected void onSizeChanged(int ancho, int alto, int ancho_anter, int alto_anter) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);
        nave.setIncX(Math.random());
        nave.setIncY(Math.random());
    }
    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        nave.DibujaGrafico(canvas);
    }
}
