package com.vis.service;
/**
 * 
 */

import com.vis.models.InputData;
import com.vis.models.OutputData;

/**
 * @author vis
 *
 */
public interface AlgorithmService {
	OutputData runAlgorithm(InputData inputData);
}
