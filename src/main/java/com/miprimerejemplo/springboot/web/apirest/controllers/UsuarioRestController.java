package com.miprimerejemplo.springboot.web.apirest.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.miprimerejemplo.springboot.web.apirest.asincronos.ExcelAsync;
import com.miprimerejemplo.springboot.web.apirest.models.entity.Excel;
import com.miprimerejemplo.springboot.web.apirest.models.entity.Recurso;
import com.miprimerejemplo.springboot.web.apirest.models.entity.Usuario;
import com.miprimerejemplo.springboot.web.apirest.models.services.IExcelService;
import com.miprimerejemplo.springboot.web.apirest.models.services.IUsuarioService;
import com.miprimerejemplo.springboot.web.apirest.validacion.UsuarioValidador;

//Para habilitar el cors con angular 
@EnableAsync
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired 
	private ExcelAsync excelAsync;
	
	@Autowired
	private IExcelService excelService;
	
	@GetMapping("/usuarios")
	public List<Usuario> index() {
		return usuarioService.findAll();
	}

	// Metodo handler para poder paginar
	// Añadir filtrado

	@GetMapping("/usuarios/page/{page}")
	public Page<Usuario> index(@PathVariable Integer page,
			@RequestParam(name = "nombre", required = false) String nombre) {
		if (nombre != null) {
			return usuarioService.findByNombre(nombre, PageRequest.of(page, 5));
		}
		return usuarioService.findAll(PageRequest.of(page, 5));
	}

	@Autowired
	private UsuarioValidador validadorUsuario;

	@GetMapping("/usuarios/{id}")
//	public Usuario show(@PathVariable Long id) {
	public ResponseEntity<?> show(@PathVariable Long id) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuario = usuarioService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al consultar en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (usuario == null) {
			response.put("mensaje", "Usuario con id_usuario: " + id.toString() + " no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
//	public Usuario create(@RequestBody Usuario usuario) {
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {

		Usuario usuarioAux = null;
		Map<String, Object> response = new HashMap<>();

		// Paso la clase validadora para ver si hay mas errores
		validadorUsuario.validate(usuario, result);

		// Validar que no existan errores en la validaciond de los campos del formulario
		if (result.hasErrors()) {

			// Lista de errores
			List<String> errores = new ArrayList<>();

			for (FieldError err : result.getFieldErrors()) {
				errores.add("El campo " + err.getField() + ": " + err.getDefaultMessage());
			}

			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			usuarioAux = usuarioService.save(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al consultar en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario se ha creado con éxito");
		response.put("usuario", usuarioAux);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
//	public Usuario uptate(@RequestBody Usuario usuario, @PathVariable Long id) {
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
		Usuario usuarioAct = usuarioService.findById(id);

		Usuario usuarioUpdate = null;

		Map<String, Object> response = new HashMap<>();
		// Paso la clase validadora para ver si hay mas errores
		validadorUsuario.validate(usuario, result);
		// Validar que no existan errores en la validaciond de los campos del formulario
		if (result.hasErrors()) {

			// Lista de errores
			List<String> errores = new ArrayList<>();

			for (FieldError err : result.getFieldErrors()) {
				errores.add("El campo " + err.getField() + ": " + err.getDefaultMessage());
			}

			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (usuarioAct == null) {
			response.put("mensaje", "Error: no se pudo editar el usuario con ID: " + id.toString()
					+ " -> No existe este id de usuario");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			// Añado todos los atributos a modificar
			usuarioAct.setApellidos(usuario.getApellidos());
			usuarioAct.setContrasenia(usuario.getContrasenia());
			usuarioAct.setEmail(usuario.getEmail());
			usuarioAct.setFoto(usuario.getFoto());
			usuarioAct.setFecha_nac(usuario.getFecha_nac());
			usuarioAct.setNick(usuario.getNick());
			usuarioAct.setNombre(usuario.getNombre());

			usuarioUpdate = usuarioService.save(usuarioAct);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// NO hay errores
		response.put("mensaje", "El usuario: " + usuarioUpdate.getNick() + " ha sido actualizado con exito");
		response.put("usuario", usuarioUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	// @Secured({"ROLE_ADMIN",""ROLE_USUARIO})
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable Long id) {
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			usuarioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario con ID: " + id + " ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PostMapping("/usuarios/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findLast();

		// Si llega id pondra la foto con la id correspondiente
		// 0 es el id que manda por defecto cuando no hay id, ademas en el autonumerico
		// del entity empieza siempre por 1
		if (id != 0) {
			usuario = usuarioService.findById(id);
		}

		if (!archivo.isEmpty()) {
			// Para generar un nombre random con identificador unico
			String nombreArchivo = UUID.randomUUID() + "_" + archivo.getOriginalFilename();
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Eror al subir la imagen del usuario" + nombreArchivo);
				response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			// borrar imagen anterios al actualizar
			String nombreAnterior = usuario.getFoto();
			Path rutaAnterior = Paths.get("uploads").resolve(nombreAnterior).toAbsolutePath();
			File archivoFotoAnterior = rutaAnterior.toFile();
			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()
					&& !nombreAnterior.equals("usuario.png")) {
				archivoFotoAnterior.delete();
			}

			usuario.setFoto(nombreArchivo);
			usuarioService.save(usuario);

			response.put("usuario", usuario);
			response.put("mensaje", "Se ha subido correctamente la imagen " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;

		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + recurso.getFilename());

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	//Tengo que transformar esto a 
	//devolver respuesta de procesamiento y future cuando lo procese
	@GetMapping("usuarios/descargar/excel/{id}")
	 public ResponseEntity<?> descargarUsuariosExcel(@PathVariable Long id) {
		 String filename = "usuarios.xlsx";
		 Excel excel;
		 	try {
		 		excel = excelService.findById(id);
		 	}catch(DataAccessException e){
		 		Map<String, Object> response = new HashMap<>();
		 		response.put("mensaje", "No existe este recurso");
		 		response.put("error", e.getMessage());
		 		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
		 	
		    InputStreamResource file = new InputStreamResource(new ByteArrayInputStream(excel.getExcel()));
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		        .body(file);
	}
	//Numero de hilos maximo que se ejecutan 
	//tarea que se guarda
	@GetMapping("usuarios/descargar/excel")
	 public ResponseEntity<Map<String,String>> generarExcel(
			 @RequestParam(name = "user") String user) throws InterruptedException, IOException {
		
		
		excelAsync.añadirRecurso();
		Map<String,String> respuesta = new HashMap<String,String>();
		
		return ResponseEntity.accepted()
				.body(respuesta);
	}
	@GetMapping("usuarios/descargar/excel/resultado")
	 public ResponseEntity<List<Recurso>> descargarUsuariosExcelCompletada() throws InterruptedException, ExecutionException {
		
		List<Recurso> listaRecursos = excelAsync.obtenerLista().get();
		
		ResponseEntity<List<Recurso>> respuesta = ResponseEntity.ok()
		.body(listaRecursos);
		return respuesta;
      };
      
      
	
	
}
