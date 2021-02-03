package co.global.fsfb.fsfbapi.delegate;

import co.global.fsfb.fsfbapi.dto.*;

import java.util.List;

/**
 *
 * @author POLLO
 */
public interface ICitaDelegate {
    
    List<ResultadoCitaDto> consultarCitas(ConsultaCitasDto consultaCitasDto);

    List<ResultadoCitaDto> consultarCitasPorAutorizar(ConsultaCitasDto consultaCitasDto);
    
    void updateAsistencia(Long citaId, String estado);
    
    ResultadoCitaDto consultarCitaPorId(ConsultaCitasDto consultaCitasDto);
    
}
