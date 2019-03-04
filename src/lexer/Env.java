package lexer;

import java.util.Hashtable;

public class Env {
    private Hashtable table = new Hashtable();
    private final Env prev;
    public Env(Env p) {
        prev = p;
    }

    public void put(String s, Symbol symbol) {
        table.put(s, symbol);
    }

    public Symbol get(String s) {
        for (Env e = this; e != null; e = e.prev) {
            Symbol symbol = (Symbol) e.table.get(s);
            if (symbol != null) {
                return symbol;
            }
        }
        return null;
    }
}
