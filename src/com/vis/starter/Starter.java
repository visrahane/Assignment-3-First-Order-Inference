/**
 *
 */
package com.vis.starter;

import java.util.List;
import java.util.Map;

import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.reader.InputReader;
import com.vis.service.AlgorithmService;
import com.vis.service.AlgorithmServiceImpl;
import com.vis.util.DataParserUtil;

/**
 * @author Vis
 *
 */
public class Starter {

	public static Map<Integer, Float> calibrationMap;

	public static void main(String[] args) {
		long curretTime = System.currentTimeMillis();
		InputData inputData = readInput();
		OutputData outputData = runAlgorithm(inputData);
		// displayOutput(outputData);
		System.out.println(System.currentTimeMillis() - curretTime);
	}

	/*private static void displayOutput(OutputData outputData) {
		List<String> outputDataList = generateOutput(outputData);
		OutputWriter outputWriter = new OutputWriter("output.txt");
		outputWriter.writeFile(outputDataList);
	}*/

	public static OutputData runAlgorithm(InputData inputData) {
		AlgorithmService algorithmService = new AlgorithmServiceImpl();
		OutputData outputData = algorithmService.runAlgorithm(inputData);
		return outputData;
	}

	/*private static List<String> generateOutput(OutputData outputData) {
		List<String> outputDataList = new ArrayList<>();
		outputDataList.add();
		return outputDataList;
	}*/

	private static InputData readInput() {
		InputReader inputReader = new InputReader("input.txt");
		List<String> inputDataList = inputReader.readIntoList();
		InputData inputData = DataParserUtil.parseInputData(inputDataList);
		return inputData;
	}

}
