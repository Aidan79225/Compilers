package com.aidan.compiler.lexer;

public class Word extends Token {
    public final String lexme;
    public Word(int tag, String lexme) {
        super(tag);
        this.lexme = lexme;
    }
}
