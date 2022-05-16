package com.miprimerejemplo.springboot.web.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.miprimerejemplo.springboot.web.apirest.models.entity.Excel;

public interface IExcelDao extends JpaRepository<Excel, Long>{

}
