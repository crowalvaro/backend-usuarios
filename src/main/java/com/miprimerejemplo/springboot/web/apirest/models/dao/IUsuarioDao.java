package com.miprimerejemplo.springboot.web.apirest.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.miprimerejemplo.springboot.web.apirest.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	
	//Para que me devuelva el utimo usuario insertado
	Usuario findTopByOrderByIdUsuarioDesc();
	
	//Aqui podre definir los tipos de busqueda para hacer el filtrado
	
	 Page<Usuario> findByNombre(String nombre,Pageable pageable);
	 Page<Usuario> findByEmail(String email,Pageable pageable);
	 
}
