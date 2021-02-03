package co.global.fsfb.fsfbapi.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CA_ORDENES_MEDICAS")
@SequenceGenerator(sequenceName = "ORDENES_MEDICAS_SEQ", allocationSize = 1, name = "ORDENES_MEDICAS_SEQ")

@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CaOrdenesMedicas implements Serializable {

    @Id
    @Column(name = "ORM_ID_ORDM_NUMERO")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDENES_MEDICAS_SEQ")
    private Long ormIdOrdmNumero;

    @Column(name = "PAC_PAC_NUMERO")
    private String pacPacNumero;

    @Column(name = "PAC_PAC_TIPOIDENTCODIGO", columnDefinition = "char")
    private String pacPacTipoIdentCodigo;

    @Column(name = "PAC_PAC_RUT")
    private String pacPacRut;

    @Column(name = "PCA_AGE_CODIGRECEP", columnDefinition = "char")
    private String pcaAgeCodigoRecep;

    @Column(name = "CG_FECHA_PROCESO")
    @CreatedDate
    private LocalDateTime cgFechaProceso;

    @Column(name = "ORM_FILENAME")
    private String ormFilename;

    @OneToMany(mappedBy = "caOrdenesMedicas", cascade = CascadeType.ALL)
    private List<CaTrazaOrdenMedicas> CaTrazaOrdenMedicas;

    public Long getOrmIdOrdmNumero() {
        return ormIdOrdmNumero;
    }

    public void setOrmIdOrdmNumero(Long ormIdOrdmNumero) {
        this.ormIdOrdmNumero = ormIdOrdmNumero;
    }

    public String getPacPacNumero() {
        return pacPacNumero;
    }

    public void setPacPacNumero(String pacPacNumero) {
        this.pacPacNumero = pacPacNumero;
    }

    public String getPacPacTipoIdentCodigo() {
        return pacPacTipoIdentCodigo;
    }

    public void setPacPacTipoIdentCodigo(String pacPacTipoIdentCodigo) {
        this.pacPacTipoIdentCodigo = pacPacTipoIdentCodigo;
    }

    public String getPacPacRut() {
        return pacPacRut;
    }

    public void setPacPacRut(String pacPacRut) {
        this.pacPacRut = pacPacRut;
    }

    public String getPcaAgeCodigoRecep() {
        return pcaAgeCodigoRecep;
    }

    public void setPcaAgeCodigoRecep(String pcaAgeCodigoRecep) {
        this.pcaAgeCodigoRecep = pcaAgeCodigoRecep;
    }

    public LocalDateTime getCgFechaProceso() {
        return cgFechaProceso;
    }

    public void setCgFechaProceso(LocalDateTime cgFechaProceso) {
        this.cgFechaProceso = cgFechaProceso;
    }

    public String getOrmFilename() {
        return ormFilename;
    }

    public void setOrmFilename(String ormFilename) {
        this.ormFilename = ormFilename;
    }

    public List<CaTrazaOrdenMedicas> getCaTrazaOrdenMedicas() {
        return CaTrazaOrdenMedicas;
    }

    public void setCaTrazaOrdenMedicas(List<CaTrazaOrdenMedicas> CaTrazaOrdenMedicas) {
        this.CaTrazaOrdenMedicas = CaTrazaOrdenMedicas;
    }

}
