package com.sauriotech.donnationgatherbe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sauriotech.donnationgatherbe.entidad.DonacionPersonal;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
public interface DonacionPersonalDao extends JpaRepository<DonacionPersonal, Long> {
	public List<DonacionPersonal> findByEstado(String estado);
}
