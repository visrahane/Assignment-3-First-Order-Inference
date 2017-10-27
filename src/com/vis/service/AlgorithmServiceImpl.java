/**
 *
 */
package com.vis.service;

import java.util.List;

import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.util.AlgorithmUtil;

/**
 * @author Vis
 *
 */
public class AlgorithmServiceImpl implements AlgorithmService {

	@Override
	public OutputData runAlgorithm(InputData inputData) {
		OutputData outputData = new OutputData();
		inputData.getQueries().forEach((query) -> resolve(AlgorithmUtil.negate(query), inputData.getKnowledgeBase()));
		return outputData;
	}

	private void resolve(String query, List<List<String>> kb) {
	}

}
