package simulation;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Sphere;
import java.util.ArrayList;
import java.util.Random;

public class StarField {
    private BranchGroup starGroup;
    private ArrayList<TransformGroup> starTransforms;
    private ArrayList<Vector3f> originalPositions;
    
    public StarField(int count, float minDistance, float maxDistance) {
        starGroup = new BranchGroup();
        starTransforms = new ArrayList<>();
        originalPositions = new ArrayList<>();
        
        createStars(count, minDistance, maxDistance);
    }
    
    private void createStars(int count, float minDistance, float maxDistance) {
        Random random = new Random();
        
        for (int i = 0; i < count; i++) {
            // Posição aleatória
            double distance = minDistance + random.nextDouble() * (maxDistance - minDistance);
            double theta = random.nextDouble() * Math.PI * 2;
            double phi = random.nextDouble() * Math.PI;
            
            float x = (float) (distance * Math.sin(phi) * Math.cos(theta));
            float y = (float) (distance * Math.sin(phi) * Math.sin(theta));
            float z = (float) (distance * Math.cos(phi));
            
            // Tamanho e cor
            float size = 0.02f + random.nextFloat() * 0.05f;
            float brightness = 0.5f + random.nextFloat() * 0.5f;
            Color3f color = new Color3f(brightness, brightness, brightness);
            
            // Criar estrela
            Appearance appearance = new Appearance();
            Material material = new Material();
            material.setEmissiveColor(color);
            material.setAmbientColor(color);
            appearance.setMaterial(material);
            
            Sphere star = new Sphere(size, Sphere.GENERATE_NORMALS, appearance);
            
            // Posicionar estrela
            Transform3D transform = new Transform3D();
            transform.setTranslation(new Vector3f(x, y, z));
            
            TransformGroup transformGroup = new TransformGroup(transform);
            transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            transformGroup.addChild(star);
            
            starGroup.addChild(transformGroup);
            starTransforms.add(transformGroup);
            originalPositions.add(new Vector3f(x, y, z));
        }
    }
    
    public BranchGroup getBranchGroup() {
        return starGroup;
    }
    
    public ArrayList<TransformGroup> getStarTransforms() {
        return starTransforms;
    }
    
    public ArrayList<Vector3f> getOriginalPositions() {
        return originalPositions;
    }
}