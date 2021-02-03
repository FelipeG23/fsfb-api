package co.global.fsfb.fsfbapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author POLLO
 */
@NoArgsConstructor
public class ResultadoOrdenMedicaDto {

    private Long ormIdOrdmNumero;
    private String cgFechaProceso;
    private String tipTipIDav;
    private String documento;
    private String nombreCompleto;
    private Long enProceso;
    private Long autorizadas;
    private Long prestaciones;
    private Long ormIdOrdmNumeroP;
    private Long continuidad;

    public Long getOrmIdOrdmNumero() {
        return ormIdOrdmNumero;
    }

    public void setOrmIdOrdmNumero(Long ormIdOrdmNumero) {
        this.ormIdOrdmNumero = ormIdOrdmNumero;
    }

    public String getCgFechaProceso() {
        return cgFechaProceso;
    }

    public void setCgFechaProceso(String cgFechaProceso) {
        this.cgFechaProceso = cgFechaProceso;
    }

    public String getTipTipIDav() {
        return tipTipIDav;
    }

    public void setTipTipIDav(String tipTipIDav) {
        this.tipTipIDav = tipTipIDav;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Long getEnProceso() {
        return enProceso;
    }

    public void setEnProceso(Long enProceso) {
        this.enProceso = enProceso;
    }

    public Long getAutorizadas() {
        return autorizadas;
    }

    public void setAutorizadas(Long autorizadas) {
        this.autorizadas = autorizadas;
    }

    public Long getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(Long prestaciones) {
        this.prestaciones = prestaciones;
    }

    public Long getOrmIdOrdmNumeroP() {
        return ormIdOrdmNumeroP;
    }

    public void setOrmIdOrdmNumeroP(Long ormIdOrdmNumeroP) {
        this.ormIdOrdmNumeroP = ormIdOrdmNumeroP;
    }

    public Long getContinuidad() {
        return continuidad;
    }

    public void setContinuidad(Long continuidad) {
        this.continuidad = continuidad;
    }

}
