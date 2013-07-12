package jo.vecmath.logic;

import java.util.Random;

import javax.vecmath.Matrix4f;
import javax.vecmath.Point3f;

public class BrownianTransformer implements ITransformer
{
    private Random      mRND;
    private Point3f     mLocation;
    private Point3f     mTarget;
    private float       mVelocity; // per second
    private float       mRadius;
    private long        mLastTick;

    public BrownianTransformer(float velocity, float radius)
    {
        mVelocity = velocity;
        mRadius = radius;
        mRND = new Random();
        mLastTick = System.currentTimeMillis();
        mLocation = new Point3f(0, 0, 0);
        newTarget();
    }
    
    private void newTarget()
    {
        mTarget = new Point3f(newDelta(), newDelta(), newDelta());
    }
    
    private float newDelta()
    {
        return (mRND.nextFloat() - .5f)*mRadius*2;
    }

    @Override
    public Matrix4f calcTransform(Matrix4f transform)
    {
        long now = System.currentTimeMillis();
        float elapsed = (now - mLastTick)/1000f;
        float traverse = elapsed*mVelocity;
        for (;;)
        {
            float dist = mTarget.distance(mLocation);
            if (traverse < dist)
                 break;
            traverse -= dist;
            mLocation.set(mTarget);
            newTarget();
        }
        Point3f delta = new Point3f();
        delta.sub(mTarget, mLocation);
        Point3fLogic.normalize(delta);
        delta.scale(traverse);
        mLocation.add(delta);
        Matrix4f newTrans = new Matrix4f(transform);
        newTrans.m03 += mLocation.x;
        newTrans.m13 += mLocation.y;
        newTrans.m23 += mLocation.z;
        mLastTick = now;
        return newTrans;
    }
}
