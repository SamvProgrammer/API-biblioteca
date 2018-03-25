/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.daoImp;

import com.biblioteca.dao.operacionesDao;
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
public class operacionesDaoImp implements operacionesDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Object consulta(int tipo,int codigo){
      String query = "select codigo,nombre,total,cantidad,autor,"
                   + "e.id as id_editorial,e.editorial,g.id as id_genero,"
                   + "g.genero from libros l inner join editorial e "
                   + "on l.editorial = e.id "
                   + "inner join genero g "
                   + "on l.genero = g.id ";
      Object resultado = null;
      if(tipo != 0){
          switch(tipo){
          case 1 :
              query += "where e.id = ?";
          break;
          case 2:
              query += "where g.id = ?";
          break;
      }
         resultado = jdbcTemplate.queryForList(query, codigo);        
      }else{
         resultado = jdbcTemplate.queryForList(query); 
      }   
      return resultado;
    }   
}
