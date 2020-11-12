/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author grifiun
 */
public class Cuenta extends EntidadBanco{
    private String cliente;
    private String creada;
    private String credito;
    /**
    * Sobreescribimos la funcion de identificador a un cliente
    * @param elementoXML 
    */
   public Cuenta(){
       this.setIdentificador("CUENTA,codigo,cliente,creada,credito");
   }
}
