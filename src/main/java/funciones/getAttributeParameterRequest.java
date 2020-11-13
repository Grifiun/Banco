/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grifiun
 */
public class getAttributeParameterRequest {
    private HttpServletRequest request;
    //constructor
    public getAttributeParameterRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    
    //Se obtiene el dato ya sea con un get Attribute o un getParam
    // Obtiene el nombre del atributo y retorna el valor correspondiente
    
    public String getAttributOrParameter(String nombreAtributo){
        String aux = "";
        try{
            if(nombreAtributo.equalsIgnoreCase("credito")){
                System.out.println("Es credito");
                aux = (String) request.getAttribute(nombreAtributo);                
                if(aux == null){//usamos Attribute si Parameter no nos retorna nada
                    System.out.println("Valor: "+aux);
                aux = (String) request.getParameter(nombreAtributo);
                System.out.println("Valor: "+aux);
                }
            }else{
                aux = (String) request.getParameter(nombreAtributo);
                if(aux == null)//usamos Attribute si Parameter no nos retorna nada
                aux = (String) request.getAttribute(nombreAtributo);
            }
            
        }catch(Exception ex){
            System.out.println("Error al obtener el atributo: "+nombreAtributo);
        }
        return aux;
       
    }            
            
}
