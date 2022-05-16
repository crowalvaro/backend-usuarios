package com.miprimerejemplo.springboot.web.apirest.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="acl_usuarios")
public class AclUsuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=20)
	private String nick;
	
	@Column(length = 60)
	private String password;
	
	private Boolean activo;
	
	//Hacer tabla intermedia donde añadir a los usuarios sus roles
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="acl_usuarios_roles",joinColumns=@JoinColumn(name="acl_usuario_id"),inverseJoinColumns=@JoinColumn(name="acl_role_id"))
	private List<AclRole> roles;
	

	/*
	 * Getters & setters
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<AclRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AclRole> roles) {
		this.roles = roles;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
