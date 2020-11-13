/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import conection_db.Actualizar;
import encriptador.Encriptar;
import funciones.ObtenerIdentificador;
import funciones.getAttributeParameterRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registros.RealizarRegistroTabla;

/**
 *
 * @author grifiun
 */
@WebServlet(name = "ControladorActualizarRegistro", urlPatterns = {"/ControladorActualizarRegistro"})
public class ControladorActualizarRegistro extends HttpServlet {

       /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //instanciamos el obtenedor de datos
        getAttributeParameterRequest gapr = new getAttributeParameterRequest(request);
        //agregamos los identificadores
        //obtenemos la tabla
        String tabla = gapr.getAttributOrParameter("tabla"); 
        //obtenemos el atributo a modificar
        String atributo = gapr.getAttributOrParameter("atributo");
        //obtenemos el valor
        String valorAtributo = gapr.getAttributOrParameter(atributo);
        //nuevo valor
        ArrayList<String> identificador = new ArrayList<String>();
        //Agregamos a los identificadores
        identificador.add(atributo);
        ArrayList<String> dato = new ArrayList<String>();
        //obtenemos los datos
        for(int i = 0; i < identificador.size(); i++){
            if(identificador.get(i).equals("password")){   
                //encriptamos
                Encriptar encrpt = new Encriptar();
                //Class.forName("org.apache.commons.codec.Driver");
                String auxPass = gapr.getAttributOrParameter(identificador.get(i));
                String auxEn = encrpt.getEncriptPass(auxPass);//encriptamos
                dato.add(auxEn);                
            }
            else{//si es un dato ordinario (a recibir del request)
                 dato.add(gapr.getAttributOrParameter(identificador.get(i)));
            }
        }     
        String restriccion = gapr.getAttributOrParameter("restriccion");        
        dato.add(gapr.getAttributOrParameter(restriccion));//agregamos el codigo al final para agregar el valor de la restriccion
        //actualizamos
        Actualizar act = new Actualizar(tabla, //tabla
            new ArrayList<String>(identificador),//valores a modificar
            new ArrayList<String>(Arrays.asList(restriccion)),//restriccion: modificar donde el codigo
            new ArrayList<String>(dato));//valores y valor restriccion
            act.actualizar();
            request.getSession().setAttribute("mensaje", "El registro se hizo con satisfaccion");
          //Si es un usuario registramos el cambio en un historial
        if(tabla.equalsIgnoreCase("cliente") ||
           tabla.equalsIgnoreCase("cajero") ||
           tabla.equalsIgnoreCase("gerente")){
            //REGISTRAR EN HISTORIAL        
                request.getSession().setAttribute("codigoAleatorio","activado");//activamos el codigo aleatorio del historial
                request.getSession().setAttribute("fechaSistema","activado");//activamos la fecha automatica para el historial
                //Agregamos los attributos necesarios
                request.setAttribute("nombre_atributo", atributo);//agregamos el nombre del atributo
                request.setAttribute("usuario_id", restriccion);//agregamos el codigo del usuario         tipoEntidad
                request.setAttribute("atributo_nuevo_valor", valorAtributo);//agregamos el codigo del usuario

                request.getSession().setAttribute("tipoEntidad", "historial");
                //Registramos el historial
                ControladorIngresoRegistro cir = new ControladorIngresoRegistro();        
                cir.doPost(request, response);        
        }else if(tabla.equalsIgnoreCase("cuenta")){
            //REGISTRAR EN TRANSACCION        
                request.getSession().setAttribute("codigoAleatorio","activado");//activamos el codigo aleatorio del historial
                request.getSession().setAttribute("fechaSistema","activado");//activamos la fecha automatica para el historial
                //Agregamos los attributos necesarios
                request.setAttribute("monto", (String)request.getAttribute("monto"));//agregamos el nombre del atributo
                request.setAttribute("cuenta_id", (String)request.getAttribute("cuenta_id"));//agregamos el codigo del usuario         tipoEntidad
                request.setAttribute("tipo", (String)request.getAttribute("tipo"));//agregamos el codigo del usuario
                request.setAttribute("cajero_id", (String)request.getAttribute("cajero_id"));//agregamos el codigo del usuario
                
                request.getSession().setAttribute("tipoEntidad", "transaccion");
                //Registramos la transaccion
                ControladorIngresoRegistro cir = new ControladorIngresoRegistro();        
                cir.doPost(request, response);   
        }else{
            String direccion = "jsp/home.jsp";
            response.sendRedirect(direccion);
        }
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
