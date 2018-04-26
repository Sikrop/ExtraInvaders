package com.kinzeroo_company.extrainvaders;

import java.util.Vector;

/**
 * Created by Sikrop on 02/04/2018.
 */

public interface Puntuaciones {
    public void guardarPuntos(int puntos, String nombre,long fecha);
    public Vector listaPuntos(int cantidad);
}
