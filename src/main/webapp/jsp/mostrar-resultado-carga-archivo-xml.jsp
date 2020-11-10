<%-- 
    Document   : mostrar-resultado-carga-archivo-xml
    Created on : 10 nov. 2020, 4:38:50
    Author     : grifiun
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cargar</title>
        <%
            String mensajeError = (String) session.getAttribute("errorMensaje");            
            String resultado = "Carga exitosa";
            if(mensajeError.equalsIgnoreCase("Sin errores") == false)
                resultado = "Error en la carga";
            
        %>
        <%@include file="../html/css-bootstrap.html"%>
        <link href="../css/posicion-caja1-login.css" rel="stylesheet" type="text/css"/>
        <link href="../css/style-login.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>           
        <%@include file="../html/partes-codigo-html/parte-superior-login.html" %> 
        <%@include file="../html/cargar-archivo-xml/imagen.html" %> 
        <%@include file="../html/cargar-archivo-xml/mostrar-resultado-carga.html" %> 
        <%@include file="../html/cargar-archivo-xml/boton-registro-a-login.html" %> 
        <%@include file="../html/partes-codigo-html/parte-inferior-login.html" %>    
        
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>