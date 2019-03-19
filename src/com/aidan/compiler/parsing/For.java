package com.aidan.compiler.parsing;

import java.awt.*;

public class For extends Stmt {
    Expr expr1;
    Expr expr2;
    Expr expr3;
    Stmt stmt;
    Label after;
    Label start;
    public For (
            Expr expr1,
            Expr expr2,
            Expr expr3,
            Stmt stmt
    ) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
        this.stmt = stmt;
        this.start = new Label();
        this.after = new Label();
    }

    @Override
    public void gen() {
        expr1.rvalue();
        Expr check = expr2.rvalue();
        emit("ifFalse " + check.toString() + " goto " + after);
        stmt.gen();
        expr3.rvalue();
        emit(" goto " + start);
    }

    public void emit(String msg) {

    }
}
