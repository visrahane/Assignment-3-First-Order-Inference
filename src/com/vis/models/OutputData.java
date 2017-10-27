/**
 *
 */
package com.vis.models;

/**
 * @author vis
 *
 */
public class OutputData {

	private boolean answer;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OutputData [answer=").append(answer).append("]");
		return builder.toString();
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

}
