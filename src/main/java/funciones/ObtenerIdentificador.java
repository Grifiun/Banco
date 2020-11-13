package funciones;

import entidades.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Obtenemos un identificador de las entidades
 * @author grifiun
 */
public class ObtenerIdentificador {
    /**
     * Funcion principal, obtiene el tipo de entidad
     * que se usara para instanciar una entidad de ese tipo
     * @param tipoEntidad
     * @return 
     */
    public String getIdentificador(String tipoEntidad){
       String identificadorAux = "";
       switch(tipoEntidad){
            case "gerente"://obtenemos el identificador
               identificadorAux = new Gerente().getIdentificador();
               break;
            case "cajero"://obtenemos el identificador
               identificadorAux = new Cajero().getIdentificador();
               break;
            case "cliente"://obtenemos el identificador
               identificadorAux = new Cliente().getIdentificador();  
               break;   
            case "cuenta"://obtenemos el identificador
               identificadorAux = new Cuenta().getIdentificador();  
               break; 
            case "historial"://obtenemos el identificador
               identificadorAux = new Historial().getIdentificador();  
               break; 
            case "asociacion"://obtenemos el identificador
               identificadorAux = new Asociacion().getIdentificador();  
               break; 
            case "transaccion"://obtenemos el identificador
               identificadorAux = new Transaccion().getIdentificador();  
               break; 
               
       }       
       return identificadorAux;
    }
}
