package org.bah.lexer;

public class LexerException extends RuntimeException {
    private final int line;

    public LexerException(String message, int line) {
        super(String.format("Line %d: %s", line, message));
        this.line = line;
    }

    public int getLine() {
        return line;
    }
}
