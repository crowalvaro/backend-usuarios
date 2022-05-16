package com.miprimerejemplo.springboot.web.apirest.models.services;

import java.util.List;


import com.miprimerejemplo.springboot.web.apirest.models.entity.Excel;


public interface IExcelService {
	public List<Excel> findAll();
	
	public Excel findById(Long id);
	
	public Excel save(Excel excel);
	
	
}
