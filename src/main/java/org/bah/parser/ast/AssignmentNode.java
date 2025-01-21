package org.bah.parser.ast;

public class AssignmentNode extends Node {
    private final VariableNode variableName;
    private final Node expression;

    public AssignmentNode(VariableNode variableName, Node expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public VariableNode getVariableName() {
        return variableName;
    }

    public Node getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return variableName + " = " + expression;
    }
}