package com.sauriotech.donnationgatherbe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sauriotech.donnationgatherbe.entidad.Evento;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
public interface EventoDao extends JpaRepository<Evento, Long> {
	public List<Evento> findByEstado(String estado);
}
