package jo.vecmath.logic;

import jo.vecmath.Matrix4f;
import jo.vecmath.Point3f;
import jo.vecmath.Tuple3f;
import jo.vecmath.Vector3f;

public class MoveTransformer implements ITransformer
{
    private Point3f     mPoint;
    private Vector3f    mVelocity;      // per second
    private Vector3f    mAcceleration;  // per second
    private boolean     mReachedTarget;
    private long        mLastTick;

    public MoveTransformer(Point3f point, Vector3f velocity, Vector3f acceleration)
    {
        mPoint = point;
        mVelocity = velocity;
        mAcceleration = acceleration;
        mLastTick = System.currentTimeMillis();
        mReachedTarget = false;
    }

    @Override
    public Matrix4f calcTransform(Matrix4f transform)
    {
        if (mReachedTarget)
            return transform;
        long now = System.currentTimeMillis();
        float elapsed = (now - mLastTick)/1000f;
        Tuple3f v = mVelocity.scaleBy(elapsed);
        Point3f target = new Point3f(transform.m03, transform.m13, transform.m23);
        //System.out.println("At "+mPoint+", v="+mVelocity+", a="+mAcceleration+", target="+target);
        if (Math.abs(mPoint.x - target.x) < v.x)
            mPoint.x = target.x;
        else
            mPoint.x += v.x*Math.signum(target.x - mPoint.x);
        if (Math.abs(mPoint.y - target.y) < v.y)
            mPoint.y = target.y;
        else
            mPoint.y += v.y*Math.signum(target.y - mPoint.y);
        if (Math.abs(mPoint.z - target.z) < v.z)
            mPoint.z = target.z;
        else
            mPoint.z += v.z*Math.signum(target.z - mPoint.z);
        mVelocity.add(mAcceleration.scaleBy(elapsed));
        Matrix4f newTrans = new Matrix4f(transform);
        newTrans.m03 = mPoint.x;
        newTrans.m13 = mPoint.y;
        newTrans.m23 = mPoint.z;
        if (MathUtils.isZero(mPoint.distanceL1(target)))
            mReachedTarget = true;       
        mLastTick = now;
        return newTrans;
    }

    public boolean isReachedTarget()
    {
        return mReachedTarget;
    }
}
