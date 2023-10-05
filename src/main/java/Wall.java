import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;

public class Wall {
    private Position position;
    private char wallChar;

    public Wall(int x, int y) {
        this.position = new Position(x, y);
        this.wallChar = '#';
    }

    public Position getPosition() {
        return position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), String.valueOf(wallChar));
    }
}
