package co.global.fsfb.fsfbapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author POLLO
 */
@NoArgsConstructor
public class ExamenDto {

    private String tipo;
    private String numero;
    private String descripcion;
    private String descFechaResultado;
    private String fechaResultado;
    private String codPrestacion;
    private String descPrestacion;
    private String indFueraRango;
    private String ordenAlterna;
    private String tipoOrdenVarios;
    private String numeroOrdenVarios;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescFechaResultado() {
        return descFechaResultado;
    }

    public void setDescFechaResultado(String descFechaResultado) {
        this.descFechaResultado = descFechaResultado;
    }

    public String getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(String fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public String getCodPrestacion() {
        return codPrestacion;
    }

    public void setCodPrestacion(String codPrestacion) {
        this.codPrestacion = codPrestacion;
    }

    public String getDescPrestacion() {
        return descPrestacion;
    }

    public void setDescPrestacion(String descPrestacion) {
        this.descPrestacion = descPrestacion;
    }

    public String getIndFueraRango() {
        return indFueraRango;
    }

    public void setIndFueraRango(String indFueraRango) {
        this.indFueraRango = indFueraRango;
    }

    public String getOrdenAlterna() {
        return ordenAlterna;
    }

    public void setOrdenAlterna(String ordenAlterna) {
        this.ordenAlterna = ordenAlterna;
    }

    public String getTipoOrdenVarios() {
        return tipoOrdenVarios;
    }

    public void setTipoOrdenVarios(String tipoOrdenVarios) {
        this.tipoOrdenVarios = tipoOrdenVarios;
    }

    public String getNumeroOrdenVarios() {
        return numeroOrdenVarios;
    }

    public void setNumeroOrdenVarios(String numeroOrdenVarios) {
        this.numeroOrdenVarios = numeroOrdenVarios;
    }

}
