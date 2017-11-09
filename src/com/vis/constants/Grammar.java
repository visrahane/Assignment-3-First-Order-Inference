/**
 *
 */
package com.vis.constants;

/**
 * @author vis
 *
 */
public enum Grammar {
	NOT("~"), BLANK(""), OPENING_BRACES("("), CLOSING_BRACES(")"), ARGUMENT_SPLITTER(",");
	private String symbol;

	private Grammar(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
