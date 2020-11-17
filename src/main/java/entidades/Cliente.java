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
    private String dpiPdf;
   /**
    * Sobreescribimos la funcion de identificador a un cliente
    * @param elementoXML 
    */
   public Cliente(){
       this.setIdentificador("CLIENTE,codigo,nombre,dpi,birth,direccion,sexo,dpi_pdf,password");
   }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDpiPdf() {
        return dpiPdf;
    }

    public void setDpiPdf(String dpiPdf) {
        this.dpiPdf = dpiPdf;
    }
   
}
