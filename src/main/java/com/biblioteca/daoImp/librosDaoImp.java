/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.daoImp;

import com.biblioteca.dao.librosDao;
import com.biblioteca.repositorios.libros;
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
@Component("objLibrosDao")
@Transactional
public class librosDaoImp implements librosDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object consultar() {
        String query = "select l.codigo,l.nombre,l.total,l.cantidad,l.autor,g.id as idGenero,g.genero,e.id as idEditorial,e.editorial from libros l "
                + "		inner join editorial e "
                + "        on l.editorial = e.id "
                + "        inner join genero g "
                + "        on g.id = l.genero;";
        Object consulta = jdbcTemplate.queryForList(query);
        return consulta;
    }

    @Override
    public Object obtenerEspecifico(libros libro) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            String auxCodigo = libro.getCodigo();
            String query = "select l.codigo,l.nombre,l.total,l.cantidad,l.autor,g.id as idGenero ,g.genero,e.id as idEditorial,e.editorial from libros l "
                    + "		inner join editorial e "
                    + "        on l.editorial = e.id "
                    + "        inner join genero g "
                    + "        on g.id = l.genero where l.codigo = ?;";
            respuesta = jdbcTemplate.queryForMap(query, auxCodigo);
        } catch (Exception e) {
            respuesta.put("respuesta", "Registro no encontrado");
        }
        return respuesta;
    }

    @Override
    public Object insertar(libros libro) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxCodigo = libro.getCodigo();
            String auxNombre = libro.getNombre();
            int auxTotal = libro.getTotal();
            int auxCantidad = libro.getCantidad();
            String auxAutor = libro.getAutor();
            int auxGenero = libro.getIdGenero();
            int auxEditorial = libro.getIdEditorial();
            String query = "insert into libros (codigo,nombre,total,cantidad,autor,genero,editorial) values(?,?,?,?,?,?,?);";
            int res = jdbcTemplate.update(query, new Object[]{
                auxCodigo,
                auxNombre,
                auxTotal,
                auxCantidad,
                auxAutor,
                auxGenero,
                auxEditorial
            });
            respuesta.put("respuesta", "Registro insertado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al insertar");
        }

        return respuesta;
    }

    @Override
    public Object actualizar(libros libro) {

        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxCodigo = libro.getCodigo();
            String auxNombre = libro.getNombre();
            int auxTotal = libro.getTotal();
            int auxCantidad = libro.getCantidad();
            String auxAutor = libro.getAutor();
            int auxGenero = libro.getIdGenero();
            int auxEditorial = libro.getIdEditorial();
            String query = "update libros set nombre=?,total=?,cantidad=?,autor=?,genero=?,editorial=? where codigo = ?;";
            int res = jdbcTemplate.update(query, new Object[]{
                auxNombre,
                auxTotal,
                auxCantidad,
                auxAutor,
                auxGenero,
                auxEditorial,
                auxCodigo
            });
            respuesta.put("respuesta", "Registro actualizado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al actualizar");
        }

        return respuesta;
    }

    @Override
    public Object eliminar(libros libro) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxCodigo = libro.getCodigo();
            String query = "delete from libros where codigo = ?";
            int res = jdbcTemplate.update(query, new Object[]{auxCodigo});
            respuesta.put("respuesta", "Registro eliminado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al eliminar");
        }
        return respuesta;
    }
}
