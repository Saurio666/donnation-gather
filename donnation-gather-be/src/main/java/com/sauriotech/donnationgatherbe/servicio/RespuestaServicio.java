package com.sauriotech.donnationgatherbe.servicio;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
public class RespuestaServicio {
	@Getter	
	@Setter
	private String estado;
	@Getter	
	@Setter
	private String mensaje;
	@Getter	
	@Setter
	private Map<String, Object> datos;
	
	public RespuestaServicio(String estado, String mensaje) {
		this.estado = estado;
		this.mensaje = mensaje;
		datos = new HashMap<>();	
	}
}
