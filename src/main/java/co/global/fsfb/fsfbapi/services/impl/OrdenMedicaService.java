package co.global.fsfb.fsfbapi.services.impl;

import co.global.fsfb.fsfbapi.constants.QueryConst;
import co.global.fsfb.fsfbapi.dto.ConsultaOrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.OrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.ResultadoOrdenMedicaDto;
import co.global.fsfb.fsfbapi.exceptions.NoContentException;
import co.global.fsfb.fsfbapi.mappers.IOrdenMedicaMapper;
import co.global.fsfb.fsfbapi.models.CaGestionAutorizacion;
import co.global.fsfb.fsfbapi.models.CaOrdenesMedicas;
import co.global.fsfb.fsfbapi.models.CaPrestacionesOrdMed;
import co.global.fsfb.fsfbapi.models.CaTrazaGestContinuidad;
import co.global.fsfb.fsfbapi.models.CaTrazaOrdenMedicas;
import co.global.fsfb.fsfbapi.repositories.ICaGestionAutorizacionRepository;
import co.global.fsfb.fsfbapi.repositories.ICaPrestacionesOrdMed;
import co.global.fsfb.fsfbapi.repositories.ICaTrazaGestContinuidadRepository;
import co.global.fsfb.fsfbapi.repositories.IOrdenMedicaRepository;
import co.global.fsfb.fsfbapi.repositories.ITrazaOrdenMedicaRepository;
import co.global.fsfb.fsfbapi.services.IOrdenMedicaService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author POLLO
 */
@Service
public class OrdenMedicaService implements IOrdenMedicaService, IOrdenMedicaMapper {

    private static final Logger log = Logger.getLogger(OrdenMedicaService.class.getName());

    @Autowired
    private IOrdenMedicaRepository ordenMedicaRepository;

    @Autowired
    private IOrdenMedicaMapper ordenMedicaMapper;

    @Autowired
    private ITrazaOrdenMedicaRepository trazaOrdenMedicaRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ICaGestionAutorizacionRepository iCaGestionAutorizacionRepository;
    @Autowired
    private ICaPrestacionesOrdMed iCaPrestacionesOrdMed;
    @Autowired
    private ICaTrazaGestContinuidadRepository iCaTrazaGestContinuidadRepository;

    @Override
    public OrdenMedicaDto getOrdenMedica(String pacPacRut) {
        List<CaOrdenesMedicas> caOrdenesMedicasList = ordenMedicaRepository.getOrdenesMedica(pacPacRut);
        if (caOrdenesMedicasList != null && !caOrdenesMedicasList.isEmpty()) {
            return ordenMedicaMapper.entityToDto(caOrdenesMedicasList.get(0));
        }
        throw new NoContentException();
    }

    @Override
    public OrdenMedicaDto createOrdenMedica(OrdenMedicaDto ordenMedicaDto) {

        log.info("Mapeando orden medica");
        CaOrdenesMedicas caOrdenesMedicas = ordenMedicaMapper.dtoToEntity(ordenMedicaDto);

        log.info("Guardando orden medica");
        ordenMedicaRepository.save(caOrdenesMedicas);

        return ordenMedicaMapper.entityToDto(caOrdenesMedicas);
    }

    @Override
    public void createTrazaOrdenMedica(OrdenMedicaDto ordenMedicaDto) {

//        log.info("Mapeando orden medica para traza");
        CaOrdenesMedicas caOrdenesMedicas = ordenMedicaMapper.dtoToEntity(ordenMedicaDto);

//        log.info("Creando tarza orden " + ordenMedicaDto.getOrmIdOrdmNumero());
        CaTrazaOrdenMedicas caTrazaOrdenMedicas = new CaTrazaOrdenMedicas(caOrdenesMedicas.getOrmIdOrdmNumero(), 1L,
                caOrdenesMedicas.getPcaAgeCodigoRecep(), caOrdenesMedicas);

        trazaOrdenMedicaRepository.save(caTrazaOrdenMedicas);

    }

