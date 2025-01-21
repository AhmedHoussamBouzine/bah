package org.bah.parser.ast;

import org.bah.lexer.Token;

public class BinaryOperationNode extends Node {
    private final Node left;
    private final Node right;
    private final Token operator;

    public BinaryOperationNode(Node left, Token operator, Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Token getOperator() {
        return operator;
    }

}