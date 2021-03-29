package co.global.fsfb.fsfbapi.services.impl;

import co.global.fsfb.fsfbapi.dto.CitasAutorizadasDto;
import co.global.fsfb.fsfbapi.dto.ListaDto;
import co.global.fsfb.fsfbapi.repositories.ICitaRepository;
import co.global.fsfb.fsfbapi.repositories.ICitaRepository.PruebaCita;
import co.global.fsfb.fsfbapi.services.IListaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.FlushModeType;

/**
 *
 * @author POLLO
 */
@Service
@Slf4j
@Transactional
public class ListaService implements IListaService {

    private static final Logger LOG = Logger.getLogger(ListaService.class.getName());

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ICitaRepository citaRepository;

    public List<PruebaCita> paginas(int init, int end) {
        return citaRepository.listaCitasAutorizadas(init, end);
    }

    @Override
    public List<ListaDto> getList(String query) {
        List<Object[]> objects = entityManager.createNativeQuery(query).getResultList();
        List<ListaDto> lista = new ArrayList();
        try {
            objects.stream().forEach(i -> {
                ListaDto aux = new ListaDto();
                aux.setId(i[0].toString() != null ? i[0].toString().trim() : null);
                aux.setDescripcion(i[1].toString() != null ? i[1].toString().trim() : i[1].toString());
                aux.setOtro(i[2] != null ? i[2].toString().trim() : null);
                aux.setOtros(i.length > 3 && i[3] != null ? i[3].toString().trim() : null);
                lista.add(aux);
            });
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "error", e);
        }
        return lista;
    }

    @Override
    public List<CitasAutorizadasDto> getLista(String query) {
        List<Object[]> objects = entityManager.createNativeQuery(query).getResultList();
        List<CitasAutorizadasDto> listas = new ArrayList();
        objects.stream().forEach(i -> {
            CitasAutorizadasDto aux = new CitasAutorizadasDto();
            aux.setIdCita(i[0].toString() != null ? i[0].toString().trim() : null);
            aux.setPacNumero(i[1].toString() != null ? i[1].toString().trim() : i[1].toString());
            aux.setCodCodigoprofe(i[2] != null ? i[2].toString().trim() : null);
            aux.setCodEstadoCita(i[3] != null ? i[3].toString().trim() : null);
            aux.setTipDocumento(i[4] != null ? i[4].toString().trim() : null);
            aux.setNumDocumento(i[5] != null ? i[5].toString().trim() : null);
            aux.setNombreCompleto(i[6] != null ? i[6].toString().trim() : null);
            aux.setCodigoAutorizacion(i[7] != null ? i[7].toString().trim() : null);
            aux.setValorPrestacion(i[8] != null ? i[8].toString().trim() : null);
            aux.setHoraCita(i[10] != null ? i[10].toString().trim() : null);
            aux.setFechaCita(i[9] != null ? i[9].toString().split(" ")[0] : null);
            aux.setFechaAutorizacion(i.length > 11 && i[11] != null ? i[11].toString().split(" ")[0] : null);
            listas.add(aux);

        });
        return listas;
    }

    @Override
    public List<ListaDto> consultarMedicos(String query) {
        List<Object[]> objects = entityManager.createNativeQuery(query).getResultList();
        List<ListaDto> lista = new ArrayList();
        try {
            objects.stream().forEach(i -> {
                ListaDto aux = new ListaDto();
                aux.setId(i[0].toString() != null ? i[0].toString().trim() : null);
                aux.setDescripcion(i[1].toString() != null ? i[1].toString().trim() : i[1].toString());
                aux.setOtro(i[2] != null ? i[2].toString().trim() : null);
                aux.setOtros(i.length > 3 && i[3] != null ? i[3].toString().trim() : null);
                lista.add(aux);
            });
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "error", e);
        }
        return lista;
    }
}
