package com.meli.restservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.meli.restservice.dto.SatelliteDTO;
import com.meli.restservice.entity.Satellite;

/**
 *
 * @author cctorresr
 */
@Mapper(componentModel = "spring")
public interface SatelliteMapper {

	SatelliteMapper INSTANCE = Mappers.getMapper(SatelliteMapper.class);

	/**
	 * Mapea satelliteDTO a Satellite entity
	 * 
	 * @param satelliteDTO
	 * @return Satellite entity
	 */
	@Mapping(expression = "java( java.util.Arrays.toString(satelliteDTO.getMessage()) )", target = "message")
	Satellite satelliteDTOToSatellite(SatelliteDTO satelliteDTO);

	/**
	 * Mapea Satellite entity a SatelliteDTO
	 * 
	 * @param satellite entity
	 * @return SatelliteDTO
	 */
	@Mapping(expression = "java( satellite.getMessage().substring(1, satellite.getMessage().length() - 1).split(\",\") )", target = "message")
	SatelliteDTO satelliteToSatelliteDTO(Satellite satellite);

	/**
	 * Mapea Satellite[] a SatelliteDTO[]
	 * 
	 * @param satelliteArray
	 * @return SatelliteDTO list
	 */
	SatelliteDTO[] satelliteArrayToSatelliteDTOArray(Satellite[] satelliteArray);

}
