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
		for(Predicate query :inputData.getQueries()){
			List<Predicate>querySentence=new ArrayList<>(1);
			try {
				querySentence.add(AlgorithmUtil.negate(query));
				List<List<Predicate>> kb = new ArrayList<>(inputData.getKnowledgeBase());
				kb.add(querySentence);
				outputData.addAnswer(resolve(querySentence, kb));
			} catch (Error e) {
				// TODO Auto-generated catch block
				outputData.addAnswer(false);
			}
		}
		return outputData;
	}

	private boolean resolve(List<Predicate> query, List<List<Predicate>> kb) throws Error {
		// List<List<Predicate>> currentKB = new ArrayList<>(kb);
		if (isResolved(query)) {
			return true;
		} else {
			List<Predicate> resolvedSentence;
			boolean flag = false;
			for (List<Predicate> sentence : kb) {
				if ((resolvedSentence = matchAndResolve(query, sentence)) != null) {
					if (kb.contains(resolvedSentence)) {
						continue;
					}
					if (resolve(resolvedSentence, kb)) {
						flag = true;
						break;
					}
				}
			}

			if (flag) {
				return true;
			}
		}

		return false;
	}

	public List<Predicate> matchAndResolve(List<Predicate> query, List<Predicate> sentence) throws Error {
		List<Predicate> resolvedSentence = null;
		for (Predicate predicate : query) {
			Predicate queryLiteral = AlgorithmUtil.negate(predicate);
			if (sentence.contains(queryLiteral)) {
				//resolve
				resolvedSentence = AlgorithmUtil.resolveFully(sentence, queryLiteral, query);
			}
		}

		return resolvedSentence;
	}

	private String performResolution(List<String> list, List<String> sentence) {
		return null;
		// sentence.remove(AlgorithmUtil.negate(list));

	}

	private boolean isResolved(List<Predicate> query) {
		// TODO Auto-generated method stub
		return query.size() == 0 ? true : false;
	}

	private void addSentenceToKB(List<String> sentence, List<List<String>> currentKB) {
		currentKB.add(sentence);
	}

}
