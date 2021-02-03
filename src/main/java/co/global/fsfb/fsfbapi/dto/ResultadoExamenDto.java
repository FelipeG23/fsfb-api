package co.global.fsfb.fsfbapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author POLLO
 */
@NoArgsConstructor
public class ResultadoExamenDto {

    private Long pacnumero;
    private String fechaInicial;
    private String fechaFinal;

    public Long getPacnumero() {
        return pacnumero;
    }

    public void setPacnumero(Long pacnumero) {
        this.pacnumero = pacnumero;
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

}
