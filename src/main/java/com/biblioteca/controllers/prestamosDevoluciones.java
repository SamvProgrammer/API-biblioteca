/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.controllers;

import com.biblioteca.dao.operacionesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author santiago antonio
 */
@RestController
@RequestMapping("/operaciones")
public class prestamosDevoluciones {
    
    @Autowired
    @Qualifier("objOperaciones")
    public operacionesDao operaciones;
    
    @GetMapping(value = "/apartados")
    public  Object apartados(@RequestParam("tipo") int tipo,@RequestParam("codigo") int codigo){
      Object resultado = operaciones.consulta(tipo, codigo);
      return resultado;
    }
}
