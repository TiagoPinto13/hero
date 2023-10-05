import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
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
            hero = new Hero(10, 10);
            arena.setHero(hero);
            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null); // we don't need a cursor
            this.screen.startScreen(); // screens must be started
            this.screen.doResizeIfNecessary(); // resize screen if necessary
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void processKey(KeyStroke key) {
        arena.processKey(key);
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
