package com.sauriotech.donnationgatherbe.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "donacion_personal", catalog = ConstantesEntidad.CATALOGO, schema = ConstantesEntidad.ESQUEMA)
public class DonacionPersonal extends Base {
	@Id
	@Column(name = "codigo_donacion_personal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long codigoDonacionPersonal;
	@Column(name = "nombre_donador")
	@Getter
	@Setter
	private String nombreDonador;
	@Column(name = "cedula_donador")
	@Getter
	@Setter
	private String cedulaDonador;
	@Column(name = "codigo_evento")
	@Getter
	@Setter
	private Long codigoEvento;
	@Column(name = "codigo_punto_entrega")
	@Getter
	@Setter
	private Long codigoPuntoEntrega;
	@Column(name = "fecha_entrega")
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date fechaEntrega;
	@Column(name = "numero_boleto")
	@Getter
	@Setter
	private String numeroBoleto;
}
