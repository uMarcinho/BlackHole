package BlackHole;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simulação de Buraco Negro");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            BlackHoleSimulation simulation = new BlackHoleSimulation();
            frame.add(simulation);
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}