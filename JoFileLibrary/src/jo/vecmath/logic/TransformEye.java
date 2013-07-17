package jo.vecmath.logic;

import jo.vecmath.AxisAngle4f;
import jo.vecmath.Matrix3f;
import jo.vecmath.Matrix4d;
import jo.vecmath.Matrix4f;
import jo.vecmath.Point3f;
import jo.vecmath.Quat4f;
import jo.vecmath.Tuple3f;
import jo.vecmath.Vector3f;

public class TransformEye extends Matrix4f
{
    /**
     * 
     */
    private static final long serialVersionUID = -6216638219578138997L;
    
    private Vector3f    mLocation = new Vector3f();
    private Vector3f    mForward = new Vector3f(0, 0, -1);
    private Vector3f    mUp = new Vector3f(0, 1, 0);
    private Vector3f    mRight = new Vector3f(1, 0, 0);
    
    public TransformEye()
    {
        super();
    }

    public TransformEye(float m00, float m01, float m02, float m03, float m10,
            float m11, float m12, float m13, float m20, float m21, float m22,
            float m23, float m30, float m31, float m32, float m33)
    {
        super(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31,
                m32, m33);
    }

    public TransformEye(float[] v)
    {
        super(v);
    }

    public TransformEye(Matrix3f m1, Vector3f t1, float s)
    {
        super(m1, t1, s);
    }

    public TransformEye(Matrix4d m1)
    {
        super(m1);
    }

    public TransformEye(Matrix4f m1)
    {
        super(m1);
    }

    public TransformEye(Quat4f q1, Vector3f t1, float s)
    {
        super(q1, t1, s);
    }

    private void assemble()
    {
        /*
        m00 = mRight.x;
        m01 = mRight.y;
        m02 = mRight.z;
        m03 = mLocation.x;
        m10 = mUp.x;
        m11 = mUp.y;
        m12 = mUp.z;
        m13 = mLocation.y;
        m20 = -mForward.x;
        m21 = -mForward.y;
        m22 = -mForward.z;
        m23 = mLocation.z;
        m30 = 0;
        m31 = 0;
        m32 = 0;
        m33 = 1;
        */
        Matrix4f rot = new Matrix4f();
        rot.setIdentity();
        Matrix4f trans = new Matrix4f();
        trans.setIdentity();
        rot.m00 = mRight.x;
        rot.m01 = mRight.y;
        rot.m02 = mRight.z;
        trans.m03 = -mLocation.x;
        rot.m10 = mUp.x;
        rot.m11 = mUp.y;
        rot.m12 = mUp.z;
        trans.m13 = -mLocation.y;
        rot.m20 = -mForward.x;
        rot.m21 = -mForward.y;
        rot.m22 = -mForward.z;
        trans.m23 = -mLocation.z;
        mul(rot, trans);
    }
    
    private Matrix3f apply(Quat4f rot)
    {
        Matrix3f m = new Matrix3f();
        m.set(rot);
        m.transform(mForward);
        m.transform(mUp);
        m.transform(mRight);
        assemble();
        return m;
    }

    public Matrix3f rotate(Tuple3f angles)
    {
        return apply(Quat4fLogic.makeRotate(angles));
    }

    public Matrix3f rotate(float xRadians, float yRadians, float zRadians)
    {
        return apply(Quat4fLogic.makeRotate(xRadians, yRadians, zRadians));
    }

    public Matrix3f rotate(float x, float y, float z, float aRadians)
    {
        return apply(Quat4fLogic.makeRotate(x, y, z, aRadians));
    }

    public Matrix3f rotateX(float aRadians)
    {
        return apply(Quat4fLogic.makeRotX(aRadians));
    }

    public Matrix3f rotateY(float aRadians)
    {
        return apply(Quat4fLogic.makeRotY(aRadians));
    }

    public Matrix3f rotateZ(float aRadians)
    {
        return apply(Quat4fLogic.makeRotZ(aRadians));
    }

    public Matrix3f rotateEuler(float xEuler, float yEuler, float zEuler)
    {
        return rotate(xEuler*MathUtils.DEG_TO_RAD, yEuler*MathUtils.DEG_TO_RAD, zEuler*MathUtils.DEG_TO_RAD);
    }

