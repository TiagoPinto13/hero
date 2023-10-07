import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends Element {
    public Monster(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "M");
    }

    public Position move() {
        int randomDirection = (int) (Math.random() * 4);
        int newX = getPosition().getX();
        int newY = getPosition().getY();

        switch (randomDirection) {
            case 0:
                newY--;
                break;
            case 1:
                newX--;
                break;
            case 2:
                newY++;
                break;
            case 3:
                newX++;
                break;
        }

        return new Position(newX, newY);
    }

}