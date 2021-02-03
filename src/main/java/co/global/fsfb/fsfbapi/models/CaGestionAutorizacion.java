package co.global.fsfb.fsfbapi.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CA_GESTION_AUTORIZACION")
@Getter
@Setter
@NoArgsConstructor
public class CaGestionAutorizacion {

	@Id
	@Column(name = "POM_ID_PREST_ORDM")
	private Long pomIdPrestOrdm;

	@Column(name = "PAC_PAC_NUMERO")
	private Long pacPacNumero;

	@Column(name = "GAU_NOMBRE_AUTORIZADOR")
	private String gauNombreAutorizador;

	@Column(name = "GAU_TELEFONO_AUTORIZADOR")
	private String gauTelefonoAutorizador;

	@Column(name = "GAU_AUTORIZA_SERV")
	private String gauAutorizaServ;

	@Column(name = "MNA_IDCODIGO")
	private Long mnaIdcodigo;

	@Column(name = "OMN_DESC")
	private String omnDesc;

	@Column(name = "GAU_CODIGO_AUTORIZACION")
	private String gauCodigoAutorizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GAU_FECHA_AUTORIZACION")
	private Date gauFechaAutorizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GAU_FECHA_VENC_AUTORIZACION")
	private Date gauFechaVencAutorizacion;

	@Column(name = "GAU_VIGENCIA_AUTORIZACION")
	private Long gauVigenciaAutorizacion;

	@Column(name = "GAU_VALOR_PRESTACION")
	private Long gauValorPrestacion;

	@Column(name = "GAU_COSTO_CONVENIO")
	private Long gauCostoConvenio;

	@Column(name = "GAU_COSTO_PAC")
	private Long gauCostoPac;

	@Column(name = "PCA_AGE_CODIGRECEP")
	private String pcaAgeCodigoRecep;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GAU_FECHA_PROCESO")
	private Date cgFechaProceso;

	@Column(name = "OGA_DESCRIPCION")
	private String ogaDescripcion;

}
