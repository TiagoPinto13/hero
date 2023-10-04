import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import javax.swing.*;
import java.io.IOException;


public class Arena {
private int width,height;
private Hero hero;

public Arena(int w,int h){
    width=w;
    height=h;
}
public void setHero(Hero hero) {
    this.hero = hero;
}
public boolean canHeroMove(Position position) {
    int x = position.getX();
    int y = position.getY();
    return x >= 0 && x < width && y >= 0 && y < height;
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
public void draw(Screen screen) throws IOException {
    screen.clear();
    hero.draw(screen);
    screen.refresh();
}
}
