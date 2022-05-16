package com.miprimerejemplo.springboot.web.apirest.models.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


//import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//Al parecer _ esta reservado y no se puede usar en las variables para escapar hay que usar otro _ o juntarlo
	private Long idUsuario;
	
	@NotEmpty
	@Size(min=4, max=16)
	@Column(nullable=false,unique=true)
	private String nick;
	
	@Email
	@Column(nullable=false,unique=true)
	private String email;
	@Column(nullable=false)
	
	@NotEmpty
	@Size(min=4, max=30)
	private String nombre;
	
	@NotEmpty
	@Size(min=10, max=30)
	@Column(nullable=false)
	private String apellidos;
	@Column(nullable=false)
	
//	@NotEmpty no se puede poner en campos que no sean string
	//Para que no sea superior a la fecha actual
	//Voy a capturarlo con un datepeeker en el front para no perder mas tiempo
	@NotNull(message="No puede estar vacio")
	@Past
	private Date fecha_nac;
	@Column(nullable=false)
	private String contrasenia;
	@Column(nullable=false,columnDefinition = "varchar(60) default 'usuario.png'")
	private String foto;
	
	//Getters && Setters
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellido) {
		this.apellidos = apellido;
	}
	public Date getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
}
