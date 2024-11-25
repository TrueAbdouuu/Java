import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;
    private final FPSCounter fpsCounter = new FPSCounter();

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);
        }
        fpsCounter.frameRendered();

        // Affichage du FPS
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("FPS : " + fpsCounter.getFPS(), 10, 20);
    }

    @Override
    public void update(){
        this.repaint();
    }
}