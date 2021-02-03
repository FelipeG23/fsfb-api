package co.global.fsfb.fsfbapi.services.impl;

import co.global.fsfb.fsfbapi.dto.ConsultaCitasDto;
import co.global.fsfb.fsfbapi.dto.ConsultaValoresDTO;
import co.global.fsfb.fsfbapi.dto.ResultadoValoresDTO;
import co.global.fsfb.fsfbapi.services.IRecaudoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author POLLO
 */
@Service
@Slf4j
public class RecaudoService implements IRecaudoService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ResultadoValoresDTO> consultaValoresPrestaciones(ConsultaValoresDTO consultaValoresDTO) {

        List<ResultadoValoresDTO> resultadoValoresDTOs = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT DISTINCT");
        sql.append(" c.con_con_codigo CODIGO_CONVENIO,");
        sql.append("c.con_con_descripcio CONVENIO,");
        sql.append("m.PRE_PRE_VALORPPIO VALOR_TARIFA_BASE,");
        sql.append("m.pre_pre_codigo CODIGO_PRESTACION,");
        sql.append("REPLACE(REPLACE(REPLACE(p.pre_pre_descripcio,CHR(10),' ') ,CHR(13),' ') ,'  ','') PRESTACION,");
        sql.append("MT.MODELOTARIFNOMBRE,");
        sql.append("CASE");
        sql.append(" WHEN TRIM(OPEDCTO) = '+' AND PJEDCTO <> 0 THEN m.PRE_PRE_VALORPPIO + ((m.PRE_PRE_VALORPPIO)*(PJEDCTO /100))");
        sql.append(" WHEN TRIM(OPEDCTO) = '-' AND PJEDCTO <> 0 THEN m.PRE_PRE_VALORPPIO - ((m.PRE_PRE_VALORPPIO)*(PJEDCTO /100))");
        sql.append(" WHEN (TRIM(OPEDCTO) = '+' OR TRIM(OPEDCTO) = '-' OR TRIM(OPEDCTO) IS NULL) AND PJEDCTO = 0 AND m.CON_DET_PORCONCONV <> 0");
        sql.append(" THEN m.mod_mod_valorpres");
        sql.append(" WHEN PJEDCTO = 0 THEN m.PRE_PRE_VALORPPIO");
        sql.append(" ELSE 0");
        sql.append("END VALOR_AMBULATORO");
        sql.append(" FROM admsalud.net_conmodprepre@ISIS m,");
        sql.append(" ADMSALUD.CON_MODELOSTARIFAS@ISIS Mta,");
        sql.append("  admsalud.tab_modelotarif@ISIS MT,");
        sql.append(" ADMSALUD.TAB_TIPOTARIF@ISIS TTA,");
        sql.append(" ADMSALUD.con_convenio@ISIS c,");
        sql.append(" admsalud.pre_prestacion@ISIS p,");
        sql.append(" ADMSALUD.Pre_tipo@ISIS Pt,");
        sql.append(" ADMSALUD.CON_Empresa@ISIS EMP,");
        sql.append(" ADMSALUD.TAB_TIPOEMPRESA@ISIS TIP_EMP");
        sql.append(" WHERE");
        sql.append(" M.MODELOTARIFCODIGO = mta.MODELOTARIFCODIGO");
        sql.append(" AND M.MOD_MOD_PERIODOINI = mta.MOD_MOD_PERIODOINI");
        sql.append(" AND M.TIPOTARIFCODIGO = mta.TIPOTARIFCODIGO");
        sql.append(" AND m.TIPOTARIFCODIGO = TTA.TIPOTARIFCODIGO");
        sql.append(" AND m.modelotarifcodigo = c.modelotarifcodigo");
        sql.append(" AND C.MODELOTARIFCODIGO = MT.MODELOTARIFCODIGO");
        sql.append(" AND c.CON_CON_VIGENCIA = 'S'");
        sql.append(" AND C.CON_EMP_RUT = EMP.CON_EMP_RUT");
        sql.append(" AND EMP.TIPOEMPRESACODIGO = TIP_EMP.TIPOEMPRESACODIGO");
        sql.append(" AND m.pre_pre_codigo = p.pre_pre_codigo");
        sql.append(" AND p.pre_pre_tipo = pt.pre_tip_tipo");
        sql.append(" and TO_CHAR(M.MOD_MOD_PERIODOINI,'YYYY') = :pAno");
        sql.append(" and trim(c.con_con_codigo) = :pCodConvenio");
        sql.append(" and trim(m.pre_pre_codigo) = :pCodPRestacion");
        sql.append(" ORDER BY 1,3");

        Query query = entityManager.createNativeQuery(sql.toString())
                .setParameter("pAno", consultaValoresDTO.getAnio())
                .setParameter("pCodConvenio", consultaValoresDTO.getCodigoConvenio()).setParameter("pCodPRestacion", consultaValoresDTO.getCodigoPrestacion());

        List<Object[]> valores = query.getResultList();
        valores.stream().forEach(object -> {
            ResultadoValoresDTO resultadoValoresDTO = new ResultadoValoresDTO();

            resultadoValoresDTO.setCodigoConvenio((String) object[0]);
            resultadoValoresDTO.setConvenio((String) object[1]);
            resultadoValoresDTO.setValorTarifaBase(object[2] != null ? ((BigDecimal) object[2]) : null);
            resultadoValoresDTO.setCodigoPrestacion((String) object[3]);
            resultadoValoresDTO.setPrestacion((String) object[4]);
            resultadoValoresDTO.setModeloTarifaNombre((String) object[5]);
            resultadoValoresDTO.setValorAmbulatorio(object[6] != null ? ((BigDecimal) object[6]) : null);

            resultadoValoresDTOs.add(resultadoValoresDTO);
        });
        return resultadoValoresDTOs;
    }
}
