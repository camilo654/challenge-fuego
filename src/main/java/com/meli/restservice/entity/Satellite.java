package com.meli.restservice.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
*
* @author cctorres
*/
@Data
@Entity
public class Satellite {

	@Id
	private Long id;
	private String name;
	private double distance;
	private String message;

}