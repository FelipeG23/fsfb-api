package co.global.fsfb.fsfbapi.delegate;


import co.global.fsfb.fsfbapi.dto.ConsultaValoresDTO;
import co.global.fsfb.fsfbapi.dto.ResultadoValoresDTO;
import java.util.List;

/**
 *
 * @author GIOVANNI-PC
 */
public interface IRecaudoDelegate {
    
    /**
     * 
     * @param consultaValoresDTO
     * @return 
     */
    public List<ResultadoValoresDTO> consultaValoresPrestaciones(ConsultaValoresDTO consultaValoresDTO);

}
