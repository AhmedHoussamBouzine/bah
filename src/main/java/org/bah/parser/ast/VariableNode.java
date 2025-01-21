package org.bah.parser.ast;

import org.bah.lexer.Token;

public class VariableNode extends Node {
    private final Token name;

    public VariableNode(Token name) {
        this.name = name;
    }

    public Token getName() {
        return name;
    }
}