/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.daoImp;

import com.biblioteca.dao.generosDao;
import com.biblioteca.repositorios.generos;
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
@Component("objGenerosDao")
@Transactional
public class generosDaoImp implements generosDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object consultar() {
        Object consulta = jdbcTemplate.queryForList("select * from genero;");
        return consulta;
    }

    @Override
    public Object obtenerEspecifico(generos genero) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            int auxId = genero.getId();
            String query = "select * from genero where id = ?;";
            respuesta = jdbcTemplate.queryForMap(query, auxId);
        } catch (Exception e) {
            respuesta.put("respuesta", "Registro no encontrado");
        }
        return respuesta;
    }

    @Override
    public Object insertar(generos genero) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxGenero = genero.getGenero();
            String query = "insert into genero (genero) values(?);";
            int res = jdbcTemplate.update(query, new Object[]{auxGenero});
            respuesta.put("respuesta", "Registro insertado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al insertar");
        }

        return respuesta;
    }

    @Override
    public Object actualizar(generos genero) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxGenero = genero.getGenero();
            int auxId = genero.getId();
            String query = "update genero set genero = ? where id = ?";
            int res = jdbcTemplate.update(query, new Object[]{auxGenero, auxId});
            respuesta.put("respuesta", "Registro actualizado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al actualizar");
        }

        return respuesta;
    }

    @Override
    public Object eliminar(generos genero) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            int auxId = genero.getId();
            String query = "delete from genero where id = ?";
            int res = jdbcTemplate.update(query, new Object[]{auxId});
            respuesta.put("respuesta", "Registro eliminado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al eliminar");
        }
        return respuesta;
    }
}
