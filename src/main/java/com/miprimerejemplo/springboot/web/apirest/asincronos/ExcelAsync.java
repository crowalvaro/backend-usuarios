package com.miprimerejemplo.springboot.web.apirest.asincronos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.miprimerejemplo.springboot.web.apirest.models.entity.Excel;
import com.miprimerejemplo.springboot.web.apirest.models.entity.Recurso;
import com.miprimerejemplo.springboot.web.apirest.models.services.IExcelService;
import com.miprimerejemplo.springboot.web.apirest.models.services.IUsuarioService;

@Component
public class ExcelAsync {
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IExcelService excelService;
	
	/**
	 * Lista donde guardar los recursos generados
	 */
	private List<Recurso> recursos = new ArrayList<Recurso>(); 
	
	/**
	 * Devuelver lista de forma asincrona
	 * @return CompletableFuture 
	 */
	@Async("asyncExcelExecutor")
	public CompletableFuture<List<Recurso>> obtenerLista() {
		 //Devuelvo la lista de recursos de forma asincrona
		 return CompletableFuture.completedFuture(this.recursos);
	}
	
	/**
	 * Método que al invocarse genera una petición en un nuevo hilo el cual
	 * crea una lista de recursos, y va a actualizandola
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	@Async("asyncExcelExecutor")
	public void añadirRecurso() throws InterruptedException, IOException {
		
		//Añado el nuevo recurso
		Recurso recurso = new Recurso();
		recurso.setEstado("Procesando");
		recurso.setUsuario("Prueba");
		recurso.setIdRecurso(Thread.currentThread().getName());
		this.recursos.add(recurso);

		//Ejecuto cambios
		
		Excel excel = new Excel();
		byte[] blob = IOUtils.toByteArray(usuarioService.excelUsuarios());
		excel.setExcel(blob);
		excelService.save(excel);
		String ruta=""+excel.getId();
		
		
		//Modifico la lista con los cambios
		recursos.get(recursos.indexOf(recurso)).setRuta(ruta);
	    recursos.get(recursos.indexOf(recurso)).setEstado("Completado");
		
	}
	
	/**
	 * Metodo para guardar archivo pasandole la ruta
	 * @parm String ruta
	 */
//	public void guardarArchivo(String rutaArchivo) {
//		File file;
//		file = new File(rutaArchivo);
//		try (FileOutputStream fileOuS = new FileOutputStream(file)){	
//			//Si existe lo elimina
//			if (file.exists()) {
//				file.delete();
//			}
//			libro.write(fileOuS);
//			fileOuS.flush();
//			fileOuS.close();
//			System.out.println("Archivo Creado");
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//	}

	
}
