/**
 *
 */
package com.vis.constants;

/**
 * @author vis
 *
 */
public enum Grammar {
	NOT("~"), BLANK("");
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
