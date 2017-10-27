/**
 *
 */
package com.vis.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vis.constants.IOConstants;
import com.vis.models.InputData;

/**
 * @author Vis
 *
 */
public class DataParserUtil {

	public static void main(String[] args) {

	}

	public static InputData parseInputData(List<String> inputDataList) {
		int counter = 0;
		InputData inputData = new InputData();
		inputData.setNoOfQueries(Integer.parseInt(inputDataList.get(counter++)));
		List<String> queries = new ArrayList<>();
		for (int i = 0; i < inputData.getNoOfQueries(); i++) {
			queries.add(inputDataList.get(counter++));
		}
		inputData.setQueries(queries);
		inputData.setKnowledgeBaseSize(Integer.parseInt(inputDataList.get(counter++)));

		List<List<String>> knowledgeBase = new ArrayList<>();
		for (int i = 0; i < inputData.getKnowledgeBaseSize(); i++) {
			String sentence = inputDataList.get(counter++);
			String predicates[] = sentence.split(IOConstants.WORD_SPLITTER);
			knowledgeBase.add(Arrays.asList(predicates));
		}
		return inputData;
	}

}
