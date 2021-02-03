package co.global.fsfb.fsfbapi.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author GIOVANNI-PC
 */
@NoArgsConstructor
public class ResultadoValoresDTO {

    private String codigoConvenio;
    private String convenio;
    private BigDecimal valorTarifaBase;
    private String codigoPrestacion;
    private String prestacion;
    private String modeloTarifaNombre;
    private BigDecimal valorAmbulatorio;

    public String getCodigoConvenio() {
        return codigoConvenio;
    }

    public void setCodigoConvenio(String codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public BigDecimal getValorTarifaBase() {
        return valorTarifaBase;
    }

    public void setValorTarifaBase(BigDecimal valorTarifaBase) {
        this.valorTarifaBase = valorTarifaBase;
    }

    public String getCodigoPrestacion() {
        return codigoPrestacion;
    }

    public void setCodigoPrestacion(String codigoPrestacion) {
        this.codigoPrestacion = codigoPrestacion;
    }

    public String getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(String prestacion) {
        this.prestacion = prestacion;
    }

    public String getModeloTarifaNombre() {
        return modeloTarifaNombre;
    }

    public void setModeloTarifaNombre(String modeloTarifaNombre) {
        this.modeloTarifaNombre = modeloTarifaNombre;
    }

    public BigDecimal getValorAmbulatorio() {
        return valorAmbulatorio;
    }

    public void setValorAmbulatorio(BigDecimal valorAmbulatorio) {
        this.valorAmbulatorio = valorAmbulatorio;
    }

}
