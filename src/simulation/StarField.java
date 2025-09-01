package simulation;

import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.Random;

public class StarField {
    private PointArray stars;
    private Point3f[] original;
    private Shape3D shape;
    private int count;
    private float lensStrength = 1.0f;
    private float schwarzschildRadius = 1.0f;

    public StarField(int count) {
        this.count = count;
        create();
    }

    private void create() {
        stars = new PointArray(count, PointArray.COORDINATES | PointArray.COLOR_3);
        original = new Point3f[count];
        Random rnd = new Random();

        for (int i = 0; i < count; i++) {
            double distance = 6 + rnd.nextDouble() * 20;
            double theta = rnd.nextDouble() * Math.PI * 2;
            double phi = rnd.nextDouble() * Math.PI;

            float x = (float) (distance * Math.sin(phi) * Math.cos(theta));
            float y = (float) (distance * Math.sin(phi) * Math.sin(theta));
            float z = (float) (distance * Math.cos(phi));

            Point3f p = new Point3f(x, y, z);
            original[i] = new Point3f(p);

            float brightness = 0.6f + rnd.nextFloat() * 0.4f;
            Color3f color = new Color3f(brightness, brightness, brightness);

            stars.setCoordinate(i, p);
            stars.setColor(i, color);
        }

        shape = new Shape3D(stars);
    }

    public Shape3D getShape3D() {
        return shape;
    }

    public void setLensStrength(float s) {
        this.lensStrength = s;
    }

    public void updateLensing() {
        for (int i = 0; i < count; i++) {
            Point3f p = new Point3f(original[i]);
            float b = (float) Math.sqrt(p.x * p.x + p.y * p.y);
            if (b < 0.05f) b = 0.05f;

            float deflection = (2.0f * schwarzschildRadius / b) * lensStrength;

            double ang = Math.atan2(p.y, p.x);
            double newAng = ang + deflection;
            float r = (float) Math.sqrt(p.x * p.x + p.y * p.y);

            p.x = (float) (Math.cos(newAng) * r);
            p.y = (float) (Math.sin(newAng) * r);

            stars.setCoordinate(i, p);
        }
    }
}
