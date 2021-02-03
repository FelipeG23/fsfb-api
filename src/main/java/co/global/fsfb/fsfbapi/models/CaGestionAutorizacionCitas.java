package co.global.fsfb.fsfbapi.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CA_GESTION_AUTORIZACION_CITA")
@NoArgsConstructor
public class CaGestionAutorizacionCitas {

    @Column(name = "CG_ID_CITA_NUMERO", nullable = true)
    private Long cgIdCitaNumero;
    @Id
    @Column(name = "PAC_PAC_NUMERO", nullable = true)
    private Long pacPacNumero;
    @Column(name = "GAU_NOMBRE_AUTORIZADOR", nullable = true)
    private String gauNombreAutorizador;
    @Column(name = "GAU_TELEFONO_AUTORIZADOR", nullable = true)
    private String gauTelefonoAutorizador;
    @Column(name = "GAU_AUTORIZA_SERV", nullable = true)
    private String gauAutorizaServ;
    @Column(name = "MNA_IDCODIGO", nullable = true)
    private Long mnaIdCodigo;
    @Column(name = "OMN_DESC", nullable = true)
    private String omnDesc;
    @Column(name = "GAU_CODIGO_AUTORIZACION", nullable = true)
    private String gauCodigoAutorizacion;
    @Column(name = "GAU_FECHA_AUTORIZACION", nullable = true)
    private Date gauFechaAutorizacion;
    @Column(name = "GAU_FECHA_VENC_AUTORIZACION", nullable = true)
    private Date gauFechaVencimientoAutorizacion;
    @Column(name = "GAU_VIGENCIA_AUTORIZACION", nullable = true)
    private Long gauVigenciaAutorizacion;
    @Column(name = "GAU_VALOR_PRESTACION", nullable = true)
    private Long gauValorPrestacion;
    @Column(name = "GAU_COSTO_CONVENIO", nullable = true)
    private Long gauCostoConvenio;
    @Column(name = "GAU_COSTO_PAC", nullable = true)
    private Long gauCostoPac;
    @Column(name = "PCA_AGE_CODIGRECEP", nullable = true)
    private String pcaAgeCodigoRecep;
    @Column(name = "GAU_FECHA_PROCESO", nullable = true)
    private Date gauFechaProceso;
    @Column(name = "OGA_DESCRIPCION")
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

    public String getOgaDescripcion() {
        return ogaDescripcion;
    }

    public void setOgaDescripcion(String ogaDescripcion) {
        this.ogaDescripcion = ogaDescripcion;
    }

}
