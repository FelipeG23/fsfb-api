package co.global.fsfb.fsfbapi.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CA_CITAS_GESTIONADAS")
@SequenceGenerator(sequenceName = "CA_CITAS_GESTIONADAS_SEQ", allocationSize = 1, name="CA_CITAS_GESTIONADAS_SEQ")
@Getter
@Setter
@NoArgsConstructor
public class CaCitasPruebas {

	@Id
	@Column(name = "CG_ID_CITA_NUMERO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CA_CITAS_GESTIONADAS_SEQ")
	private Long cGIdCitaNumero;

	@Column(name = "PAC_PAC_NUMERO")
	private Long pacPacNumero;

	@Column(name = "PCA_AGE_FECHACITAC")
	private Date pcaAgeFechaCitac;

	@Column(name = "PCA_AGE_HORACITAC", columnDefinition = "char")
	private String pcaAgeHoraCitac;

	@Column(name = "PCA_AGE_CODIGSERVI", columnDefinition = "char")
	private String pcaAgeCodigServi;

	@Column(name = "PCA_AGE_CODIGPROFE")
	private String pcaAgeCodigProfe;

	@Column(name = "PCA_AGE_OBJETO")
	private String pcaAgeObjeto;

	@Column(name = "PRE_PRE_CODIGO", columnDefinition = "char")
	private String prePreCodigo;

	@Column(name = "PCA_AGE_LUGAR", columnDefinition = "char")
	private String pcaAgeLugar;

	@Column(name = "CON_CON_CODIGO", columnDefinition = "char")
	private String conConCodigo;

	@Column(name = "PCA_AGE_CODIGRECEP", columnDefinition = "char")
	private String pcaAgeCodigRecep;

	@Column(name = "PCA_AGE_FECHADIGIT")
	private Date pcaAgeFechaDigit;

	@Column(name = "PCA_AGE_RECEPCIONADO", columnDefinition = "char")
	private String pcaAgeRecepcionado;

	@Column(name = "EC_IDCODIGO")
	private Long ecIdCodigo;

	@Column(name = "CG_ASISTENCIA")
	private String cgAsistencia;
	
	@Column(name = "Nombre_Completo")
	private String Nombre_Completo;
	
	@Column(name = "Documento")
	private String Documento;
	
	@Column(name = "codigoAutorizacion")
	private String codigoAutorizacion;
	
	@Column(name = "valorPrestacion")
	private String valorPrestacion;
	
	@Column(name = "fechaCita")
	private String fechaCita;
	
	@Column(name = "horaCita")
	private String horaCita;
	
	@Column(name = "fechautorizacion")
	private String fechautorizacion;
	
	@Column(name = "Tipo_Documento_homologado")
	private String Tipo_Documento_homologado;
		
}
