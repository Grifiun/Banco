/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import funciones.GenerarCodigoAleatorio;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registros.RealizarRegistroTabla;
import conection_db.Consultar;
import encriptador.Encriptar;
import funciones.ObtenerIdentificador;
import funciones.getAttributeParameterRequest;

/**
 *
 * @author grifiun
 */
@WebServlet(name = "ControladorIngresoRegistro", urlPatterns = {"/ControladorIngresoRegistro"})
public class ControladorIngresoRegistro extends HttpServlet {

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
        //Instanciamos el metodo de obtener datos
        getAttributeParameterRequest getAtorPar = new getAttributeParameterRequest(request);
        //Instaanciamos 
        RealizarRegistroTabla rrt;
        rrt = new RealizarRegistroTabla();
        //agregamos el tipo de Entidad
        String tipoEntidad = (String) request.getSession().getAttribute("tipoEntidad");
        //obtenemos el identificador
        ObtenerIdentificador obi = new ObtenerIdentificador();        
        String ident = obi.getIdentificador(tipoEntidad);//obtnemos los identificadores
        rrt.setIdentificador(new ArrayList<String>(Arrays.asList(ident.split(","))));//agregamos los identificadores
        //obtenemos los datos
        for(int i = 1; i < rrt.getIdentificador().size(); i++){
            //CODIGOS ALEATORIOS
            if(rrt.getIdentificador().get(i).equals("codigo") && request.getSession().getAttribute("codigoAleatorio").equals("activado")){
                //Creamos el codigo si esta activado la generacion de codigo aleatorio
                GenerarCodigoAleatorio genC = new GenerarCodigoAleatorio();
                String auxCod = genC.generarCodAleatorio(rrt.getIdentificador().get(0), rrt.getIdentificador().get(0).substring(0, 3), 1000, 9999);
                rrt.addToDato(auxCod);                
            }//PASSWORDS
            else if(rrt.getIdentificador().get(i).equals("password")){   
                //encriptamos
                Encriptar encrpt = new Encriptar();
                //Class.forName("org.apache.commons.codec.Driver");
                //String auxPass = request.getParameter(rrt.getIdentificador().get(i));
                String auxPass = getAtorPar.getAttributOrParameter(rrt.getIdentificador().get(i));
                String auxEn = encrpt.getEncriptPass(auxPass);//encriptamos
                rrt.addToDato(auxEn);                
            }//FECHAS
            else if(rrt.getIdentificador().get(i).equals("birth") ||
                    rrt.getIdentificador().get(i).equals("creada") ||
                    rrt.getIdentificador().get(i).equals("fecha") &&
                    request.getSession().getAttribute("fechaSistema").equals("activado")){
                //Si son fechas y la fecha del sistema esta activado
                //Agregamos la fecha del sistema                
                rrt.addToDato((String)request.getSession().getAttribute("fecha_sistema"));            
            }else{//si es un dato ordinario (a recibir del request)
                 //rrt.addToDato(request.getParameter(rrt.getIdentificador().get(i)));
                 rrt.addToDato(getAtorPar.getAttributOrParameter(rrt.getIdentificador().get(i)));
            }
        }                 
        //registramos
        rrt.realizarRegistro();
        
        //Retornamos otra pagina dependiendo del resultado
        Consultar cons = new Consultar();
        boolean isRegistroCompleto = false;
        //Obtenemos el resutlado TRUE/FALSE si se realizo el registro
        isRegistroCompleto = cons.consultarExistenciaRegistro(rrt.getIdentificador().get(0), //TABLA
                new ArrayList<String>(Arrays.asList(rrt.getIdentificador().get(1))), // Consutlar PK 
                new ArrayList<String>(Arrays.asList(rrt.getDatos().get(0))));//con valor
       
        if(isRegistroCompleto){
            if(rrt.getIdentificador().get(0).equalsIgnoreCase("cliente") ||
                rrt.getIdentificador().get(0).equalsIgnoreCase("cajero") ||
                rrt.getIdentificador().get(0).equalsIgnoreCase("gerente")){
                request.getSession().setAttribute("mensaje", "El registro se hizo con satisfaccion\nel codigo de "+rrt.getIdentificador().get(0)+" es: "+
                        rrt.getDatos().get(rrt.getIdentificador().indexOf("codigo") - 1));
            }else
            request.getSession().setAttribute("mensaje", "El registro se hizo con satisfaccion");
        }
        else{
            request.getSession().setAttribute("mensaje", "El registro no se realizo debido a un error");
        }
        request.getSession().setAttribute("codigoAleatorio", "activado");//volvemos a activar la generacion del codigo aleatorio
        request.getSession().setAttribute("fechaSistema", "activado");//volvemos a activar la generacion del codigo aleatorio
        String direccion = "jsp/home.jsp";
        response.sendRedirect(direccion);
        
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
