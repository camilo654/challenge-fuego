package com.meli.restservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.restservice.dto.ResponseDTO;
import com.meli.restservice.dto.SatellitesDTO;
import com.meli.restservice.service.IIntelligenceService;

/**
 *
 * @author cctorresr
 */
@RestController
@CrossOrigin(origins = "*")
public class CentralCommandController {

	@Autowired
	IIntelligenceService intelligenceService;

	private static final Logger logger = LoggerFactory.getLogger(CentralCommandController.class);

	/**
	 * Obtiene la ubicación de la nave portacarga y el mensaje que emite.
	 * 
	 * @param satellites que contienen la información de cada satelite
	 * @return
	 *         <p>
	 *         {@code ResponseEntity<ResponseDTO>} con {@code status 200}, con la
	 *         posicion y el mensaje descifrado en el body.
	 *         </p>
	 * 
	 *         <p>
	 *         En caso de que no se pueda determinar la posición o el mensaje, se
	 *         retorna un {@code status 500}
	 *         </p>
	 */
	@PostMapping(path = "/topsecret", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseDTO> getInformation(@RequestBody SatellitesDTO satellites) {
		logger.info("getInformation - satellites: {}", satellites);
		try {
			return new ResponseEntity<>(intelligenceService.getInformation(satellites.getSatellites()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("getInformation - error: {}", e);
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
