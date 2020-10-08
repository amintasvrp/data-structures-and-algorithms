#  Infix to Postfix Expression Converter

This algorithm takes an expression in infix form and transforms it to its postfix equivalent.

The project consists of the following files:

- **AbstractTransform.java**: It's an interface that holds an abstract method called `transform()` which takes an argument of type T.
- **PostfixTransform.java**: A class that implements the AbstractTransform interface. Its `transform()` method takes an string and returns an string. It uses the concept of a stack to sort the order of operations based on its corresponding priority.
- **PostfixTransformTest.java**: A JUnit class that holds some useful test cases.
