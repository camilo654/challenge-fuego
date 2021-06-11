package com.meli.restservice.dto;

import lombok.Data;

/**
 *
 * @author cctorresr
 */
@Data
public class ResponseDTO {

	private PositionDTO position;
	private String message;

	public ResponseDTO() {
	}

	public ResponseDTO(PositionDTO position, String message) {
		this.position = position;
		this.message = message;
	}

}