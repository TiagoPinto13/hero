import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Coin extends Element{

    public Coin(int x, int y) {
        super(x,y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFD700"));
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "C");
    }

}
