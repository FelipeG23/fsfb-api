package co.global.fsfb.fsfbapi.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CA_TRAZA_ORDENMEDICAS")
@SequenceGenerator(sequenceName = "CA_TRAZA_ORDENMEDICAS_SEQ", allocationSize = 1, name = "CA_TRAZA_ORDENMEDICAS_SEQ")

@EntityListeners(AuditingEntityListener.class)
public class CaTrazaOrdenMedicas {

    @Id
    @Column(name = "TOM_ID_TRAZA_ORDM_NUM")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CA_TRAZA_ORDENMEDICAS_SEQ")
    private Long tomIdTrazaOrdmNum;

    @Column(name = "ORM_ID_ORDM_NUMERO")
    @NonNull
    private Long ormIdOrdmNumero;

    @Column(name = "EOM_IDCODIGO")
    @NonNull
    private Long eomIdCodigo;

    @Column(name = "PCA_AGE_CODIGRECEP", columnDefinition = "char")
    @NonNull
    private String pcaAgeCodigRecep;

    @Column(name = "CG_FECHA_PROCESO")
    @CreatedDate
    private LocalDateTime cgFechaProceso;

    @ManyToOne
    @JoinColumn(name = "ORM_ID_ORDM_NUMERO", referencedColumnName = "ORM_ID_ORDM_NUMERO", insertable = false, updatable = false)
    @NonNull
    private CaOrdenesMedicas caOrdenesMedicas;

    public Long getTomIdTrazaOrdmNum() {
        return tomIdTrazaOrdmNum;
    }

    public void setTomIdTrazaOrdmNum(Long tomIdTrazaOrdmNum) {
        this.tomIdTrazaOrdmNum = tomIdTrazaOrdmNum;
    }

    public Long getOrmIdOrdmNumero() {
        return ormIdOrdmNumero;
    }

    public void setOrmIdOrdmNumero(Long ormIdOrdmNumero) {
        this.ormIdOrdmNumero = ormIdOrdmNumero;
    }

    public Long getEomIdCodigo() {
        return eomIdCodigo;
    }

    public void setEomIdCodigo(Long eomIdCodigo) {
        this.eomIdCodigo = eomIdCodigo;
    }

    public String getPcaAgeCodigRecep() {
        return pcaAgeCodigRecep;
    }

    public void setPcaAgeCodigRecep(String pcaAgeCodigRecep) {
        this.pcaAgeCodigRecep = pcaAgeCodigRecep;
    }

    public LocalDateTime getCgFechaProceso() {
        return cgFechaProceso;
    }

    public void setCgFechaProceso(LocalDateTime cgFechaProceso) {
        this.cgFechaProceso = cgFechaProceso;
    }

    public CaOrdenesMedicas getCaOrdenesMedicas() {
        return caOrdenesMedicas;
    }

    public void setCaOrdenesMedicas(CaOrdenesMedicas caOrdenesMedicas) {
        this.caOrdenesMedicas = caOrdenesMedicas;
    }

    public CaTrazaOrdenMedicas() {
    }

    public CaTrazaOrdenMedicas(Long tomIdTrazaOrdmNum, Long ormIdOrdmNumero, String pcaAgeCodigRecep, CaOrdenesMedicas caOrdenesMedicas) {
        this.tomIdTrazaOrdmNum = tomIdTrazaOrdmNum;
        this.ormIdOrdmNumero = ormIdOrdmNumero;
        this.pcaAgeCodigRecep = pcaAgeCodigRecep;
        this.caOrdenesMedicas = caOrdenesMedicas;
    }

}
