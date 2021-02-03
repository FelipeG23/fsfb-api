package co.global.fsfb.fsfbapi.delegate.impl;

import co.global.fsfb.fsfbapi.delegate.ICitaDelegate;
import co.global.fsfb.fsfbapi.dto.ConsultaCitasDto;
import co.global.fsfb.fsfbapi.dto.ResultadoCitaDto;
import co.global.fsfb.fsfbapi.services.ICitaService;
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

}