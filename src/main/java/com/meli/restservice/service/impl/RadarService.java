package com.meli.restservice.service.impl;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.restservice.dto.SatelliteDTO;
import com.meli.restservice.service.IRadarService;

@Service
public class RadarService implements IRadarService{

	private static final double[] KENOBI_POSITION = { -500, -200 };
	private static final double[] SKYWALKER_POSITION = { 100.0, -100.0 };
	private static final double[] SATO_POSITION = { 500.0, 100.0 };

	private static final Logger logger = LoggerFactory.getLogger(RadarService.class);

	@Override
	public float[] getLocation(SatelliteDTO[] satellites) {
		logger.info("getLocation()");

		float distanceToKenobi = 0f;
		float distanceToSkywalker = 0f;
		float distanceToSato = 0f;

		for (SatelliteDTO satelliteDTO : satellites) {
			if (satelliteDTO.getName().equals("kenobi")) {
				distanceToKenobi = satelliteDTO.getDistance();
			} else if (satelliteDTO.getName().equals("skywalker")) {
				distanceToSkywalker = satelliteDTO.getDistance();
			} else if (satelliteDTO.getName().equals("sato")) {
				distanceToSato = satelliteDTO.getDistance();
			}
		}

		return trilateration(distanceToKenobi, distanceToSkywalker, distanceToSato);
	}

	/**
	 * Solves a trilateration problem using a nonlinear least squares optimizer.
	 * 
	 * @param distanceToKenobi
	 * @param distanceToSkywalker
	 * @param distanceToSato
	 * @return returns the coordinate of the point
	 */
	private float[] trilateration(float distanceToKenobi, float distanceToSkywalker, float distanceToSato) {

		logger.info("trilateration() - distanceToKenobi: {}", distanceToKenobi);
		logger.info("trilateration() - distanceToSkywalker: {}", distanceToSkywalker);
		logger.info("trilateration() - distanceToSato: {}", distanceToSato);

		double[][] positions = new double[][] { KENOBI_POSITION, SKYWALKER_POSITION, SATO_POSITION };

		double[] distances = new double[] { distanceToKenobi, distanceToSkywalker, distanceToSato };

		// coordinate calculation
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();

		// the answer
		double[] centroid = optimum.getPoint().toArray();

		// conversion to Float
		float x = (float) centroid[0];
		float y = (float) centroid[1];
		float[] position = { x, y };

		logger.info("exit trilateration() - position: {}", position);
		return position;
	}
	
}
