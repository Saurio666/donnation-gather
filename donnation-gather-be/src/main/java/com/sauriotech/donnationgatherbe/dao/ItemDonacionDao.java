package com.sauriotech.donnationgatherbe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sauriotech.donnationgatherbe.entidad.ItemDonacion;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
public interface ItemDonacionDao extends JpaRepository<ItemDonacion, Long> {
	public List<ItemDonacion> findByEstado(String estado);
	public List<ItemDonacion> findByCodigoDonacionPersonalAndEstado(Long codigoDonacionPersonal, String estado);
}
