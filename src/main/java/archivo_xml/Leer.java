/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo_xml;

import conection_db.Registrar;
import entidades.Gerente;
import funciones.GenerarStringIdentificador;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * En esta clase se interpretaran los nodos obtenidos en listas
 * y se guardaran usando la db
 * @author grifiun
 */
public class Leer {
    private ListaXML listaXML;
    private final String[] entidades = {"GERENTE", "CAJERO", "CLIENTE", "TRANSACCION"};
    public Leer(String path){
        listaXML = new ListaXML(path);    
    }
    private ArrayList<String> querys = new ArrayList();//querys
    List<ArrayList<String>> datosQuerys = new ArrayList();//datos querys
    /**
     * Se interpreta la informacion obtenida de los nodos del archivo xml para registrarlos
     * @param path 
     */
    public void leer() throws SAXException{   
        /**
         * Recorremos todo el listado de nodos, cada valor de i representa los siguientes listados
         * i = 0 "GERENTE"
         * i = 1 "CAJERO"
         * i = 2 "CLIENTE"
         * i = 3 "TRANSACCION"
         */
        for (int i = 0; i < 4; i++) {
            NodeList listaNodoAux = listaXML.getListaNodos(entidades[i]);//obtenemos cada una de las listas de las entidades
            
            for(int j = 0;j < listaNodoAux.getLength(); j++){
                Node nodoAux = listaNodoAux.item(j);//obtenemos nodo por nodo (conjunto de datos)
                if(nodoAux.getNodeType() == Node.ELEMENT_NODE){
                    Element elementAux = (Element) nodoAux; 
                    //System.out.println(""+nodoAux.getNodeName());                    
                    interpretarNodo(null, elementAux);                    
                }
            }
        }
    }
    /**
     * Se interpretan los datos obtenidos de un nodo
     * @param tipoNodo
     * @param elementAux 
     */
    public void interpretarNodo(String datoAuxiliar, Element elementAux){
        ArrayList<String> identificador = new ArrayList();
        ArrayList<String> valores = new ArrayList();
        //System.out.println(""+elementAux.getNodeName());        
        NodeList nodosHijo = elementAux.getChildNodes(); 
        for(int i = 0;i < nodosHijo.getLength(); i++){
            Node nodoHijoN = nodosHijo.item(i);//Son las etiquetas de primer rango
            if(nodoHijoN.getNodeType() == Node.ELEMENT_NODE){                
                //TAG                
                String tagNodoHijoN =  nodoHijoN.getNodeName();
                //VALOR
                String valorNodoHijoN = nodoHijoN.getTextContent();
                //Agregamos el codigo del cliente para usarlo en las cuentas
                if(elementAux.getNodeName().equalsIgnoreCase("CUENTA") == false && tagNodoHijoN.equalsIgnoreCase("CODIGO")){
                    datoAuxiliar = valorNodoHijoN;
                 }
                //Agregamos a los arraylist
                if(tagNodoHijoN.equalsIgnoreCase("CUENTAS") == false){
                    identificador.add(tagNodoHijoN.toLowerCase().replaceAll("-", "_"));//Agregamos los identificadores
                    valores.add(valorNodoHijoN);//Agregamos los valores
                }                
                if (tagNodoHijoN.equalsIgnoreCase("CUENTAS") || tagNodoHijoN.equalsIgnoreCase("CUENTA")){//Recorremos todas las cuentas                   
                    interpretarNodo(datoAuxiliar,(Element) nodoHijoN.getChildNodes());
                }
            }
        }
        if(elementAux.getNodeName().equalsIgnoreCase("CUENTA")){//Agregamos el codigo del cliente
            identificador.add("cliente");
            valores.add(datoAuxiliar);
        }        
        //Mostramos las querys        
        GenerarStringIdentificador gsi = new GenerarStringIdentificador();
        //Primera parte de la query, agregamos al arraylist       
                    
        if(elementAux.getNodeName().equalsIgnoreCase("CUENTAS") == false){
            //agregamos query
            querys.add(gsi.generarStringIdentificador("INSERT INTO "+elementAux.getNodeName()+"(", 
                     ")", ",", identificador)+//2da parte
                    gsi.generarStringIdentificador(" VALUES (", 
                    ")", ",", identificador.size()));
            //agregamos valores
            datosQuerys.add(valores);
            if(elementAux.getNodeName().equalsIgnoreCase("CUENTA") == false){
                //si no es una cuenta EJECUTAMOS
                for(int i = querys.size() - 1; i >= 0; i--){
                    System.out.println(querys.get(i));
                }
                //REGISTRAMOS
                Registrar registrar = new Registrar(datosQuerys, querys);
                registrar.realizarRegistro();
                
                ///limpiamos
                querys.clear();
                datosQuerys.clear();
            }
            
        }   
    }
}
