package co.global.fsfb.fsfbapi.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author POLLO
 */
@NoArgsConstructor
public class ConsultaCitasDto {

    private String fechaInicial;
    private String fechaFinal;
    //Paciente
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String numDocId;
    private String tipoDocId;
    //Cita
    private String codCentroAten;
    private String codEspecialidad;
    private String codSubEspecialidad;
    private String codProf;
    private List<String> convenios;
    private String indRecepcionado;
    private String codServicio;
    private Integer estado;
    private Integer idCita;
    private Integer page;
    private String nombreSede;
    private String tipoConvenio;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
    
    
    
    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNumDocId() {
        return numDocId;
    }

    public void setNumDocId(String numDocId) {
        this.numDocId = numDocId;
    }

    public String getTipoDocId() {
        return tipoDocId;
    }

    public void setTipoDocId(String tipoDocId) {
        this.tipoDocId = tipoDocId;
    }

    public String getCodCentroAten() {
        return codCentroAten;
    }

    public void setCodCentroAten(String codCentroAten) {
        this.codCentroAten = codCentroAten;
    }

    public String getCodEspecialidad() {
        return codEspecialidad;
    }

    public void setCodEspecialidad(String codEspecialidad) {
        this.codEspecialidad = codEspecialidad;
    }

    public String getCodSubEspecialidad() {
        return codSubEspecialidad;
    }

    public void setCodSubEspecialidad(String codSubEspecialidad) {
        this.codSubEspecialidad = codSubEspecialidad;
    }

    public String getCodProf() {
        return codProf;
    }

    public void setCodProf(String codProf) {
        this.codProf = codProf;
    }

    public List<String> getConvenios() {
        return convenios;
    }

    public void setConvenios(List<String> convenios) {
        this.convenios = convenios;
    }

    public String getIndRecepcionado() {
        return indRecepcionado;
    }

    public void setIndRecepcionado(String indRecepcionado) {
        this.indRecepcionado = indRecepcionado;
    }

    public String getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(String codServicio) {
        this.codServicio = codServicio;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(String tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }

}
