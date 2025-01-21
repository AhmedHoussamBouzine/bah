package org.bah.parser.ast;

import org.bah.lexer.Token;

public class NumberNode extends Node {
    private final Token value;

    public NumberNode(Token value) {
        this.value = value;
    }

    public Token getValue() {
        return value;
    }
}