import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena {
private int width,height,score=0;
private Hero hero;
private List<Wall> walls;
private List<Coin> coins;
private List<Monster> monsters;
Screen screen;
public Arena(int w,int h){
    width=w;
    height=h;
    this.walls = createWalls();
    this.coins = createCoins();
    this.monsters=createMonsters();
}
public void setHero(Hero hero) {
    this.hero = hero;
}
public boolean canHeroMove(Position position) {
    int x = position.getX();
    int y = position.getY();
    if (x < 0 || x >= width || y < 0 || y >= height){
        return false;
    }
    for (Wall wall : walls) {
        if (wall.getPosition().equals(position)) {
            return false;
        }
    }
    return true;
}
public int getScore(){
    return score;
}
public void moveHero(Position newPosition) {
    if (canHeroMove(newPosition)) {
        hero.setPosition(newPosition);
        retrieveCoins();
        moveMonsters();

    }
}
public void processKey(KeyStroke key)  {
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int a = random.nextInt(width - 2) + 1;
            int b = random.nextInt(height - 2) + 1;

            if (a == 10 && b == 10){
                i--;
            }
            else if (isCoinsOnTopOfAnother(coins,a,b)) {
                coins.add(new Coin(a, b));
            }
            else{
                i--;
            }
        }
        return coins;
    }
    public void retrieveCoins() {
        Position heroPosition = hero.getPosition();
        List<Coin> coinsToRemove = new ArrayList<>();

        for (Coin coin : coins) {
            if (coin.getPosition().equals(heroPosition)) {
                coinsToRemove.add(coin);
                score+=1;
            }
        }

        coins.removeAll(coinsToRemove);
    }

    private boolean isCoinsOnTopOfAnother(ArrayList<Coin> coins, int a, int b){
        for(Coin coin : coins){
            if(coin.getPosition().getX() == a && coin.getPosition().getY() == b){
                return false;
            }
        }
        return true;
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i=0;i<3;i++) {
            int a = random.nextInt(width - 2) +1 ;
            int b = random.nextInt(height - 2) +1;
            if (a==10 && b==10){
                i--;
            }
            else if (isMonsterOnTopOfAnother(monsters, a,b)) {
                monsters.add(new Monster(a, b));
            }
            else{
                i--;
            }
        }
        return monsters;
    }
    private boolean isMonsterOnTopOfAnother(ArrayList<Monster> monsters,int x, int y) {
        for (Monster monster : monsters) {
            if (monster.getPosition().getX() == x && monster.getPosition().getY() == y) {
                return false;
            }
        }
        for (Coin coin : coins){
            if(coin.getPosition().getX() == x && coin.getPosition().getY() == y){
                return false;
            }
        }
        return true;
    }
    public void moveMonsters() {
        for (Monster monster : monsters) {
            Position newPosition = monster.move();
            if (canMonsterMove(newPosition)) {
                monster.setPosition(newPosition);
            }
            }
        }

    public boolean canMonsterMove(Position position) {
    int x = position.getX();

        int y = position.getY();

        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }

        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }

        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position)) {
                return false;
            }
        }

        return true;
    }
    public boolean verifyMonsterCollisions() {
        Position heroPosition = hero.getPosition();
        for (Monster monster : monsters) {
            if (heroPosition.equals(monster.getPosition())) {
                return true;
            }
        }
        return false;
     }




                public void draw(TextGraphics graphics) {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
                    graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
                    hero.draw(graphics);
                    for (Wall wall : walls)
                        wall.draw(graphics);
                    hero.draw(graphics);
                    for (Coin coin : coins)
                        coin.draw(graphics);
                    for (Monster monster : monsters)
                        monster.draw(graphics);
                }
            }

