

import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.Color;

public class AccretionDisk {
    private TransformGroup transformGroup;
    private float innerRadius;
    private float outerRadius;
    
    public AccretionDisk(float blackHoleRadius) {
        this.innerRadius = blackHoleRadius * 1.5f;
        this.outerRadius = blackHoleRadius * 3.0f;
        createDisk();
    }
    
    private void createDisk() {
        int segments = 36;
        
        // Criar geometria do disco
        QuadArray geometry = new QuadArray(
            segments * 4, 
            GeometryArray.COORDINATES | GeometryArray.COLOR_3
        );
        
        // Criar vértices
        Point3f[] innerPoints = new Point3f[segments];
        Point3f[] outerPoints = new Point3f[segments];
        
        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            float cos = (float) Math.cos(angle);
            float sin = (float) Math.sin(angle);
            
            innerPoints[i] = new Point3f(cos * innerRadius, 0, sin * innerRadius);
            outerPoints[i] = new Point3f(cos * outerRadius, 0, sin * outerRadius);
        }
        
        // Adicionar vértices e cores
        for (int i = 0; i < segments; i++) {
            int next = (i + 1) % segments;
            
            // Coordenadas
            geometry.setCoordinate(i * 4, innerPoints[i]);
            geometry.setCoordinate(i * 4 + 1, innerPoints[next]);
            geometry.setCoordinate(i * 4 + 2, outerPoints[next]);
            geometry.setCoordinate(i * 4 + 3, outerPoints[i]);
            
            // Cores (gradiente)
            float colorFactor = (float) i / segments;
            Color3f innerColor = new Color3f(1.0f, colorFactor, 0.0f);
            Color3f outerColor = new Color3f(colorFactor, 0.0f, 1.0f);
            
            geometry.setColor(i * 4, innerColor);
            geometry.setColor(i * 4 + 1, innerColor);
            geometry.setColor(i * 4 + 2, outerColor);
            geometry.setColor(i * 4 + 3, outerColor);
        }
        
        // Aparência com transparência
        Appearance appearance = new Appearance();
        TransparencyAttributes transparency = new TransparencyAttributes(
            TransparencyAttributes.BLENDED, 0.5f
        );
        appearance.setTransparencyAttributes(transparency);
        
        // Criar forma
        Shape3D disk = new Shape3D(geometry, appearance);
        
        // Grupo de transformação
        transformGroup = new TransformGroup();
        transformGroup.addChild(disk);
    }
    
    public TransformGroup getTransformGroup() {
        return transformGroup;
    }
}