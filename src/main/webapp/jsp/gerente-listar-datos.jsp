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
            } 
        %>      
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>
