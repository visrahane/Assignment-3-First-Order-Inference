/**
 *
 */
package com.vis.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.vis.constants.Grammar;
import com.vis.models.Predicate;
import com.vis.util.AlgorithmUtil;

/**
 * @author vis
 *
 */
public class AlgorithmUtilTest {

	@Test
	public void testNegate_shouldNegate() {
		Predicate p = new Predicate();
		p.setName("~A");
		Assert.assertEquals("A", AlgorithmUtil.negate(p).getName());
		p.setName("A");
		Assert.assertEquals("~A", AlgorithmUtil.negate(p).getName());
	}

	@Test
	public void testResolve_when1Arg2Symbols() {
		// F(Joe) vs ~F(x)|G(x)
		List<Predicate> sentence = getSentence();
		Predicate query = getPredicate();
		List<Predicate> resolvedSentence = AlgorithmUtil.resolve(sentence, query);
		Assert.assertEquals(resolvedSentence.get(0).getArgumentList(), Arrays.asList("Joe"));
	}

	@Test
	public void testResolve_when1Arg3Symbols() {
		// F(Joe) vs ~F(x)|G(x)|G(y)
		List<Predicate> sentence = getSentence();
		Predicate p1 = new Predicate("G", Arrays.asList("y"));
		sentence.add(p1);
		Predicate query = getPredicate();
		List<Predicate> resolvedSentence = AlgorithmUtil.resolve(sentence, query);
		Assert.assertEquals(resolvedSentence.get(1).getArgumentList(), Arrays.asList("y"));
	}

	@Test
	public void testResolve_when2Arg2Predicate() {
		// F(Joe,Jill) vs ~F(x,y)|G(x,y) => G(Joe,Jill)
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate("F", Arrays.asList("x", "y"));
		Predicate p2 = new Predicate("G", Arrays.asList("x", "y"));
		sentence.add(p1);
		sentence.add(p2);

		Predicate query = new Predicate("F", Arrays.asList("Joe", "Jill"));

		List<Predicate> resolvedSentence = AlgorithmUtil.resolve(sentence, query);
		Assert.assertEquals(resolvedSentence.get(0).getArgumentList(), query.getArgumentList());
	}

	@Test
	public void testResolve_when2Arg3Predicate() {
		// F(Joe,Jill) vs ~F(x,y)|G(x,y)|G(x,z)|G(z,y) => G(Joe,Jill)|G(Joe,z)|G(z,Jill)
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate("F", Arrays.asList("x", "y"));
		Predicate p2 = new Predicate("G", Arrays.asList("x", "y"));
		Predicate p3 = new Predicate("G", Arrays.asList("x", "z"));
		Predicate p4 = new Predicate("G", Arrays.asList("z", "y"));
		sentence.add(p1);
		sentence.add(p2);
		sentence.add(p3);
		sentence.add(p4);

		Predicate query = new Predicate("F", Arrays.asList("Joe", "Jill"));

		List<Predicate> resolvedSentence = AlgorithmUtil.resolve(sentence, query);
		Assert.assertEquals(resolvedSentence.get(0).getArgumentList(), query.getArgumentList());
	}

	// ----------------

	@Test
	public void testResolveFully_when1Arg2Symbols() {
		// F(Joe) vs F(x)|~G(x) --> ~G(Joe)
		List<Predicate> sentence = getSentence();
		Predicate query = getPredicate();
		List<Predicate> querySentence = new ArrayList<Predicate>();
		querySentence.add(query);
		List<Predicate> resolvedSentence = AlgorithmUtil.resolveFully(sentence, query, querySentence);
		Assert.assertEquals(resolvedSentence.get(0).getArgumentList(), Arrays.asList("Joe"));
	}

