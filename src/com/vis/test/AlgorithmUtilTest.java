/**
 *
 */
package com.vis.test;

import org.junit.Assert;
import org.junit.Test;

import com.vis.util.AlgorithmUtil;

/**
 * @author vis
 *
 */
public class AlgorithmUtilTest {

	@Test
	public void testNegate_shouldNegate() {
		Assert.assertEquals("A", AlgorithmUtil.negate("~A"));
		Assert.assertEquals("~A", AlgorithmUtil.negate("A"));
	}

}
