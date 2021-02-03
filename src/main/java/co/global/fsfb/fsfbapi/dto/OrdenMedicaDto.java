package co.global.fsfb.fsfbapi.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author POLLO
 */
@NoArgsConstructor
public class OrdenMedicaDto {

    private Long ormIdOrdmNumero;
    private String pacPacNumero;
    private String pacPacTipoIdentCodigo;
    private String pacPacRut;
    private String pcaAgeCodigoRecep;
    private Date cgFechaProceso;
    private String ormFilename;

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

    public Date getCgFechaProceso() {
        return cgFechaProceso;
    }

    public void setCgFechaProceso(Date cgFechaProceso) {
        this.cgFechaProceso = cgFechaProceso;
    }

    public String getOrmFilename() {
        return ormFilename;
    }

    public void setOrmFilename(String ormFilename) {
        this.ormFilename = ormFilename;
    }

}
