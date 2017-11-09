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

	private List<Predicate> queries;

	private int knowledgeBaseSize;

	private List<List<Predicate>> knowledgeBase;

	public int getNoOfQueries() {
		return noOfQueries;
	}

	public void setNoOfQueries(int noOfQueries) {
		this.noOfQueries = noOfQueries;
	}

	public List<Predicate> getQueries() {
		return queries;
	}

	public void setQueries(List<Predicate> queries) {
		this.queries = queries;
	}

	public List<List<Predicate>> getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(List<List<Predicate>> knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	public int getKnowledgeBaseSize() {
		return knowledgeBaseSize;
	}

	public void setKnowledgeBaseSize(int knowledgeBaseSize) {
		this.knowledgeBaseSize = knowledgeBaseSize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InputData [noOfQueries=").append(noOfQueries).append(", queries=").append(queries)
		.append(", knowledgeBaseSize=").append(knowledgeBaseSize).append(", knowledgeBase=")
		.append(knowledgeBase).append("]");
		return builder.toString();
	}

}
