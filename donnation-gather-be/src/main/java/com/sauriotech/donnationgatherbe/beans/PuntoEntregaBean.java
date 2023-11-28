package com.sauriotech.donnationgatherbe.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sauriotech.donnationgatherbe.dao.PuntoEntregaDao;
import com.sauriotech.donnationgatherbe.entidad.ConstantesEntidad;
import com.sauriotech.donnationgatherbe.entidad.PuntoEntrega;
import com.sauriotech.donnationgatherbe.modelo.PuntoEntregaModelo;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
@Service
public class PuntoEntregaBean {
	@Autowired
	private PuntoEntregaDao puntoEntregaDao;
	
	public List<PuntoEntregaModelo> getPuntosEntregaActivos(){
		List<PuntoEntrega> puntosEntregaDb = puntoEntregaDao.findByEstado(ConstantesEntidad.ESTADO_ACTIVO);
		List<PuntoEntregaModelo> puntos = new ArrayList<>();
		
		for(PuntoEntrega p : puntosEntregaDb) {
			PuntoEntregaModelo modelo = new PuntoEntregaModelo();
			BeanUtils.copyProperties(p, modelo);
			puntos.add(modelo);
		}
		
		
		return puntos;
	}
}
