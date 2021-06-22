package co.global.fsfb.fsfbapi.services.impl;

import co.global.fsfb.fsfbapi.constants.QueryConst;
import co.global.fsfb.fsfbapi.dto.ConsultaCitasDto;
import co.global.fsfb.fsfbapi.dto.ResultadoCitaDto;
import co.global.fsfb.fsfbapi.models.CaCitasGestionadas;
import co.global.fsfb.fsfbapi.models.CaUbicacionSedes;
import co.global.fsfb.fsfbapi.persistencia.Conexion;
import co.global.fsfb.fsfbapi.repositories.ICaUbicacionSedesRepository;
import co.global.fsfb.fsfbapi.repositories.ICitaRepository;
import co.global.fsfb.fsfbapi.services.ICitaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Parameter;

/**
 *
 * @author POLLO
 */
@Service
@Slf4j
public class CitaService implements ICitaService {

    @Autowired
    private ICitaRepository citaRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    ICaUbicacionSedesRepository iCaUbicacionSedesRepository;
    private static final Logger LOG = Logger.getLogger(CitaService.class.getName());

    @Override
    public List<ResultadoCitaDto> consultarCitas(ConsultaCitasDto consultaCitasDto) {
        try {
            StringBuilder SQL = new StringBuilder(QueryConst.Citas.CONSULTAR_CITAS);
            if (consultaCitasDto.getEstado() != null) {
                SQL.append((consultaCitasDto.getEstado() == 1) ? " AND EC.EC_IDCODIGO IS NULL" : " AND EC.EC_IDCODIGO = :ESTADO");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodCentroAten())) {
                SQL.append(" AND CA.CODIGOINTERNOCENTRO = :SEDE");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodEspecialidad())) {
                SQL.append(" AND ESPE.SER_ESP_CODIGO = :ESPE");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodSubEspecialidad())) {
                SQL.append(" AND SUBESPE.SER_SUB_CODIGO = :SUBESPE");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodServicio())) {
                SQL.append(" AND SER.SER_SER_CODIGO = :SERVICIO");
            }
            if (consultaCitasDto.getConvenios() != null && !consultaCitasDto.getConvenios().isEmpty()) {
                final StringBuilder finalStringBuilder;
                final StringBuilder stringBuilder = finalStringBuilder = new StringBuilder();
                consultaCitasDto.getConvenios().forEach(v -> finalStringBuilder.append(",'" + v + "'"));
                final String str = stringBuilder.substring(1);
                SQL.append(" AND CONV.CON_CON_CODIGO IN (:CODCON)".replace(":CODCON", str));
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getTipoDocId())) {
                SQL.append(" AND TIP.TAB_TIPOIDENTCODIGO = :TIPIDENCOD");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNumDocId())) {
                SQL.append(" AND TRIM(PAC.PAC_PAC_RUT) = :PAC");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNombres())) {
                SQL.append(" AND UPPER(TRIM(PAC.PAC_PAC_NOMBRE)) LIKE (CONCAT('%', CONCAT(:NOMBRE, '%')))");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getPrimerApellido())) {
                SQL.append(" AND UPPER(TRIM(PAC.PAC_PAC_APELLPATER)) LIKE (CONCAT('%', CONCAT(:APEPATER, '%')))");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getSegundoApellido())) {
                SQL.append(" AND UPPER(TRIM(PAC.PAC_PAC_APELLMATER)) LIKE (CONCAT('%', CONCAT(:APEMATER, '%')))");
            }
            if (consultaCitasDto.getNombreSede() != null && !Strings.isEmpty((CharSequence) consultaCitasDto.getNombreSede())) {
                SQL.append(" AND TRIM(AGEND.PCA_AGE_OBJETO) IN (:UBICACIONES)\n");
            }
            SQL.append(" ORDER BY \"Fecha Cita\" desc,\n         \"Hora Cita\" desc,\n         \"Tipo de Documento homologado\" desc,\n         \"Documento\" desc   \n");
            LOG.log(Level.INFO, SQL.toString());

            Query query = entityManager.createNativeQuery(SQL.toString())
                    .setParameter("FECHAINICIAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaInicial()).concat(" 00:00:00")))
                    .setParameter("FECHAFINAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaFinal()).concat(" 23:59:59")));

            if (consultaCitasDto.getEstado() != null && consultaCitasDto.getEstado() != 1) {
                query.setParameter("ESTADO", (Object) consultaCitasDto.getEstado());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodCentroAten())) {
                query.setParameter("SEDE", (Object) consultaCitasDto.getCodCentroAten());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodEspecialidad())) {
                query.setParameter("ESPE", (Object) consultaCitasDto.getCodEspecialidad());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodSubEspecialidad())) {
                query.setParameter("SUBESPE", (Object) consultaCitasDto.getCodSubEspecialidad());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodServicio())) {
                query.setParameter("SERVICIO", (Object) consultaCitasDto.getCodServicio());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getTipoDocId())) {
                query.setParameter("TIPIDENCOD", (Object) consultaCitasDto.getTipoDocId());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNumDocId())) {
                query.setParameter("PAC", (Object) consultaCitasDto.getNumDocId());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNombres())) {
                query.setParameter("NOMBRE", (Object) ((consultaCitasDto.getNombres() == null) ? "" : consultaCitasDto.getNombres().trim().toUpperCase()));
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getPrimerApellido())) {
                query.setParameter("APEPATER", (Object) ((consultaCitasDto.getPrimerApellido() == null) ? "" : consultaCitasDto.getPrimerApellido().trim().toUpperCase()));
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getSegundoApellido())) {
                query.setParameter("APEMATER", (Object) ((consultaCitasDto.getSegundoApellido() == null) ? "" : consultaCitasDto.getSegundoApellido().trim().toUpperCase()));
            }
            if (consultaCitasDto.getNombreSede() != null && !Strings.isEmpty((CharSequence) consultaCitasDto.getNombreSede())) {
                final List<CaUbicacionSedes> caUbicacionSedes = this.iCaUbicacionSedesRepository.findDistinctByUbicacion(consultaCitasDto.getNombreSede().trim());
                final List<String> in = new ArrayList<String>();
                caUbicacionSedes.forEach(caUbicacionSede -> in.add(caUbicacionSede.getConsultorio().trim()));
                query.setParameter("UBICACIONES", (Object) in);
            }
            final List<Object[]> citas = (List<Object[]>) query.getResultList();

            final List<ResultadoCitaDto> resultadoCitaDtos = new ArrayList<ResultadoCitaDto>();
            citas.stream().forEach(object -> {
                ResultadoCitaDto resultadoCitaDto = new ResultadoCitaDto();
                resultadoCitaDto.setCiudad((object[0] != null) ? object[0].toString().trim() : null);
                resultadoCitaDto.setIdCita((object[1] != null) ? Long.valueOf(((BigDecimal) object[1]).longValue()) : null);
                resultadoCitaDto.setCodEstadoCita((object[2] != null) ? object[2].toString() : null);
                resultadoCitaDto.setEstadoCita((String) object[3]);
                resultadoCitaDto.setAsistencia((String) object[4]);
                resultadoCitaDto.setPacNum(((BigDecimal) object[5]).longValue());
                resultadoCitaDto.setNombreCompleto((object[6] != null) ? object[6].toString().trim() : null);
                resultadoCitaDto.setTipoDocId(object[7] + "");
                resultadoCitaDto.setTipTipIDav(object[8].toString().trim());
                resultadoCitaDto.setNumDocId(object[9].toString().trim());
                resultadoCitaDto.setTelefono((object[10] != null) ? object[10].toString().trim() : null);
                resultadoCitaDto.setEmail((object[11] != null) ? object[11].toString().trim() : null);
                resultadoCitaDto.setHoraCita(object[12].toString().trim());
                resultadoCitaDto.setFechaCita((object[13] != null) ? object[13].toString().split(" ")[0] : null);
                resultadoCitaDto.setCodServicio((object[14] != null) ? object[14].toString() : null);
                resultadoCitaDto.setServicio((object[15] != null) ? object[15].toString().trim() : null);
                resultadoCitaDto.setCodProf((object[16] != null) ? object[16].toString() : null);
                resultadoCitaDto.setConsultorio((object[17] != null) ? object[17].toString().trim() : null);
                resultadoCitaDto.setCodigoPrestacion((object[18] != null) ? object[18].toString().trim() : null);
                resultadoCitaDto.setPrestacion((object[19] != null) ? object[19].toString().trim() : null);
                resultadoCitaDto.setCodCentroAten((object[20] != null) ? object[20].toString().trim() : null);
                resultadoCitaDto.setNombreCentroAten((object[21] != null) ? object[21].toString().trim() : null);
                resultadoCitaDto.setNombreCentroAten((object[21] != null) ? object[21].toString().trim() : null);
                resultadoCitaDto.setCodConvenio(object[22] != null ? Arrays.asList(object[22].toString()) : null);
                resultadoCitaDto.setConvenio((object[23] != null) ? object[23].toString().trim() : null);
                resultadoCitaDto.setCodUsrCita((object[24] != null) ? object[24].toString().trim() : null);
                resultadoCitaDto.setFechaAsigna((object[25] != null) ? object[25].toString().split(" ")[0] : null);
                resultadoCitaDto.setDireccionCentroOperativo((object[26] != null) ? object[26].toString().trim() : null);
                resultadoCitaDto.setTelefonoCentroOperativo((object[27] != null) ? object[27].toString().trim() : null);
                resultadoCitaDto.setCodEspecialidad((object[29] != null) ? object[29].toString() : null);
                resultadoCitaDto.setEspecialidad((object[30] != null) ? object[30].toString().trim() : null);
                resultadoCitaDto.setCodSubespecialidad((object[31] != null) ? object[31].toString() : null);
                resultadoCitaDto.setSubEspecialidad((object[32] != null) ? object[32].toString().trim() : null);
                resultadoCitaDto.setNombreProf((object[33] != null) ? object[33].toString().trim() : null);
                resultadoCitaDto.setUsrCita((object[34] != null) ? object[34].toString().trim() : null);
                resultadoCitaDto.setUbicacion((object[35] != null) ? object[35].toString().trim() : null);
                resultadoCitaDto.setLetraCodCentroAten((object[36] != null) ? object[36].toString().trim() : null);
                resultadoCitaDto.setUbicacionSede((object[37] != null) ? object[37].toString().trim() : null);
                resultadoCitaDto.setDireccionSede((object[38] != null) ? object[38].toString().trim() : null);
                resultadoCitaDto.setTipoConvenio((object[39] != null) ? object[39].toString().trim() : null);
                resultadoCitaDtos.add(resultadoCitaDto);
            });
            return resultadoCitaDtos;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "error", e);
        }
        return null;
    }

    @Override
    public List<ResultadoCitaDto> consultarCitasPaginate(
            ConsultaCitasDto consultaCitasDto, int page) {
        try {
            StringBuilder SQL = new StringBuilder(QueryConst.Citas.CONSULTAR_CITAS);
            if (consultaCitasDto.getEstado() != null) {
                SQL.append((consultaCitasDto.getEstado() == 1) ? " AND EC.EC_IDCODIGO IS NULL" : " AND EC.EC_IDCODIGO = :ESTADO");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodCentroAten())) {
                SQL.append(" AND CA.CODIGOINTERNOCENTRO = :SEDE");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodEspecialidad())) {
                SQL.append(" AND TRIM(ESPE.SER_ESP_CODIGO) = :ESPE");
                if (consultaCitasDto.getCodSubEspecialidad().trim().isEmpty()){
                    SQL.append(" AND TRIM(SUBESPE.SER_ESP_CODIGO) = :ESPE");
                }
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodSubEspecialidad())) {
                SQL.append(" AND TRIM(SUBESPE.SER_SUB_CODIGO) = :SUBESPE");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodServicio())) {
                SQL.append(" AND SER.SER_SER_CODIGO = :SERVICIO");
            }
            if (consultaCitasDto.getConvenios() != null && !consultaCitasDto.getConvenios().isEmpty()) {
                final StringBuilder finalStringBuilder;
                final StringBuilder stringBuilder = finalStringBuilder = new StringBuilder();
                consultaCitasDto.getConvenios().forEach(v -> finalStringBuilder.append(",'" + v + "'"));
                final String str = stringBuilder.substring(1);
                SQL.append(" AND CONV.CON_CON_CODIGO IN (:CODCON)".replace(":CODCON", str));
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getTipoDocId())) {
                SQL.append(" AND TIP.TAB_TIPOIDENTCODIGO = :TIPIDENCOD");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNumDocId())) {
                SQL.append(" AND TRIM(PAC.PAC_PAC_RUT) = :PAC");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNombres())) {
                SQL.append(" AND UPPER(TRIM(PAC.PAC_PAC_NOMBRE)) LIKE (CONCAT('%', CONCAT(:NOMBRE, '%')))");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getPrimerApellido())) {
                SQL.append(" AND UPPER(TRIM(PAC.PAC_PAC_APELLPATER)) LIKE (CONCAT('%', CONCAT(:APEPATER, '%')))");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getSegundoApellido())) {
                SQL.append(" AND UPPER(TRIM(PAC.PAC_PAC_APELLMATER)) LIKE (CONCAT('%', CONCAT(:APEMATER, '%')))");
            }
            if (consultaCitasDto.getNombreSede() != null && !Strings.isEmpty((CharSequence) consultaCitasDto.getNombreSede())) {
                SQL.append(" AND TRIM(AGEND.PCA_AGE_OBJETO) IN (:UBICACIONES)\n");
            }
            SQL.append(" ORDER BY \"Fecha Cita\" desc,\n         \"Hora Cita\" desc,\n         \"Tipo de Documento homologado\" desc,\n         \"Documento\" desc   \n");
            LOG.log(Level.INFO, SQL.toString());

            SQL.append("    OFFSET " + page + "ROWS FETCH NEXT 10 ROWS ONLY");

            Query query = entityManager.createNativeQuery(SQL.toString())
                    .setParameter("FECHAINICIAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaInicial()).concat(" 00:00:00")))
                    .setParameter("FECHAFINAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaFinal()).concat(" 23:59:59")));

            if (consultaCitasDto.getEstado() != null && consultaCitasDto.getEstado() != 1) {
                query.setParameter("ESTADO", (Object) consultaCitasDto.getEstado());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodCentroAten())) {
                query.setParameter("SEDE", (Object) consultaCitasDto.getCodCentroAten());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodEspecialidad())) {
                query.setParameter("ESPE", (Object) consultaCitasDto.getCodEspecialidad());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodSubEspecialidad())) {
                query.setParameter("SUBESPE", (Object) consultaCitasDto.getCodSubEspecialidad());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodServicio())) {
                query.setParameter("SERVICIO", (Object) consultaCitasDto.getCodServicio());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getTipoDocId())) {
                query.setParameter("TIPIDENCOD", (Object) consultaCitasDto.getTipoDocId());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNumDocId())) {
                query.setParameter("PAC", (Object) consultaCitasDto.getNumDocId());
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNombres())) {
                query.setParameter("NOMBRE", (Object) ((consultaCitasDto.getNombres() == null) ? "" : consultaCitasDto.getNombres().trim().toUpperCase()));
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getPrimerApellido())) {
                query.setParameter("APEPATER", (Object) ((consultaCitasDto.getPrimerApellido() == null) ? "" : consultaCitasDto.getPrimerApellido().trim().toUpperCase()));
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getSegundoApellido())) {
                query.setParameter("APEMATER", (Object) ((consultaCitasDto.getSegundoApellido() == null) ? "" : consultaCitasDto.getSegundoApellido().trim().toUpperCase()));
            }
            if (consultaCitasDto.getNombreSede() != null && !Strings.isEmpty((CharSequence) consultaCitasDto.getNombreSede())) {
                final List<CaUbicacionSedes> caUbicacionSedes = this.iCaUbicacionSedesRepository.findDistinctByUbicacion(consultaCitasDto.getNombreSede().trim());
                final List<String> in = new ArrayList<String>();
                caUbicacionSedes.forEach(caUbicacionSede -> in.add(caUbicacionSede.getConsultorio().trim()));
                query.setParameter("UBICACIONES", (Object) in);
            }

            System.out.println("Query: " + query);
            final List<Object[]> citas = (List<Object[]>) query.getResultList();

            final List<ResultadoCitaDto> resultadoCitaDtos = new ArrayList<ResultadoCitaDto>();
            citas.stream().forEach(object -> {
                ResultadoCitaDto resultadoCitaDto = new ResultadoCitaDto();
                resultadoCitaDto.setCiudad((object[0] != null) ? object[0].toString().trim() : null);
                resultadoCitaDto.setIdCita((object[1] != null) ? Long.valueOf(((BigDecimal) object[1]).longValue()) : null);
                resultadoCitaDto.setCodEstadoCita((object[2] != null) ? object[2].toString() : null);
                resultadoCitaDto.setEstadoCita((String) object[3]);
                resultadoCitaDto.setAsistencia((String) object[4]);
                resultadoCitaDto.setPacNum(((BigDecimal) object[5]).longValue());
                resultadoCitaDto.setNombreCompleto((object[6] != null) ? object[6].toString().trim() : null);
                resultadoCitaDto.setTipoDocId(object[7] + "");
                resultadoCitaDto.setTipTipIDav(object[8].toString().trim());
                resultadoCitaDto.setNumDocId(object[9].toString().trim());
                resultadoCitaDto.setTelefono((object[10] != null) ? object[10].toString().trim() : null);
                resultadoCitaDto.setEmail((object[11] != null) ? object[11].toString().trim() : null);
                resultadoCitaDto.setHoraCita(object[12].toString().trim());
                resultadoCitaDto.setFechaCita((object[13] != null) ? object[13].toString().split(" ")[0] : null);
                resultadoCitaDto.setCodServicio((object[14] != null) ? object[14].toString() : null);
                resultadoCitaDto.setServicio((object[15] != null) ? object[15].toString().trim() : null);
                resultadoCitaDto.setCodProf((object[16] != null) ? object[16].toString() : null);
                resultadoCitaDto.setConsultorio((object[17] != null) ? object[17].toString().trim() : null);
                resultadoCitaDto.setCodigoPrestacion((object[18] != null) ? object[18].toString().trim() : null);
                resultadoCitaDto.setPrestacion((object[19] != null) ? object[19].toString().trim() : null);
                resultadoCitaDto.setCodCentroAten((object[20] != null) ? object[20].toString().trim() : null);
                resultadoCitaDto.setNombreCentroAten((object[21] != null) ? object[21].toString().trim() : null);
                resultadoCitaDto.setNombreCentroAten((object[21] != null) ? object[21].toString().trim() : null);
                resultadoCitaDto.setCodConvenio(object[22] != null ? Arrays.asList(object[22].toString()) : null);
                resultadoCitaDto.setConvenio((object[23] != null) ? object[23].toString().trim() : null);
                resultadoCitaDto.setCodUsrCita((object[24] != null) ? object[24].toString().trim() : null);
                resultadoCitaDto.setFechaAsigna((object[25] != null) ? object[25].toString().split(" ")[0] : null);
                resultadoCitaDto.setDireccionCentroOperativo((object[26] != null) ? object[26].toString().trim() : null);
                resultadoCitaDto.setTelefonoCentroOperativo((object[27] != null) ? object[27].toString().trim() : null);
                resultadoCitaDto.setCodEspecialidad((object[29] != null) ? object[29].toString() : null);
                resultadoCitaDto.setEspecialidad((object[30] != null) ? object[30].toString().trim() : null);
                resultadoCitaDto.setCodSubespecialidad((object[31] != null) ? object[31].toString() : null);
                resultadoCitaDto.setSubEspecialidad((object[32] != null) ? object[32].toString().trim() : null);
                resultadoCitaDto.setNombreProf((object[33] != null) ? object[33].toString().trim() : null);
                resultadoCitaDto.setUsrCita((object[34] != null) ? object[34].toString().trim() : null);
                resultadoCitaDto.setUbicacion((object[35] != null) ? object[35].toString().trim() : null);
                resultadoCitaDto.setLetraCodCentroAten((object[36] != null) ? object[36].toString().trim() : null);
                resultadoCitaDto.setUbicacionSede((object[37] != null) ? object[37].toString().trim() : null);
                resultadoCitaDto.setDireccionSede((object[38] != null) ? object[38].toString().trim() : null);
                resultadoCitaDto.setTipoConvenio((object[39] != null) ? object[39].toString().trim() : null);
                resultadoCitaDtos.add(resultadoCitaDto);
            });
            return resultadoCitaDtos;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "error", e);
        }
        return null;
    }

    @Override
    public List<ResultadoCitaDto> consultarCitasPorAutorizar(ConsultaCitasDto consultaCitasDto) {

        try {
            StringBuilder SQL = new StringBuilder(QueryConst.Citas.CONSULTAR_CITAS_POR_AUTORIZAR);

            SQL.append(QueryConst.Citas.AND_FECHAS);

            if (!Strings.isEmpty(consultaCitasDto.getTipoDocId())) {
                SQL.append(QueryConst.Citas.AND_TIPO_DOCUMENTO);
            }
            if (!Strings.isEmpty(consultaCitasDto.getNumDocId())) {
                SQL.append(QueryConst.Citas.AND_NUMERO_DOCUMENTO);
            }
            if (!Strings.isEmpty(consultaCitasDto.getNombres())) {
                SQL.append(QueryConst.Citas.AND_NOMBRES);
            }
            if (!Strings.isEmpty(consultaCitasDto.getPrimerApellido())) {
                SQL.append(QueryConst.Citas.AND_PRIMER_APPELLIDO);
            }
            if (!Strings.isEmpty(consultaCitasDto.getSegundoApellido())) {
                SQL.append(QueryConst.Citas.AND_SEGUNDO_APPELLIDO);
            }

            SQL.append(QueryConst.Citas.ORDERS_BY_CITAS_POR_AUTORIZAR);

            Query query = entityManager.createNativeQuery(SQL.toString())
                    .setParameter("FECHA_INICIAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaInicial()).concat(" 00:00:00")))
                    .setParameter("FECHA_FINAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaFinal()).concat(" 23:59:59")));

            List<Object[]> citas = query.getResultList();
            List<ResultadoCitaDto> resultadoCitaDtos = new ArrayList<ResultadoCitaDto>();
            citas.stream().forEach(object -> {
                ResultadoCitaDto resultadoCitaDto = new ResultadoCitaDto();
                resultadoCitaDto.setIdCita(object[0] != null ? ((BigDecimal) object[0]).longValue() : null);
                resultadoCitaDto.setPacNum(((BigDecimal) object[1]).longValue());
                resultadoCitaDto.setNombreCompleto(object[2] != null ? object[2].toString().trim() : null);
                resultadoCitaDto.setTipTipIDav(object[3].toString().trim());
                resultadoCitaDto.setNumDocId(object[4].toString().trim());
                resultadoCitaDto.setFechaCita(
                        object[5] != null ? new SimpleDateFormat("yyyy/MM/dd").format((Timestamp) object[5]) : null);
                resultadoCitaDto.setHoraCita(object[6] != null ? object[6].toString().trim() : null);
                resultadoCitaDto.setFechaCitaCA(
                        object[7] != null ? new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format((Timestamp) object[7])
                                : null);
                resultadoCitaDto.setCodUsrCita(object[8].toString().trim());
                resultadoCitaDto.setCodConvenio(Arrays.asList(object[9] != null ? object[9].toString().trim() : ""));
                resultadoCitaDto.setCodigoPrestacion(object[10] != null ? object[10].toString().trim() : "");
                resultadoCitaDto.setCodEstadoCita(object[12] != null ? object[12].toString().trim() : "");
                resultadoCitaDto.setValorPrestacional(object[13] != null ? object[13].toString().trim() : "");
                resultadoCitaDto.setTipoConvenio(object[14] != null ? object[14].toString().trim() : "");
                resultadoCitaDtos.add(resultadoCitaDto);
            });
            return resultadoCitaDtos;
        } catch (Exception e) {
//            log.error("Error consultando citas medicas", e);
            return new ArrayList<ResultadoCitaDto>();
        }
    }

    public String convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter).format(formatter2);
    }

    @Override
    public void updateAsistencia(Long citaId, String estado) {
        Optional<CaCitasGestionadas> citaOptional = citaRepository.findById(citaId);
        if (citaOptional.isPresent()) {
            CaCitasGestionadas cita = citaOptional.get();
            cita.setCgAsistencia(estado);
            citaRepository.save(cita);
        }
    }

    @Override
    public ResultadoCitaDto consultarCitaPorId(ConsultaCitasDto consultaCitasDto) {
        String SQL = QueryConst.Citas.CONSULTAR_CITA_POR_ID;
        System.out.print(SQL);
        Query query = entityManager.createNativeQuery(SQL)
                .setParameter("IDCITA", consultaCitasDto.getIdCita());

        List<Object[]> citas = query.getResultList();
        List<ResultadoCitaDto> resultadoCitaDtos = new ArrayList<ResultadoCitaDto>();
        citas.stream().forEach(object -> {
            ResultadoCitaDto resultadoCitaDto = new ResultadoCitaDto();
            resultadoCitaDto.setIdCita(object[0] != null ? ((BigDecimal) object[0]).longValue() : null);
            resultadoCitaDto.setCodEstadoCita(object[1] != null ? object[1].toString() : null);
            resultadoCitaDto.setEstadoCita((String) object[2]);
            resultadoCitaDto.setAsistencia((String) object[3]);
            resultadoCitaDto.setPacNum(((BigDecimal) object[4]).longValue());
            resultadoCitaDto.setNombreCompleto(object[5] != null ? object[5].toString().trim() : null);
            resultadoCitaDto.setTipoDocId(object[6] + "");
            resultadoCitaDto.setTipTipIDav(object[7].toString().trim());
            resultadoCitaDto.setNumDocId(object[8].toString().trim());
            resultadoCitaDto.setTelefono(object[9] != null ? object[9].toString().trim() : null);
            resultadoCitaDto.setEmail(object[10] != null ? object[10].toString().trim() : null);
            resultadoCitaDto.setHoraCita(object[11].toString().trim());
            resultadoCitaDto.setFechaCita(object[12] != null ? object[12].toString().split(" ")[0] : null);
            resultadoCitaDto.setCodServicio(object[13] != null ? object[13].toString() : null);
            resultadoCitaDto.setServicio(object[14] != null ? object[14].toString().trim() : null);
            resultadoCitaDto.setCodProf(object[15] != null ? object[15].toString() : null);
            resultadoCitaDto.setConsultorio(object[16] != null ? object[16].toString().trim() : null);
            resultadoCitaDto.setCodigoPrestacion(object[17] != null ? object[17].toString().trim() : null);
            resultadoCitaDto.setPrestacion(object[18] != null ? object[18].toString().trim() : null);
            resultadoCitaDto.setCodCentroAten(object[19] != null ? object[19].toString().trim() : null);
            resultadoCitaDto.setNombreCentroAten(object[20] != null ? object[20].toString().trim() : null);
            resultadoCitaDto.setCodConvenio(object[21] != null ? Arrays.asList(object[21].toString()) : null);
            resultadoCitaDto.setConvenio(object[22] != null ? object[22].toString().trim() : null);
            resultadoCitaDto.setCodUsrCita(object[23] != null ? object[23].toString().trim() : null);
            resultadoCitaDto.setFechaAsigna(object[24] != null ? object[24].toString().split(" ")[0] : null);
            resultadoCitaDto.setDireccionCentroOperativo(object[25] != null ? object[25].toString().trim() : null);
            resultadoCitaDto.setTelefonoCentroOperativo(object[26] != null ? object[26].toString().trim() : null);
            resultadoCitaDto.setCodEspecialidad(object[28] != null ? object[28].toString() : null);
            resultadoCitaDto.setEspecialidad(object[29] != null ? object[29].toString().trim() : null);
            resultadoCitaDto.setCodSubespecialidad(object[30] != null ? object[30].toString() : null);
            resultadoCitaDto.setSubEspecialidad(object[31] != null ? object[31].toString().trim() : null);
            resultadoCitaDto.setNombreProf(object[32] != null ? object[32].toString().trim() : null);
            resultadoCitaDto.setUsrCita(object[33] != null ? object[33].toString().trim() : null);
            resultadoCitaDto.setUbicacion(object[34] != null ? object[34].toString().trim() : null);
            resultadoCitaDto.setLetraCodCentroAten(object[35] != null ? object[35].toString().trim() : null);
            resultadoCitaDto.setTipoConvenio(object[36] != null ? object[36].toString().trim() : null);
            resultadoCitaDto.setNroFormulario(object[37] != null ? object[37].toString().trim() : null);
            resultadoCitaDtos.add(resultadoCitaDto);
        });
        return !resultadoCitaDtos.isEmpty() ? resultadoCitaDtos.get(0) : null;

    }

    @Override
    public List<ResultadoCitaDto> consultarCitas2(ConsultaCitasDto consultaCitasDto) {
        Connection conn = null;
        try {
            LOG.log(Level.INFO, "Conexion directa inicio - query citas");

            Conexion conexion = new Conexion();
            conn = conexion.obtener();

            StringBuilder SQL = new StringBuilder("SELECT\n"
                    + "DP.DVP_PRO_GLOSA \"ciudad\" ,\n"
                    + "CIT.CG_ID_CITA_NUMERO \"id_cita\",\n"
                    + "EC.EC_IDCODIGO \"cod estado_cita_ca_descripcion\",\n"
                    + "EC.EC_DESCRIPCION \"estado_cita_ca_descripcion\",\n"
                    + "CIT.CG_ASISTENCIA \"asistencia\",\n"
                    + "PAC.PAC_PAC_NUMERO \"pacNum\",\n"
                    + "TRIM(PAC.PAC_PAC_NOMBRE)||' '||TRIM(PAC.PAC_PAC_APELLPATER)||' '||TRIM(PAC.PAC_PAC_APELLMATER) \"Nombre Completo\",\n"
                    + "PAC.PAC_PAC_TIPOIDENTCODIGO \"Codigo Tipo de Documento\",\n"
                    + "TIP.TIPIDAV \"Tipo de Documento homologado\",\n"
                    + "PAC.PAC_PAC_RUT \"Documento\",\n"
                    + "PAC.PAC_PAC_FONO \"Telefono\",\n"
                    + "PAC.DONURL \"Correo Electronico\",\n"
                    + "AGEND.PCA_AGE_HORACITAC \"Hora Cita\",\n"
                    + "AGEND.PCA_AGE_FECHACITAC \"Fecha Cita\",\n"
                    + "SER.SER_SER_CODIGO \"codigo servicio\",\n"
                    + "SER.SER_SER_DESCRIPCIO \"Servicio\",\n"
                    + "AGEND.PCA_AGE_CODIGPROFE \"codigo Profesional\",\n"
                    + "AGEND.PCA_AGE_OBJETO \"Consultorio\",\n"
                    + "PREST.PRE_PRE_CODIGO \"codigo Prestacion\",\n"
                    + "PREST.PRE_PRE_DESCRIPCIO \"Prestacion\",\n"
                    + "CA.CODIGOINTERNOCENTRO \"Codigo Centro Operativo\",\n"
                    + "CA.NOMBRECENTROATEN \"Centro Operativo\",\n"
                    + "CONV.CON_CON_CODIGO \"Codigo Convenio\",\n"
                    + "CONV.CON_CON_DESCRIPCIO \"Convenio\",\n"
                    + "AGEND.PCA_AGE_CODIGRECEP \"Codigo usuario\",\n"
                    + "AGEND.PCA_AGE_FECHADIGIT \"Fecha de Asignacion\",\n"
                    + "(SELECT MAX(DIRECCION) FROM admsalud.log_centrosatenc@ISIS DIR\n"
                    + "WHERE CA.CODIGOINTERNOCENTRO =DIR.CODIGOINTERNOCENTRO ) \"Direccion Centro Operativo\",\n"
                    + "CA.TELEFONO \"Telefono Centro Operativo\",\n"
                    + "CA.DVP_PRO_CODIGO \"Ciudad\",\n"
                    + "ESPE.SER_ESP_CODIGO \"Cod Especialidad\",\n"
                    + "ESPE.SER_ESP_DESCRIPCIO \"Especialidad\",\n"
                    + "SER.SER_SER_CODSUBESPE \"Cod subespecialidad\",\n"
                    + "SUBESPE.SER_SUB_DESCRIPCIO \"Subespecialidad\",\n"
                    + "funchspgetprof@isis(1,AGEND.PCA_AGE_CODIGPROFE) \"Profesional\",\n"
                    + "admsalud.glbgetnomusr@isis(AGEND.PCA_AGE_CODIGRECEP) \"Usuario que asigna cita\",\n"
                    + "(SELECT MAX(SER_LUG_UBICACION)\n"
                    + "FROM ADMSALUD.SER_LUGARES@ISIS LUG,\n"
                    + "ADMSALUD.SER_RECASISTEN@ISIS REC\n"
                    + "WHERE OBJ.SER_OBJ_UBICACION = LUG.SER_LUG_CODIGO\n"
                    + "AND OBJ.CODIGOCENTROATEN = LUG.CODIGOCENTROATEN\n"
                    + "AND OBJ.SER_REC_TIPO = REC.SER_REC_TIPO\n"
                    + "AND LUG.SER_LUG_TIPOLUGAR= REC.SER_REC_TIPOLUGAR) \"ubicacion\",\n"
                    + "CA.CODIGOCENTROATEN \"Letra Codigo Centro Operativo\",\n"
                    + "UBIC.UBICACION \"ubicacion sede\" ,\n"
                    + "UBIC.DIRECCION \"direccion\" ,\n"
                    + "CONV.CON_TIPOCONVECOD \"TipoConvenio\"\n"
                    + "FROM ADMSALUD.PCA_AGENDA@ISIS AGEND\n"
                    + "\n"
                    + "INNER JOIN ADMSALUD.PAC_PACIENTE@ISIS PAC\n"
                    + "ON AGEND.PCA_AGE_NUMERPACIE = PAC.PAC_PAC_NUMERO\n"
                    + "\n"
                    + "LEFT JOIN ADMSALUD.TAB_TIPOIDENT@ISIS TIP\n"
                    + "ON PAC.PAC_PAC_TIPOIDENTCODIGO = TIP.TAB_TIPOIDENTCODIGO\n"
                    + "\n"
                    + "INNER JOIN ADMSALUD.SER_SERVICIOS@ISIS SER\n"
                    + "ON AGEND.PCA_AGE_CODIGSERVI = SER.SER_SER_CODIGO\n"
                    + "\n"
                    + "LEFT JOIN ADMSALUD.SER_ESPECIALI@ISIS ESPE\n"
                    + "ON ESPE.SER_ESP_CODIGO = SER.SER_SER_CODIGESPEC\n"
                    + "\n"
                    + "LEFT JOIN ADMSALUD.SER_SUBESPECIA@ISIS SUBESPE\n"
                    + "ON SER.SER_SER_CODSUBESPE= SUBESPE.SER_SUB_CODIGO\n"
                    + "AND SUBESPE.SER_ESP_CODIGO=ESPE.SER_ESP_CODIGO\n"
                    + "\n"
                    + "INNER JOIN ADMSALUD.RPA_FORCIT@ISIS FORCIT\n"
                    + "ON AGEND.PCA_AGE_TIPOFORMU = FORCIT.RPA_FOR_TIPOFORMU\n"
                    + "AND AGEND.PCA_AGE_NUMERFORMU = FORCIT.RPA_FOR_NUMERFORMU\n"
                    + "AND AGEND.PCA_AGE_FECHACITAC = FORCIT.RPA_FCI_FECHACITAC\n"
                    + "AND AGEND.PCA_AGE_HORACITAC = FORCIT.RPA_FCI_HORACITAC\n"
                    + "\n"
                    + "\n"
                    + "LEFT JOIN ADMSALUD.CON_CONVENIO@ISIS CONV\n"
                    + "ON FORCIT.CON_CON_CODIGO = CONV.CON_CON_CODIGO\n"
                    + "\n"
                    + "LEFT JOIN ADMSALUD.PRE_PRESTACION@ISIS PREST\n"
                    + "ON FORCIT.PRE_PRE_CODIGO = PREST.PRE_PRE_CODIGO\n"
                    + "\n"
                    + "\n"
                    + "LEFT JOIN ADMSALUD.TAB_CENTROSATENC@ISIS CA\n"
                    + "ON AGEND.PCA_AGE_LUGAR= CA.CODIGOCENTROATEN\n"
                    + "\n"
                    + "\n"
                    + "INNER JOIN ADMSALUD.SER_OBJETOS@ISIS OBJ\n"
                    + "ON AGEND.PCA_AGE_TIPOOBJET = OBJ.SER_REC_TIPO\n"
                    + "AND AGEND.PCA_AGE_OBJETO = OBJ.SER_OBJ_CODIGO\n"
                    + "\n"
                    + "\n"
                    + "INNER JOIN CA_UBICACION_SEDES UBIC\n"
                    + "ON AGEND.PCA_AGE_OBJETO = UBIC.CONSULTORIO\n"
                    + "LEFT JOIN CA_CITAS_GESTIONADAS CIT\n"
                    + "ON TRIM(AGEND.PCA_AGE_NUMERPACIE) = TRIM(CIT.PAC_PAC_NUMERO)\n"
                    + "AND AGEND.PCA_AGE_FECHACITAC = CIT.PCA_AGE_FECHACITAC AND\n"
                    + "AGEND.PCA_AGE_HORACITAC = CIT.PCA_AGE_HORACITAC\n"
                    + "LEFT JOIN CA_ESTADOS_CITAS EC\n"
                    + "ON CIT.EC_IDCODIGO = EC.EC_IDCODIGO\n"
                    + "LEFT JOIN ADMSALUD.DVP_PROVINCIA@ISIS DP\n"
                    + "ON CA.DVP_PRO_CODIGO = DP.DVP_PRO_CODIGO\n"
                    + "\n"
                    + "WHERE AGEND.PCA_AGE_FECHACITAC BETWEEN to_Date('" + consultaCitasDto.getFechaInicial() + "','dd/MM/yyyy') AND  to_Date('" + consultaCitasDto.getFechaFinal() + "','dd/MM/yyyy')");
            if (consultaCitasDto.getEstado() != null) {
                SQL.append((consultaCitasDto.getEstado() == 1) ? " AND EC.EC_IDCODIGO IS NULL" : " AND EC.EC_IDCODIGO = '" + consultaCitasDto.getEstado() + "'");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodCentroAten())) {
                SQL.append(" AND CA.CODIGOINTERNOCENTRO = '" + consultaCitasDto.getCodCentroAten() + "'");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodEspecialidad())) {
                SQL.append(" AND ESPE.SER_ESP_CODIGO = '" + consultaCitasDto.getCodEspecialidad() + "'");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodSubEspecialidad())) {
                SQL.append(" AND SUBESPE.SER_SUB_CODIGO = '" + consultaCitasDto.getCodSubEspecialidad() + "'");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getCodServicio())) {
                SQL.append(" AND SER.SER_SER_CODIGO = '" + consultaCitasDto.getCodServicio() + "'");
            }
            if (consultaCitasDto.getConvenios() != null && !consultaCitasDto.getConvenios().isEmpty()) {
                final StringBuilder finalStringBuilder;
                final StringBuilder stringBuilder = finalStringBuilder = new StringBuilder();
                consultaCitasDto.getConvenios().forEach(v -> finalStringBuilder.append(",'" + v + "'"));
                final String str = stringBuilder.substring(1);
                SQL.append(" AND CONV.CON_CON_CODIGO IN (:CODCON)".replace(":CODCON", str));
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getTipoDocId())) {
                SQL.append(" AND TIP.TAB_TIPOIDENTCODIGO = '" + consultaCitasDto.getTipoDocId() + "'");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNumDocId())) {
                SQL.append(" AND TRIM(PAC.PAC_PAC_RUT) = '" + consultaCitasDto.getNumDocId() + "'");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getNombres())) {
                SQL.append(" AND UPPER(TRIM(PAC.PAC_PAC_NOMBRE)) LIKE (CONCAT('%', CONCAT('" + consultaCitasDto.getNombres() + "', '%')))");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getPrimerApellido())) {
                SQL.append(" AND UPPER(TRIM(PAC.PAC_PAC_APELLPATER)) LIKE (CONCAT('%', CONCAT('" + consultaCitasDto.getPrimerApellido() + "', '%')))");
            }
            if (!Strings.isEmpty((CharSequence) consultaCitasDto.getSegundoApellido())) {
                SQL.append(" AND UPPER(TRIM(PAC.PAC_PAC_APELLMATER)) LIKE (CONCAT('%', CONCAT('" + consultaCitasDto.getSegundoApellido() + "', '%')))");
            }
            if (consultaCitasDto.getNombreSede() != null && !Strings.isEmpty((CharSequence) consultaCitasDto.getNombreSede())) {
                SQL.append(" AND TRIM(AGEND.PCA_AGE_OBJETO) IN ('" + consultaCitasDto.getNombreSede() + "')\n");
            }
            final List<ResultadoCitaDto> resultadoCitaDtos = new ArrayList<ResultadoCitaDto>();

            Statement st = conn.createStatement();
            st.setFetchSize(200);
            ResultSet rs = st.executeQuery(SQL.toString());
            while (rs.next()) {

            }
            LOG.log(Level.INFO, "Termino consulta- query citas");
            return resultadoCitaDtos;
        } catch (Exception e) {
            LOG.log(Level.INFO, e.toString());
            LOG.log(Level.SEVERE, "error", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }

            }
        }
        return null;
    }

    @Override
    public List<ResultadoCitaDto> consultarCitasPorAutorizarPaginate(ConsultaCitasDto consultaCitasDto) {

        try {
            StringBuilder SQL = new StringBuilder(QueryConst.Citas.CONSULTAR_CITAS_POR_AUTORIZAR);

            SQL.append(QueryConst.Citas.AND_FECHAS);

            if (!Strings.isEmpty(consultaCitasDto.getTipoDocId())) {
                SQL.append(QueryConst.Citas.AND_TIPO_DOCUMENTO);
            }
            if (!Strings.isEmpty(consultaCitasDto.getNumDocId())) {
                SQL.append(QueryConst.Citas.AND_NUMERO_DOCUMENTO);
            }
            if (!Strings.isEmpty(consultaCitasDto.getNombres())) {
                SQL.append(QueryConst.Citas.AND_NOMBRES);
            }
            if (!Strings.isEmpty(consultaCitasDto.getPrimerApellido())) {
                SQL.append(QueryConst.Citas.AND_PRIMER_APPELLIDO);
            }
            if (!Strings.isEmpty(consultaCitasDto.getSegundoApellido())) {
                SQL.append(QueryConst.Citas.AND_SEGUNDO_APPELLIDO);
            }

            SQL.append(QueryConst.Citas.ORDERS_BY_CITAS_POR_AUTORIZAR);
            SQL.append("    OFFSET ");
            SQL.append(consultaCitasDto.getPage());
            SQL.append("ROWS FETCH NEXT 10 ROWS ONLY");

            Query query = entityManager.createNativeQuery(SQL.toString())
                    .setParameter("FECHA_INICIAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaInicial()).concat(" 00:00:00")))
                    .setParameter("FECHA_FINAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaFinal()).concat(" 23:59:59")));

            List<Object[]> citas = query.getResultList();
            List<ResultadoCitaDto> resultadoCitaDtos = new ArrayList<ResultadoCitaDto>();
            citas.stream().forEach(object -> {
                ResultadoCitaDto resultadoCitaDto = new ResultadoCitaDto();
                resultadoCitaDto.setIdCita(object[0] != null ? ((BigDecimal) object[0]).longValue() : null);
                resultadoCitaDto.setPacNum(((BigDecimal) object[1]).longValue());
                resultadoCitaDto.setNombreCompleto(object[2] != null ? object[2].toString().trim() : null);
                resultadoCitaDto.setTipTipIDav(object[3].toString().trim());
                resultadoCitaDto.setNumDocId(object[4].toString().trim());
                resultadoCitaDto.setFechaCita(
                        object[5] != null ? new SimpleDateFormat("yyyy/MM/dd").format((Timestamp) object[5]) : null);
                resultadoCitaDto.setHoraCita(object[6] != null ? object[6].toString().trim() : null);
                resultadoCitaDto.setFechaCitaCA(
                        object[7] != null ? new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format((Timestamp) object[7])
                                : null);
                resultadoCitaDto.setCodUsrCita(object[8].toString().trim());
                resultadoCitaDto.setCodConvenio(Arrays.asList(object[9] != null ? object[9].toString().trim() : ""));
                resultadoCitaDto.setCodigoPrestacion(object[10] != null ? object[10].toString().trim() : "");
                resultadoCitaDto.setCodEstadoCita(object[12] != null ? object[12].toString().trim() : "");
                resultadoCitaDto.setValorPrestacional(object[13] != null ? object[13].toString().trim() : "");
                resultadoCitaDto.setTipoConvenio(object[14] != null ? object[14].toString().trim() : "");
                resultadoCitaDtos.add(resultadoCitaDto);
            });
            return resultadoCitaDtos;
        } catch (Exception e) {
//            log.error("Error consultando citas medicas", e);
            return new ArrayList<ResultadoCitaDto>();
        }
    }

    @Override
    public String cambioConvenio(Integer citaId, Integer nuevoConv) {
        //TODO HACER QUERY PARA CAMBIO DE CONVENIO, PENDIENTE DE DE PERMISOS USUARIO BD
        return null;
    }

}
