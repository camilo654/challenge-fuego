package com.meli.restservice.dto;

import lombok.Data;

/**
 *
 * @author cctorresr
 */
@Data
public class PositionDTO {

	private float x;
	private float y;

	public PositionDTO() {
	}

	public PositionDTO(float x, float y) {
		this.x = x;
		this.y = y;
	}

}