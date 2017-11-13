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
import com.vis.service.AlgorithmServiceImpl;


/**
 * @author Vis
 *
 */
public class AlgorithmServiceTest {

	@Test
	public void testMatchAndResolve_whenNegCase() {
		// F(Joe,Jill) vs F(Ken,Jill);
		AlgorithmServiceImpl algorithmServiceImpl = new AlgorithmServiceImpl();
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate("F", Arrays.asList("Ken", "Jill"));
		sentence.add(p1);

		Predicate p2 = new Predicate(Grammar.NOT.getSymbol() + "F", Arrays.asList("Joe", "Jill"));
		List<Predicate> predicate = new ArrayList<>();
		predicate.add(p2);
		Assert.assertNull(algorithmServiceImpl.matchAndResolve(predicate, sentence));
	}

	@Test
	public void testMatchAndResolve_whenPosCase() {
		// F(Joe,Jill) vs F(Ken,Jill);
		AlgorithmServiceImpl algorithmServiceImpl = new AlgorithmServiceImpl();
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate("F", Arrays.asList("x", "y"));
		sentence.add(p1);

		Predicate p2 = new Predicate(Grammar.NOT.getSymbol() + "F", Arrays.asList("Joe", "Jill"));
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(p2);
		Assert.assertEquals(0, algorithmServiceImpl.matchAndResolve(predicates, sentence).size());
	}

	@Test
	public void testMatchAndResolve_when2Args() {
		// D(Alice,y) vs ~C(x,y) | ~D(x,Alice) is ~C(Alice, Alice)
		AlgorithmServiceImpl algorithmServiceImpl = new AlgorithmServiceImpl();
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate("D", Arrays.asList("Alice", "y"));
		Predicate p2 = new Predicate("C", Arrays.asList("x", "y"));
		sentence.add(p1);
		sentence.add(p2);

		Predicate query = new Predicate(Grammar.NOT.getSymbol() + "D", Arrays.asList("x", "Alice"));
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(query);
		Assert.assertEquals("Alice", algorithmServiceImpl.matchAndResolve(predicates, sentence).get(0).getArgs(1));
	}

}
