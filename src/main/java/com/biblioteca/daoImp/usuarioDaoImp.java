/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.daoImp;

import com.biblioteca.dao.usuarioDao;
import com.biblioteca.repositorios.usuarios;
import java.util.ArrayList;
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
@Component("objUsuarioDao")
@Transactional
public class usuarioDaoImp implements usuarioDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object consultar() {
        String query = "select usuario.codigo,"
                + "       usuario.nombre,"
                + "	   usuario.apellido_paterno,"
                + "       usuario.apellido_materno,"
                + "       usuario.fecha_nacimiento,"
                + "       usuario.password,"
                + "       rol.rol,"
                + "       rol.rango from usuarios usuario "
                + "		 inner join rel_rol_usuario relacion"
                + "         on usuario.codigo = relacion.codigo_usuario"
                + "         inner join roles rol"
                + "         on relacion.id_rol = rol.id";
        Object consulta = jdbcTemplate.queryForList(query);
        return consulta;
    }

    @Override
    public Object insertar(usuarios usuario) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxCodigo = usuario.getCodigo();
            String auxNombre = usuario.getNombre();
            String auxApellidoPaterno = usuario.getApellidoPaterno();
            String auxApellidoMaterno = usuario.getApellidoMaterno();
            String auxFechaNacimiento = usuario.getFechaNacimiento();
            String auxPassword = usuario.getPassword();
            String query = "insert into usuarios values (?,?,?,?,?,?)";
            int res = jdbcTemplate.update(query, new Object[]{auxCodigo, auxNombre, auxApellidoPaterno, auxApellidoMaterno, auxFechaNacimiento, auxPassword});

            query = "insert into rel_rol_usuario(codigo_usuario,id_rol) values (?,?)";

            int auxIdRol = usuario.getId_rol();

            res = jdbcTemplate.update(query, auxCodigo, auxIdRol);

            respuesta.put("respuesta", "Registro insertado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al insertar");
        }

        return respuesta;
    }

    @Override
    public Object actualizar(usuarios usuario) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxCodigo = usuario.getCodigo();
            String auxNombre = usuario.getNombre();
            String auxApellidoPaterno = usuario.getApellidoPaterno();
            String auxApellidoMaterno = usuario.getApellidoMaterno();
            String auxFechaNacimiento = usuario.getFechaNacimiento();
            String auxPassword = usuario.getPassword();
            String query = "update usuarios set nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, password=? where codigo = ?";
            int res = jdbcTemplate.update(query, new Object[]{auxNombre, auxApellidoPaterno, auxApellidoMaterno, auxFechaNacimiento, auxPassword, auxCodigo});

            int auxIdRol = usuario.getId_rol();

            query = "update rel_rol_usuario set id_rol=? where codigo_usuario=?";
            res = jdbcTemplate.update(query, new Object[]{auxIdRol, auxCodigo});

            respuesta.put("respuesta", "Registro actualizado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al actualizar");
        }

        return respuesta;
    }

    @Override
    public Object eliminar(usuarios usuario) {
        Map<String, Object> respuesta = new HashMap<String, Object>();

        try {
            String auxCodigo = usuario.getCodigo();
            String query = "delete from usuarios where codigo = ?";
            int res = jdbcTemplate.update(query, new Object[]{auxCodigo});
            respuesta.put("respuesta", "Registro eliminado");
        } catch (Exception e) {
            respuesta.put("respuesta", "Error al eliminar");
        }
        return respuesta;
    }

    @Override
    public Object obtenerEspecifico(usuarios usuario) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            String auxCodigo = usuario.getCodigo();
            String query = "select usuario.codigo,"
                    + "       usuario.nombre,"
                    + "	   usuario.apellido_paterno,"
                    + "       usuario.apellido_materno,"
                    + "       usuario.fecha_nacimiento,"
                    + "       usuario.password,"
                    + "       rol.rol,"
                    + "       rol.rango from usuarios usuario "
                    + "		 inner join rel_rol_usuario relacion"
                    + "         on usuario.codigo = relacion.codigo_usuario"
                    + "         inner join roles rol"
                    + "         on relacion.id_rol = rol.id where usuario.codigo = ?";
            respuesta = jdbcTemplate.queryForMap(query, auxCodigo);

        } catch (Exception e) {
            respuesta.put("respuesta", "Usuario no encontrado");
        }
        return respuesta;
    }

    @Override
    public Object obtenerLogin(usuarios usuario) {
        List<Map<String, Object>> aux = new ArrayList<Map<String, Object>>();
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            String auxCodigo = usuario.getCodigo();
            String auxPassword = usuario.getPassword();
            String query = "select usuario.codigo,"
                    + "       usuario.nombre,"
                    + "	   usuario.apellido_paterno,"
                    + "       usuario.apellido_materno,"
                    + "       usuario.fecha_nacimiento,"
                    + "       usuario.password,"
                    + "       rol.rol,"
                    + "       rol.rango from usuarios usuario "
                    + "		 inner join rel_rol_usuario relacion"
                    + "         on usuario.codigo = relacion.codigo_usuario"
                    + "         inner join roles rol"
                    + "         on relacion.id_rol = rol.id"
                    + "         where usuario.codigo = ?";
            aux = jdbcTemplate.queryForList(query, auxCodigo);

            if (!aux.get(0).get("password").equals(auxPassword)) {
                respuesta.put("respuesta", "Usuario y/o contraseña invalido");
                respuesta.put("acceso", false);
                return respuesta;
            }

            respuesta.put("codigo", aux.get(0).get("codigo"));
            respuesta.put("nombre", aux.get(0).get("nombre"));
            respuesta.put("apellido_paterno", aux.get(0).get("apellido_paterno"));
            respuesta.put("apellido_materno", aux.get(0).get("apellido_materno"));
            respuesta.put("fecha_nacimiento", aux.get(0).get("fecha_nacimiento"));
            respuesta.put("password", aux.get(0).get("password"));

            List<Map<String, Object>> listaTemporal = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> item : aux) {
                Map<String, Object> aux2 = new HashMap<String, Object>();
                aux2.put("rol", item.get("rol"));
                aux2.put("rango", item.get("rango"));
                listaTemporal.add(aux2);
            }
            respuesta.put("roles", listaTemporal);
            respuesta.put("acceso", true);

        } catch (Exception e) {
            respuesta.put("respuesta", "Usuario y/o contraseña invalido");
            respuesta.put("acceso", false);
        }
        return respuesta;
    }

}
