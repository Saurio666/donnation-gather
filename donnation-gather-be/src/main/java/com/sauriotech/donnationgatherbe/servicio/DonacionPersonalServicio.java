package com.sauriotech.donnationgatherbe.servicio;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sauriotech.donnationgatherbe.beans.DonacionPersonalBean;
import com.sauriotech.donnationgatherbe.modelo.PuntoEntregaModelo;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
@RestController
@RequestMapping("/api/donacion_personal")
@CrossOrigin(origins = "*")
public class DonacionPersonalServicio extends ServicioBase {
	@Autowired
	private DonacionPersonalBean donacionPersonalBean;
	
	@PostMapping("/registrar")
	public ResponseEntity<RespuestaServicio> getCatalogoPuntosEntrega(@RequestBody Map<String, Object> data) {
		System.out.println("data --> " + data);
		try {
			String token = String.valueOf(data.get("token"));
			validarToken(token);
			Map<String, Object> resultado = donacionPersonalBean.registrarDonacion(data);
			RespuestaServicio respuesta = new RespuestaServicio(ConstantesServicio.OK, null);
			respuesta.setDatos(resultado);
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (SeguridadExcepcion ex) {
			RespuestaServicio respuesta = new RespuestaServicio(ConstantesServicio.ERROR, null);
			respuesta.getDatos().put(ConstantesServicio.ERROR_AUTORIZACION, ex.getMessage());
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
