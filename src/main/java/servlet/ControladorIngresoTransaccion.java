/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import conection_db.Consultar;
import funciones.QuerysTitulosTablas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author grifiun
 */
@WebServlet(name = "ControladorIngresoTransaccion", urlPatterns = {"/ControladorIngresoTransaccion"})
public class ControladorIngresoTransaccion extends HttpServlet {

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
        String transaccion_tipo = request.getParameter("transaccion_tipo");
        String cuenta_origen = request.getParameter("cuenta_origen");
        String cajero_id = request.getParameter("cajero_id");
        String monto = request.getParameter("monto");
        System.out.println("Transaccion tipo: "+transaccion_tipo);
        //obtenemos los saldos de las cuentas 
        Consultar cons = new Consultar();
        QuerysTitulosTablas queryObjeto = new QuerysTitulosTablas();
        //despues de instanciar
        String queryAux = queryObjeto.getQuery("saldo-cuenta");//obtenemos la query correspondiente
        ArrayList<String> datos = new ArrayList();
        datos.add(cuenta_origen);//agregamos la cuenta origen
        
        List<ArrayList<String>> verCuentasAsociadas = cons.obtenerRegistros(queryAux, 
           new ArrayList<String>(datos));      
        
        String saldo_origen = verCuentasAsociadas.get(0).get(1);
        
        //calculamos el saldo final de la cuenta ded origen
        double saldo = 0;
            if(transaccion_tipo.equalsIgnoreCase("DEBITO")){
                saldo = Double.parseDouble(saldo_origen) - Double.parseDouble(monto);
            }else{
                saldo = Double.parseDouble(saldo_origen) + Double.parseDouble(monto);
            }
                        
        //instqanciamos el registrador
        
        //Seteamos variables que pide la actualizacion
        request.setAttribute("tabla", "CUENTA");
        request.setAttribute("restriccion", "codigo");
        request.setAttribute("codigo", cuenta_origen);//agregamos la primera cuentta para el debito
        request.setAttribute("atributo", "credito");
        request.setAttribute("credito", String.valueOf(saldo));//valor final
        //seteamos datos para registrar la transaccion
        request.setAttribute("tipo", transaccion_tipo);        
        request.setAttribute("cuenta_id", cuenta_origen);  
        request.setAttribute("cajero_id", cajero_id);//agregamos el codigo del usuario
        
        //Registramos el debito a la cuenta origen  
        request.getSession().setAttribute("redireccionarRegistro", "No");
        ControladorActualizarRegistro car = new ControladorActualizarRegistro();
        car.doPost(request, response);        
           
        
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
