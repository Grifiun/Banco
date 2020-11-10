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
    * Sobreescribimos la funcion de cargar a un gerente
    * @param elementoXML 
    */
   @Override
   public void cargar(Element elementoXML){
       /**
        * Teniendo en cuenta que la estructura de un gerente viene dado por:
        * Gerente:
        *  <GERENTE>
        *   <CODIGO>GER1</CODIGO>
        *   <NOMBRE>Ana Pais</NOMBRE>
        *   <TURNO>MATUTINO</TURNO>
        *   <DPI>1234567890901</DPI>
        *   <DIRECCION>4ta calle 1-100 zona 3, Guatemala</DIRECCION>
        *   <SEXO>Femenino</SEXO>
        *   <PASSWORD>c85X=CE3XKWf@zML</PASSWORD>
        *  </GERENTE>
        */
            try {//obtenemos los valores
            codigo = elementoXML.getElementsByTagName("CODIGO").item(0).getTextContent();
            nombre = elementoXML.getElementsByTagName("NOMBRE").item(0).getTextContent();
            turno = elementoXML.getElementsByTagName("TURNO").item(0).getTextContent();
            dpi = elementoXML.getElementsByTagName("DPI").item(0).getTextContent();
            direccion = elementoXML.getElementsByTagName("DIRECCION").item(0).getTextContent();
            sexo = elementoXML.getElementsByTagName("SEXO").item(0).getTextContent();
            password = elementoXML.getElementsByTagName("PASSWORD").item(0).getTextContent();
            
                System.out.println("Gerente ");
                System.out.println("codigo: "+codigo);
                System.out.println("Nombre: "+nombre);
                System.out.println("turno: "+turno);
                System.out.println("dpi: "+dpi);
                System.out.println("direccion: "+direccion);
                System.out.println("sexo: "+sexo);
                System.out.println("password: "+password);
        } catch (Exception ex) {
            System.out.println("Uno de los formatos del archivo no esta bien,"
                    + "revisar su archivo de entrada en: "+codigo+"\n Error: "+ex.getMessage());
            ex.printStackTrace();
        }
   
   };
}
