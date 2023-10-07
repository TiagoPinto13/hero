import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;



public class Game {
    private Screen screen;
    private Hero hero;
    private Arena arena;

    public Game(){
        try {

            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            arena = new Arena(40, 20);
            hero = new Hero(new Position(10,10));
            arena.setHero(hero);
            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null);
            this.screen.startScreen();
            this.screen.doResizeIfNecessary();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key);
        arena.moveMonsters();
        if (arena.verifyMonsterCollisions()) {
            System.out.println("Game over! The hero touched a monster.");
        }
        else if (arena.getScore()==5) {
            screen.close();
            System.out.println("Score:5.");
            System.out.println("You Won!");
        }
    }
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void moveHero(Position position) {
        hero.setPosition(position);
    }
    public void run() throws IOException{
        while(true) {
        draw();
            KeyStroke key = this.screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.EOF) {
                break;
            }
        }
    }
}
