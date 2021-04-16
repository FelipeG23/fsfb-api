/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.global.fsfb.fsfbapi.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SISCOMPUTO
 */
@RestController
@RequestMapping("utils")
public class Utils {
    @GetMapping("/getTime")
    public ResponseEntity<?> getGestionAutorizacionCita() {
        Map<String, Long> time = new HashMap<>();
        time.put("time", (new Date()).getTime());
        return ResponseEntity.ok(time);
    };
    
}
