package com.meli.restservice.service;

import com.meli.restservice.dto.SatelliteDTO;

/**
 *
 * @author cctorresr
 */
public interface IRadarService {

	/**
	 * Calcula las coordenadas (x,y) del emisor del mensaje, a partir de las
	 * distancias de los satelites a la nave portacarga
	 * 
	 * @param satellites que contiene las distancias de cada satelite al emisor el
	 *                   mensaje
	 * @return {@code float[]} con las coordenadas x,y del emisor del mensaje
	 */
	float[] getLocation(SatelliteDTO[] satellites);
}
