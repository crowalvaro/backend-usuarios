package com.miprimerejemplo.springboot.web.apirest.models.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miprimerejemplo.springboot.web.apirest.exportar.xlsx.UsuariosXlsx;
import com.miprimerejemplo.springboot.web.apirest.models.dao.IUsuarioDao;
import com.miprimerejemplo.springboot.web.apirest.models.entity.Usuario;


@Service
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}


	@Override
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}
	@Override
	public Usuario findLast() {
		return usuarioDao.findTopByOrderByIdUsuarioDesc();
	}
	
	@Override
	public Page<Usuario> findByNombre(String nombre,Pageable pageable){
		return usuarioDao.findByNombre(nombre, pageable);
		
	}
	
	@Override
	 public ByteArrayInputStream excelUsuarios() {
		    List<Usuario> usuarios = usuarioDao.findAll();
		    ByteArrayInputStream in = UsuariosXlsx.usuariosToExcel(usuarios);
		    return in;
		  }
	
	
	


}
