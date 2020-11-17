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
public class Cajero extends Usuario{
    private String turno;
   /**
    * Sobreescribimos la funcion de identificador a un cajero
    */
   public Cajero(){
       this.setIdentificador("CAJERO,codigo,nombre,turno,dpi,direccion,sexo,password");
   }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
   
}
