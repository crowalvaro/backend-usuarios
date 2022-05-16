package com.miprimerejemplo.springboot.web.apirest.models.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miprimerejemplo.springboot.web.apirest.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll();
	public Page<Usuario> findAll(Pageable pageable);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
	
	public Usuario findLast();
	
	public Page<Usuario>findByNombre(String nombre,Pageable pageable);
	
	public ByteArrayInputStream excelUsuarios();
}
