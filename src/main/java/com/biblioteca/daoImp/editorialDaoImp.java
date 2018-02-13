/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.daoImp;

import com.biblioteca.dao.editorialDao;
import com.biblioteca.repositorios.editorial;
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
@Component("objEditorialDao")
@Transactional
public class editorialDaoImp implements editorialDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object consultar() {
        Object consulta = jdbcTemplate.queryForList("select * from editorial;");
        return consulta;
    }

    @Override
    public Object obtenerEspecifico(editorial editorial) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            int auxId = editorial.getId();
            String query = "select * from editorial where id = ?;";
            respuesta = jdbcTemplate.queryForMap(query, auxId);
        } catch (Exception e) {
            respuesta.put("respuesta", "Registro no encontrado");
        }
        return respuesta;
    }

    @Override
    public Object insertar(editorial editorial) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxEditorial = editorial.getEditorial();
            String query = "insert into editorial (editorial) values(?);";
            int res = jdbcTemplate.update(query, new Object[]{auxEditorial});
            respuesta.put("respuesta", "Registro insertado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al insertar");
        }

        return respuesta;
    }

    @Override
    public Object actualizar(editorial editorial) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxEditorial = editorial.getEditorial();
            int auxId = editorial.getId();
            String query = "update editorial set editorial = ? where id = ?";
            int res = jdbcTemplate.update(query, new Object[]{auxEditorial, auxId});
            respuesta.put("respuesta", "Registro actualizado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al actualizar");
        }

        return respuesta;
    }

    @Override
    public Object eliminar(editorial editorial) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            int auxId = editorial.getId();
            String query = "delete from editorial where id = ?";
            int res = jdbcTemplate.update(query, new Object[]{auxId});
            respuesta.put("respuesta", "Registro eliminado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al eliminar");
        }
        return respuesta;
    }

}
