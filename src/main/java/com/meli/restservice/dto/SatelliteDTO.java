package com.meli.restservice.dto;

import lombok.Data;

@Data
public class SatelliteDTO {

	private String name;
	private float distance;
	private String[] message;

	public SatelliteDTO() {
	}

	public SatelliteDTO(String name, float distance, String[] message) {
		this.name = name;
		this.distance = distance;
		this.message = message;
	}

}
