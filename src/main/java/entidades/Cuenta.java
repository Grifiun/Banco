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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCreada() {
        return creada;
    }

    public void setCreada(String creada) {
        this.creada = creada;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }
   
   
}
