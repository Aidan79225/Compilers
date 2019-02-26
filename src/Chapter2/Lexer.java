package Chapter2;

import java.io.IOException;
import java.util.Hashtable;

public class Lexer {
    public int line = 1;
    private char peek = ' ';
    private Hashtable<String, Word> words = new Hashtable();

    void reserve(Word word) {
        words.put(word.lexme, word);
    }

    public Lexer() {
        reserve(new Word(Tag.TRUE, "true"));
        reserve(new Word(Tag.FALSE, "false"));
        reserve(new Word(Tag.LESS_THEN, "<"));
        reserve(new Word(Tag.LESS_THEN_OR_EQUAL_TO, "<="));
        reserve(new Word(Tag.GREATER_THEN, ">"));
        reserve(new Word(Tag.GREATER_THEN_OR_EQUAL_TO, ">="));
        reserve(new Word(Tag.EQUAL_TO, "=="));
        reserve(new Word(Tag.NOT_EQUAL_TO, "!="));
    }

    private void readPeek() throws IOException {
        peek = (char)System.in.read();
    }

    public Token scan() throws IOException {
        for (; ; readPeek()) {
            if (peek == ' ' || peek == '\t') continue;
            else if (peek == '\n') line = line + 1;
            else break;
        }

        if (Character.isDigit(peek) || peek == '.') {
            String v = getOneNum();
            return new Num(Float.valueOf(v));
        }

        if (Character.isLetter(peek)) {
            String s = getOneWord();
            Word word = words.get(s);
            if (word == null) {
                word = new Word(Tag.ID, s);
                words.put(s, word);
            }
            return word;
        }

        if (peek == '>' || peek == '=' || peek == '!' || peek == '<') {
            String s = "" + peek;
            readPeek();
            if (peek == '=') {
                s = s + peek;
            }
            Word word = words.get(s);
            if (word == null) {
                throw new RuntimeException("not support \"" + s + "\", at line: " + line);
            }
            return words.get(s);
        }

        if (peek == '/') {
            readPeek();
            switch (peek) {
                case '/':
                    for (; ; readPeek()) {
                        if (peek == '\n') {
                            line = line + 1;
                            break;
                        }
                    }
                    peek = ' ';
                    return scan();
                case '*':
                    for (; ; readPeek()) {
                        if (peek == '\n') line = line + 1;
                        else if (peek == '*') {
                            readPeek();
                            if (peek == '/') {
                                peek = ' ';
                                return scan();
                            }
                        }
                    }
            }
        }

        Token token = new Token(peek);
        peek = ' ';
        return token;
    }

    private String getOneNum() throws IOException {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(peek);
            readPeek();
        } while (Character.isLetterOrDigit(peek));
        return sb.toString();
    }

    private String getOneWord() throws IOException {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(peek);
            readPeek();
        } while (Character.isLetterOrDigit(peek));
        return sb.toString();
    }

}
