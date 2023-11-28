package com.sauriotech.donnationgatherbe.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sauriotech.donnationgatherbe.dao.EventoDao;
import com.sauriotech.donnationgatherbe.entidad.ConstantesEntidad;
import com.sauriotech.donnationgatherbe.entidad.Evento;
import com.sauriotech.donnationgatherbe.entidad.PuntoEntrega;
import com.sauriotech.donnationgatherbe.modelo.EventoModelo;
import com.sauriotech.donnationgatherbe.modelo.PuntoEntregaModelo;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
@Service
public class EventoBean {
	@Autowired
	private EventoDao eventoDao;
	
	public List<EventoModelo> getEventosActivos(){
		List<Evento> eventosDb = eventoDao.findByEstado(ConstantesEntidad.ESTADO_ACTIVO);
		List<EventoModelo> eventos = new ArrayList<>();
		
		for(Evento e : eventosDb) {
			EventoModelo modelo = new EventoModelo();
			BeanUtils.copyProperties(e, modelo);
			eventos.add(modelo);
		}
		
		
		return eventos;
	}
}
