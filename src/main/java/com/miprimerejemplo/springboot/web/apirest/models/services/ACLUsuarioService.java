package com.miprimerejemplo.springboot.web.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miprimerejemplo.springboot.web.apirest.models.dao.IACLUsuarioDao;
import com.miprimerejemplo.springboot.web.apirest.models.entity.AclUsuario;

@Service
public class ACLUsuarioService implements UserDetailsService{
	
	//Variable para guardar un log con los errores
	private Logger logger = LoggerFactory.getLogger(ACLUsuarioService.class);

	@Autowired
	private IACLUsuarioDao aclUsuarioDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AclUsuario usuario = aclUsuarioDao.findByNick(username);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario"+username);
			throw new UsernameNotFoundException("Error en el login: no existe el usuario"+username);
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: "+authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getNick(), usuario.getPassword(), usuario.getActivo(), true, true, true, authorities);
	}

}

