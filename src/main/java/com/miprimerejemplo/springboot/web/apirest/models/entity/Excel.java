package com.miprimerejemplo.springboot.web.apirest.models.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Excel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Lob 
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="excel", columnDefinition="longblob", nullable=true) 
	private byte[] excel;

	/**
	 * Getters & setters
	 * 
	 */
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getExcel() {
		return excel;
	}

	public void setExcel(byte[] excel) {
		this.excel = excel;
	}
 
 

}
