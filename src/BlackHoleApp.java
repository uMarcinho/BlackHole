import javax.swing.*;

public class BlackHoleApp {
    private JFrame frame;

    public BlackHoleApp() {
        frame = new JFrame("Simulação 3D de Buraco Negro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        simulation.BlackHoleScene scene = new simulation.BlackHoleScene();
        frame.add(scene);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }
}
