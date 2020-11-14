<%-- 
    Document   : mostrar-datos-usuario
    Created on : 11 nov. 2020, 14:07:25
    Author     : grifiun
--%>

<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="conection_db.Consultar"%>
<%@page import="funciones.QuerysTitulosTablas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%        
        //titulos
        String rol = (String) session.getAttribute("rol");
        String codigoGerente = (String) session.getAttribute("codigo");
        //el tipod e tabla a usar
        String tipoQuery = (String) request.getParameter("tipoQuery");
        String verUsuario = (String) request.getParameter("verUsuario");
        String codigoUsuario = (String) request.getParameter("codigoUsuario");  
        
        if(verUsuario.equalsIgnoreCase("ver-gerente")){
            codigoUsuario = codigoGerente;
        }
        
        //arreglo de restricciones a cumplir
        ArrayList<String> listaDatosRestriccion = new ArrayList();
        listaDatosRestriccion.add(codigoUsuario);
        //instanciamos el identificador
        QuerysTitulosTablas queryObjeto = new QuerysTitulosTablas();
        Consultar cons = new Consultar();
        String query = queryObjeto.getQuery(tipoQuery);//obtenemos la query correspondiente  
        query += " WHERE codigo = ?";   
        //titulos de campos
        System.out.println("Ver pagina usuario:"+verUsuario);
         ArrayList<String> identificador = new ArrayList<>(Arrays.asList(queryObjeto.getTitulos(verUsuario).split(",")));
        System.out.println("IDENTIFICADOR: "+identificador);
         //Listas datos
        List<ArrayList<String>> lista = cons.obtenerRegistros(query, 
           new ArrayList<String>(listaDatosRestriccion));  

        String redireccion = "";//servlet a usar
        String rolUsuario = "";
        switch (verUsuario){
            case "ver-cliente":
                redireccion = "../ControladorActualizarRegistro?tabla=CLIENTE";
                rolUsuario = "CLIENTE";
                break;
            case "ver-cajero":
                redireccion = "../ControladorActualizarRegistro?tabla=CAJERO";
                rolUsuario = "CAJERO";
                break;
            case "ver-gerente":
                redireccion = "../ControladorActualizarRegistro?tabla=GERENTE";
                rolUsuario = "GERENTE";
                break;
        }
        %>   
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos</title>        
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
        <%@include file="../html/datos/ver-usuario.html" %>     
        <%@include file="../html/ingresos/parte-inferior.html" %>
        <%@include file="../html/js-bootstrap.html"%>
    </body>
</html>

