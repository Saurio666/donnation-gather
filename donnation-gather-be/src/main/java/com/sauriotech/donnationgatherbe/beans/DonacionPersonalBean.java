package com.sauriotech.donnationgatherbe.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sauriotech.donnationgatherbe.dao.DonacionPersonalDao;
import com.sauriotech.donnationgatherbe.dao.ItemDonacionDao;
import com.sauriotech.donnationgatherbe.entidad.ConstantesEntidad;
import com.sauriotech.donnationgatherbe.entidad.DonacionPersonal;
import com.sauriotech.donnationgatherbe.entidad.ItemDonacion;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
@Service
public class DonacionPersonalBean {
	
	@Autowired
	private DonacionPersonalDao donacionPersonalDao;
	@Autowired
	private ItemDonacionDao itemDonacionDao;
	
	public Map<String, Object> registrarDonacion(Map<String, Object> data) {
		Map<String, Object> resultado = new HashMap<>();
		
		DonacionPersonal dp = mapearInformacion(data);
		DonacionPersonal newDp = donacionPersonalDao.saveAndFlush(dp);
		guardarItems(newDp, data);
		
		resultado.put("codigo_donacion", newDp.getCodigoDonacionPersonal());
		return resultado;
	}
	
	private DonacionPersonal mapearInformacion(Map<String, Object> data) {
		DonacionPersonal dp = new DonacionPersonal();
		
		dp.setCedulaDonador(String.valueOf(data.get("identificacion")));
		dp.setCodigoEvento(Long.valueOf(String.valueOf(data.get("evento"))));
		dp.setCodigoPuntoEntrega(Long.valueOf(String.valueOf(data.get("punto_entrega"))));
		dp.setEstado(ConstantesEntidad.ESTADO_ACTIVO);
		dp.setFechaEntrega(Calendar.getInstance().getTime());
		dp.setNombreDonador(String.valueOf(data.get("nombre")));
		dp.setNumeroBoleto(String.valueOf(data.get("boleto")));
		return dp;
	}
	
	private void guardarItems(DonacionPersonal dp, Map<String, Object> data) {
		if(dp.getCodigoDonacionPersonal() != null) {
			System.out.println(data.get("items"));
			
			List<Map<String, Object>> items = (List) data.get("items");
			System.out.println("items.size() " + items.size());
			System.out.println("items " + items);
			
			List<ItemDonacion> entidades = new ArrayList<>();
			for(Map<String, Object> item : items) {
				ItemDonacion id = new ItemDonacion();
				id.setCantidadItemDonacion(Long.parseLong(String.valueOf(item.get("cantidad"))));
				id.setCodigoDonacionPersonal(dp.getCodigoDonacionPersonal());
				id.setDescripcionItemDonacion(String.valueOf(item.get("descripcion")));
				id.setEstado(ConstantesEntidad.ESTADO_ACTIVO);
				entidades.add(id);
			}
			
			itemDonacionDao.saveAllAndFlush(entidades);
		}
	}
}
