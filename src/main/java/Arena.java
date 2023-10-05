import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Arena {
private int width,height;
private Hero hero;
private List<Wall> walls;
public Arena(int w,int h){
    width=w;
    height=h;
    this.walls = createWalls();
}
public void setHero(Hero hero) {
    this.hero = hero;
}
public boolean canHeroMove(Position position) {
    int x = position.getX();
    int y = position.getY();
    if (x < 1 || x >= width-1 || y < 1 || y >= height-1){
        return false;
    }
    for (Wall wall : walls) {
        if (wall.getPosition().equals(position)) {
            return false;
        }
    }
    return true;
}
public void moveHero(Position newPosition) {
    if (canHeroMove(newPosition)) {
        hero.setPosition(newPosition);
    }
}
public void processKey(KeyStroke key) {
    switch (key.getKeyType()) {
        case ArrowUp:
            moveHero(hero.moveUp());
            break;
        case ArrowLeft:
            moveHero(hero.moveLeft());
            break;
        case ArrowDown:
            moveHero(hero.moveDown());
            break;
        case ArrowRight:
            moveHero(hero.moveRight());
            break;
    }
}
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    public void draw(TextGraphics graphics) {
    graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
    graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    hero.draw(graphics);
    for (Wall wall : walls)
        wall.draw(graphics);
    if (canHeroMove(hero.getPosition())) {
        hero.draw(graphics);
    }
}
}
