package simulation;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Primitive;

public class AccretionDisk {
    private TransformGroup tg;
    private float innerRadius;
    private float outerRadius;
    private int segments = 64;

    public AccretionDisk(BlackHole bh) {
        innerRadius = bh.getRadius() * 1.6f;
        outerRadius = bh.getRadius() * 3.0f;
        create();
    }

    private void create() {
        QuadArray disk = new QuadArray(segments * 4, QuadArray.COORDINATES | QuadArray.COLOR_3);

        Point3f[] vertices = new Point3f[segments * 2];
        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            float cos = (float) Math.cos(angle);
            float sin = (float) Math.sin(angle);

            vertices[i] = new Point3f(cos * innerRadius, 0, sin * innerRadius);
            vertices[i + segments] = new Point3f(cos * outerRadius, 0, sin * outerRadius);
        }

        for (int i = 0; i < segments; i++) {
            int next = (i + 1) % segments;
            disk.setCoordinate(i * 4, vertices[i]);
            disk.setCoordinate(i * 4 + 1, vertices[next]);
            disk.setCoordinate(i * 4 + 2, vertices[next + segments]);
            disk.setCoordinate(i * 4 + 3, vertices[i + segments]);

            float t = (float) i / segments;
            Color3f c1 = new Color3f(1.0f, t, 0.0f);
            Color3f c2 = new Color3f(t, 0.0f, 1.0f);

            disk.setColor(i * 4, c1);
            disk.setColor(i * 4 + 1, c1);
            disk.setColor(i * 4 + 2, c2);
            disk.setColor(i * 4 + 3, c2);
        }

        Appearance app = new Appearance();
        TransparencyAttributes ta = new TransparencyAttributes();
        ta.setTransparencyMode(TransparencyAttributes.BLENDED);
        ta.setTransparency(0.3f);
        app.setTransparencyAttributes(ta);

        Shape3D shape = new Shape3D(disk, app);
        tg = new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.addChild(shape);

        // rotator simples
        Alpha alpha = new Alpha(-1, 8000);
        RotationInterpolator rot = new RotationInterpolator(alpha, tg);
        rot.setSchedulingBounds(new BoundingSphere(new Point3d(), 100));
        tg.addChild(rot);
    }

    public TransformGroup getTransformGroup() {
        return tg;
    }
}
