/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

/**
 * Clase dirigida a la obtencion de querys
 * @author grifiun
 */
public class QuerysTitulosTablas {
    /**
     * Retornamos un tipo de query dependiendo de su clase
     * @param tipoQuery
     * @return 
     */
    public String getQuery (String tipoQuery){
        String queryAux = "";
        switch (tipoQuery){
            case "listado-clientes"://Obtenemos todo el listado de los clientes
                queryAux = "SELECT codigo, nombre, dpi, birth, direccion, sexo, dpi_pdf, password FROM CLIENTE";
                break;
            case "listado-gerentes"://Obtenemos todo el listado de los clientes
                queryAux = "SELECT codigo, nombre, turno, dpi, direccion, sexo, password FROM GERENTE";
                break;
            case "listado-cajeros"://Obtenemos todo el listado de los clientes
                queryAux = "SELECT codigo, nombre, turno, dpi, direccion, sexo, password FROM CAJERO";
                break;
        
        }        
        return queryAux;
    }
    
    /**
     * Retornamos un tipo de query dependiendo de su clase
     * @param tipoQuery
     * @return 
     */
    public String getTitulos (String tipoQuery){
        String tituloAux = "";
        switch (tipoQuery){
            case "listado-clientes"://Obtenemos todo el listado de los clientes
                tituloAux = "Codigo,Nombre,DPI,Fecha de nacimiento,Direccion,Sexo,DPI-PDF,Acciones: 1.Ver cliente 2.ver cuentas 3.ver transacciones 4.agregar cuenta";
                break;
            case "listado-gerentes"://Obtenemos todo el listado de los clientes
                tituloAux = "Codigo,Nombre,Turno,DPI,Direccion,Sexo";
                break;
            case "listado-cajeros"://Obtenemos todo el listado de los clientes
                tituloAux = "Codigo,Nombre,Turno,DPI,Direccion,Sexo,Acciones: 1.Ver cajero 2.ver transacciones";
                break;
            case "ver-cliente"://Obtenemos todo el listado de los clientes
                tituloAux = "codigo,nombre,dpi,birth,direccion,sexo,dpi_pdf,password";
                break;
            case "ver-gerente"://Obtenemos todo el listado de los clientes
                tituloAux = "codigo,nombre,turno,dpi,direccion,sexo,password";
                break;
            case "ver-cajero"://Obtenemos todo el listado de los clientes
                tituloAux = "codigo,nombre,turno,dpi,direccion,sexo,password";
                break;
                
        
        }        
        System.out.println(tituloAux);
        return tituloAux;
    }
}