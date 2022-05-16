package com.miprimerejemplo.springboot.web.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miprimerejemplo.springboot.web.apirest.models.dao.IExcelDao;
import com.miprimerejemplo.springboot.web.apirest.models.entity.Excel;

@Service
public class ExcelService implements IExcelService{
	@Autowired
	private IExcelDao excelDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Excel> findAll() {
		return (List<Excel>) excelDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Excel findById(Long id) {
		return excelDao.findById(id).orElse(null);
	}

	@Override
	public Excel save(Excel excel) {
		return excelDao.save(excel);
	}

}
