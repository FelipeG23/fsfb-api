package co.global.fsfb.fsfbapi.repositories;

import co.global.fsfb.fsfbapi.models.CaCitasGestionadas;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author POLLO
 */
@Repository
public interface ICitaRepository extends JpaRepository<CaCitasGestionadas, Long> {
	@Query(value= "SELECT numero_consecutivo, idCita, pacNumero, codCodigoprofe, codEstadocita, horaCita, fechaCita, valorPrestacion, codigoAutorizacion,"
			+ "fechaAutorizacion, monthAutorizacion, nombreCompleto, numDocumento, tipDocumento, R "
			+ "FROM (SELECT CA_CITAS_GESTIONADAS.CG_ID_CITA_NUMERO idCita,"
			+ "ROW_NUMBER() OVER ("
			+ "ORDER BY TO_DATE(TO_CHAR(CA_CITAS_GESTIONADAS.PCA_AGE_FECHACITAC, 'DD/MM/YYYY') || ' ' ||CA_CITAS_GESTIONADAS.PCA_AGE_HORACITAC, 'DD/MM/YYYY HH24:MI'  )  DESC) numero_consecutivo,"
			+ "CA_CITAS_GESTIONADAS.PAC_PAC_NUMERO pacNumero,"
			+ "CA_CITAS_GESTIONADAS.PCA_AGE_CODIGPROFE codCodigoprofe,"
			+ "CA_CITAS_GESTIONADAS.EC_IDCODIGO codEstadocita,"
			+ "CA_CITAS_GESTIONADAS.PCA_AGE_HORACITAC horaCita,"
			+ "TO_CHAR(CA_CITAS_GESTIONADAS.PCA_AGE_FECHACITAC, 'DD/MM/YYYY') fechaCita,"
			+ "CA_GESTION_AUTORIZACION_CITA.GAU_VALOR_PRESTACION valorPrestacion,"
			+ "CA_GESTION_AUTORIZACION_CITA.GAU_CODIGO_AUTORIZACION codigoAutorizacion,"
			+ "TO_CHAR(CA_GESTION_AUTORIZACION_CITA.GAU_FECHA_AUTORIZACION, 'DD/MM/YYYY') fechaAutorizacion,"
			+ "TO_CHAR(CA_GESTION_AUTORIZACION_CITA.GAU_FECHA_AUTORIZACION, 'MM/DD/YYYY') monthAutorizacion,"
			+ "(ADMSALUD.PAC_PACIENTE.PAC_PAC_NOMBRE)|| ' ' ||(ADMSALUD.PAC_PACIENTE.PAC_PAC_APELLPATER)|| ' ' ||(ADMSALUD.PAC_PACIENTE.PAC_PAC_APELLMATER) nombreCompleto,"
			+ "ADMSALUD.PAC_PACIENTE.PAC_PAC_RUT numDocumento,"
			+ "ADMSALUD.TAB_TIPOIDENT.TIPIDAV tipDocumento,"
			+ "ROWNUM R "
			+ "FROM CA_CITAS_GESTIONADAS, ADMSALUD.PAC_PACIENTE@ISIS, ADMSALUD.TAB_TIPOIDENT@ISIS, CA_GESTION_AUTORIZACION_CITA "
			+ "WHERE CA_CITAS_GESTIONADAS.EC_IDCODIGO = 3 AND (CA_CITAS_GESTIONADAS.PAC_PAC_NUMERO) = (ADMSALUD.PAC_PACIENTE.PAC_PAC_NUMERO) AND (ADMSALUD.PAC_PACIENTE.PAC_PAC_TIPOIDENTCODIGO) = (ADMSALUD.TAB_TIPOIDENT.TAB_TIPOIDENTCODIGO) AND CA_GESTION_AUTORIZACION_CITA.CG_ID_CITA_NUMERO = CA_CITAS_GESTIONADAS.CG_ID_CITA_NUMERO) "
			+ "WHERE NUMERO_CONSECUTIVO >= ?1 AND NUMERO_CONSECUTIVO <= ?2  "
			+ " ",
			//+ "WHERE R > ?1 AND R < ?2 ",
			nativeQuery = true
			)
	List<PruebaCita> listaCitasAutorizadas (Integer init, Integer end);
	
	public interface PruebaCita{
		int getidCita();
		int getpacNumero();
		int getcodCodigoprofe();
		String getnumDocumento();
		String getTipDocumento();
		String getcodEstadoCita();
		String getnombreCompleto();
		String getvalorPrestacion();
		String getFechacita(); 
		String getHoracita(); 
		String getfechaAutorizacion();
		String getmonthAutorizacion();
		String getcodigoAutorizacion();
	}
}

