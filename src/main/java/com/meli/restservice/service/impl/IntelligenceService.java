package com.meli.restservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.restservice.dto.PositionDTO;
import com.meli.restservice.dto.ResponseDTO;
import com.meli.restservice.dto.SatelliteDTO;
import com.meli.restservice.service.IDecoderService;
import com.meli.restservice.service.IIntelligenceService;
import com.meli.restservice.service.IRadarService;

/**
 *
 * @author cctorresr
 */
@Service
public class IntelligenceService implements IIntelligenceService {

	@Autowired
	IRadarService radarService;
	@Autowired
	IDecoderService decoderService;

	private static final Logger logger = LoggerFactory.getLogger(IntelligenceService.class);

	@Override
	public ResponseDTO getInformation(SatelliteDTO[] satellites) {
		logger.info("getInformation");

		// Se valida informacion recibida
		validateInformation(satellites);
		
		// Se calcula coordenada
		float[] positionArray = radarService.getLocation(satellites);
		float coordinateX = positionArray[0];
		float coordinateY = positionArray[1];
		PositionDTO position = new PositionDTO(coordinateX, coordinateY);

		// Se descifra mensaje
		String message = decoderService.getMessage(satellites);

		logger.info("getInformation - position: {}", position);
		logger.info("getInformation - message: {}", message);
		return new ResponseDTO(position, message);
	}

	private void validateInformation(SatelliteDTO[] satellites) {
		logger.info("validateInformation");
		
		if(satellites.length < 3) {
			throw new IllegalArgumentException("Se necesita la informacion de los 3 satelites.");
		}
	}

}
