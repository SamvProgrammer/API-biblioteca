/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.dao;

import com.biblioteca.repositorios.apartado;

/**
 *
 * @author santiago antonio
 */
public interface operacionesDao {
    public Object consulta(int tipo,int codigo,String idUsuario);
    public Object apartar(apartado obj);
    public Object consultarPedidos(int tipo,String codigo,String idUsuario);
}
