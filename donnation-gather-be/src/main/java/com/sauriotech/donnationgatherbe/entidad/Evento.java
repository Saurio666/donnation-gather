package com.sauriotech.donnationgatherbe.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SAURIOTech Solutions.
 *
 */
@Entity
@Table(name = "evento", catalog = ConstantesEntidad.CATALOGO, schema = ConstantesEntidad.ESQUEMA)
public class Evento extends Base {
	@Id
	@Column(name = "codigo_evento")
	@Getter
	@Setter
	private Long codigoEvento;
	@Column(name = "nombre_evento")
	@Getter
	@Setter
	private String nombreEvento;
	@Column(name = "direccion_evento")
	@Getter
	@Setter
	private String direccionEvento;
	@Column(name = "url_direccion_evento")
	@Getter
	@Setter
	private String urlDireccionEvento;
	@Column(name = "fecha_evento")
	@Temporal(TemporalType.DATE)
	@Getter
	@Setter
	private Date fechaRegistro;
	@Column(name = "cantidad_tickets_evento")
	@Getter
	@Setter
	private Long cantidadTicketsEvento;
	
}
