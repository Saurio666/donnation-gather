package com.sauriotech.donnationgatherbe.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SAURIOTech Solutions.
 *
 */
@MappedSuperclass
public class Base {
	
	@Column(name = "estado", insertable = false)
	@Getter
	@Setter
	private String estado;
	
	@Column(name = "fecha_registro", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date fechaRegistro;
	
	@Column(name = "usuario_registro", updatable = false, insertable = false)
	@Getter
	@Setter
	private String usuarioRegistro;
	
	@Column(name = "fecha_actualizacion", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date fechaActualizacion;
	
	@Column(name = "usuario_actualizacion", updatable = false, insertable = false)
	@Getter
	@Setter
	private String usuarioActualizacion;
}
