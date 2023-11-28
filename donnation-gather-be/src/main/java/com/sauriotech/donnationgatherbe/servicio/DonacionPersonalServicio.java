package com.sauriotech.donnationgatherbe.servicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
@RestController
@RequestMapping("/api/donacion_personal")
@CrossOrigin(origins = "*")
public class DonacionPersonalServicio {
	
	@GetMapping("/{token}")
	public ResponseEntity<RespuestaServicio> getCatalogoPuntosEntrega(String token) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
