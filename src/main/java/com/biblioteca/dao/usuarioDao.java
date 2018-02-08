/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.dao;

import com.biblioteca.repositorios.usuarios;

/**
 *
 * @author santiago antonio
 */
public interface usuarioDao {
   public Object consultar();
   public Object obtenerEspecifico(usuarios usuario);
   public Object insertar(usuarios usuario);
   public Object actualizar(usuarios usuario);
   public Object eliminar(usuarios usuario);
   public Object obtenerLogin(usuarios usuario);
}
