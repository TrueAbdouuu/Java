import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    Timer gameTimer;
    Timer renderTimer;
    Timer physicTimer;
    Timer deathTimer;

    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);

        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        renderTimer = new Timer(50,(time)-> renderEngine.update());
        gameTimer = new Timer(50,(time)-> gameEngine.update());
        physicTimer = new Timer(50,(time)-> physicEngine.update());
        deathTimer = new Timer(10000, (time) -> gameOver());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
        deathTimer.setRepeats(false);
        deathTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    private void gameOver() {
        renderTimer.stop();
        gameTimer.stop();
        physicTimer.stop();
        deathTimer.stop();

        displayZoneFrame.dispose();

        SwingUtilities.invokeLater(() -> new GameOverScreen());
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TitleScreen(() -> {
                try {
                    new Main();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }

}