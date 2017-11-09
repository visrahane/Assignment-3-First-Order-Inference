/**
 *
 */
package com.vis.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vis.constants.Grammar;
import com.vis.models.Predicate;

/**
 * @author vis
 *
 */
public class AlgorithmUtil {

	public static List<Predicate> negate(Predicate query) {
		List<Predicate> sentence = new ArrayList<>();
		Predicate p = new Predicate();
		p.setArgumentList(query.getArgumentList());
		if (query.getName().contains(Grammar.NOT.getSymbol())) {
			p.setName(query.getName().replaceAll(Grammar.NOT.getSymbol(), Grammar.BLANK.getSymbol()));
			sentence.add(p);
		} else {
			p.setName(Grammar.NOT.getSymbol() + query.getName());
			sentence.add(p);
		}
		return sentence;
	}

	public static List<Predicate> resolve(List<Predicate> sentence, Predicate query) {
		List<Predicate> resolvedSentence = new ArrayList<>();
		sentence.forEach((predicate) -> {
			resolvedSentence.add(new Predicate(predicate.getName(), predicate.getArgumentList()));
		});

		Predicate resolvedP = resolvedSentence.remove(resolvedSentence.indexOf(query));
		Map<String, String> substitutionMap = new LinkedHashMap<>();
		for (int i = 0; i < resolvedP.getArgumentList().size(); i++) {
			substitutionMap.put(resolvedP.getArgs(i), query.getArgs(i));
		}
		resolvedSentence.forEach((term) -> {
			//substitute in sentence and query and append
			for (int i = 0; i < term.getArgumentList().size(); i++) {
				if (substitutionMap.containsKey(term.getArgs(i))) {
					term.setArgs(i, substitutionMap.get(term.getArgs(i)));
				}
			}

		});
		return resolvedSentence;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
