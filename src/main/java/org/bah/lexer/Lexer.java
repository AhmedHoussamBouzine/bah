package org.bah.lexer;

public class Lexer {
    private final String input;
    private int position;
    private char currentChar;
    private int line;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
        this.line = 1;
        this.currentChar = !input.isEmpty() ? input.charAt(0) : '\0';
    }

    private void advance() {
        if (currentChar == '\n') {
            line++;
        }
        position++;
        currentChar = position < input.length() ? input.charAt(position) : '\0';
    }

    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    private Token number() {
        StringBuilder result = new StringBuilder();

        // Handle integer part
        while (currentChar != '\0' && Character.isDigit(currentChar)) {
            result.append(currentChar);
            advance();
        }

        // Handle decimal part
        if (currentChar == '.') {
            result.append(currentChar);
            advance();

            if (!Character.isDigit(currentChar)) {
                throw new LexerException("Invalid number format: Expected digit after decimal point", line);
            }

            while (currentChar != '\0' && Character.isDigit(currentChar)) {
                result.append(currentChar);
                advance();
            }
        }

        return new Token(TokenType.NUMBER, result.toString(), line);
    }

    private Token identifier() {
        StringBuilder result = new StringBuilder();

        // First character must be a letter or underscore
        if (Character.isLetter(currentChar) || currentChar == '_') {
            result.append(currentChar);
            advance();
        }

        // Subsequent characters can be letters, numbers, or underscores
        while (currentChar != '\0' && (Character.isLetterOrDigit(currentChar) || currentChar == '_')) {
            result.append(currentChar);
            advance();
        }

        String value = result.toString();
        // Check for keywords
        TokenType type = Keywords.getKeywordToken(value);
        return type != null ? new Token(type, value, line)
                : new Token(TokenType.IDENTIFIER, value, line);
    }

    public Token getNextToken() {
        while (currentChar != '\0') {
            // Handle whitespace
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }

            // Handle numbers
            if (Character.isDigit(currentChar)) {
                return number();
            }

            // Handle identifiers and keywords
            if (Character.isLetter(currentChar) || currentChar == '_') {
                return identifier();
            }

            // Store current position for error reporting
            int currentLine = line;

            // Handle operators and special characters
            switch (currentChar) {
                case '+':
                    advance();
                    return new Token(TokenType.PLUS, "+", currentLine);
                case '-':
                    advance();
                    return new Token(TokenType.MINUS, "-", currentLine);
                case '*':
                    advance();
                    return new Token(TokenType.MULTIPLY, "*", currentLine);
                case '/':
                    advance();
                    // Check for comments
                    if (currentChar == '/') {
                        skipSingleLineComment();
                        continue;
                    } else if (currentChar == '*') {
                        skipMultiLineComment();
                        continue;
                    }
                    return new Token(TokenType.DIVIDE, "/", currentLine);
                case '=':
                    advance();
                    if (currentChar == '=') {
                        advance();
                        return new Token(TokenType.EQUALS, "==", currentLine);
                    }
                    return new Token(TokenType.ASSIGN, "=", currentLine);
                case '(':
                    advance();
                    return new Token(TokenType.LEFT_PAREN, "(", currentLine);
                case ')':
                    advance();
                    return new Token(TokenType.RIGHT_PAREN, ")", currentLine);
                case ';':
                    advance();
                    return new Token(TokenType.SEMICOLON, ";", currentLine);
            }

            throw new LexerException("Invalid character: " + currentChar, line);
        }

        return new Token(TokenType.EOF, null, line);
    }

    private void skipSingleLineComment() {
        // Advance past the second '/'
        advance();
        // Continue until newline or EOF
        while (currentChar != '\0' && currentChar != '\n') {
            advance();
        }
    }

    private void skipMultiLineComment() {
        // Advance past the '*'
        advance();
        while (currentChar != '\0') {
            if (currentChar == '*') {
                advance();
                if (currentChar == '/') {
                    advance();
                    return;
                }
            } else {
                advance();
            }
        }
        throw new LexerException("Unterminated multi-line comment", line);
    }
}