/**
 *
 */
package com.vis.util;

import com.vis.constants.Grammar;

/**
 * @author vis
 *
 */
public class AlgorithmUtil {

	public static String negate(String query) {
		if (query.contains(Grammar.NOT.getSymbol())) {
			return query.replaceAll(Grammar.NOT.getSymbol(), Grammar.BLANK.getSymbol());
		} else {
			return Grammar.NOT.getSymbol() + query;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
