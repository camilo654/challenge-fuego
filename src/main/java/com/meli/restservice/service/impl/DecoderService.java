package com.meli.restservice.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meli.restservice.dto.SatelliteDTO;
import com.meli.restservice.service.IDecoderService;

/**
 *
 * @author cctorresr
 */
@Service
public class DecoderService implements IDecoderService {

	private static final Logger logger = LoggerFactory.getLogger(DecoderService.class);

	@Override
	public String getMessage(SatelliteDTO[] satellites) {
		logger.info("getMessage()");

		String[] messageInKenobi = satellites[0].getMessage();
		String[] messageInSkywalker = satellites[1].getMessage();
		String[] messageInSato = satellites[2].getMessage();

		return decodeMessage(messageInKenobi, messageInSkywalker, messageInSato);
	}

	/**
	 * Decifra mensaje a partir de mensajes captados por los satelites
	 * 
	 * @param messageInKenobi mensaje captado por Kenobi
	 * @param messageInSkywalker mensaje captado por Skywalker
	 * @param messageInSato mensaje captado por Sato
	 * @return un {@code String} construido a partir de los {@code params}
	 */
	private String decodeMessage(String[] messageInKenobi, String[] messageInSkywalker, String[] messageInSato) {
		logger.info("decodeMessage() - messageInKenobi: {}", Arrays.toString(messageInKenobi));
		logger.info("decodeMessage() - messageInSkywalker: {}", Arrays.toString(messageInSkywalker));
		logger.info("decodeMessage() - messageInSato: {}", Arrays.toString(messageInSato));
		String[] message = new String[messageInKenobi.length];

		for (int i = 0; i < message.length; i++) {
			if (!messageInKenobi[i].trim().equals("")) {
				message[i] = messageInKenobi[i].trim();
			} else if (!messageInSkywalker[i].trim().equals("")) {
				message[i] = messageInSkywalker[i].trim();
			} else if (!messageInSato[i].trim().equals("")) {
				message[i] = messageInSato[i].trim();
			}
		}

		logger.info("exit decodeMessage()");
		return String.join(" ", message).trim();
	}

}
