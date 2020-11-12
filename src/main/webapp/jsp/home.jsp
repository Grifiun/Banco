<%-- 
    Document   : home-gerente
    Created on : 10 nov. 2020, 5:42:45
    Author     : grifiun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%                  
            session.setAttribute("codigoAleatorio", "activado");
            String rol = (String) session.getAttribute("rol");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/css-bootstrap.html"%>
        <title>Admin</title>
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
        <%@include file="../html/form-mensaje-resultado-ingreso.html" %>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>

