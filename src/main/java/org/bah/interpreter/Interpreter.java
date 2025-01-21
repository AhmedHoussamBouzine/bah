package org.bah.interpreter;

import org.bah.parser.ast.*;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    private final Map<String, Double> environment; // Stores variable values

    public Interpreter() {
        environment = new HashMap<>();
    }

    // Evaluate the AST node
    public double interpret(Node node) {
        return switch (node) {
            case AssignmentNode assignmentNode -> evaluateAssignment(assignmentNode);
            case BinaryOperationNode binaryOperationNode -> evaluateBinaryOperation(binaryOperationNode);
            case NumberNode numberNode -> evaluateNumber(numberNode);
            case VariableNode variableNode -> evaluateVariable(variableNode);
            case null, default -> {
                assert node != null;
                throw new RuntimeException("Unknown node type: " + node.getClass());
            }
        };
    }


    private double evaluateAssignment(AssignmentNode assignmentNode) {
        // Get the variable name
        String variableName = assignmentNode.getVariableName().getName().getValue();

        // Evaluate the expression on the right-hand side
        double value = interpret(assignmentNode.getExpression());

        // Store the evaluated value in the environment (variable map)
        environment.put(variableName, value);

        // Return the value of the assignment
        return value;
    }

    // Handle binary operations (e.g., 3 + 4)
    private double evaluateBinaryOperation(BinaryOperationNode binOpNode) {
        double leftValue = interpret(binOpNode.getLeft()); // Evaluate the left operand
        double rightValue = interpret(binOpNode.getRight()); // Evaluate the right operand
        String operator = binOpNode.getOperator().getValue();

        return switch (operator) {
            case "+" -> leftValue + rightValue;
            case "-" -> leftValue - rightValue;
            case "*" -> leftValue * rightValue;
            case "/" -> {
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield leftValue / rightValue;
            }
            default -> throw new RuntimeException("Unknown operator: " + operator);
        };
    }

    // Handle numbers (e.g., 5)
    private double evaluateNumber(NumberNode numberNode) {
        return Double.parseDouble(numberNode.getValue().getValue());
    }

    // Handle variables (e.g., x)
    private double evaluateVariable(VariableNode variableNode) {
        String variableName = variableNode.getName().getValue();
        if (!environment.containsKey(variableName)) {
            throw new RuntimeException("Variable not defined: " + variableName);
        }
        return environment.get(variableName);
    }
}
