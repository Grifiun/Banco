/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * se recibe un listado de Arraylist y se retornara un Map
 * @author grifiun
 */
public class ObtenerParametrosMap {
    /**
     * Retorna un map al transformar un listado de arraylist
     * @return 
     */
    public Map obtenerParameterMap(List<ArrayList<String>> datosAux){
        //Instanciamos 
        InvertirFilasYColumnasAL invertir = new InvertirFilasYColumnasAL();
        //Invertimos los datos
        List<ArrayList<String>> datosTransformados = invertir.invertirColumnasFilas(datosAux);
        Map<String, Object> parameters = new HashMap<String,Object>();
            //Agregamos los datos obtenidos del List de arraylist
            //si obtiene datos
        for(int i = 0; i < datosTransformados.size(); i++){
             //Agregamos las columnas
             parameters.put("columna"+i, datosTransformados.get(i)); 
        }
        return parameters;
    }
}
