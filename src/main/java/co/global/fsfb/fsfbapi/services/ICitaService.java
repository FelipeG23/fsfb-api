package co.global.fsfb.fsfbapi.services;

import co.global.fsfb.fsfbapi.dto.ConsultaCitasDto;
import co.global.fsfb.fsfbapi.dto.ResultadoCitaDto;

import java.util.List;

/**
 *
 * @author POLLO
 */
public interface ICitaService {

    List<ResultadoCitaDto> consultarCitas(ConsultaCitasDto consultaCitasDto);

    List<ResultadoCitaDto> consultarCitasPorAutorizar(ConsultaCitasDto consultaCitasDto);
    
    void updateAsistencia(Long citaId, String estado);
    
    ResultadoCitaDto consultarCitaPorId(ConsultaCitasDto consultaCitasDto);
    
}
