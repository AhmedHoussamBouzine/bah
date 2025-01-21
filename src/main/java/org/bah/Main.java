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
        StringBuilder output = new StringBuilder();

        // Initial text
        output.append("Hi there!\r\n\r\n");
        output.append("\r\nBah is a simple programming language implemented to explore and understand how a programming language works, specifically focusing on the Lexer, Parser, and Interpreter components.\r\n\r\n");

        // ASCII Art with red color
        output.append("\u001B[31m"); // Red color
        output.append(" ____       _            \r\n");
        output.append("||   \\\\    /_\\    ||   ||\r\n");
        output.append("||   //   // \\\\   ||   ||\r\n");
        output.append("||===|   ||===||  ||===||\r\n");
        output.append("||   \\\\  ||   ||  ||   ||\r\n");
        output.append("||___//  ||   ||  ||   ||\r\n");
        output.append("\u001B[0m"); // Reset color
        System.out.println(output.toString());
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
    }
}
