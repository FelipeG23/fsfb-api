package co.global.fsfb.fsfbapi.delegate.impl;

import co.global.fsfb.fsfbapi.delegate.ICitaDelegate;
import co.global.fsfb.fsfbapi.dto.ConsultaCitasDto;
import co.global.fsfb.fsfbapi.dto.ResultadoCitaDto;
import co.global.fsfb.fsfbapi.services.ICitaService;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author POLLO
 */
@Service
@Slf4j
public class CitaDelegate implements ICitaDelegate {

    @Autowired
    private ICitaService citaService;

    @Override
    public List<ResultadoCitaDto> consultarCitas(ConsultaCitasDto consultaCitasDto) {
        return citaService.consultarCitas(consultaCitasDto);
    }

    @Override
    public List<ResultadoCitaDto> consultarCitasPaginate(
            ConsultaCitasDto consultaCitasDto, int page) {
        return citaService.consultarCitasPaginate(consultaCitasDto, page);
    }

    @Override
    public List<ResultadoCitaDto> consultarCitasPorAutorizar(ConsultaCitasDto consultaCitasDto) {
        return citaService.consultarCitasPorAutorizar(consultaCitasDto);
    }

    @Override
    public void updateAsistencia(Long citaId, String estado) {
        citaService.updateAsistencia(citaId, estado);
    }

    @Override
    public ResultadoCitaDto consultarCitaPorId(ConsultaCitasDto consultaCitasDto) {
        return citaService.consultarCitaPorId(consultaCitasDto);
    }

    @Override
    public List<ResultadoCitaDto> consultarCitas2(ConsultaCitasDto consultaCitasDto) {
        return citaService.consultarCitas2(consultaCitasDto);
    }

    @Override
    public List<ResultadoCitaDto> consultarCitasPorAutorizarPaginate(ConsultaCitasDto consultaCitasDto) {
        return citaService.consultarCitasPorAutorizarPaginate(consultaCitasDto);
    }

    @Override
    public String cambioConvenio(Integer citaId, Integer nuevoConv) {
        return citaService.cambioConvenio(citaId, nuevoConv);
    }

}
