package co.global.fsfb.fsfbapi.services;

import co.global.fsfb.fsfbapi.dto.ConsultaValoresDTO;
import co.global.fsfb.fsfbapi.dto.ResultadoValoresDTO;
import java.util.List;

/**
 * 
 * @author GIOVANNI-PC
 */
public interface IRecaudoService {

    public List<ResultadoValoresDTO> consultaValoresPrestaciones(ConsultaValoresDTO consultaValoresDTO);
}
