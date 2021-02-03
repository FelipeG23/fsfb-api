package co.global.fsfb.fsfbapi.services;

import co.global.fsfb.fsfbapi.dto.ConsultaOrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.OrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.ResultadoOrdenMedicaDto;

import java.util.List;

/**
 *
 * @author POLLO
 */
public interface IOrdenMedicaService {
    
    OrdenMedicaDto getOrdenMedica(String pacPacRut);
    
    OrdenMedicaDto createOrdenMedica(OrdenMedicaDto ordenMedicaDto);

    void createTrazaOrdenMedica(OrdenMedicaDto ordenMedicaDto);

    List<ResultadoOrdenMedicaDto> getOrdenesMedicas(ConsultaOrdenMedicaDto consultaOrdenMedicaDto);
    
    boolean validarOrdenContinuidad(long ormIdOrdmNumero);
}
