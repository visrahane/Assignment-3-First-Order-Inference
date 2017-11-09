/**
 *
 */
package com.vis.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vis
 *
 */
public class Predicate {

	private String name;

	private List<String> argumentList;

	public Predicate(String string, List<String> asList) {
		argumentList = new ArrayList<>(1);
		asList.forEach((arg) -> argumentList.add(arg));
		name = string;

	}

	public Predicate() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getArgumentList() {
		return argumentList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Predicate [name=").append(name).append(", argumentList=").append(argumentList).append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Predicate other = (Predicate) obj;
		if (argumentList == null) {
			if (other.argumentList != null)
				return false;
		}
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		//args equality
		String argList1, argList2;
		for(int i=0;i<argumentList.size();i++){
			argList1=argumentList.get(i);
			argList2=other.argumentList.get(i);
			if (Character.isUpperCase(argList1.charAt(0)) && Character.isUpperCase(argList2.charAt(0))
					&& !argList1.equals(argList2))
			{
				return false;
			}
		}

		return true;
	}

	public String getArgs(int index) {
		return argumentList.get(index);
	}

	public void setArgumentList(List<String> argumentList) {
		this.argumentList = argumentList;
	}

	public void setArgs(int index, String argument) {
		argumentList.set(index, argument);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
