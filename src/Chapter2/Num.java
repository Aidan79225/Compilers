package Chapter2;

public class Num extends Token {
    public final float value;
    public Num(float value) {
        super(Tag.NUM);
        this.value = value;
    }
}
