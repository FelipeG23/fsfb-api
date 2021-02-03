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
@Table(name = "CA_PRESTACIONES_ORDMED")
@NoArgsConstructor
public class CaPrestacionesOrdMed {

    @Id
    @Column(name = "POM_ID_PREST_ORDM")
    private Long pomIdPrestOrdm;

    @Column(name = "ORM_ID_ORDM_NUMERO")
    private Long ormIdOrmdNumero;

    @Column(name = "SER_SER_CODIGO")
    private String serSerCodigo;

    @Column(name = "PRE_PRE_CODIGO")
    private String prePreCodigo;

    @Column(name = "SER_SER_DESC")
    private String serSerDec;

    @Column(name = "PRE_PRE_DESC")
    private String prePreDesc;

    @Column(name = "PCA_AGE_CODIGRECEP")
    private String pcaAgeCodigRecep;

    @Column(name = "CG_FECHA_PROCESO")
    private Date cgFechaProceso;

    public Long getPomIdPrestOrdm() {
        return pomIdPrestOrdm;
    }

    public void setPomIdPrestOrdm(Long pomIdPrestOrdm) {
        this.pomIdPrestOrdm = pomIdPrestOrdm;
    }

    public Long getOrmIdOrmdNumero() {
        return ormIdOrmdNumero;
    }

    public void setOrmIdOrmdNumero(Long ormIdOrmdNumero) {
        this.ormIdOrmdNumero = ormIdOrmdNumero;
    }

    public String getSerSerCodigo() {
        return serSerCodigo;
    }

    public void setSerSerCodigo(String serSerCodigo) {
        this.serSerCodigo = serSerCodigo;
    }

    public String getPrePreCodigo() {
        return prePreCodigo;
    }

    public void setPrePreCodigo(String prePreCodigo) {
        this.prePreCodigo = prePreCodigo;
    }

    public String getSerSerDec() {
        return serSerDec;
    }

    public void setSerSerDec(String serSerDec) {
        this.serSerDec = serSerDec;
    }

    public String getPrePreDesc() {
        return prePreDesc;
    }

    public void setPrePreDesc(String prePreDesc) {
        this.prePreDesc = prePreDesc;
    }

    public String getPcaAgeCodigRecep() {
        return pcaAgeCodigRecep;
    }

    public void setPcaAgeCodigRecep(String pcaAgeCodigRecep) {
        this.pcaAgeCodigRecep = pcaAgeCodigRecep;
    }

    public Date getCgFechaProceso() {
        return cgFechaProceso;
    }

    public void setCgFechaProceso(Date cgFechaProceso) {
        this.cgFechaProceso = cgFechaProceso;
    }

}