    public Matrix3f rotateEuler(float x, float y, float z, float aEuler)
    {
        return rotate(x, y, z, aEuler*MathUtils.DEG_TO_RAD);
    }

    public Matrix3f rotateXEuler(float aEuler)
    {
        return rotateX(aEuler*MathUtils.DEG_TO_RAD);
    }

    public Matrix3f rotateYEuler(float aEuler)
    {
        return rotateY(aEuler*MathUtils.DEG_TO_RAD);
    }

    public Matrix3f rotateZEuler(float aEuler)
    {
        return rotateZ(aEuler*MathUtils.DEG_TO_RAD);
    }

    public Matrix3f rotate(AxisAngle4f axis)
    {
        return apply(Quat4fLogic.makeRotate(axis));
    }
    
    public Matrix3f rotate(Matrix3f rot)
    {
        return apply(Quat4fLogic.makeRotate(rot));
    }
    
    public void scale(float s)
    {
        assemble();
    }

    public void translateAbsolute(float x, float y, float z)
    {
        //System.out.print("Translating from "+mLocation);
        mLocation.x += x;
        mLocation.y += y;
        mLocation.z += z;
        //System.out.println(" to "+mLocation);
        assemble();
    }

    public void translate(float x, float y, float z)
    {
        //System.out.print("Translating from "+mLocation);
        mLocation.add(mRight.scaleBy(x));
        mLocation.add(mUp.scaleBy(y));
        mLocation.add(mForward.scaleBy(z));
        //System.out.println(" to "+mLocation);
        assemble();
    }

    public void translate(Tuple3f scale)
    {
        translate(scale.x, scale.y, scale.z);
    }
    
    public void translateAbsolute(Tuple3f scale)
    {
        translateAbsolute(scale.x, scale.y, scale.z);
    }
    
    public Vector3f getForward()
    {
        return new Vector3f(mForward);
    }
    
    public Vector3f getUp()
    {
        return new Vector3f(mUp);
    }
    
    public Vector3f getRight()
    {
        return new Vector3f(mRight);
    }
    
    public Point3f getLocation()
    {
        return new Point3f(mLocation);
    }
    
    public void setLocation(Tuple3f o)
    {
        mLocation.set(o);
        assemble(); 
    }
    
    public void moveForward(float dist)
    {
        translate(0, 0, dist);
    }
    
    public void moveUp(float dist)
    {
        translate(0, dist, 0);
    }
    
    public void moveRight(float dist)
    {
        translate(dist, 0, 0);
    }
    
    public void rotateAround(Tuple3f around, Tuple3f rot)
    {
        Vector3f delta = new Vector3f(mLocation);
        delta.sub(around);
        yaw(rot.x).transform(delta);
        pitch(rot.y).transform(delta);
        roll(rot.z).transform(delta);
        delta.add(around);
        mLocation.set(delta);
        assemble();
    }
    
    public Matrix3f yaw(float aRadians)
    {
        Vector3f axis = getUp();
        //System.out.println("Yawing around "+axis);
        return rotate(axis.x, axis.y, axis.z, aRadians);
    }
    
    public Matrix3f pitch(float aRadians)
    {
        Vector3f axis = getRight();
        //System.out.println("Pitching around "+axis);
        return rotate(axis.x, axis.y, axis.z, aRadians);
    }
    
    public Matrix3f roll(float aRadians)
    {
        Vector3f axis = getForward();
        //System.out.println("Rolling around "+axis);
        return rotate(axis.x, axis.y, axis.z, aRadians);
    }

    public void reset()
    {
        mLocation = new Vector3f();
        mForward = new Vector3f(0, 0, -1);
        mUp = new Vector3f(0, 1, 0);
        mRight = new Vector3f(1, 0, 0);
        assemble();
    }
    
    public void check()
    {
        System.out.println("At     : "+mLocation);
        System.out.println("Up     : "+mUp);
        System.out.println("Right  : "+mRight);
        System.out.println("Forward: "+mForward);
    }
}
