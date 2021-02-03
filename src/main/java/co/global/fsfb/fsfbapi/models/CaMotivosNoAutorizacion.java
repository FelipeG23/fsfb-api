/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.global.fsfb.fsfbapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "CA_MOTIVOS_NO_AUTORIZACION")
@Getter
@Setter
@NoArgsConstructor
public class CaMotivosNoAutorizacion {

    // MNA_IDCODIGO
    @Id
    @Column(name = "MNA_IDCODIGO")
    private Long mnaIdCodigo;
    // MNA_DESCRIPCION
    @Column(name = "MNA_DESCRIPCION")
    private String mnaDescripcion;
    // MNA_ESTADO
    @Column(name = "MNA_ESTADO")
    private String mnaEstado;

}
