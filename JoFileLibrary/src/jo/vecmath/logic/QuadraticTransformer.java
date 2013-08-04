package jo.vecmath.logic;

import jo.vecmath.Matrix3f;
import jo.vecmath.Matrix4f;
import jo.vecmath.Point3f;
import jo.vecmath.Vector3f;

public class QuadraticTransformer implements ITransformer
{
    private Point3f     mByEuler;  // per second
    private Point3f     mXQuad;  
    private Point3f     mYQuad;  
    private Point3f     mZQuad;  
    private Point3f     mScaleQuad;  
    private long        mStartTime;
    private long        mLastTime;
    private Matrix4f    mLastMatrix;

    public QuadraticTransformer()
    {
        mStartTime = System.currentTimeMillis();
    }

    public QuadraticTransformer(long startTime)
    {
        mStartTime = startTime;
    }

    @Override
    public Matrix4f calcTransform(Matrix4f transform)
    {
        long now = System.currentTimeMillis();
        if (now == mLastTime)
            return mLastMatrix;
        mLastTime = now;
        if (now > mStartTime)
        {
            mLastMatrix = new Matrix4f(transform);
            float seconds = (now - mStartTime) / 1000.0f;
            Matrix3f rot;
            if (mByEuler != null)
            {
                Point3f angles = new Point3f(mByEuler);
                angles.scale(seconds);
                rot = Matrix3fLogic.makeRotateMatrix(angles);
            }
            else
            {
                rot = new Matrix3f();
                rot.setIdentity();
            }
            Vector3f trans = new Vector3f(evalQuad(seconds, mXQuad), evalQuad(seconds, mYQuad), evalQuad(seconds, mZQuad));
            float scale = (mScaleQuad != null) ? evalQuad(seconds, mScaleQuad) : 1;
            mLastMatrix.set(rot, trans, scale);
        }
        else
            mLastMatrix = transform;
        return mLastMatrix;
    }
    
    private float evalQuad(float t, Point3f coeff)
    {
        if (coeff == null)
            return 0;
        return t*t*coeff.x + t*coeff.y + coeff.z;
    }

    public Point3f getByEuler()
    {
        return mByEuler;
    }

    public void setByEuler(Point3f byEuler)
    {
        mByEuler = byEuler;
    }

    public Point3f getXQuad()
    {
        return mXQuad;
    }

    public void setXQuad(Point3f xQuad)
    {
        mXQuad = xQuad;
    }

    public Point3f getYQuad()
    {
        return mYQuad;
    }

    public void setYQuad(Point3f yQuad)
    {
        mYQuad = yQuad;
    }

    public Point3f getZQuad()
    {
        return mZQuad;
    }

    public void setZQuad(Point3f zQuad)
    {
        mZQuad = zQuad;
    }

    public Point3f getScaleQuad()
    {
        return mScaleQuad;
    }

    public void setScaleQuad(Point3f scaleQuad)
    {
        mScaleQuad = scaleQuad;
    }

    public long getStartTime()
    {
        return mStartTime;
    }

    public void setStartTime(long startTime)
    {
        mStartTime = startTime;
    }
}
