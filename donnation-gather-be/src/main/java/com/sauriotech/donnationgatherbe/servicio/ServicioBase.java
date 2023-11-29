package com.sauriotech.donnationgatherbe.servicio;

/**
 * 
 * @author SAURIOTech Solutions.
 *
 */
public class ServicioBase {
	public void validarToken(String token) throws SeguridadExcepcion {
		System.out.println("[ServicioBase] [validarToken] token " + token);
		if(!"12345".equals(token)) {
			throw new SeguridadExcepcion("Usuario no autorizado");
		}
	}
}
