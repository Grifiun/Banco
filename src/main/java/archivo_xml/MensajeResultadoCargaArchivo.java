/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo_xml;

/**
 * indica errores
 * @author grifiun
 */
public class MensajeResultadoCargaArchivo {
    private static String mensajeErrores = "Sin errores";

    public static String getMensajeErrores() {
        return mensajeErrores;
    }

    public static void setMensajeErrores(String mensajeErrores) {
        MensajeResultadoCargaArchivo.mensajeErrores = mensajeErrores;
    }

    
    
    public static void resetMensajeErrores() {
        MensajeResultadoCargaArchivo.mensajeErrores = "Sin errores";
    }
    
}
