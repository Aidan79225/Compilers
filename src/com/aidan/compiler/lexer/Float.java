package com.aidan.compiler.lexer;

class Float extends Token {
    public final float value;
    public Float(float value) {
        super(Tag.FLOAT);
        this.value = value;
    }
}