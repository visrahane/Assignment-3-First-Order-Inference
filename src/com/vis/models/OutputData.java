/**
 *
 */
package com.vis.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vis
 *
 */
public class OutputData {

	private List<Boolean> answers;

	public OutputData() {
		answers = new ArrayList<>(1);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OutputData [answers=").append(answers).append("]");
		return builder.toString();
	}

	public List<Boolean> getAnswers() {
		return answers;
	}

	public void addAnswer(boolean resolve) {
		answers.add(resolve);
	}

}
