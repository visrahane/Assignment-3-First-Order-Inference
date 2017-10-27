/**
 *
 */
package com.vis.models;

import java.util.List;

/**
 * @author Vis
 *
 */
public class InputData {

	private int noOfQueries;

	private List<String> queries;

	private int knowledgeBaseSize;

	private List<List<String>> knowledgeBase;

	public int getNoOfQueries() {
		return noOfQueries;
	}

	public void setNoOfQueries(int noOfQueries) {
		this.noOfQueries = noOfQueries;
	}

	public List<String> getQueries() {
		return queries;
	}

	public void setQueries(List<String> queries) {
		this.queries = queries;
	}

	public List<List<String>> getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(List<List<String>> knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InputData [noOfQueries=").append(noOfQueries).append(", queries=").append(queries)
		.append(", knowledgeBaseSize=").append(knowledgeBaseSize).append(", knowledgeBase=")
		.append(knowledgeBase).append("]");
		return builder.toString();
	}

	public int getKnowledgeBaseSize() {
		return knowledgeBaseSize;
	}

	public void setKnowledgeBaseSize(int knowledgeBaseSize) {
		this.knowledgeBaseSize = knowledgeBaseSize;
	}


}
