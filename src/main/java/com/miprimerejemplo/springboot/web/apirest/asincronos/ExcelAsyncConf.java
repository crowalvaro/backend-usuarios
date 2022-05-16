package com.miprimerejemplo.springboot.web.apirest.asincronos;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ExcelAsyncConf {

/**
 * Configuracion para ejecutar en un mismo threadpool (executor) todos los 
 * métodos asincronos que lo posean.
 * @return Executor
 */
@Bean(name = "asyncExcelExecutor")
public Executor asyncExcelExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(4); //Lo suyo es poner el numero de nucleos de mi procesador
    executor.setMaxPoolSize(6);
    executor.setQueueCapacity(100); //Maximo de hilos total estando a la espera
    executor.setThreadNamePrefix("ExcelThread-");
    executor.initialize();
    return executor;
  }
		
}
