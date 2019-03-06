package lexer;

import java.util.HashMap;

public class Env {
    private HashMap<String, Symbol> table = new HashMap<>();
    private final Env prev;
    public Env(Env p) {
        prev = p;
    }

    public void put(String s, Symbol symbol) {
        table.put(s, symbol);
    }

    public Symbol get(String s) {
        for (Env e = this; e != null; e = e.prev) {
            Symbol symbol = e.table.get(s);
            if (symbol != null) {
                return symbol;
            }
        } 
        return null;
    }
}
