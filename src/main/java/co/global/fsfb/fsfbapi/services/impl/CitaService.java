package co.global.fsfb.fsfbapi.services.impl;

import co.global.fsfb.fsfbapi.constants.QueryConst;
import co.global.fsfb.fsfbapi.dto.ConsultaCitasDto;
import co.global.fsfb.fsfbapi.dto.ResultadoCitaDto;
import co.global.fsfb.fsfbapi.models.CaCitasGestionadas;
import co.global.fsfb.fsfbapi.models.CaUbicacionSedes;
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


            Query query = entityManager.createNativeQuery(SQL.toString())
                    .setParameter("FECHAINICIAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaInicial()).concat(" 00:00:00")))
                    .setParameter("FECHAFINAL", Timestamp.valueOf(convertDate(consultaCitasDto.getFechaFinal()).concat(" 23:59:59")));

            if (consultaCitasDto.getEstado() != null && consultaCitasDto.getEstado() != 1) {
                query.setParameter("ESTADO", (Object) consultaCitasDto.getEstado());
            }
            LOG.log(Level.WARNING, "Entra consulta citas 3");
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
            LOG.log(Level.WARNING, "Entra consulta citas 4");
            return resultadoCitaDtos;
        } catch (Exception e) {
            LOG.log(Level.INFO, e.toString());
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
            resultadoCitaDtos.add(resultadoCitaDto);
        });
        return !resultadoCitaDtos.isEmpty() ? resultadoCitaDtos.get(0) : null;

    }
}
