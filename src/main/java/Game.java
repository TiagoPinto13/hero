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
    private int x = 10;
    private int y = 10;
    public Game(){
        try {

            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null); // we don't need a cursor
            this.screen.startScreen(); // screens must be started
            this.screen.doResizeIfNecessary(); // resize screen if necessary

        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        this.screen.clear();
        this.screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        this.screen.refresh();
    }
   private void processKey(KeyStroke key) throws IOException {
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp:
                y -= 1;
                break;
            case ArrowLeft:
                x -= 1;
                break;
            case ArrowDown:
                y += 1;
                break;

            case ArrowRight:
                x += 1;
                break;
            case Character:
                if (key.getCharacter() == 'q' || key.getCharacter() == 'Q') {
                    try {
                        this.screen.close(); 
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
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
