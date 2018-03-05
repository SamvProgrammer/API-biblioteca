/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.controllers;

import com.biblioteca.dao.usuarioDao;
import com.biblioteca.repositorios.usuarios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author santiago antonio repositorio en git
 */
@RestController
public class login {

    @Autowired
    @Qualifier("objUsuarioDao")
    private usuarioDao usuarios;

    private ArrayList<Map<String, Object>> logeosTotales = new ArrayList<Map<String, Object>>();

    @PostMapping("/login")
    public Object getLoginUser(@RequestBody usuarios obj, HttpServletRequest request) {
        Map<String, Object> respuesta = (Map<String, Object>) usuarios.obtenerLogin(obj);
        if ((boolean) (respuesta.get("acceso")) == true) {
            boolean repetido = false;
            for (Map<String, Object> item : logeosTotales) {
                if (item.get("codigo").equals(respuesta.get("codigo"))) {
                    respuesta = new HashMap<String, Object>();
                    respuesta.put("respuesta", "Usuario ya activo en otra sesión.");
                    respuesta.put("acceso", false);
                    repetido = true;
                    break;
                }
            }
            if (!repetido) {
                logeosTotales.add(respuesta);
            }
        }
        return respuesta;
    }

    @PostMapping("/logout")
    public Object logOut(@RequestBody usuarios obj, HttpServletRequest request) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Map<String, Object> temporal = null;
        boolean eliminar = false;
        for (Map<String, Object> item : logeosTotales) {
            if (item.get("codigo").equals(obj.getCodigo())) {
                temporal = item;
                eliminar = true;
                respuesta.put("respuesta", "Cerrando sesión");
                respuesta.put("acceso", false);
                break;
            }
        }
        if (eliminar) {
            logeosTotales.remove(temporal);
        }
        return respuesta;
    }

    @PostMapping("/autentificando")
    public Object autentificado(@RequestBody usuarios obj, HttpServletRequest request) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        if (logeosTotales.size() != 0) {
            for (Map<String, Object> item : logeosTotales) {
                if (item.get("codigo").equals(obj.getCodigo())) {
                    respuesta.put("acceso", true);
                    respuesta.put("roles", item.get("roles"));
                } else {
                    respuesta.put("acceso", false);
                }
            }
        } else {
            respuesta.put("acceso", false);
        }
        return respuesta;
        
    }
    
    
    
    //Hola ando modificando con joel
    //dsojfsdf
}
