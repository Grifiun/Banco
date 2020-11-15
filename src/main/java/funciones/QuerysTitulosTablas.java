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
     * Esta clase es primordial al funcionamiento de mostrar datos
     * El se manda un tipo de query a obtener al igual que uhn tipo de 
     * titulos, pueden haber titulos sin query, pero no querys sin titulos
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
                queryAux = "SELECT codigo, nombre, turno, dpi, direccion, sexo, password FROM CAJERO WHERE codigo <> '101'";
                break;
            case "listado-cuentas"://Obtenemos todo el listado de las cuentas de un cliente
                queryAux = "SELECT * FROM CUENTA WHERE cliente = ?;";
                break;
            case "listado-transacciones-por-cuenta"://Obtenemos todo el listado de las cuentas de un cliente
                queryAux = "SELECT cuenta_id, fecha, hora, tipo, monto FROM TRANSACCION WHERE cuenta_id = ? ORDER BY fecha DESC, hora DESC";
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
            //REPORTES
                //R2
            case "listado-clientes-transacciones-mayores-limite": //reporte 2 gerente
                queryAux = "SELECT D.codigo, D.nombre, D.dpi, D.birth, D.direccion, D.sexo FROM TRANSACCION A JOIN LIMITE B ON A.codigo IS NOT NULL AND B.tipo = 'Limite por transaccion' JOIN CUENTA C ON C.codigo = A.cuenta_id JOIN CLIENTE D ON C.cliente = D.codigo WHERE A.monto > B.monto GROUP BY C.cliente;";
                break;
            case "listado-transacciones-mayores-limite-por-cliente": //transacciones de reporte 2 gerente
                queryAux = "SELECT A.* FROM TRANSACCION A JOIN LIMITE B ON A.codigo IS NOT NULL AND B.tipo = 'Limite por transaccion' JOIN CUENTA C ON C.codigo = A.cuenta_id WHERE A.monto > B.monto AND C.cliente = ? ORDER BY A.fecha DESC, A.hora DESC, A.monto DESC;";
                break;
            //R3
            case "listado-clientes-transacciones-mayores-suma-limite": //reporte 2 gerente
                queryAux = "SELECT CL.codigo, CL.nombre, CL.dpi, CL.birth, CL.direccion, CL.sexo, D.total FROM (SELECT C.cliente, SUM(A.monto) AS total FROM TRANSACCION A JOIN CUENTA C ON C.codigo = A.cuenta_id GROUP BY C.cliente) AS D JOIN LIMITE B ON D.cliente IS NOT NULL AND B.tipo = 'Limite por suma de transaccion' JOIN CLIENTE AS CL ON D.cliente = CL.codigo WHERE D.total > B.monto;";
                break;
            case "listado-transacciones-por-cliente": //reporte 3 gerente transacciones
                queryAux = "SELECT A.* FROM TRANSACCION A JOIN CUENTA B ON A.cuenta_id = B.codigo WHERE B.cliente = ? ORDER BY A.fecha DESC, A.hora, A.monto DESC;";
                break;
                //R4 gerente
            case "listado-10-clientes-con-mas-dinero":
                queryAux = "SELECT A.codigo, A.nombre, A.dpi, A.birth, A.direccion, A.sexo, SUM(B.credito) AS total FROM CUENTA B JOIN CLIENTE A ON B.cliente = A.codigo GROUP BY A.codigo ORDER BY SUM(B.credito) DESC;";
                break;
            case "listado-clientes-sin-transacciones-intervalo-tiempo":
                queryAux = "SELECT AA.codigo, AA.nombre, AA.dpi, AA.birth, AA.direccion, AA.sexo FROM CLIENTE AS AA LEFT JOIN (SELECT C.cliente FROM TRANSACCION A JOIN CUENTA C ON C.codigo = A.cuenta_id WHERE fecha BETWEEN ? AND ? GROUP BY C.cliente) AS BB ON AA.codigo = BB.cliente WHERE BB.cliente IS NULL;";
                break;
                //R6
            case "listado-cajero-con-mas-transacciones-en-intervalo-tiempo":
                queryAux = "SELECT A.cajero_id, B.nombre, B.turno, B.dpi, B.sexo, COUNT(*) AS cantidad FROM TRANSACCION AS A JOIN CAJERO AS B ON A.cajero_id = B.codigo WHERE (A.fecha BETWEEN ? AND ?) AND (A.cajero_id <> '101') GROUP BY A.cajero_id ORDER BY A.cajero_id DESC LIMIT 1;";
                break;
            case "listado-cajero-con-mas-transacciones-en-intervalo-tiempo-transacciones":
                queryAux = "SELECT * FROM TRANSACCION WHERE (fecha BETWEEN ? AND ?) AND (cajero_id = ?) ORDER BY fecha DESC, hora DESC, monto DESC;";
                break;
                //R7
            case "listado-clientes-por-nombre":
                queryAux = "SELECT codigo, nombre, dpi, birth, sexo FROM CLIENTE WHERE nombre LIKE '%' ? '%';";
                break;
            case "listado-transacciones-cliente-intervalo-dinero":
                queryAux = "SELECT A.* FROM TRANSACCION A JOIN CUENTA B ON A.cuenta_id = B.codigo WHERE (B.cliente = ?) AND (monto BETWEEN ? AND ?) ORDER BY A.fecha DESC, A.hora, A.monto DESC;";
                break;
            case "listado-transacciones-por-cajero":
                queryAux = "SELECT * FROM TRANSACCION WHERE cajero_id = ?;";
                break; 
            case "listado-historial-cambio-datos-usuario":
                queryAux = "SELECT * FROM HISTORIAL WHERE usuario_id = ?;";
                break;
               
                
        }    
        System.out.println(queryAux);
        return queryAux;
    }
    
    /**
     * Retornamos un tipo de query dependiendo de su clase
     * @param tipoQuery
     * @return 
     */
    public String getTitulos (String tipoQuery){
        String tituloAux = "";
        System.out.println("GET Listado titulos: "+tipoQuery);
        switch (tipoQuery){
            case "listado-clientes"://Obtenemos todo el listado de los clientes
                tituloAux = "Codigo,Nombre,DPI,Fecha de nacimiento,Direccion,Sexo,DPI-PDF,Acciones: 1.Ver cliente 2.ver cuentas 3.ver transacciones,4.agregar cuenta 5.Ver historial de cambio";
                break;
            case "listado-gerentes"://Obtenemos todo el listado de los clientes
                tituloAux = "Codigo,Nombre,Turno,DPI,Direccion,Sexo";
                break;
            case "listado-cajeros"://Obtenemos todo el listado de los clientes
                tituloAux = "Codigo,Nombre,Turno,DPI,Direccion,Sexo,Acciones: 1.Ver cajero 2.ver transacciones 3.Ver historial de cambio";
                break;
            case "listado-cuentas"://Obtenemos todo el listado de las cuentas de un cliente
                tituloAux = "Codigo de cuenta,Codigo propietario,Creada en,Balance actual (Q),Transacciones";
                break;
            case "listado-transacciones-por-cuenta"://Obtenemos todo el listado de las cuentas de un cliente
                tituloAux = "Codigo cuenta, Fecha, Hora, Tipo transaccion, Monto, Saldo inicial, Saldo final";
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
                //REPORTES
                //R2
            case "listado-clientes-transacciones-mayores-limite": //reporte 2 gerente
                tituloAux = "Codigo,Nombre,DPI,Fecha de nacimiento,Direccion,Sexo,Acciones: 1.Ver cliente 2.ver cuentas 3.ver transacciones 4.agregar cuenta";
                break;
            case "listado-transacciones-mayores-limite-por-cliente": //transacciones de reporte 2 gerente
                tituloAux = "Codigo transaccion,Cuenta,Fecha,Hora,Tipo,Monto,Cajero codigo";
                break;
                //R3
            case "listado-clientes-transacciones-mayores-suma-limite": //reporte 3 gerente
                tituloAux = "Codigo,Nombre,DPI,Fecha de nacimiento,Direccion,Sexo,Total transacciones,Acciones: 1.Ver cliente 2.ver cuentas 3.ver transacciones 4.agregar cuenta";
                break;
            case "listado-transacciones-por-cliente": //reporte 3 gerente transacciones
                tituloAux = "Codigo transaccion,Cuenta,Fecha,Hora,Tipo,Monto,Cajero codigo";
                break;
                 //R4 gerente
            case "listado-10-clientes-con-mas-dinero":
                tituloAux = "Codigo,Nombre,DPI,Fecha de nacimiento,Direccion,Sexo,Dinero entre las cuentas,Acciones: 1.Ver cliente 2.ver cuentas 3.ver transacciones 4.agregar cuenta";
                break;
                //R5
            case "listado-clientes-sin-transacciones-intervalo-tiempo":
                tituloAux = "Codigo,Nombre,DPI,Fecha de nacimiento,Direccion,Sexo,Acciones: 1.Ver cliente 2.ver cuentas 3.ver transacciones generales 4.agregar cuenta";
                break;
                //R6
            case "listado-cajero-con-mas-transacciones-en-intervalo-tiempo":
                tituloAux = "Codigo cajero,Nombre,Turno,DPI,Sexo,Cantidad de transacciones,Acciones: 1. Ver cajero 2.Ver transacciones";
                break;
            case "listado-cajero-con-mas-transacciones-en-intervalo-tiempo-transacciones":
                tituloAux = "Codigo transaccion,Cuenta,Fecha,Hora,Tipo,Monto,Cajero codigo";
                break;
                //R7
            case "listado-clientes-por-nombre":
                tituloAux = "Codigo,Nombre,DPI,Fecha de nacimiento,Sexo,Acciones: 1.Ver cliente 2.ver cuentas 3.ver transacciones 4.agregar cuenta";
                break;
            case "listado-transacciones-cliente-intervalo-dinero":
                tituloAux = "Codigo transaccion,Cuenta,Fecha,Hora,Tipo,Monto,Cajero codigos";
                break;
            case "listado-transacciones-por-cajero":
                tituloAux = "Codigo transaccion,Cuenta,Fecha,Hora,Tipo,Monto,Cajero codigos";
                break;
            case "listado-historial-cambio-datos-usuario":
                tituloAux = "codigo,Codigo usuario,Rol Usuario,Codigo del gerente que hizo el cambio,Campo modificado,Valor inicial,Valor final,Fecha ";
                break;
        }        
        System.out.println(tituloAux);
        return tituloAux;
    }
}