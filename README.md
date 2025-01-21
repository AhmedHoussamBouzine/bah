# Bah - A Simple Programming Language

Bah is a simple programming language implemented to explore and understand how a programming language works, specifically focusing on the Lexer, Parser, and Interpreter components.

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
