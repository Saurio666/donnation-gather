package com.sauriotech.donnationgatherbe.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SAURIOTech Solutions.
 *
 */
@Entity
@Table(name = "item_donacion", catalog = ConstantesEntidad.CATALOGO, schema = ConstantesEntidad.ESQUEMA)
public class ItemDonacion extends Base {
	@Id
	@Column(name = "codigo_item_donacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long codigoItemDonacion;
	@Column(name = "codigo_donacion_personal")
	@Getter
	@Setter
	private Long codigoDonacionPersonal;
	@Column(name = "descripcion_item_donacion")
	@Getter
	@Setter
	private String descripcionItemDonacion;
	@Column(name = "cantidad_item_donacion")
	@Getter
	@Setter
	private Long cantidadItemDonacion;
	
}
