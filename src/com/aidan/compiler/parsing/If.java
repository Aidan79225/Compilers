package com.aidan.compiler.parsing;

import java.awt.*;

public class If extends Stmt {
    final Expr expr;
    final Stmt stmt;
    final Label after;

    public If(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
        after = new Label();
    }

    @Override
    public void gen() {
        Expr n = expr.rvalue();
        emit("ifFalse " + n.toString() + " goto " + after);
        stmt.gen();
        emit(after + ":");
    }

    public void emit(String msg) {

    }
}
