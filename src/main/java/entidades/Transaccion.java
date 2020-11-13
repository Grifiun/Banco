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
public class Transaccion extends EntidadBanco{
     public Transaccion(){
       this.setIdentificador("TRANSACCION,codigo,cuenta_id,fecha,hora,tipo,monto,cajero_id");
   }
}
