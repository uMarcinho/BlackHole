package simulation;

import javax.swing.*;
import java.awt.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import javax.media.j3d.*;
import javax.vecmath.*;

public class BlackHoleScene extends JPanel {

    private SimpleUniverse universe;
    private BranchGroup root;
    private StarField starField;
    private AccretionDisk disk;
    private BlackHole blackHole;
    private float lensStrength = 1.0f;

    public BlackHoleScene() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 700));
        setupUniverse();
        add(createControlPanel(), BorderLayout.SOUTH);
    }

    private void setupUniverse() {
        universe = new SimpleUniverse();
        universe.getViewer().getView().setBackClipDistance(100.0);

        root = new BranchGroup();
        root.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        root.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        root.setCapability(BranchGroup.ALLOW_DETACH);

        createLights();

        blackHole = new BlackHole(0.5f);
        root.addChild(blackHole.getTransformGroup());

        disk = new AccretionDisk(blackHole);
        root.addChild(disk.getTransformGroup());

        starField = new StarField(2000);
        root.addChild(starField.getShape3D());

        setupCamera();

        // comportamento da lente (usa StarField internamente)
        LensingBehavior lens = new LensingBehavior(starField);
        root.addChild(lens);

        root.compile();
        universe.addBranchGraph(root);
    }

    private void createLights() {
        AmbientLight ambient = new AmbientLight(new Color3f(0.3f, 0.3f, 0.3f));
        ambient.setInfluencingBounds(new BoundingSphere(new Point3d(), 100));
        root.addChild(ambient);

        DirectionalLight dir = new DirectionalLight(
                new Color3f(0.9f, 0.9f, 0.9f),
                new Vector3f(-1, -1, -1));
        dir.setInfluencingBounds(new BoundingSphere(new Point3d(), 100));
        root.addChild(dir);
    }

    private void setupCamera() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup cameraTG = vp.getViewPlatformTransform();
        Transform3D t3d = new Transform3D();
        t3d.set(new Vector3f(0, 0, 15));
        cameraTG.setTransform(t3d);

        OrbitBehavior orbit = new OrbitBehavior();
        orbit.setSchedulingBounds(new BoundingSphere(new Point3d(), 100));
        universe.getViewingPlatform().setViewPlatformBehavior(orbit);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Simulação 3D de Buraco Negro");
        title.setForeground(Color.WHITE);
        panel.add(title);

        JSlider lensSlider = new JSlider(0, 200, (int) (lensStrength * 100));
        lensSlider.addChangeListener(e -> {
            lensStrength = lensSlider.getValue() / 100f;
            starField.setLensStrength(lensStrength);
        });

        panel.add(new JLabel("Força da Lente:"));
        panel.add(lensSlider);

        return panel;
    }
}