	@Test
	public void testResolveFully_when1Arg3Symbols() {
		// F(Joe) vs ~F(x)|~G(x)|G(y)
		List<Predicate> sentence = getSentence();
		Predicate p1 = new Predicate("G", Arrays.asList("y"));
		sentence.add(p1);
		Predicate query = getPredicate();
		List<Predicate> querySentence = new ArrayList<Predicate>();
		querySentence.add(query);
		List<Predicate> resolvedSentence = AlgorithmUtil.resolveFully(sentence, query, querySentence);
		Assert.assertEquals(resolvedSentence.get(1).getArgumentList(), Arrays.asList("y"));
	}

	@Test
	public void testResolveFully_when2Arg2Predicate() {
		// F(Joe,Jill) vs ~F(x,y)|G(x,y) => G(Joe,Jill)
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate("F", Arrays.asList("x", "y"));
		Predicate p2 = new Predicate("G", Arrays.asList("x", "y"));
		sentence.add(p1);
		sentence.add(p2);

		Predicate query = new Predicate("F", Arrays.asList("Joe", "Jill"));
		List<Predicate> querySentence = new ArrayList<Predicate>();
		querySentence.add(query);
		List<Predicate> resolvedSentence = AlgorithmUtil.resolveFully(sentence, query, querySentence);
		Assert.assertEquals(resolvedSentence.get(0).getArgumentList(), query.getArgumentList());
	}

	@Test
	public void testResolveFully_when2Arg3Predicate() {
		// F(Joe,Jill) vs ~F(x,y)|G(x,y)|G(x,z)|G(z,y) =>
		// G(Joe,Jill)|G(Joe,z)|G(z,Jill)
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate("F", Arrays.asList("x", "y"));
		Predicate p2 = new Predicate("G", Arrays.asList("x", "y"));
		Predicate p3 = new Predicate("G", Arrays.asList("x", "z"));
		Predicate p4 = new Predicate("G", Arrays.asList("z", "y"));
		sentence.add(p1);
		sentence.add(p2);
		sentence.add(p3);
		sentence.add(p4);

		Predicate query = new Predicate("F", Arrays.asList("Joe", "Jill"));
		List<Predicate> querySentence = new ArrayList<Predicate>();
		querySentence.add(query);

		List<Predicate> resolvedSentence = AlgorithmUtil.resolveFully(sentence, query, querySentence);
		Assert.assertEquals(resolvedSentence.get(0).getArgumentList(), query.getArgumentList());
	}

	@Test
	public void testResolveFully_when2Arg2Sentences2Predicate() {
		// ~Parent(Liz,y|~Ancestor(y,Billy) vs ~Mother(x,y)|Parent(x,y) =>
		// ~Ancestor(y,Billy)| ~Mother(x,y)
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate("Parent", Arrays.asList("Liz", "y"));
		Predicate p2 = new Predicate("~Ancestor", Arrays.asList("y", "Billy"));
		sentence.add(p1);
		sentence.add(p2);

		Predicate query1 = new Predicate("Parent", Arrays.asList("x", "y"));
		Predicate query2 = new Predicate("~Mother", Arrays.asList("x", "y"));
		List<Predicate> querySentence = new ArrayList<Predicate>();
		querySentence.add(query1);
		querySentence.add(query2);
		List<Predicate> resolvedSentence = AlgorithmUtil.resolveFully(sentence, query1, querySentence);
		Assert.assertEquals(resolvedSentence.get(0).getName(), p2.getName());
	}

	private Predicate getPredicate() {
		Predicate p = new Predicate();
		p.setName("F");
		p.setArgumentList(Arrays.asList("Joe"));
		return p;
	}

	private List<Predicate> getSentence() {
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate();
		p1.setName("F");
		p1.setArgumentList(Arrays.asList("x"));
		Predicate p2 = new Predicate();
		p2.setName(Grammar.NOT.getSymbol() + "G");
		p2.setArgumentList(Arrays.asList("x"));
		sentence.add(p1);
		sentence.add(p2);
		return sentence;

	}

}
