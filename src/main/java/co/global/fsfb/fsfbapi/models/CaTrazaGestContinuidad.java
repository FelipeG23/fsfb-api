/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.global.fsfb.fsfbapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "CA_TRAZA_GEST_CONTINUIDAD")
@Getter
@Setter
@NoArgsConstructor
public class CaTrazaGestContinuidad {

    @Id
    @Column(name = "POM_ID_PREST_ORDM")
    private Long pomIdPrestOrdm;

    @Column(name = "GCO_IDCODIGO_ESTADO")
    private Long gcoIdCodigoEstado;

    @Column(name = "GCO_IDCODIGO_MOTIVO")
    private Long gcoIdCodigoMotivo;

    @Column(name = "GCO_DIREC_PACIENTE")
    private String gcoDirecPaciente;

    @Column(name = "GCO_REALIZO_AGENDAMIENTO")
    private String gcoRealizoAgendamiento;

    @Column(name = "GCO_OBSERVACIONES")
    private String gcoObservaciones;

    @Column(name = "PCA_AGE_CODIGRECEP")
    private String pcaAgeCodigoRecep;

    @Column(name = "CG_FECHA_PROCESO")
    private String cgFechaProceso;

}
