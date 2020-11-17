<%-- 
    Document   : ver-nueva-fecha
    Created on : 06-oct-2020, 0:16:23
    Author     : grifiun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String rol = (String) session.getAttribute("rol");
           
            if(request.getParameter("fecha") != null && request.getParameter("fecha").length() > 1){
                 session.setAttribute("fecha_sistema", request.getParameter("fecha"));
            }
            if(request.getParameter("hora") != null && request.getParameter("hora").length() > 1){
                 session.setAttribute("hora_sistema", request.getParameter("hora"));
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/css-bootstrap.html"%>
        <title>Ver fecha</title>
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
        <h1>La fecha es: <%=session.getAttribute("fecha_sistema")%></h1>
        <h1>La hora es: <%=session.getAttribute("hora_sistema")%></h1>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>