    @Override
    public List<ResultadoOrdenMedicaDto> getOrdenesMedicas(ConsultaOrdenMedicaDto consultaOrdenMedicaDto) {
        boolean fechas = false;
        StringBuilder SQL = new StringBuilder(QueryConst.OrdenesMedicas.CONSULTAR_ORDENES);
        if (consultaOrdenMedicaDto.getEstados().size() == 1) {
            if (consultaOrdenMedicaDto.getEstados().get(0).toString().trim().equalsIgnoreCase("3")) {
                fechas = true;
                SQL.append("WHERE COM.CG_FECHA_PROCESO BETWEEN :FECHAINICIAL AND :FECHAFINAL");
            } else {
                fechas = true;
                SQL.append("WHERE COM.CG_FECHA_PROCESO BETWEEN :FECHAINICIAL AND :FECHAFINAL");
            }
        } else if (consultaOrdenMedicaDto.getEstados().size() > 1) {
            fechas = true;
            SQL.append("WHERE COM.CG_FECHA_PROCESO BETWEEN :FECHAINICIAL AND :FECHAFINAL");
        }

        if (consultaOrdenMedicaDto.getEstados() != null && !consultaOrdenMedicaDto.getEstados().isEmpty()) {
            SQL.append(QueryConst.OrdenesMedicas.AND_ESTADOS);
        }

        SQL.append(QueryConst.OrdenesMedicas.ORDERS_BY);

        Query query = entityManager.createNativeQuery(SQL.toString());
        if (fechas) {
            query.setParameter("FECHAINICIAL", Timestamp.valueOf(convertDate(consultaOrdenMedicaDto.getFechaInicial()).concat(" 00:00:00")));
            query.setParameter("FECHAFINAL", Timestamp.valueOf(convertDate(consultaOrdenMedicaDto.getFechaFinal()).concat(" 23:59:59")));

        }

        if (consultaOrdenMedicaDto.getEstados() != null && !consultaOrdenMedicaDto.getEstados().isEmpty()) {
            query.setParameter("ESTADOS", consultaOrdenMedicaDto.getEstados());
        }

        List<Object[]> ordenes = query.getResultList();
        List<ResultadoOrdenMedicaDto> resultadoOrdenesDtos = new ArrayList();
        ordenes.stream().forEach(object -> {
            // realizamos la consulta de las autorizaciones de la orden medica

            ResultadoOrdenMedicaDto resultadoOrdenMedicaDto = new ResultadoOrdenMedicaDto();
            resultadoOrdenMedicaDto.setOrmIdOrdmNumero(object[0] != null ? ((BigDecimal) object[0]).longValue() : null);
            resultadoOrdenMedicaDto
                    .setCgFechaProceso(object[1] != null ? new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format((Timestamp) object[1]) : null);
            resultadoOrdenMedicaDto.setTipTipIDav(object[2] != null ? object[2].toString() : null);
            resultadoOrdenMedicaDto.setDocumento(object[3] != null ? object[3].toString() : null);
            resultadoOrdenMedicaDto.setNombreCompleto(object[4] != null ? object[4].toString() : null);

            if (object.length > 5) {
                resultadoOrdenMedicaDto.setEnProceso(object[5] != null ? ((BigDecimal) object[5]).longValue() : null);
                resultadoOrdenMedicaDto.setAutorizadas(object[6] != null ? ((BigDecimal) object[6]).longValue() : null);
                resultadoOrdenMedicaDto.setPrestaciones(object[7] != null ? ((BigDecimal) object[7]).longValue() : null);
                resultadoOrdenMedicaDto.setOrmIdOrdmNumeroP(object[8] != null ? ((BigDecimal) object[8]).longValue() : null);
                resultadoOrdenMedicaDto.setContinuidad(object[9] != null ? ((BigDecimal) object[9]).longValue() : null);
            }

            resultadoOrdenesDtos.add(resultadoOrdenMedicaDto);
        });

        return resultadoOrdenesDtos;

    }

    public String convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter).format(formatter2);
    }

    @Override
    public boolean validarOrdenContinuidad(long ormIdOrdmNumero) {
        List<CaPrestacionesOrdMed> listCaPrestacionesOrdMed = iCaPrestacionesOrdMed.findByOrmIdOrmdNumero(ormIdOrdmNumero);
        if (listCaPrestacionesOrdMed != null && !listCaPrestacionesOrdMed.isEmpty()) {
            int cant = 0;
            for (CaPrestacionesOrdMed caPrestacionesOrdMed : listCaPrestacionesOrdMed) {
                CaTrazaGestContinuidad caTraza = iCaTrazaGestContinuidadRepository.
                        findByPomIdPrestOrdm(caPrestacionesOrdMed.getPomIdPrestOrdm());
                if (caTraza != null) {
                    cant++;
                }
            }
            if (listCaPrestacionesOrdMed.size() == cant) {
                return true;
            }
        }

        return false;
    }

    @Override
    public CaOrdenesMedicas dtoToEntity(OrdenMedicaDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrdenMedicaDto entityToDto(CaOrdenesMedicas entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrdenMedicaDto> entityToDto(List<CaOrdenesMedicas> entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CaOrdenesMedicas> dtoToEntity(List<OrdenMedicaDto> dto) {
        // TODO Auto-generated method stub
        return null;
    }

}
