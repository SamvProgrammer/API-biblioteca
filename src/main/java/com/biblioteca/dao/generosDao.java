/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.dao;

import com.biblioteca.repositorios.generos;

/**
 *
 * @author santiago antonio
 */
public interface generosDao {
   public Object consultar();
   public Object obtenerEspecifico(generos genero);
   public Object insertar(generos genero);
   public Object actualizar(generos genero);
   public Object eliminar(generos genero);
}
