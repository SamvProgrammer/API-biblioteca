/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 probando
 */
package com.biblioteca.controllers;
import com.biblioteca.dao.editorialDao;
import com.biblioteca.dao.generosDao;
import com.biblioteca.dao.librosDao;
import com.biblioteca.dao.rolesDao;
import com.biblioteca.dao.usuarioDao;
import com.biblioteca.repositorios.editorial;
import com.biblioteca.repositorios.generos;
import com.biblioteca.repositorios.libros;
import com.biblioteca.repositorios.roles;
import com.biblioteca.repositorios.usuarios;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author santiago antonio
 */
@RestController
@RequestMapping("/catalogos")
public class catalogos {

    /*
    ====================================================
    ==       D E C L A R A C I Ó N  B E A N S         ==
    ==     DESCRIPCIÓN:                               ==
    ==                 En esta sección se declara     ==
    ==                 los beans a ser utilizados.    ==
    ====================================================
     */
    @Autowired
    @Qualifier("objRolesDao")
    private rolesDao roles;

    @Autowired
    @Qualifier("objUsuarioDao")
    private usuarioDao usuarios;

    @Autowired
    @Qualifier("objEditorialDao")
    private editorialDao editoriales;

    @Autowired
    @Qualifier("objGenerosDao")
    private generosDao generos;

    @Autowired
    @Qualifier("objLibrosDao")
    private librosDao libros;

    /*
    ====================================================
    ==       C Á T A L O G O   R O L E S              ==
    ==     DESCRIPCIÓN:                               ==
    ==                 En esta sección se agregara,   ==
    ==                 actualizara, eliminara,        ==
    ==                 consultara roles de usuarios   ==
    ====================================================
     */
    @GetMapping("/roles")
    public Object consultarRoles() {
        Object consulta = roles.consultar();
        return consulta;
    }

    @GetMapping("/roles/{identificador}")
    public Object consultarEspecificoRol(@PathVariable(value = "identificador") int id) {
        roles rol = new roles();
        rol.setId(id);
        Object respuesta = roles.obtenerEspecifico(rol);
        return respuesta;
    }

    @PostMapping("/roles")
    public Object insertarRol(@RequestBody roles obj, HttpServletRequest request) {
        Object respuesta = roles.insertar(obj);
        return respuesta;
    }

    @DeleteMapping("/roles/{identificador}")
    public Object eliminarRol(@PathVariable(value = "identificador") String id) {
        roles obj = new roles();
        obj.setId(Integer.parseInt(id));
        Object respuesta = roles.eliminar(obj);
        return respuesta;
    }

    @PutMapping("/roles/{identificador}")
    public Object actualizarRol(@PathVariable(value = "identificador") String id, @RequestBody roles obj, HttpServletRequest request) {
        obj.setId(Integer.parseInt(id));
        Object respuesta = roles.actualizar(obj);
        return respuesta;
    }

    /*
    ====================================================
    ==       C Á T A L O G O   U S U A R I O S        ==
    ==     DESCRIPCIÓN:                               ==
    ==                 En esta sección se agregara,   ==
    ==                 actualizara, eliminara,        ==
    ==                 consultara los usuarios        ==
    ====================================================
     */
    @GetMapping("/usuarios")
    public Object consultarUsuarios() {
        Object consulta = usuarios.consultar();
        return consulta;
    }

    @GetMapping("/usuarios/{identificador}")
    public Object consultarEspecificoUsuario(@PathVariable(value = "identificador") String id) {
        usuarios usuario = new usuarios();
        usuario.setCodigo(id);
        Object respuesta = this.usuarios.obtenerEspecifico(usuario);
        return respuesta;
    }

    @PostMapping("/usuarios")
    public Object insertarUsuarios(@RequestBody usuarios obj, HttpServletRequest request) {
        Object respuesta = usuarios.insertar(obj);
        return respuesta;
    }

    @DeleteMapping("/usuarios/{identificador}")
    public Object eliminarUsuario(@PathVariable(value = "identificador") String id) {
        usuarios obj = new usuarios();
        obj.setCodigo(id);
        Object respuesta = usuarios.eliminar(obj);
        return respuesta;
    }

    @PutMapping("/usuarios/{identificador}")
    public Object actualizarUsuario(@PathVariable(value = "identificador") String id, @RequestBody usuarios obj, HttpServletRequest request) {
        obj.setCodigo(id);
        Object respuesta = usuarios.actualizar(obj);
        return respuesta;
    }

