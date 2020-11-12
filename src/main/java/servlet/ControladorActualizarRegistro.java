/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import conection_db.Actualizar;
import encriptador.Encriptar;
import funciones.ObtenerIdentificador;
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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * recibe los parametros de
     * Tabla: para indicar en que tabla se hará el cambio
     * Atributo a cambiar: ej: nombre
     * Valor actual y nuevo valor (atrivuto y atributo1)
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //agregamos los identificadores
        //obtenemos la tabla
        String tabla = request.getParameter("tabla");
        //obtenemos el atributo a modificar
        String atributo = request.getParameter("atributo");
        //obtenemos el valor
        String valorAtributo = request.getParameter(atributo);
        //nuevo valor
        ArrayList<String> identificador = new ArrayList<String>();
        //Agregamos a los identificadores
        identificador.add(atributo);
        
        ArrayList<String> dato = new ArrayList<String>();
        System.out.println("Atributo a obtener: "+atributo+" = "+request.getParameter(atributo));
        //obtenemos los datos
        for(int i = 0; i < identificador.size(); i++){
            if(identificador.get(i).equals("password")){   
                //encriptamos
                Encriptar encrpt = new Encriptar();
                //Class.forName("org.apache.commons.codec.Driver");
                String auxPass = request.getParameter(identificador.get(i));
                String auxEn = encrpt.getEncriptPass(auxPass);//encriptamos
                dato.add(auxEn);                
            }else{//si es un dato ordinario (a recibir del request)
                 dato.add(request.getParameter(identificador.get(i)));
            }
        }     
        System.out.println("Datos: "+dato);
        String restriccion = request.getParameter("restriccion");
        dato.add(request.getParameter(restriccion));//agregamos el codigo al final para agregar el valor de la restriccion
        //actualizamos
        Actualizar act = new Actualizar(tabla, //tabla
            new ArrayList<String>(identificador),//valores a modificar
            new ArrayList<String>(Arrays.asList(restriccion)),//restriccion: modificar donde el codigo
            new ArrayList<String>(dato));//valores y valor restriccion
            act.actualizar();
            request.getSession().setAttribute("mensaje", "El registro se hizo con satisfaccion");
          
        
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
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        processRequest(request, response);
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
