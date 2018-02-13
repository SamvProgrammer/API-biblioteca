/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.dao;

import com.biblioteca.repositorios.libros;

/**
 *
 * @author santiago antonio
 */
public interface librosDao {

    public Object consultar();

    public Object obtenerEspecifico(libros libro);

    public Object insertar(libros libro);

    public Object actualizar(libros libro);

    public Object eliminar(libros libro);
}
