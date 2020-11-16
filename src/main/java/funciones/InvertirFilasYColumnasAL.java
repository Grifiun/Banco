/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.util.ArrayList;
import java.util.List;

/**
 * Funcion de invertir las filas y columas de un listado de arraylist
 * @author grifiun
 */
public class InvertirFilasYColumnasAL {
    public List<ArrayList<String>> invertirColumnasFilas(List<ArrayList<String>> aux){
        List<ArrayList<String>> auxResultado = new ArrayList<>();
        //Cantidad  de columnas
        for(int i = 0; i < aux.get(0).size(); i++){
            ArrayList<String> auxiliarColumna = new ArrayList<>();
            //numero de filas
            for(int j = 0; j < aux.size(); j++){
                //se obtiene el dato en la columna i y la fila j
                //obteniendo asi las columnas una por una
                auxiliarColumna.add(aux.get(j).get(i));
            }
            auxResultado.add(auxiliarColumna);
        }            

        return auxResultado;

    }
}
