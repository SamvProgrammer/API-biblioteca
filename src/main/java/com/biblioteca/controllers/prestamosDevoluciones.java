/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.controllers;

import com.biblioteca.dao.operacionesDao;
import com.biblioteca.repositorios.apartado;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public  Object apartados(@RequestParam("tipo") int tipo,@RequestParam("codigo") int codigo,
                             @RequestParam("idUsuario") String idUsuario){
      Object resultado = operaciones.consulta(tipo, codigo,idUsuario);
      return resultado;
    }
    
    @PostMapping("/apartados")
    public Object apartados(@RequestBody apartado obj,HttpServletRequest request){
      Object respuesta = operaciones.apartar(obj);
      return respuesta;
    }
    
    @GetMapping(value = "/pedidos")
    public  Object pedidos(@RequestParam("tipo") int tipo,@RequestParam("codigo") String codigo,
                             @RequestParam("idUsuario") String idUsuario){
      Object resultado = operaciones.consultarPedidos(tipo, codigo,idUsuario);
      return resultado;
    }
    
    @PostMapping("/pedidos")
    public Object pedidos(@RequestBody apartado obj,HttpServletRequest request){
     Object respuesta = operaciones.devolverLibro(obj);
      return respuesta;
    }
        
}
