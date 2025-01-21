# Bah - A Simple Programming Language Implementation

This project is my personal learning journey into programming language design and implementation. Through building Bah, I'm exploring the fundamental concepts of how programming languages work under the hood.
## How It Works

### Lexical Analysis

The Lexer converts the input string into a series of tokens, such as numbers, operators, and keywords (`let`,...). This is the first step in the language pipeline and ensures the input can be processed by the parser.

### Parsing

The Parser processes the tokens to build an Abstract Syntax Tree (AST). The AST represents the structure of the program in a hierarchical manner. For example:

```text
let x = 5 + 3;
```
Would be represented as an assignment node with a binary operation node `(5 + 3)` as its value.

### Interpretation
The Interpreter walks through the AST and evaluates its nodes:

1. Assignments: Stores the variables in an environment map.
2. Expressions: Computes the results using the operators and values provided.
