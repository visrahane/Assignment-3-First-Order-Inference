/**
 *
 */
package com.vis.service;

import java.util.ArrayList;
import java.util.List;

import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.models.Predicate;
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

	private boolean resolve(List<Predicate> query, List<List<Predicate>> kb) {
		List<List<Predicate>> currentKB = new ArrayList<>(kb);
		// addQueryToKB(query, currentKB);
		if (hasEmptyClause(currentKB)) {
			return true;
		} else {
			List<Predicate> resolvedSentence;
			for (List<Predicate> sentence : kb) {
				if((resolvedSentence=matchAndResolve(query, sentence))!=null){
					resolve(resolvedSentence, currentKB);
				}

			}
		}

		return false;
	}

	public List<Predicate> matchAndResolve(List<Predicate> query, List<Predicate> sentence) {
		for (Predicate predicate : query) {
			Predicate queryLiteral = AlgorithmUtil.negate(predicate).get(0);
			if(sentence.contains(queryLiteral)){
				//resolve
				List<Predicate> resolvedSentence = AlgorithmUtil.resolve(sentence, queryLiteral);
				// recursion here
				return resolvedSentence;
			}
		}
		return null;
	}

	private String performResolution(List<String> list, List<String> sentence) {
		return null;
		// sentence.remove(AlgorithmUtil.negate(list));

	}

	private boolean hasEmptyClause(List<List<Predicate>> currentKB) {
		// TODO Auto-generated method stub
		return false;
	}

	private void addSentenceToKB(List<String> sentence, List<List<String>> currentKB) {
		currentKB.add(sentence);
	}

}
