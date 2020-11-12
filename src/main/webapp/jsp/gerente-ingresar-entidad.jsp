<%-- 
    Document   : gerente-ingresar-usuario
    Created on : 10 nov. 2020, 17:44:57
    Author     : grifiun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String tipoEntidad = (String) request.getParameter("tipoEntidad");
            session.setAttribute("tipoEntidad", tipoEntidad);
            String rutaForm = "../html/ingresos-formularios/form-ingresar-"+tipoEntidad+".html";
        
            String rol = (String) session.getAttribute("rol");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso <%=tipoEntidad%></title>        
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
            switch(tipoEntidad){
                case "cuenta":%><%@include file="../html/ingresos-formularios/form-ingresar-cuenta.html" %><%
                    break; 
                default: %> <jsp:include page="<%=rutaForm%>"/><%
                    break;
            } 
        %>
        
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>
