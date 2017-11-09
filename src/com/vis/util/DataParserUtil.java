/**
 *
 */
package com.vis.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vis.constants.Grammar;
import com.vis.constants.IOConstants;
import com.vis.models.InputData;
import com.vis.models.Predicate;

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
		List<Predicate> queries = new ArrayList<>();
		for (int i = 0; i < inputData.getNoOfQueries(); i++) {
			String predicateStr = inputDataList.get(counter++);
			String[] arguments = getArguments(predicateStr);
			Predicate p = getPredicate(arguments, predicateStr);
			queries.add(p);
		}
		inputData.setQueries(queries);
		inputData.setKnowledgeBaseSize(Integer.parseInt(inputDataList.get(counter++)));

		List<List<Predicate>> knowledgeBase = new ArrayList<>();
		for (int i = 0; i < inputData.getKnowledgeBaseSize(); i++) {
			String sentence = inputDataList.get(counter++).replaceAll(IOConstants.SPACE, IOConstants.BLANK);
			String predicates[] = sentence.split(IOConstants.WORD_SPLITTER);
			List<Predicate> p = getPredicateList(predicates);
			knowledgeBase.add(p);
		}
		inputData.setKnowledgeBase(knowledgeBase);
		return inputData;
	}

	private static List<Predicate> getPredicateList(String[] predicates) {
		List<Predicate> predicateList = new ArrayList<>();
		for (String predicate : predicates) {
			String[] arguments = getArguments(predicate);
			Predicate p = getPredicate(arguments, predicate);
			predicateList.add(p);
		}
		return predicateList;
	}

	private static String[] getArguments(String predicate) {
		int startIndex = predicate.indexOf(Grammar.OPENING_BRACES.getSymbol()) + 1;
		int endIndex = predicate.indexOf(Grammar.CLOSING_BRACES.getSymbol());
		String[] arguments = predicate.substring(startIndex, endIndex).split(Grammar.ARGUMENT_SPLITTER.getSymbol());
		return arguments;
	}

	private static Predicate getPredicate(String[] arguments, String predicate) {
		Predicate p = new Predicate();
		String predicateName = predicate.substring(0, predicate.indexOf(Grammar.OPENING_BRACES.getSymbol()));
		p.setName(predicateName);
		p.setArgumentList(Arrays.asList(arguments));
		return p;
	}

}
