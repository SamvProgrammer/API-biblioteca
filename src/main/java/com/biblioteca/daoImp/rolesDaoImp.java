/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.daoImp;

import com.biblioteca.dao.rolesDao;
import com.biblioteca.repositorios.roles;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author santiago antonio
 */
@Component("objRolesDao")
@Transactional
public class rolesDaoImp implements rolesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object consultar() {
        Object consulta = jdbcTemplate.queryForList("select * from roles;");
        return consulta;
    }

    @Override
    public Object insertar(roles rol) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxRol = rol.getRol();
            int auxRango = rol.getRango();
            String query = "insert into roles (rol,rango) values (?,?)";
            int res = jdbcTemplate.update(query, new Object[]{auxRol, auxRango});
            respuesta.put("respuesta", "Registro insertado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al insertar");
        }

        return respuesta;
    }

    @Override
    public Object actualizar(roles rol) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxRol = rol.getRol();
            int auxRango = rol.getRango();
            int auxId = rol.getId();
            String query = "update roles set rol = ?, rango = ? where id = ?";
            int res = jdbcTemplate.update(query, new Object[]{auxRol, auxRango, auxId});
            respuesta.put("respuesta", "Registro actualizado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al actualizar");
        }

        return respuesta;
    }

    @Override
    public Object eliminar(roles rol) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            int auxId = rol.getId();
            String query = "delete from roles where id = ?";
            int res = jdbcTemplate.update(query, new Object[]{auxId});
            respuesta.put("respuesta", "Registro eliminado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al eliminar");
        }
        return respuesta;
    }

    @Override
    public Object obtenerEspecifico(roles rol) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            int auxId = rol.getId();
            String query = "select * from roles where id = ?";
            respuesta = jdbcTemplate.queryForMap(query, auxId);
        } catch (Exception e) {
            respuesta.put("respuesta", "Registro no encontrado");
        }
        return respuesta;
    }

}
