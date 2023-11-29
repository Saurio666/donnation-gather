package com.sauriotech.donnationgatherbe.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sauriotech.donnationgatherbe.beans.EventoBean;
import com.sauriotech.donnationgatherbe.beans.PuntoEntregaBean;
import com.sauriotech.donnationgatherbe.modelo.EventoModelo;
import com.sauriotech.donnationgatherbe.modelo.PuntoEntregaModelo;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
@RestController
@RequestMapping("/api/catalogo")
@CrossOrigin(origins = "*")
public class CatalogoServicio extends ServicioBase {
	@Autowired
	private PuntoEntregaBean puntoEntregaBean;
	@Autowired
	private EventoBean eventoBean;

	@GetMapping("/puntos_entrega/{token}")
	public ResponseEntity<RespuestaServicio> getCatalogoPuntosEntrega(@PathVariable("token") String token) {
		try {
			validarToken(token);
			List<PuntoEntregaModelo> puntosEntrega = puntoEntregaBean.getPuntosEntregaActivos();
			RespuestaServicio respuesta = new RespuestaServicio(ConstantesServicio.OK, null);
			respuesta.getDatos().put("puntosEntrega", puntosEntrega);
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (SeguridadExcepcion ex) {
			RespuestaServicio respuesta = new RespuestaServicio(ConstantesServicio.ERROR, null);
			respuesta.getDatos().put(ConstantesServicio.ERROR_AUTORIZACION, ex.getMessage());
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/evento/{token}")
	public ResponseEntity<RespuestaServicio> getCatalogoEventos(@PathVariable("token")String token) {
		try {
			validarToken(token);
			List<EventoModelo> eventos = eventoBean.getEventosActivos();
			RespuestaServicio respuesta = new RespuestaServicio(ConstantesServicio.OK, null);
			respuesta.getDatos().put("eventos", eventos);
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
