package org.bah.lexer;

import java.util.HashMap;
import java.util.Map;

public class Keywords {
    private static final Map<String, TokenType> keywords = new HashMap<>();

    static {
        keywords.put("let", TokenType.LET);
    }

    public static TokenType getKeywordToken(String word) {
        return keywords.get(word);
    }
}
