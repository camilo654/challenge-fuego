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

}
