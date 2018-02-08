/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.controllers;

import com.biblioteca.dao.usuarioDao;
import com.biblioteca.repositorios.usuarios;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author santiago antonio
 */
@RestController
public class login {

    @Autowired
    @Qualifier("objUsuarioDao")
    private usuarioDao usuarios;

    @PostMapping("/login")
    public Object getLoginUser(@RequestBody usuarios obj, HttpServletRequest request) {
        Object respuesta = usuarios.obtenerLogin(obj);
        return respuesta;
    }

}
