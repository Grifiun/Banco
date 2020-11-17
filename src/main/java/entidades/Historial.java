/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 * Entida de historial de cambios de usuarios
 * @author grifiun
 */
public class Historial extends EntidadBanco{
    private String usuarioRol;
    private String usuarioId;
    private String gerenteId;
    private String nombreAtributo;
    private String atributoValor;
    private String atributoNuevoValor;
    private String fecha;
    
    public Historial(){
       this.setIdentificador("HISTORIAL,codigo,usuario_id,rol_usuario,gerente_id,nombre_atributo,atributo_valor,atributo_nuevo_valor,fecha");
   }

    public String getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(String usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getGerenteId() {
        return gerenteId;
    }

    public void setGerenteId(String gerenteId) {
        this.gerenteId = gerenteId;
    }

    public String getNombreAtributo() {
        return nombreAtributo;
    }

    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }

    public String getAtributoValor() {
        return atributoValor;
    }

    public void setAtributoValor(String atributoValor) {
        this.atributoValor = atributoValor;
    }

    public String getAtributoNuevoValor() {
        return atributoNuevoValor;
    }

    public void setAtributoNuevoValor(String atributoNuevoValor) {
        this.atributoNuevoValor = atributoNuevoValor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
