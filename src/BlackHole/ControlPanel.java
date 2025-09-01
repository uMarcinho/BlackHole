package BlackHole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel {
    private JSlider sizeSlider;
    private JSlider distortionSlider;
    private JButton resetButton;
    
    public ControlPanel() {
        setLayout(new FlowLayout());
        setBackground(Color.DARK_GRAY);
        
        // Slider para tamanho do buraco negro
        add(new JLabel("Tamanho:"));
        sizeSlider = new JSlider(10, 100, 30);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        add(sizeSlider);
        
        // Slider para distorção
        add(new JLabel("Distorção:"));
        distortionSlider = new JSlider(50, 500, 200);
        distortionSlider.setMajorTickSpacing(50);
        distortionSlider.setPaintTicks(true);
        add(distortionSlider);
        
        // Botão reset
        resetButton = new JButton("Reset");
        add(resetButton);
        
        // Ações
        resetButton.addActionListener(e -> {
            sizeSlider.setValue(30);
            distortionSlider.setValue(200);
        });
    }
    
    public int getBlackHoleSize() {
        return sizeSlider.getValue();
    }
    
    public int getDistortionSize() {
        return distortionSlider.getValue();
    }
    
    public void addSizeChangeListener(ChangeListener listener) {
        sizeSlider.addChangeListener(listener);
    }
    
    public void addDistortionChangeListener(ChangeListener listener) {
        distortionSlider.addChangeListener(listener);
    }
}