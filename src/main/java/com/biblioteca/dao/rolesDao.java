/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.dao;

import com.biblioteca.repositorios.roles;

/**
 *
 * @author santiago antonio
 */
public interface rolesDao {
   public Object consultar();
   public Object obtenerEspecifico(roles rol);
   public Object insertar(roles rol);
   public Object actualizar(roles rol);
   public Object eliminar(roles rol);
}
