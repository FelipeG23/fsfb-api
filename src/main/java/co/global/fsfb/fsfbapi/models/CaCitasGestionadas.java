package co.global.fsfb.fsfbapi.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CA_CITAS_GESTIONADAS")
@SequenceGenerator(sequenceName = "CA_CITAS_GESTIONADAS_SEQ", allocationSize = 1, name = "CA_CITAS_GESTIONADAS_SEQ")
@NoArgsConstructor
public class CaCitasGestionadas {

    @Id
    @Column(name = "CG_ID_CITA_NUMERO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CA_CITAS_GESTIONADAS_SEQ")
    private Long cGIdCitaNumero;

    @Column(name = "PAC_PAC_NUMERO")
    private Long pacPacNumero;

    @Column(name = "PCA_AGE_FECHACITAC")
    private Date pcaAgeFechaCitac;

    @Column(name = "PCA_AGE_HORACITAC", columnDefinition = "char")
    private String pcaAgeHoraCitac;

    @Column(name = "PCA_AGE_CODIGSERVI", columnDefinition = "char")
    private String pcaAgeCodigServi;

    @Column(name = "PCA_AGE_CODIGPROFE")
    private String pcaAgeCodigProfe;

    @Column(name = "PCA_AGE_OBJETO")
    private String pcaAgeObjeto;

    @Column(name = "PRE_PRE_CODIGO", columnDefinition = "char")
    private String prePreCodigo;

    @Column(name = "PCA_AGE_LUGAR", columnDefinition = "char")
    private String pcaAgeLugar;

    @Column(name = "CON_CON_CODIGO", columnDefinition = "char")
    private String conConCodigo;

    @Column(name = "PCA_AGE_CODIGRECEP", columnDefinition = "char")
    private String pcaAgeCodigRecep;

    @Column(name = "PCA_AGE_FECHADIGIT")
    private Date pcaAgeFechaDigit;

    @Column(name = "PCA_AGE_RECEPCIONADO", columnDefinition = "char")
    private String pcaAgeRecepcionado;

    @Column(name = "EC_IDCODIGO")
    private Long ecIdCodigo;

    @Column(name = "CG_ASISTENCIA")
    private String cgAsistencia;

    public Long getcGIdCitaNumero() {
        return cGIdCitaNumero;
    }

    public void setcGIdCitaNumero(Long cGIdCitaNumero) {
        this.cGIdCitaNumero = cGIdCitaNumero;
    }

    public Long getPacPacNumero() {
        return pacPacNumero;
    }

    public void setPacPacNumero(Long pacPacNumero) {
        this.pacPacNumero = pacPacNumero;
    }

    public Date getPcaAgeFechaCitac() {
        return pcaAgeFechaCitac;
    }

    public void setPcaAgeFechaCitac(Date pcaAgeFechaCitac) {
        this.pcaAgeFechaCitac = pcaAgeFechaCitac;
    }

    public String getPcaAgeHoraCitac() {
        return pcaAgeHoraCitac;
    }

    public void setPcaAgeHoraCitac(String pcaAgeHoraCitac) {
        this.pcaAgeHoraCitac = pcaAgeHoraCitac;
    }

    public String getPcaAgeCodigServi() {
        return pcaAgeCodigServi;
    }

    public void setPcaAgeCodigServi(String pcaAgeCodigServi) {
        this.pcaAgeCodigServi = pcaAgeCodigServi;
    }

    public String getPcaAgeCodigProfe() {
        return pcaAgeCodigProfe;
    }

    public void setPcaAgeCodigProfe(String pcaAgeCodigProfe) {
        this.pcaAgeCodigProfe = pcaAgeCodigProfe;
    }

    public String getPcaAgeObjeto() {
        return pcaAgeObjeto;
    }

    public void setPcaAgeObjeto(String pcaAgeObjeto) {
        this.pcaAgeObjeto = pcaAgeObjeto;
    }

    public String getPrePreCodigo() {
        return prePreCodigo;
    }

    public void setPrePreCodigo(String prePreCodigo) {
        this.prePreCodigo = prePreCodigo;
    }

    public String getPcaAgeLugar() {
        return pcaAgeLugar;
    }

    public void setPcaAgeLugar(String pcaAgeLugar) {
        this.pcaAgeLugar = pcaAgeLugar;
    }

    public String getConConCodigo() {
        return conConCodigo;
    }

    public void setConConCodigo(String conConCodigo) {
        this.conConCodigo = conConCodigo;
    }

    public String getPcaAgeCodigRecep() {
        return pcaAgeCodigRecep;
    }

    public void setPcaAgeCodigRecep(String pcaAgeCodigRecep) {
        this.pcaAgeCodigRecep = pcaAgeCodigRecep;
    }

    public Date getPcaAgeFechaDigit() {
        return pcaAgeFechaDigit;
    }

    public void setPcaAgeFechaDigit(Date pcaAgeFechaDigit) {
        this.pcaAgeFechaDigit = pcaAgeFechaDigit;
    }

    public String getPcaAgeRecepcionado() {
        return pcaAgeRecepcionado;
    }

    public void setPcaAgeRecepcionado(String pcaAgeRecepcionado) {
        this.pcaAgeRecepcionado = pcaAgeRecepcionado;
    }

    public Long getEcIdCodigo() {
        return ecIdCodigo;
    }

    public void setEcIdCodigo(Long ecIdCodigo) {
        this.ecIdCodigo = ecIdCodigo;
    }

    public String getCgAsistencia() {
        return cgAsistencia;
    }

    public void setCgAsistencia(String cgAsistencia) {
        this.cgAsistencia = cgAsistencia;
    }

}
