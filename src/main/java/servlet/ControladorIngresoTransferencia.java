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
@WebServlet(name = "ControladorIngresoTransferencia", urlPatterns = {"/ControladorIngresoTransferencia"})
public class ControladorIngresoTransferencia extends HttpServlet {

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
        
        String cuenta_origen = request.getParameter("cuenta_origen");
        String cuenta_destino = request.getParameter("cuenta_destino");
        String monto = request.getParameter("monto");
        
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
        
        //obtenemos el saldo de la cuenta desitno
        datos.clear();
        verCuentasAsociadas.clear();
        datos.add(cuenta_destino);//agregamos la cuenta destino
        verCuentasAsociadas = cons.obtenerRegistros(queryAux, 
           new ArrayList<String>(datos));
        String saldo_destino = verCuentasAsociadas.get(0).get(1); 
        //calculamos el saldo final de la cuenta ded origen
        double saldo_final_c1 = Double.parseDouble(saldo_origen) - Double.parseDouble(monto);
        System.out.println("Saldo final or: "+saldo_final_c1);
        //saldo final cuenta destino
        double saldo_final_c2 = Double.parseDouble(saldo_destino) + Double.parseDouble(monto);
        System.out.println("Saldo final des: "+saldo_final_c2);
        
        //instqanciamos el registrador
        
        //Seteamos variables que pide la actualizacion
        request.setAttribute("tabla", "CUENTA");
        request.setAttribute("restriccion", "codigo");
        request.setAttribute("codigo", cuenta_origen);//agregamos la primera cuentta para el debito
        request.setAttribute("atributo", "credito");
        request.setAttribute("credito", String.valueOf(saldo_final_c1));//valor final
        //seteamos datos para registrar la transaccion
        request.setAttribute("tipo", "DEBITO POR TRANSFERENCIA");        
        request.setAttribute("cuenta_id", cuenta_origen);  
        request.setAttribute("cajero_id", "101");//agregamos el codigo del usuario
        
        //Registramos el debito a la cuenta origen  
        request.getSession().setAttribute("redireccionarRegistro", "No");
        ControladorActualizarRegistro car = new ControladorActualizarRegistro();
        car.doPost(request, response);        
        
        request.setAttribute("tabla", "CUENTA");
        request.setAttribute("restriccion", "codigo");
        request.setAttribute("codigo", cuenta_destino);//agregamos la primera cuentta para el debito
        request.setAttribute("atributo", "credito");
        request.setAttribute("credito", String.valueOf(saldo_final_c2));//valor final
        //seteamos datos para registrar la transaccion
        request.setAttribute("tipo", "CREDITO POR TRANSFERENCIA");     
        request.setAttribute("cuenta_id", cuenta_destino);  
        request.setAttribute("cajero_id", "101");//agregamos el codigo del usuario
        //Registramos el debito a la cuenta origen  
        request.getSession().setAttribute("redireccionarRegistro", "No");
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
