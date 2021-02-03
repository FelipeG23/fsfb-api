package co.global.fsfb.fsfbapi.dto;

import lombok.Builder;


public class CitasAutorizadasDto {

    private String idCita;
    private String pacNumero;
    private String codCodigoprofe;
    private String codEstadoCita;
    private String tipDocumento;
    private String numDocumento;
    private String nombreCompleto;
    private String fechaAutorizacion;
    private String codigoAutorizacion;
    private String valorPrestacion;
    private String fechaCita;
    private String horaCita;

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getPacNumero() {
        return pacNumero;
    }

    public void setPacNumero(String pacNumero) {
        this.pacNumero = pacNumero;
    }

    public String getCodCodigoprofe() {
        return codCodigoprofe;
    }

    public void setCodCodigoprofe(String codCodigoprofe) {
        this.codCodigoprofe = codCodigoprofe;
    }

    public String getCodEstadoCita() {
        return codEstadoCita;
    }

    public void setCodEstadoCita(String codEstadoCita) {
        this.codEstadoCita = codEstadoCita;
    }

    public String getTipDocumento() {
        return tipDocumento;
    }

    public void setTipDocumento(String tipDocumento) {
        this.tipDocumento = tipDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(String fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    public void setCodigoAutorizacion(String codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }

    public String getValorPrestacion() {
        return valorPrestacion;
    }

    public void setValorPrestacion(String valorPrestacion) {
        this.valorPrestacion = valorPrestacion;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

}
