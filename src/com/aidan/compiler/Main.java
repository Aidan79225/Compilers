package com.aidan.compiler;

import com.aidan.compiler.lexer.Lexer;
import com.aidan.compiler.lexer.Token;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            Token token = new Lexer().scan();
            System.out.println(token.tag + "");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
