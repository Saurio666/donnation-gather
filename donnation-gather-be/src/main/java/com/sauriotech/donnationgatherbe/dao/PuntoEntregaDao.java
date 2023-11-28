package com.sauriotech.donnationgatherbe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sauriotech.donnationgatherbe.entidad.PuntoEntrega;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
public interface PuntoEntregaDao extends JpaRepository<PuntoEntrega, Long> {
	public List<PuntoEntrega> findByEstado(String estado);
}
