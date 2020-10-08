package com.java.algorithm.postfix;

import java.util.ArrayList;

public class PostfixTransform implements AbstractTransform<String> {

	@Override
	public String transform(String str) {
		String[] expression = str.split("");
		ArrayList<String> stack = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < expression.length; i++) {
			String s = expression[i];
			Integer num;
			try {
				num = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				num = -1;
			}
			
			if (num != -1) result.add(s);
			else if (!s.equals(")")) {
				
				if (stack.size() == 0) stack.add(0, s);
				else {
					int pCh = this.getPriority(s, false);
					int pTop = this.getPriority(stack.get(0), true);
					if (pCh > pTop) stack.add(0, s);
					else {
						result.add(stack.get(0));
						stack.remove(0);
						i -= 1;
					}
				}
			} else {
				while (stack.size() > 0 && !stack.get(0).equals("(")) {
					result.add(stack.get(0));
					stack.remove(0);
				}
				if (stack.size() > 0) stack.remove(0);
			}
		}
		
		System.out.println(stack);
		while (stack.size() > 0) {
			result.add(stack.get(0));
			stack.remove(0);
		}
		
		return result.stream().reduce("", (acc, s) -> acc + s);
	}
	
	private Integer getPriority(String s, boolean insideStack) {
		if (s.equals("*") || s.equals("/")) return 2;
		else if (s.equals("+") || s.equals("-")) return 1;
		else if (s.equals("^")) return (insideStack ? 3 : 4);
		else if (s.equals("("))  return (insideStack ? 0 : 5);
		return -1;
	}
}