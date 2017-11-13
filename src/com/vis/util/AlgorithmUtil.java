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

	public static Predicate negate(Predicate query) throws Error {
		Predicate p = new Predicate();
		p.setArgumentList(query.getArgumentList());
		if (query.getName().contains(Grammar.NOT.getSymbol())) {
			p.setName(query.getName().replaceAll(Grammar.NOT.getSymbol(), Grammar.BLANK.getSymbol()));
		} else {
			p.setName(Grammar.NOT.getSymbol() + query.getName());
		}
		return p;
	}

	public static List<Predicate> resolve(List<Predicate> sentence, Predicate query) {
		List<Predicate> resolvedSentence = cloneSentence(sentence);

		Predicate resolvedP = resolvedSentence.remove(resolvedSentence.indexOf(query));
		Map<String, String> substitutionMap = prepareSubsMap(resolvedP, query);
		performSubstitution(resolvedSentence, substitutionMap);
		return resolvedSentence;
	}

	public static List<Predicate> resolveFully(List<Predicate> sentence, Predicate queryLiteral,
			List<Predicate> query) throws Error {
		List<Predicate> resolvedSentence = cloneSentence(sentence);
		List<Predicate> querySentence = cloneSentence(query);

		//remove from both - query & sentence
		Predicate resolvedP = removeResolvedPredicate(queryLiteral, resolvedSentence);
		Predicate queryP = removeResolvedPredicate(queryLiteral, querySentence);

		Map<String, String> substitutionMap = prepareSubsMap(resolvedP, queryP);

		performSubstitution(resolvedSentence, substitutionMap);
		performSubstitution(querySentence, substitutionMap);

		resolvedSentence.addAll(querySentence);
		return new ArrayList<Predicate>(resolvedSentence);
	}

	private static void performSubstitution(List<Predicate> resolvedSentence, Map<String, String> substitutionMap) {
		for (Predicate term : resolvedSentence) {
			// substitute in sentence and query and append
			for (int i = 0; i < term.getArgumentList().size(); i++) {
				if (substitutionMap.containsKey(term.getArgs(i))) {
					term.setArgs(i, substitutionMap.get(term.getArgs(i)));
				}
			}

		}
	}

	private static Map<String, String> prepareSubsMap(Predicate resolvedP, Predicate queryP) {
		Map<String, String> substitutionMap = new LinkedHashMap<>();
		for (int i = 0; i < resolvedP.getArgumentList().size(); i++) {
			String args1 = resolvedP.getArgs(i);
			String args2 = queryP.getArgs(i);
			if (Character.isUpperCase(args2.charAt(0))) {
				substitutionMap.put(args1, args2);
			} else {
				substitutionMap.put(args2, args1);
			}

		}
		return substitutionMap;
	}

	private static Predicate removeResolvedPredicate(Predicate queryLiteral, List<Predicate> resolvedSentence)
			throws Error {
		int indexOf = resolvedSentence.indexOf(queryLiteral);
		if (indexOf == -1) {
			indexOf = resolvedSentence.indexOf(AlgorithmUtil.negate(queryLiteral));
		}
		Predicate resolvedP = resolvedSentence.remove(indexOf);
		return resolvedP;
	}

	private static List<Predicate> cloneSentence(List<Predicate> sentence) {
		List<Predicate> resolvedSentence = new ArrayList<>();
		sentence.forEach((predicate) -> {
			resolvedSentence.add(new Predicate(predicate.getName(), predicate.getArgumentList()));
		});
		return resolvedSentence;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
