package jo.vecmath.logic;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4d;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

public class TransformQ extends Matrix4f
{
    /**
     * 
     */
    private static final long serialVersionUID = -6216638219578138997L;
    
    private Quat4f      mRotation = new Quat4f(0f, 0f, 0f, -1f);
    private Vector3f    mTranslation = new Vector3f();
    private float       mScale = 1.0f;

    public TransformQ()
    {
        super();
    }

    public TransformQ(float m00, float m01, float m02, float m03, float m10,
            float m11, float m12, float m13, float m20, float m21, float m22,
            float m23, float m30, float m31, float m32, float m33)
    {
        super(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31,
                m32, m33);
    }

    public TransformQ(float[] v)
    {
        super(v);
    }

    public TransformQ(Matrix3f m1, Vector3f t1, float s)
    {
        super(m1, t1, s);
    }

    public TransformQ(Matrix4d m1)
    {
        super(m1);
    }

    public TransformQ(Matrix4f m1)
    {
        super(m1);
    }

    public TransformQ(Quat4f q1, Vector3f t1, float s)
    {
        super(q1, t1, s);
    }

    private void assemble()
    {
        set(mRotation, mTranslation, mScale);
    }
    
    private void apply(Quat4f rot)
    {
        System.out.print(mRotation+" x "+rot);
        mRotation.mul(rot, mRotation);
        mRotation.normalize();
        System.out.println(" = "+mRotation);
        assemble();
    }

    public void rotate(Tuple3f angles)
    {
        apply(Quat4fLogic.makeRotate(angles));
    }

    public void rotate(float xRadians, float yRadians, float zRadians)
    {
        apply(Quat4fLogic.makeRotate(xRadians, yRadians, zRadians));
    }

    public void rotate(float x, float y, float z, float aRadians)
    {
        apply(Quat4fLogic.makeRotate(x, y, z, aRadians));
    }

    public void rotateX(float aRadians)
    {
        apply(Quat4fLogic.makeRotX(aRadians));
    }

    public void rotateY(float aRadians)
    {
        apply(Quat4fLogic.makeRotY(aRadians));
    }

    public void rotateZ(float aRadians)
    {
        apply(Quat4fLogic.makeRotZ(aRadians));
    }

    public void rotateEuler(float xEuler, float yEuler, float zEuler)
    {
        rotate(xEuler*MathUtils.DEG_TO_RAD, yEuler*MathUtils.DEG_TO_RAD, zEuler*MathUtils.DEG_TO_RAD);
    }

    public void rotateEuler(float x, float y, float z, float aEuler)
    {
        rotate(x, y, z, aEuler*MathUtils.DEG_TO_RAD);
    }

    public void rotateXEuler(float aEuler)
    {
        rotateX(aEuler*MathUtils.DEG_TO_RAD);
    }

    public void rotateYEuler(float aEuler)
    {
        rotateY(aEuler*MathUtils.DEG_TO_RAD);
    }

    public void rotateZEuler(float aEuler)
    {
        rotateZ(aEuler*MathUtils.DEG_TO_RAD);
    }

    public void rotate(AxisAngle4f axis)
    {
        apply(Quat4fLogic.makeRotate(axis));
    }
    
    public void rotate(Matrix3f rot)
    {
        apply(Quat4fLogic.makeRotate(rot));
    }
    
    public void scale(float s)
    {
        mScale *= s;
        assemble();
    }

    public void translate(float x, float y, float z)
    {
        System.out.print("Translating from "+mTranslation);
        mTranslation.x += x;
        mTranslation.y += y;
        mTranslation.z += z;
        System.out.println(" to "+mTranslation);
        assemble();
    }

    public void translate(Tuple3f scale)
    {
        translate(scale.x, scale.y, scale.z);
    }
    
    public Vector3f getForward()
    {
        Vector3f fwd = new Vector3f(0, 0, 1);
        Matrix3f rot = new Matrix3f();
        get(rot);
        rot.transform(fwd);
        return fwd;
    }
    
    public Vector3f getUp()
    {
        Vector3f up = new Vector3f(0, 1, 0);
        Matrix3f rot = new Matrix3f();
        get(rot);
        rot.transform(up);
        return up;
    }
    
    public Vector3f getRight()
    {
        Vector3f right = new Vector3f(1, 0, 0);
        Matrix3f rot = new Matrix3f();
        get(rot);
        rot.transform(right);
        return right;
    }
    
    public void advance(float dist)
    {
        Vector3f fwd = getForward();
        fwd.scale(dist);
        translate(fwd);
    }
    
    public void rotateAround(Tuple3f around, Tuple3f rot)
    {
        translate(around);
        rotate(rot);
        translate(around.scaleBy(-1));
    }
    
    public void yaw(float aRadians)
    {
        Vector3f axis = getUp();
        System.out.println("Yawing around "+axis);
        rotate(axis.x, axis.y, axis.z, aRadians);
        //rotate(0, 1, 0, aRadians);
    }
    
    public void pitch(float aRadians)
    {
        Vector3f axis = getRight();
        System.out.println("Pitching around "+axis);
        rotate(axis.x, axis.y, axis.z, aRadians);
        //rotate(1, 0, 0, aRadians);
    }
    
    public void roll(float aRadians)
    {
        Vector3f axis = getForward();
        System.out.println("Rolling around "+axis);
        rotate(axis.x, axis.y, axis.z, aRadians);
        //rotate(0, 0, 1, aRadians);
    }

    public void reset()
    {
        mRotation = new Quat4f(0f, 0f, 0f, -1f);
        assemble();
    }
}
