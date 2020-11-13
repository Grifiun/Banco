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
            case "confirmar-numero-cuenta"://Para confirmar que el numero de cuenta ingresado es correcta
                queryAux = "SELECT A.nombre, A.dpi, A.codigo FROM CLIENTE AS A JOIN CUENTA AS B on A.codigo = B.cliente WHERE B.codigo = ?";                
                break;
            case "confirmar-numero-cuenta-transaccion"://Para confirmar que el numero de cuenta ingresado es correcta
                queryAux = "SELECT A.nombre, A.dpi, A.codigo, B.credito FROM CLIENTE AS A JOIN CUENTA AS B on A.codigo = B.cliente WHERE B.codigo = ?";                
                break;
            case "confirmar-monto-transferencia"://Para confirmar que el numero de cuenta ingresado es correcta
                queryAux = "SELECT credito FROM CUENTA WHERE codigo = ?";                
                break;
            case "listado-solicitudes-asociacion-sin-responder"://Para ver las solicitudes pendientes
                queryAux = "SELECT * FROM ASOCIACION WHERE cliente_propietario_id = ? AND estado = 'pendiente'";                
                break;
            case "listado-solicitudes-asociacion-aceptadas"://Para ver las solicitudes aceptadas
                queryAux = "SELECT * FROM ASOCIACION WHERE cliente_id = ? AND estado = 'aceptado'";               
                break;
            case "listado-solicitudes-asociacion-hechas"://Para ver las solicitudes aceptadas
                queryAux = "SELECT * FROM ASOCIACION WHERE cliente_id = ? AND estado <> 'aceptado'";               
                break;
            case "listado-cuentas-propias"://Para ver las solicitudes aceptadas
                queryAux = "SELECT B.codigo, C.nombre, B.credito FROM CUENTA B JOIN CLIENTE C ON B.cliente = C.codigo WHERE B.cliente = ?";               
                break;
            case "listado-cuentas-asociadas"://Para ver las solicitudes aceptadas
                queryAux = "SELECT A.cuenta_asociada, C.nombre, B.credito FROM ASOCIACION A JOIN CUENTA B ON A.cuenta_asociada = B.codigo JOIN CLIENTE C ON B.cliente = C.codigo WHERE A.cliente_id = ? && estado='aceptado';";               
                break;
            case "saldo-cuenta"://Para ver las solicitudes aceptadas
                queryAux = "SELECT A.codigo, A.credito FROM CUENTA A WHERE A.codigo = ?;";               
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
            case "confirmar-numero-cuenta"://Para confirmar que el numero de cuenta ingresado es correcta
                tituloAux = "nombre,dpi";                
                break;
            case "listado-solicitudes-asociacion-sin-responder"://Para confirmar que el numero de cuenta ingresado es correcta
                tituloAux = "Codigo solicitud,Estado,Codigo solicitante,Codigo propietario,Respuesta";                
                break;    
            case "listado-solicitudes-asociacion-aceptadas"://Para confirmar que el numero de cuenta ingresado es correcta
                tituloAux = "Codigo solicitud,Estado,Codigo solicitante,Codigo propietario";                
                break; 
            case "listado-solicitudes-asociacion-hechas"://Para confirmar que el numero de cuenta ingresado es correcta
                tituloAux = "Codigo solicitud,Estado,Codigo solicitante,Codigo propietario";                
                break; 
        
        }        
        System.out.println(tituloAux);
        return tituloAux;
    }
}