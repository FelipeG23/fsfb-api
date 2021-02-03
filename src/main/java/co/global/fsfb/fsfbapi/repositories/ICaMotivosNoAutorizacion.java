/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.global.fsfb.fsfbapi.repositories;

import co.global.fsfb.fsfbapi.models.CaMotivosNoAutorizacion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author julio
 */
public interface ICaMotivosNoAutorizacion extends JpaRepository<CaMotivosNoAutorizacion, Long>{
    
}
