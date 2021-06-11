package com.meli.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.restservice.service.IRadarService;

@RestController
public class CentralCommandController {
	
	@Autowired
	IRadarService radarService;

	@GetMapping("/getLocation")
	public String getLocation() {
		return "Coordenadas: " + radarService.getLocation();
	}
}
