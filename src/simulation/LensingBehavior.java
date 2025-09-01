package simulation;

import javax.media.j3d.*;
import javax.vecmath.*;

public class LensingBehavior extends Behavior {
    private WakeupCriterion criterion = new WakeupOnElapsedFrames(0);
    private BoundingSphere bounds = new BoundingSphere(new Point3d(), 100);
    private StarField starField;

    public LensingBehavior(StarField sf) {
        this.starField = sf;
    }

    @Override
    public void initialize() {
        setSchedulingBounds(bounds);
        wakeupOn(criterion);
    }

    @Override
    public void processStimulus(java.util.Enumeration criteria) {
        starField.updateLensing();
        wakeupOn(criterion);
    }
}
