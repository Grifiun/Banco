<%-- 
    Document   : ingresar-intervalo-dato
    Created on : 14 nov. 2020, 20:13:33
    Author     : grifiun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%                  
            session.setAttribute("codigoAleatorio", "activado");
            String tipoIntervalo = request.getParameter("intervalo");
            String rol = (String) session.getAttribute("rol");
            
            String direccionTipo = request.getParameter("direccion");
            String direccion = "";
            switch(direccionTipo){
                case "listado-clientes-sin-transacciones-intervalo-tiempo":
                    direccion = "gerente-listar-datos.jsp?tipoListado=listado-clientes-sin-transacciones-intervalo-tiempo";
                    break;
                case "listado-cajero-con-mas-transacciones-en-intervalo-tiempo":
                    direccion = "gerente-listar-datos.jsp?tipoListado=listado-cajero-con-mas-transacciones-en-intervalo-tiempo";
                    break;
                case "listado-transacciones-cliente-intervalo-dinero":
                    String clienteAux = request.getParameter("cliente-id");
                    direccion = "gerente-listar-datos.jsp?tipoListado=listado-transacciones-cliente-intervalo-dinero&cliente-id="+clienteAux;
                    break; 
                case "listado-cantidad-transacciones-cajero-intervalo-tiempo":
                    String codigoUsuario11 = request.getParameter("codigoUsuario");
                    direccion = "gerente-listar-datos.jsp?tipoListado=listado-cantidad-transacciones-cajero-intervalo-tiempo&codigoUsuario="+codigoUsuario11;
                    break;  
                  case "listado-transaciones-por-cuenta-intervalo-tiempo":
                    String cuentaAuxiliar = request.getParameter("cuenta-id");
                    direccion = "gerente-listar-datos.jsp?tipoListado=listado-transaciones-por-cuenta-intervalo-tiempo&cuenta-id="+cuentaAuxiliar;
                    break;
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/css-bootstrap.html"%>
        <title>Intervalo</title>
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
            switch(tipoIntervalo){
                case "tiempo":%><%@include file="../html/ingresos-formularios/form-ingresar-intervalo-tiempo.html" %><%
                    break;
                case "dinero":%><%@include file="../html/ingresos-formularios/form-ingresar-intervalo-dinero.html" %><%
                    break;
            } 
        %>
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>

