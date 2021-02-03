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
public class ConsultaOrdenMedicaDto {

    private String fechaInicial;
    private String fechaFinal;
    private List<Long> estados;

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

    public List<Long> getEstados() {
        return estados;
    }

    public void setEstados(List<Long> estados) {
        this.estados = estados;
    }

}
