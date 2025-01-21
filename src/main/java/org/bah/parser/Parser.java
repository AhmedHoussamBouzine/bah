package org.bah.parser;

import org.bah.lexer.*;
import org.bah.parser.ast.*;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    private void consume(TokenType type) {
        if (currentToken.getType() == type) {
            currentToken = lexer.getNextToken();
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken);
        }
    }

    public Node parse() {
        return statement();
    }

    // Statement parsing (let variable = expression;)
    private Node statement() {
        if (currentToken.getType() == TokenType.LET) {
            consume(TokenType.LET);
            Token variableName = currentToken;
            consume(TokenType.IDENTIFIER);
            consume(TokenType.ASSIGN);
            Node value = expression();
            consume(TokenType.SEMICOLON);
            return new AssignmentNode(new VariableNode(variableName), value);
        }
        return expression();
    }

    private Node expression() {
        Node left = term();

        while (currentToken.getType() == TokenType.PLUS || currentToken.getType() == TokenType.MINUS) {
            Token operator = currentToken;
            consume(currentToken.getType());
            Node right = term();
            left = new BinaryOperationNode(left, operator, right);
        }

        return left;
    }

    private Node term() {
        Node left = factor();

        while (currentToken.getType() == TokenType.MULTIPLY || currentToken.getType() == TokenType.DIVIDE) {
            Token operator = currentToken;
            consume(currentToken.getType());
            Node right = factor();
            left = new BinaryOperationNode(left, operator, right);
        }

        return left;
    }

    private Node factor() {
        if (currentToken.getType() == TokenType.NUMBER) {
            Token value = currentToken;
            consume(TokenType.NUMBER);
            return new NumberNode(value);
        }

        if (currentToken.getType() == TokenType.IDENTIFIER) {
            Token variableName = currentToken;
            consume(TokenType.IDENTIFIER);
            return new VariableNode(variableName);
        }

        if (currentToken.getType() == TokenType.LEFT_PAREN) {
            consume(TokenType.LEFT_PAREN);
            Node expr = expression();
            consume(TokenType.RIGHT_PAREN);
            return expr;
        }

        throw new RuntimeException("Unexpected token: " + currentToken);
    }
}
