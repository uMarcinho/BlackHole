package simulation;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.*;
import javax.vecmath.*;

public class BlackHole {
    private TransformGroup tg;
    private float radius;

    public BlackHole(float radius) {
        this.radius = radius;
        create();
    }

    private void create() {
        Appearance appearance = new Appearance();
        appearance.setMaterial(new Material(
                new Color3f(0, 0, 0),
                new Color3f(0, 0, 0),
                new Color3f(0, 0, 0),
                new Color3f(0, 0, 0),
                1.0f));

        Sphere sphere = new Sphere(radius, Primitive.GENERATE_NORMALS, appearance);
        tg = new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.addChild(sphere);
    }

    public TransformGroup getTransformGroup() {
        return tg;
    }

    public float getRadius() {
        return radius;
    }
}
