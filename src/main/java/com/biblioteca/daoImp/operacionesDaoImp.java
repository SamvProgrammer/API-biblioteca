/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.daoImp;

import com.biblioteca.dao.operacionesDao;
import com.biblioteca.repositorios.apartado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author santiago antonio
 */
@Component("objOperaciones")
@Transactional
public class operacionesDaoImp implements operacionesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object consulta(int tipo, int codigo, String idUsuario) {
        String query = "select codigo,nombre,total,cantidad,autor,"
                + "e.id as id_editorial,e.editorial,g.id as id_genero,"
                + "g.genero from libros l inner join editorial e "
                + "on l.editorial = e.id "
                + "inner join genero g "
                + "on l.genero = g.id ";
        Object resultado = null;
        if (tipo != 0) {
            switch (tipo) {
                case 1:
                    query += "where e.id = ?";
                    break;
                case 2:
                    query += "where g.id = ?";
                    break;
            }
            resultado = jdbcTemplate.queryForList(query, codigo);
        } else {
            resultado = jdbcTemplate.queryForList(query);

        }
        List<Map<String, Object>> objetos = (List<Map<String, Object>>) resultado;
        for (Map<String, Object> item : objetos) {
            String buscarCodigo = item.get("codigo").toString();
            String query2 = "select count(*) as cantidad from apartado where codigo_libro = ? and codigo_usuario = ?";
            Map<String, Object> busqueda = jdbcTemplate.queryForMap(query2, buscarCodigo, idUsuario);
            int cantidad = Integer.parseInt(String.valueOf(busqueda.get("cantidad")));
            if (cantidad != 0) {
                item.put("apartado", true);
                item.put("apartadoName", "Apartado");
                item.put("notificar", false);
            } else {
                String query3 = "select cantidad from libros where codigo = ?";
                Map<String, Object> tmp1 = jdbcTemplate.queryForMap(query3, buscarCodigo);
                int totalLibros = Integer.parseInt(String.valueOf(tmp1.get("cantidad")));
                if (totalLibros == 0) {
                    item.put("apartado", true);
                    item.put("apartadoName", "Agotado");
                    item.put("notificar", true);
                } else {
                    item.put("apartado", false);
                    item.put("apartadoName", "Apartar");
                    item.put("notificar", false);
                }
            }
        }
        return resultado;
    }

    @Override
    public Object apartar(apartado obj) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            String query;
            String idUsuario = obj.getIdUsuario();
            String codigo = obj.getCodigo();
            if (!obj.isPrestado()) {
                query = "insert into apartado values(?,?,now(),null,0)";
                jdbcTemplate.update(query, new Object[]{codigo, idUsuario});
                
                query = "update libros set cantidad = cantidad - 1 where codigo = ?;";
                jdbcTemplate.update(query, new Object[]{codigo});
                respuesta.put("respuesta", "Libro apartado correctamente.");
            } else {
                query = "update apartado set fecha_fin = ? , prestado = 1 where codigo_libro = ? and codigo_usuario = ?";
                jdbcTemplate.update(query, new Object[]{obj.getFechaFinal(),codigo, idUsuario});
                respuesta.put("respuesta", "Libro prestado correctamente.");
            }
        } catch (Exception e) {
            respuesta.put("respuesta", "Error en el apartado de un libro, contacte a sistemas");
        }
        return respuesta;
    }

    @Override
    public Object consultarPedidos(int tipo, String codigo, String idUsuario) {
        String query = "select codigo_libro,l.nombre,fecha_inicio,fecha_fin,ap.prestado, "
                + "l.cantidad,l.autor,l.genero,l.editorial,u.codigo as codigo_usuario, "
                + "u.nombre as nombre_usuario from apartado ap inner join libros l "
                + "on l.codigo = ap.codigo_libro "
                + "inner join usuarios u on u.codigo = ap.codigo_usuario ";
        Object resultado = null;
        if (tipo != 0) {
            switch (tipo) {
                case 1:
                    query += "where codigo_libro = ?";
                    break;
                case 2:
                    query += "where u.codigo = ?";
                    break;
            }
            resultado = jdbcTemplate.queryForList(query, codigo);
        } else {
            resultado = jdbcTemplate.queryForList(query);
        }
        return resultado;
    }
}
