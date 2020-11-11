/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 * Cajero
 * @author grifiun
 */
public class Cliente extends Usuario{
    private String birth;
    private String dpi_pdf;
   /**
    * Sobreescribimos la funcion de identificador a un cliente
    * @param elementoXML 
    */
   public Cliente(){
       this.setIdentificador("CLIENTE,codigo,nombre,dpi,birth,direccion,sexo,dpi_pdf,password");
   }
}
