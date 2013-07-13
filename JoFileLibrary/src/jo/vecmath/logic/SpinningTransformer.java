package jo.vecmath.logic;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Point3f;

public class SpinningTransformer implements ITransformer
{
    private Point3f     mByEuler;  // per second
    private AxisAngle4f mByAxis;
    private long        mStartTime;

    public SpinningTransformer()
    {
        mStartTime = System.currentTimeMillis();
    }

    public SpinningTransformer(Point3f rotation)
    {
        mByEuler = rotation;
        mStartTime = System.currentTimeMillis();
    }

    public SpinningTransformer(AxisAngle4f axis)
    {
        mByAxis = axis;
        mStartTime = System.currentTimeMillis();
    }

    public SpinningTransformer(Point3f rotation, long startTime)
    {
        mByEuler = rotation;
        mStartTime = startTime;
    }

    public SpinningTransformer(AxisAngle4f axis, long startTime)
    {
        mByAxis = axis;
        mStartTime = startTime;
    }

    @Override
    public Matrix4f calcTransform(Matrix4f transform)
    {
        Matrix4f t;
        long now = System.currentTimeMillis();
        if (now > mStartTime)
        {
            t = new Matrix4f(transform);
            float seconds = (now - mStartTime) / 1000.0f;
            if (mByEuler != null)
                Matrix4fLogic.rotateEuler(t, mByEuler.scaleBy(seconds));
            else
            {
                t = new Matrix4f();
                AxisAngle4f a = new AxisAngle4f(mByAxis);
                a.angle *= seconds;
                t.set(a);
                t.mul(transform, t);
            }
        }
        else
            t = transform;
        return t;
    }

    public long getStartTime()
    {
        return mStartTime;
    }

    public void setStartTime(long startTime)
    {
        mStartTime = startTime;
    }

    public Point3f getByEuler()
    {
        return mByEuler;
    }

    public void setByEuler(Point3f byEuler)
    {
        mByEuler = byEuler;
    }

    public AxisAngle4f getByAxis()
    {
        return mByAxis;
    }

    public void setByAxis(AxisAngle4f byAxis)
    {
        mByAxis = byAxis;
    }
}
