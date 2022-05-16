package com.miprimerejemplo.springboot.web.apirest.models.entity;

public class Recurso {
	private String idRecurso;
	private String usuario;
	private String estado;
	private String ruta;
	
	
	/**
	 * Constructor
	 */
	public Recurso() {
		
	}
	public Recurso(String estado) {
		this.estado=estado;
	}
	
	/**
	 * Getters & setters
	 * @return
	 */
	public String getIdRecurso() {
		return idRecurso;
	}
	public void setIdRecurso(String idRecurso) {
		this.idRecurso = idRecurso;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
}