    /*
    ====================================================
    ==       C Á T A L O G O   E D I T O R I A L      ==
    ==     DESCRIPCIÓN:                               ==
    ==                 En esta sección se agregara,   ==
    ==                 actualizara, eliminara,        ==
    ==                 consultara las editoriales.    ==
    ====================================================
     */
    @GetMapping("/editorial")
    public Object consultarEditorial() {
        Object consulta = editoriales.consultar();
        return consulta;
    }

    @GetMapping("/editorial/{identificador}")
    public Object consultarEspecificoEditorial(@PathVariable(value = "identificador") int id) {
        editorial editorial = new editorial();
        editorial.setId(id);
        Object respuesta = this.editoriales.obtenerEspecifico(editorial);
        return respuesta;
    }

    @PostMapping("/editorial")
    public Object insertarEditorial(@RequestBody editorial obj, HttpServletRequest request) {
        Object respuesta = editoriales.insertar(obj);
        return respuesta;
    }

    @DeleteMapping("/editorial/{identificador}")
    public Object eliminarEditorial(@PathVariable(value = "identificador") int id) {
        editorial obj = new editorial();
        obj.setId(id);
        Object respuesta = editoriales.eliminar(obj);
        return respuesta;
    }

    @PutMapping("/editorial/{identificador}")
    public Object actualizarEditorial(@PathVariable(value = "identificador") int id, @RequestBody editorial obj, HttpServletRequest request) {
        obj.setId(id);
        Object respuesta = editoriales.actualizar(obj);
        return respuesta;
    }

    /*
    ====================================================
    ==       C Á T A L O G O   G E N E R O S          ==
    ==     DESCRIPCIÓN:                               ==
    ==                 En esta sección se agregara,   ==
    ==                 actualizara, eliminara,        ==
    ==                 consultara los generos.   .    ==
    ====================================================
     */
    @GetMapping("/generos")
    public Object consultarGenero() {
        Object consulta = generos.consultar();
        return consulta;
    }

    @GetMapping("/generos/{identificador}")
    public Object consultarEspecificoGenero(@PathVariable(value = "identificador") int id) {
        generos genero = new generos();
        genero.setId(id);
        Object respuesta = this.generos.obtenerEspecifico(genero);
        return respuesta;
    }

    @PostMapping("/generos")
    public Object insertarGenero(@RequestBody generos obj, HttpServletRequest request) {
        Object respuesta = generos.insertar(obj);
        return respuesta;
    }

    @DeleteMapping("/generos/{identificador}")
    public Object eliminarGenero(@PathVariable(value = "identificador") int id) {
        generos obj = new generos();
        obj.setId(id);
        Object respuesta = generos.eliminar(obj);
        return respuesta;
    }

    @PutMapping("/generos/{identificador}")
    public Object actualizarGenero(@PathVariable(value = "identificador") int id, @RequestBody generos obj, HttpServletRequest request) {
        obj.setId(id);
        Object respuesta = generos.actualizar(obj);
        return respuesta;
    }

    /*
    ====================================================
    ==       C Á T A L O G O   L I B R O S            ==
    ==     DESCRIPCIÓN:                               ==
    ==                 En esta sección se agregara,   ==
    ==                 actualizara, eliminara,        ==
    ==                 consultara los libros.         ==
    ====================================================
     */
    @GetMapping("/libros")
    public Object consultarLibro() {
        Object consulta = libros.consultar();
        return consulta;
    }

    @GetMapping("/libros/{identificador}")
    public Object consultarEspecificoLibro(@PathVariable(value = "identificador") String id) {
        libros libro = new libros();
        libro.setCodigo(id);
        Object respuesta = this.libros.obtenerEspecifico(libro);
        return respuesta;
    }

    @PostMapping("/libros")
    public Object insertarLibros(@RequestBody libros obj, HttpServletRequest request) {
        Object respuesta = libros.insertar(obj);
        return respuesta;
    }

    @DeleteMapping("/libros/{identificador}")
    public Object eliminarLibro(@PathVariable(value = "identificador") String id) {
        libros obj = new libros();
        obj.setCodigo(id);
        Object respuesta = libros.eliminar(obj);
        return respuesta;
    }

    @PutMapping("/libros/{identificador}")
    public Object actualizarLibro(@PathVariable(value = "identificador") String id, @RequestBody libros obj, HttpServletRequest request) {
        obj.setCodigo(id);
        Object respuesta = libros.actualizar(obj);
        return respuesta;
    }
}
