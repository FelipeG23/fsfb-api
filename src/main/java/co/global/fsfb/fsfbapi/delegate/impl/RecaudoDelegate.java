package co.global.fsfb.fsfbapi.delegate.impl;

import co.global.fsfb.fsfbapi.delegate.IRecaudoDelegate;
import co.global.fsfb.fsfbapi.dto.ConsultaValoresDTO;
import co.global.fsfb.fsfbapi.dto.ResultadoValoresDTO;
import co.global.fsfb.fsfbapi.services.IRecaudoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author GIOVANNI-PC
 */
@Service
@Slf4j
public class RecaudoDelegate implements IRecaudoDelegate {

    @Autowired
    private IRecaudoService iRecaudoService;

    @Override
    public List<ResultadoValoresDTO> consultaValoresPrestaciones(ConsultaValoresDTO consultaValoresDTO) {
        return iRecaudoService.consultaValoresPrestaciones(consultaValoresDTO);
    }
}
