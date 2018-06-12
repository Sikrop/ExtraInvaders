package com.kinzeroo_company.extrainvaders;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Grafico {
    private Drawable drawable;
    private double posX, posY;
    private double incX, incY;
    private  int angulo,rotacion;
    private int ancho,alto;
    private  int RadColision;

    private View view;
    public static final int Max_Velocidad = 20;

    public Grafico(View view, Drawable drawable){
        this.view = view;
        this.drawable = drawable;
        ancho = drawable.getIntrinsicWidth();
        alto = drawable.getIntrinsicHeight();
        RadColision = (alto+ancho)/4;
    }
    public void moverImagen() {
        posX += incX;
        posY += incY;
    }


    public void dibujaGrafico(Canvas canvas){
        canvas.save();
        int x=(int) (posX+ancho/2);
        int y=(int) (posY+alto/2);
        drawable.setBounds((int)posX, (int)posY,(int)posX+ancho, (int)posY+alto);
        drawable.draw(canvas);
        canvas.restore();
        int rInval = (int) Math.hypot(ancho,alto)/2 + Max_Velocidad;
        view.invalidate(x-rInval, y-rInval, x+rInval, y+rInval);
    }

    public void incrementaPos(double factor){
        /*posX += incX * factor;
        if (posX < -ancho / 2) {
            posX = view.getWidth() - ancho / 2;
        }
        if (posX > view.getWidth() - ancho / 2) {
            posX = -ancho / 2;
        }*/
        posY -= incY * factor; //laser sube en Y -=
        if (posY < -alto / 2) {
            posY = view.getHeight() - alto / 2;
        }
        if (posY > view.getHeight() - alto / 2) {
            posY = -alto / 2;
        }
    }

    public double distancia(Grafico g) {
        return Math.hypot(posX-g.posX, posY-g.posY);
    }

    public boolean verificaColision(Grafico g) {
        return(distancia(g) < (RadColision+g.RadColision));
    }
    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public int getRotacion() {
        return rotacion;
    }

    public void setRotacion(int rotacion) {
        this.rotacion = rotacion;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getRadColision() {
        return RadColision;
    }

    public void setRadColision(int radColision) {
        RadColision = radColision;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static int getMax_Velocidad() {
        return Max_Velocidad;
    }
}