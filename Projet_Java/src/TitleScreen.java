import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TitleScreen {
    private JFrame frame;

    public TitleScreen(Runnable onStartGame) {
        frame = new JFrame("Ecran Titre");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Bienvenue au Jeu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.CENTER);

        // Bouton pour démarrer le jeu
        JButton startButton = new JButton("Commencer");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        startButton.addActionListener((ActionEvent e) -> {
            frame.dispose(); // Ferme l'écran titre
            onStartGame.run(); // Lance le jeu
        });
        frame.add(startButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
