/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo_xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Aqui estableceremos las etiquetas a usar en la lectura del archivo XML
 * @author grifiun
 */
public class ListaXML {
    private Document doc;
    private NodeList listaNodosEntidad;
    /**
     * Transformamos los atributos del xml a nodos o en su clase NodeList 
     * para extraer los elementos
     * @param path 
     */
    public ListaXML(String path){
        //Archivo archivo = new Archivo();
        try{
            this.doc = getDocumentFile(path);//obtenemos el archivo en formato file para reconocerlo como un archivo xml
            doc.getDocumentElement().normalize();
        }catch(SAXException ex){
            MensajeResultadoCargaArchivo.setMensajeErrores("Error en la entrada de datos: \n" +ex.toString().substring(ex.toString().indexOf("lineNumber:"), ex.toString().length() - 1).replaceAll(";", "\\\n").trim());
            
            System.out.println("Error en la entrada de datos: \n" +ex.toString().substring(ex.toString().indexOf("lineNumber:"), ex.toString().length() - 1).replaceAll(";", "\\\n").trim());
        }  
    }
    
    /**
     * Obtiene el listado de nodos de una entidad (GERENTE, CAJERO, CLIENTE, TRANSACCION)
     * @param entidad
     * @return 
     */
    public NodeList getListaNodos(String entidad) {
        return doc.getElementsByTagName(entidad);
    }
    
    /**
     * Funcion que nos servira para obtener el file convertido a Document desde el path recibido
     * @param path
     * @return
     * @throws SAXException 
     */
    private org.w3c.dom.Document getDocumentFile(String path) throws SAXException {
        /**
         * Creamos un documentBuilderFactory el cual utilizaremos en el DocumentBuilder para construir el archivo de tipo document xml
         */
        try{
            if(new File(path).exists()){                
                System.out.println("Existe el archivo "+path);
                File auxArchivo = new File(path);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder dbuild = dbf.newDocumentBuilder();
                org.w3c.dom.Document document = (org.w3c.dom.Document) dbuild.parse(auxArchivo);
                return document;
            }
        } catch (IOException | ParserConfigurationException  ex) {
            System.out.println("Error: "+ex.getMessage());
        }        
        System.out.println("No existe "+path);
        return null;
    }
    
}
