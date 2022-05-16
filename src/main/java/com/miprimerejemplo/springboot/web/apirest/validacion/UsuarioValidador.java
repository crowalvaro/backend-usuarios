package com.miprimerejemplo.springboot.web.apirest.validacion;




import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.miprimerejemplo.springboot.web.apirest.models.entity.Usuario;

@Component
public class UsuarioValidador implements Validator {
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario)target;
		//Con el codigo desde messages.properties no establece el defaultMesagge con lo de dentro del codigo por lo que creo una id para el error
		//y establezco mensaje por defecto desde el mismo rejectValue como 3 parametro
		
		
		//Validar Nombre que no tenga numeros (Se puede hacer con @Pattern en el modelo)
		if(!usuario.getNombre().toLowerCase().matches("^[a-z]+$")) {
			
			errors.rejectValue("nombre","errores.Usuario.nombre","El nombre no debe tener numeros");
		}
		
		//Validar la fecha para que le llegue en formato dd/mm/yyyy
		//Formato valido 
//		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//		formato.setLenient(false); //Para que sea estricto
			
			
			
			
		
	}
	

}
