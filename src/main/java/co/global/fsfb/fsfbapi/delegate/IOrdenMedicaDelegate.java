package co.global.fsfb.fsfbapi.delegate;

import co.global.fsfb.fsfbapi.dto.ConsultaOrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.OrdenMedicaDto;
import co.global.fsfb.fsfbapi.dto.ResultadoOrdenMedicaDto;

import java.util.List;

/**
 *
 * @author POLLO
 */
public interface IOrdenMedicaDelegate {
    
    OrdenMedicaDto getOrdenMedica(String pacPacRut);
    
    OrdenMedicaDto createOrdenMedica(OrdenMedicaDto ordenMedicaDto);

    List<ResultadoOrdenMedicaDto> getOrdenesMedicas(ConsultaOrdenMedicaDto consultaOrdenMedicaDto);
    
        List<ResultadoOrdenMedicaDto> getOrdenesMedicasPaginate(
            ConsultaOrdenMedicaDto consultaOrdenMedicaDto, int page);
    
    boolean validarGestionContinuidad(long ormIdOrdmNumero); 
    
}
