package com.meli.restservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.restservice.dto.PositionDTO;
import com.meli.restservice.dto.ResponseDTO;
import com.meli.restservice.dto.SatelliteDTO;
import com.meli.restservice.entity.Satellite;
import com.meli.restservice.mapper.SatelliteMapper;
import com.meli.restservice.repository.SatelliteRepository;
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
	@Autowired
	SatelliteMapper satelliteMapper;
	@Autowired
	SatelliteRepository satelliteRepository;

	private static final Logger logger = LoggerFactory.getLogger(IntelligenceService.class);

	@Override
	public ResponseDTO getInformation(SatelliteDTO[] satellites) {
		logger.info("getInformation - satellites: {}", Arrays.toString(satellites));

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

	@Override
	public ResponseDTO getInformationSplit() {
		logger.info("getInformationSplit");

		// Se recupera la informacion de los satelites de la DB
		Iterable<Satellite> satellites;
		satellites = satelliteRepository.findAll();
		satellites.forEach(s -> logger.info("getInformationSplit - satellite: {}", s));

		// Cast a List<Satellite>
		List<Satellite> result = StreamSupport.stream(satellites.spliterator(), false).collect(Collectors.toList());

		// Se limpia tabla
		satelliteRepository.deleteAll();

		// Cast to Satellite[]
		Satellite[] satellitesArray = result.stream().toArray(Satellite[]::new);
		// Cast to SatelliteDTO[]
		return getInformation(satelliteMapper.satelliteArrayToSatelliteDTOArray(satellitesArray));
	}

	/**
	 * Valida si se cuenta con la informacion de los 3 satelites, necesaria para
	 * poder descifrar el mensaje y obtener la ubicacion.
	 * 
	 * @param satellites
	 */
	private void validateInformation(SatelliteDTO[] satellites) {
		logger.info("validateInformation");

		if (satellites.length < 3) {
			throw new IllegalArgumentException("Se necesita la informacion de los 3 satelites.");
		}
	}

}
