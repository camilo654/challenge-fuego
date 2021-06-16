package com.meli.restservice.service;

import com.meli.restservice.dto.SatelliteDTO;

/**
 *
 * @author cctorresr
 */
public interface IDecoderService {

	/**
	 * Descifra mensaje enviado por la nave portacarga a partir de los mensajes
	 * interceptados por los satelites
	 * 
	 * @param satellites que contienen los mensajes captados por cada satelite
	 * @return {@code String} mensaje descifrado
	 */
	String getMessage(SatelliteDTO[] satellites);
}
