<%-- 
    Document   : gerente-listar-datos
    Created on : 11 nov. 2020, 10:04:31
    Author     : grifiun
--%>

<%@page import="funciones.QuerysTitulosTablas"%>
<%@page import="funciones.ObtenerIdentificador"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="conection_db.Consultar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%        
        //titulos
        String rol = (String) session.getAttribute("rol");
        //el tipod e tabla a usar
        String tipoListado = (String) request.getParameter("tipoListado");
        //instanciamos el identificador
        QuerysTitulosTablas queryObjeto = new QuerysTitulosTablas();
        Consultar cons = new Consultar();
        String query = queryObjeto.getQuery(tipoListado);//obtenemos la query correspondiente  
        ArrayList<String> titulo = new ArrayList<>(Arrays.asList(queryObjeto.getTitulos(tipoListado).split(",")));
        
        ArrayList<String> restriccionLista;
        List<ArrayList<String>> lista;
        ArrayList<String> auxiliarTitulos;
           

        %>   
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>        
        <%@include file="../html/css-bootstrap.html"%>
        <link href="../css/style-ingresos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%          
            switch(rol){
                case "gerente":%><%@include file="../html/navs/nav-gerente.html" %><%
                    break; 
                case "cliente":%><%@include file="../html/navs/nav-cliente.html" %><%
                    break; 
                case "cajero":%><%@include file="../html/navs/nav-cajero.html" %><%
                    break; 
            } 
        %>
        <%@include file="../html/ingresos/parte-superior.html" %>
        
        <%          
            //cargamos la tabla            
            switch(tipoListado){
                case "listado-clientes":%><%@include file="../html/tablas/tabla-listado-clientes.html" %><%
                    break; 
                case "listado-gerentes":%><%@include file="../html/tablas/tabla-listado-gerentes.html" %><%
                    break; 
                case "listado-cajeros":%><%@include file="../html/tablas/tabla-listado-cajeros.html" %><%
                    break;
                case "listado-cuentas":%><%@include file="../html/tablas/tabla-listado-cuentas.html" %><%
                    break;
                    case "listado-transacciones-por-cuenta":%><%@include file="../html/tablas/tabla-listado-transacciones-por-cuenta.html" %><%
                    break;
                case "confirmar-numero-cuenta":%><%@include file="../html/datos/ver-confirmacion-numero-cuenta.html" %><%
                    break;
                case "confirmar-numero-cuenta-transaccion":%><%@include file="../html/datos/ver-confirmacion-numero-cuenta-transaccion.html" %><%
                    break;
                    case "confirmar-monto-transferencia":%><%@include file="../html/datos/ver-confirmacion-monto-transferencia.html" %><%
                    break;
                case "listado-solicitudes-asociacion-sin-responder":%><%@include file="../html/tablas/tabla-listado-solicitudes-asosiacion-pendientes.html" %><%
                    break;
                case "listado-solicitudes-asociacion-aceptadas":%><%@include file="../html/tablas/tabla-listado-solicitudes-asociacion-aceptadas.html" %><%
                    break;
                case "listado-solicitudes-asociacion-hechas":%><%@include file="../html/tablas/tabla-listado-solicitudes-asosiacion-hechas.html" %><%
                    break;
                case "listado-cuentas-propias":%><%@include file="../html/ingresos-formularios/form-ingresar-transaccion-cuentas.html" %><%
                    break;
                    //REPORTES
                case "listado-clientes-transacciones-mayores-limite":%><%@include file="../html/tablas/listado-clientes-transacciones-mayores-limite.html" %><%
                    break;
                case "listado-transacciones-mayores-limite-por-cliente":%><%@include file="../html/tablas/listado-transacciones-mayores-limite-por-cliente.html" %><%
                    break;
                case "listado-clientes-transacciones-mayores-suma-limite":%><%@include file="../html/tablas/listado-clientes-transacciones-mayores-suma-limite.html" %><%
                    break;
                case "listado-transacciones-por-cliente":%><%@include file="../html/tablas/listado-transacciones-por-cliente.html" %><%
                    break;
                case "listado-10-clientes-con-mas-dinero":%><%@include file="../html/tablas/listado-10-clientes-con-mas-dinero.html" %><%
                    break;
                case "listado-clientes-sin-transacciones-intervalo-tiempo":%><%@include file="../html/tablas/listado-clientes-sin-transacciones-intervalo-tiempo.html" %><%
                    break;
                case "listado-cajero-con-mas-transacciones-en-intervalo-tiempo":%><%@include file="../html/tablas/listado-cajero-con-mas-transacciones-en-intervalo-tiempo.html" %><%
                    break;
                case "listado-cajero-con-mas-transacciones-en-intervalo-tiempo-transacciones":%><%@include file="../html/tablas/listado-cajero-con-mas-transacciones-en-intervalo-tiempo-transacciones.html" %><%
                    break;
                case "listado-clientes-por-nombre":%><%@include file="../html/tablas/listado-clientes-por-nombre.html" %><%
                    break;
                case "listado-transacciones-cliente-intervalo-dinero":%><%@include file="../html/tablas/listado-transacciones-cliente-intervalo-dinero.html" %><%
                    break;
                case "listado-transacciones-por-cajero":%><%@include file="../html/tablas/listado-transacciones-por-cajero.html" %><%
                    break;
                case "listado-historial-cambio-datos-usuario":%><%@include file="../html/tablas/listado-historial-cambio-datos-usuario.html" %><%
                    break;
                case "listado-transacciones-dia-cajero":%><%@include file="../html/tablas/listado-transacciones-dia-cajero.html" %><%
                    break;
                case "listado-cantidad-transacciones-cajero-intervalo-tiempo":%><%@include file="../html/tablas/listado-cantidad-transacciones-cajero-intervalo-tiempo.html" %><%
                    break;
                case "listado-cuenta-con-mas-dinero":%><%@include file="../html/tablas/listado-cuenta-con-mas-dinero.html" %><%
                    break;
                case "listado-cuentas-ultimas-15-transacciones":%><%@include file="../html/tablas/listado-cuentas-ultimas-15-transacciones.html" %><%
                    break;
                case "listado-ver-ultimas-15-transacciones-por-cuenta":%><%@include file="../html/tablas/listado-ver-ultimas-15-transacciones-por-cuenta.html" %><%
                    break;
                case "listado-cuentas-intervalo-tiempo":%><%@include file="../html/tablas/listado-cuentas-intervalo-tiempo.html" %><%
                    break;
                case "listado-transaciones-por-cuenta-intervalo-tiempo":%><%@include file="../html/tablas/listado-transaciones-por-cuenta-intervalo-tiempo.html" %><%
                    break;

            } 
        %>      
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>
