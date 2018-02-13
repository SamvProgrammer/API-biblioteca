/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.dao;

import com.biblioteca.repositorios.editorial;

/**
 *
 * @author santiago antonio
 */
public interface editorialDao {
   public Object consultar();
   public Object obtenerEspecifico(editorial editorial);
   public Object insertar(editorial editorial);
   public Object actualizar(editorial editorial);
   public Object eliminar(editorial editorial);
}
