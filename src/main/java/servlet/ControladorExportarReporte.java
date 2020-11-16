/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import funciones.ObtenerParametrosMap;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Clase vital para la exportancion de datos por jasper reports
 * Obtiene el listado de datos a exportar y setea los atributos
 * tambien titulos y subtitulos
 * @author grifiun
 */
@WebServlet(name = "ControladorExportarReporte", urlPatterns = {"/ControladorExportarReporte"})
public class ControladorExportarReporte extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
            //Obtenemos la lista
            List<ArrayList<String>> listadoDatos = (List<ArrayList<String>>)request.getSession().getAttribute("listadoDatos");
            ArrayList<String> listadoTitulos = (ArrayList<String>)request.getSession().getAttribute("listadoTitulos");
            //Obtenemos el titulo
            String subtitulo = (String) request.getSession().getAttribute("subtitulo");            
            String nombrePDFSaliente = (String) request.getSession().getAttribute("nombrePDFSaliente");
            String archivojrxml = (String) request.getSession().getAttribute("archivojrxml");
            
         try {        
             //iniciamos el hasmap para guardar los parametros
            Map<String, Object> parameters = new HashMap<String,Object>();
            //Agregamos los datos obtenidos del List de arraylist
            //si obtiene datos
            if(listadoDatos != null && listadoDatos.size() > 0){//Obtenemos por la cantidad de columnas
                //instanciamos
                ObtenerParametrosMap obtenerMap = new ObtenerParametrosMap();
                //Obtenemos los parametros
                parameters = obtenerMap.obtenerParameterMap(listadoDatos);                
            }
            if(listadoTitulos != null && listadoTitulos.size() > 0){//Obtenemos por la cantidad de columnas de titulos
                //instanciamos   
                for(int i = 0; i < listadoTitulos.size(); i++){
                    parameters.put("titulo"+i,listadoTitulos.get(i));
                }
            }
            //Agregamos los titulos, subtitulos
            parameters.put("subtitulo",subtitulo);
            
            //Archivo
            File fileAux = new File(request.getServletContext().getRealPath("/resources/"+archivojrxml+".jrxml"));
            String path = fileAux.getAbsolutePath();
            //ruta archivo
            //String path = file.getAbsolutePath();
            //Jreports
            //design
            //report
            JasperReport report = JasperCompileManager.compileReport(path);

            //imprimir	
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());            

            //Exportamos
            //byte[] archivoBytes = JasperExportManager.exportReportToPdf(print);

            response.setContentType("application/pdf");//tipo pdf
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+nombrePDFSaliente+".pdf");
            //Esportamos
            JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException | JRException e) {
            e.printStackTrace();
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
