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
    }

    private void readPeek() throws IOException{
        peek = (char)System.in.read();
    }

    public Token scan() throws IOException {
        for (; ; readPeek()) {
            if (peek == ' ' || peek == '\t') continue;
            else if (peek == '\n') line = line + 1;
            else break;
        }

        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                v = v * 10 + Character.digit(peek, 10);
                readPeek();
            } while (Character.isDigit(peek));
            return new Num(v);
        }

        if (Character.isLetter(peek)) {
            StringBuffer sb = new StringBuffer();
            do {
                sb.append(peek);
                readPeek();
            } while (Character.isLetterOrDigit(peek));
            String s = sb.toString();
            Word word = words.get(s);
            if (word == null) {
                word = new Word(Tag.ID, s);
                words.put(s, word);
            }
            return word;
        }

        Token token = new Token(peek);
        peek = ' ';
        return token;
    }
}