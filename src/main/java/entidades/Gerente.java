/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Entidad gerente
 * @author grifiun
 */
public class Gerente extends Usuario{
   private String turno;
   /**
    * Sobreescribimos la funcion de identificador a un gerente
    * @param elementoXML 
    */
   public Gerente(){
       this.setIdentificador("GERENTE,codigo,nombre,turno,dpi,direccion,sexo,password");
   }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
   
}
