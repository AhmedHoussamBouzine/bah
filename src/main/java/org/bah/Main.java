package org.bah;

import org.bah.lexer.Lexer;
import org.bah.parser.Parser;
import org.bah.interpreter.Interpreter;
import org.bah.parser.ast.Node;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter();

        System.out.println("Welcome to your custom programming language!");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            try {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                // Exit condition
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // Ignore empty input
                if (input.isEmpty()) {
                    continue;
                }

                // Create a lexer and parse the input
                Lexer lexer = new Lexer(input);
                Parser parser = new Parser(lexer);
                Node ast = parser.parse();

                // Interpret the AST and print the result
                double result = interpreter.interpret(ast);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                // Print any errors that occur during parsing or interpretation
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Goodbye!");
    }
}
