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

		List<Predicate> query = new ArrayList<>();
		Predicate p2 = new Predicate(Grammar.NOT.getSymbol() + "F", Arrays.asList("Joe", "Jill"));
		query.add(p2);

		Assert.assertNull(algorithmServiceImpl.matchAndResolve(query, sentence));
	}

	@Test
	public void testMatchAndResolve_whenPosCase() {
		// F(Joe,Jill) vs F(Ken,Jill);
		AlgorithmServiceImpl algorithmServiceImpl = new AlgorithmServiceImpl();
		List<Predicate> sentence = new ArrayList<>();
		Predicate p1 = new Predicate("F", Arrays.asList("x", "y"));
		sentence.add(p1);

		List<Predicate> query = new ArrayList<>();
		Predicate p2 = new Predicate(Grammar.NOT.getSymbol() + "F", Arrays.asList("Joe", "Jill"));
		query.add(p2);

		Assert.assertEquals(0, algorithmServiceImpl.matchAndResolve(query, sentence).size());
	}
}
