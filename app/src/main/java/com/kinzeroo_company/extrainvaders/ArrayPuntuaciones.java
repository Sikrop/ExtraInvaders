package com.kinzeroo_company.extrainvaders;

import java.util.Vector;

/**
 * Created by Sikrop on 02/04/2018.
 */

public class ArrayPuntuaciones implements Puntuaciones{
    private Vector puntuaciones;
    public  ArrayPuntuaciones(){
        puntuaciones = new Vector();
        puntuaciones.add("123000 Pablo Shaker");
    }
    public void guardarPuntos(int puntos,String nombre, long fecha){
        puntuaciones.add(0,puntos +""+nombre);
    }
    public Vector listaPuntos(int cantidad){
        return puntuaciones;
    }
}
