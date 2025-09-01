package BlackHole;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class BlackHole3D extends JPanel {
    private int centerX, centerY;
    private int blackHoleRadius = 30;
    private int distortionRadius = 200;
    
    public BlackHole3D() {
        setBackground(Color.BLACK);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        
        // Desenhar estrelas de fundo
        drawStars(g2d);
        
        // Desenhar distorção gravitacional
        drawGravitationalDistortion(g2d);
        
        // Desenhar disco de acreção
        drawAccretionDisk(g2d);
        
        // Desenhar buraco negro
        drawBlackHole(g2d);
    }
    
    private void drawStars(Graphics2D g2d) {
        Random rand = new Random();
        g2d.setColor(Color.WHITE);
        
        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt(getWidth());
            int y = rand.nextInt(getHeight());
            int size = rand.nextInt(3) + 1;
            g2d.fillOval(x, y, size, size);
        }
    }
    
    private void drawGravitationalDistortion(Graphics2D g2d) {
        // Desenhar distorção gravitacional (lentes)
        for (int i = 0; i < 360; i += 10) {
            double angle = Math.toRadians(i);
            int startX = centerX + (int) (blackHoleRadius * Math.cos(angle));
            int startY = centerY + (int) (blackHoleRadius * Math.sin(angle));
            
            int endX = centerX + (int) (distortionRadius * Math.cos(angle));
            int endY = centerY + (int) (distortionRadius * Math.sin(angle));
            
            g2d.setColor(new Color(100, 100, 255, 100));
            g2d.drawLine(startX, startY, endX, endY);
        }
        
        // Desenhar anéis de distorção
        for (int r = distortionRadius; r > blackHoleRadius; r -= 20) {
            float alpha = 1.0f - (float) (r - blackHoleRadius) / (distortionRadius - blackHoleRadius);
            g2d.setColor(new Color(0, 100, 255, (int) (alpha * 100)));
            g2d.drawOval(centerX - r, centerY - r, 2 * r, 2 * r);
        }
    }
    
    private void drawAccretionDisk(Graphics2D g2d) {
        // Disco interno (quente)
        GradientPaint gp = new GradientPaint(
            centerX - 100, centerY - 100, Color.RED,
            centerX + 100, centerY + 100, Color.YELLOW
        );
        g2d.setPaint(gp);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawOval(centerX - 100, centerY - 100, 200, 200);
        
        // Disco externo (frio)
        gp = new GradientPaint(
            centerX - 150, centerY - 150, Color.BLUE,
            centerX + 150, centerY + 150, Color.CYAN
        );
        g2d.setPaint(gp);
        g2d.drawOval(centerX - 150, centerY - 150, 300, 300);
    }
    
    private void drawBlackHole(Graphics2D g2d) {
        // Desenhar buraco negro
        g2d.setColor(Color.BLACK);
        g2d.fillOval(centerX - blackHoleRadius, centerY - blackHoleRadius, 
                     blackHoleRadius * 2, blackHoleRadius * 2);
        
        // Adicionar brilho ao redor
        g2d.setColor(new Color(50, 50, 150, 100));
        g2d.drawOval(centerX - blackHoleRadius - 5, centerY - blackHoleRadius - 5, 
                     blackHoleRadius * 2 + 10, blackHoleRadius * 2 + 10);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }
}