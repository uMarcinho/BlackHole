package BlackHole;

import javax.swing.*;

import java.awt.*;

public class BlackHoleSimulation extends JPanel {
    private BlackHole3D blackHole;
    private ControlPanel controls;
    
    public BlackHoleSimulation() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        
        // Inicializar componentes
        blackHole = new BlackHole3D();
        controls = new ControlPanel();
        
        // Adicionar à interface
        add(blackHole, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
    }
}