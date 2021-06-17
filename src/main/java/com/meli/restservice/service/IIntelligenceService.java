package com.meli.restservice.service;

import com.meli.restservice.dto.ResponseDTO;
import com.meli.restservice.dto.SatelliteDTO;

/**
 *
 * @author cctorresr
 */
public interface IIntelligenceService {

	/**
	 * Valida la informacion recibida, calcula la posicion de la nave portacarga y
	 * el mensaje que esta emite.
	 * 
	 * @param satellites que contiene la informacion de cada satelite
	 * @return {@code ResponseDTO} con las coordenadas (x,y) y el mensaje del emisor
	 */
	ResponseDTO getInformation(SatelliteDTO[] satellites);

	/**
	 * <p>
	 * Recupera la informacion de los satelites almacenada en la DB.
	 * </p>
	 * <p>
	 * Valida la informacion, calcula la posicion de la nave portacarga y el mensaje
	 * que esta emite.
	 * </p>
	 * 
	 * @return {@code ResponseDTO} con las coordenadas (x,y) y el mensaje del emisor
	 */
	ResponseDTO getInformationSplit();

	/**
	 * Guarda la informacion del satelite en DB.
	 * 
	 * @param satelliteDTO
	 * @return Un {@code String} con un mensaje de confirmacion.
	 * @throws Exception Si el nombre del satelite no es valido o si se presenta un
	 *                   error al almacenar la informacion en DB.
	 */
	String saveInformation(SatelliteDTO satelliteDTO) throws Exception;

}
