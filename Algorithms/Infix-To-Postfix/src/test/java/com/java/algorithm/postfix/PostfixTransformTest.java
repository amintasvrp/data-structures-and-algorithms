package com.java.algorithm.postfix;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostfixTransformTest {
	
	private Map<String, String> testExpressions;
	
	@Before
	public void setUp() {
		testExpressions = new HashMap<>();
		testExpressions.put("2+7*5", "275*+");
		testExpressions.put("3*3/(7+1)", "33*71+/");
		testExpressions.put("5+(6-2)*9+3^(7-1)", "562-9*+371-^+");
		testExpressions.put("(5-4-1)+9/5/2-7/1/7", "54-1-95/2/+71/7/-");
	}

	@Test
	public void test() {
		PostfixTransform postfix = new PostfixTransform();
		for (String key : this.testExpressions.keySet()) {
			Assert.assertEquals(this.testExpressions.get(key), postfix.transform(key));
		}
	}

}
