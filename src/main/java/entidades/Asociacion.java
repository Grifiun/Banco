/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 * Entidad de asociacon para cuentas bancarias
 * @author grifiun
 */
public class Asociacion extends EntidadBanco{
    private String estado;
    private String clienteId;
    private String clientePropietarioId;
    private String cuentaAsociada;
    
    public Asociacion(){
       this.setIdentificador("ASOCIACION,codigo,estado,cliente_id,cliente_propietario_id,cuenta_asociada");
   }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getClientePropietarioId() {
        return clientePropietarioId;
    }

    public void setClientePropietarioId(String clientePropietarioId) {
        this.clientePropietarioId = clientePropietarioId;
    }

    public String getCuentaAsociada() {
        return cuentaAsociada;
    }

    public void setCuentaAsociada(String cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

   
    
   
}
