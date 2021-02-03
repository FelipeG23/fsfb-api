/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.global.fsfb.fsfbapi.repositories;

import co.global.fsfb.fsfbapi.models.CaTrazaGestContinuidad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author julio
 */
public interface ICaTrazaGestContinuidadRepository extends JpaRepository<CaTrazaGestContinuidad, Long>{
    
    CaTrazaGestContinuidad findByPomIdPrestOrdm(long pomIdPrestOrdm);
    
}
