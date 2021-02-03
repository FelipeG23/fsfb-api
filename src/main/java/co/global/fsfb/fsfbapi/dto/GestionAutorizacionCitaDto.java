package co.global.fsfb.fsfbapi.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class GestionAutorizacionCitaDto {

    private Long cgIdCitaNumero;
    private Long pacPacNumero;
    private String gauNombreAutorizador;
    private String gauTelefonoAutorizador;
    private String gauAutorizaServ;
    private Long mnaIdCodigo;
    private String omnDesc;
    private String gauCodigoAutorizacion;
    private Date gauFechaAutorizacion;
    private Date gauFechaVencimientoAutorizacion;
    private Long gauVigenciaAutorizacion;
    private Long gauValorPrestacion;
    private Long gauCostoConvenio;
    private Long gauCostoPac;
    private String pcaAgeCodigoRecep;
    private Date gauFechaProceso;
    private String numeroPoliza;
    private String ogaDescripcion;

    public Long getCgIdCitaNumero() {
        return cgIdCitaNumero;
    }

    public void setCgIdCitaNumero(Long cgIdCitaNumero) {
        this.cgIdCitaNumero = cgIdCitaNumero;
    }

    public Long getPacPacNumero() {
        return pacPacNumero;
    }

    public void setPacPacNumero(Long pacPacNumero) {
        this.pacPacNumero = pacPacNumero;
    }

    public String getGauNombreAutorizador() {
        return gauNombreAutorizador;
    }

    public void setGauNombreAutorizador(String gauNombreAutorizador) {
        this.gauNombreAutorizador = gauNombreAutorizador;
    }

    public String getGauTelefonoAutorizador() {
        return gauTelefonoAutorizador;
    }

    public void setGauTelefonoAutorizador(String gauTelefonoAutorizador) {
        this.gauTelefonoAutorizador = gauTelefonoAutorizador;
    }

    public String getGauAutorizaServ() {
        return gauAutorizaServ;
    }

    public void setGauAutorizaServ(String gauAutorizaServ) {
        this.gauAutorizaServ = gauAutorizaServ;
    }

    public Long getMnaIdCodigo() {
        return mnaIdCodigo;
    }

    public void setMnaIdCodigo(Long mnaIdCodigo) {
        this.mnaIdCodigo = mnaIdCodigo;
    }

    public String getOmnDesc() {
        return omnDesc;
    }

    public void setOmnDesc(String omnDesc) {
        this.omnDesc = omnDesc;
    }

    public String getGauCodigoAutorizacion() {
        return gauCodigoAutorizacion;
    }

    public void setGauCodigoAutorizacion(String gauCodigoAutorizacion) {
        this.gauCodigoAutorizacion = gauCodigoAutorizacion;
    }

    public Date getGauFechaAutorizacion() {
        return gauFechaAutorizacion;
    }

    public void setGauFechaAutorizacion(Date gauFechaAutorizacion) {
        this.gauFechaAutorizacion = gauFechaAutorizacion;
    }

    public Date getGauFechaVencimientoAutorizacion() {
        return gauFechaVencimientoAutorizacion;
    }

    public void setGauFechaVencimientoAutorizacion(Date gauFechaVencimientoAutorizacion) {
        this.gauFechaVencimientoAutorizacion = gauFechaVencimientoAutorizacion;
    }

    public Long getGauVigenciaAutorizacion() {
        return gauVigenciaAutorizacion;
    }

    public void setGauVigenciaAutorizacion(Long gauVigenciaAutorizacion) {
        this.gauVigenciaAutorizacion = gauVigenciaAutorizacion;
    }

    public Long getGauValorPrestacion() {
        return gauValorPrestacion;
    }

    public void setGauValorPrestacion(Long gauValorPrestacion) {
        this.gauValorPrestacion = gauValorPrestacion;
    }

    public Long getGauCostoConvenio() {
        return gauCostoConvenio;
    }

    public void setGauCostoConvenio(Long gauCostoConvenio) {
        this.gauCostoConvenio = gauCostoConvenio;
    }

    public Long getGauCostoPac() {
        return gauCostoPac;
    }

    public void setGauCostoPac(Long gauCostoPac) {
        this.gauCostoPac = gauCostoPac;
    }

    public String getPcaAgeCodigoRecep() {
        return pcaAgeCodigoRecep;
    }

    public void setPcaAgeCodigoRecep(String pcaAgeCodigoRecep) {
        this.pcaAgeCodigoRecep = pcaAgeCodigoRecep;
    }

    public Date getGauFechaProceso() {
        return gauFechaProceso;
    }

    public void setGauFechaProceso(Date gauFechaProceso) {
        this.gauFechaProceso = gauFechaProceso;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getOgaDescripcion() {
        return ogaDescripcion;
    }

    public void setOgaDescripcion(String ogaDescripcion) {
        this.ogaDescripcion = ogaDescripcion;
    }

}
