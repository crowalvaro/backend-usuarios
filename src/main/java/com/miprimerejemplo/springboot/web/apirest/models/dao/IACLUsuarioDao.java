package com.miprimerejemplo.springboot.web.apirest.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.miprimerejemplo.springboot.web.apirest.models.entity.AclUsuario;

public interface IACLUsuarioDao extends CrudRepository<AclUsuario, Long>{
	
	//Encontrar nick
	public AclUsuario findByNick(String nick);
	
	
	//Para hacer consulta personalizada con nombre que quieras NOTA: la tabla es el nombre de la clase
	//@Query("select u.* from AclUsuario u where u.nick=?1")
	//public AclUsuario buscarNick(String nick);
}
