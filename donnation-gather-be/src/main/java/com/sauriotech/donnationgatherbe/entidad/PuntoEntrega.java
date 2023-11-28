package com.sauriotech.donnationgatherbe.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SAURIOTech Solutions.
 *
 */
@Entity
@Table(name = "punto_entrega", catalog = ConstantesEntidad.CATALOGO, schema = ConstantesEntidad.ESQUEMA)
public class PuntoEntrega extends Base{
	@Id
	@Column(name = "codigo_punto_entrega")
	@Getter
	@Setter
	private Long codigoPuntoEntrega;
	@Column(name = "nombre_punto_entrega")
	@Getter
	@Setter
	private String nombrePuntoEntrega;
	@Column(name = "direccion_punto_entrega")
	@Getter
	@Setter
	private String direccionPuntoEntrega;
	@Column(name = "url_direccion_punto_entrega")
	@Getter
	@Setter
	private String urlDireccionPuntoEntrega;
	@Column(name = "responsable_punto_entrega")
	@Getter
	@Setter
	private String responsablePuntoEntrega; 
}
