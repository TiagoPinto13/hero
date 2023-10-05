import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    }
@Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "#");
    }
}
