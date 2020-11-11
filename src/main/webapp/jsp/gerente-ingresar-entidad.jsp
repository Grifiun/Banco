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
            String navRol = "../html/navs/nav-"+rol+".html";
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingreso <%=tipoEntidad%></title>        
        <%@include file="../html/css-bootstrap.html"%>
        <link href="../css/style-ingresos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="<%=navRol%>"/>    
        <%@include file="../html/ingresos/parte-superior.html" %>
        <jsp:include page="<%=rutaForm%>"/>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>
