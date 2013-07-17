package jo.vecmath.logic;

import jo.vecmath.AxisAngle4f;
import jo.vecmath.Matrix3f;
import jo.vecmath.Matrix4d;
import jo.vecmath.Matrix4f;
import jo.vecmath.Point3f;
import jo.vecmath.Quat4f;
import jo.vecmath.Tuple3f;
import jo.vecmath.Vector3f;

public class Transform extends Matrix4f
{
    /**
     * 
     */
    private static final long serialVersionUID = -6216638219578138997L;

    public Transform()
    {
        super();
    }

    public Transform(float m00, float m01, float m02, float m03, float m10,
            float m11, float m12, float m13, float m20, float m21, float m22,
            float m23, float m30, float m31, float m32, float m33)
    {
        super(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31,
                m32, m33);
    }

    public Transform(float[] v)
    {
        super(v);
    }

    public Transform(Matrix3f m1, Vector3f t1, float s)
    {
        super(m1, t1, s);
    }

    public Transform(Matrix4d m1)
    {
        super(m1);
    }

    public Transform(Matrix4f m1)
    {
        super(m1);
    }

    public Transform(Quat4f q1, Vector3f t1, float s)
    {
        super(q1, t1, s);
    }
    
    public void prepend(Matrix4f m)
    {
        mul(m, this);
    }
    
    public void append(Matrix4f m)
    {
        mul(this, m);
    }

    public void rotate(Tuple3f angles)
    {
        prepend(Matrix4fLogic.makeRotateMatrix(angles));
    }

    public void rotate(float xRadians, float yRadians, float zRadians)
    {
        prepend(Matrix4fLogic.makeRotateMatrix(xRadians, yRadians, zRadians));
    }

    public void rotate(float x, float y, float z, float aRadians)
    {
        prepend(Matrix4fLogic.makeRotateMatrix(x, y, z, aRadians));
    }

    public void rotateX(float aRadians)
    {
        prepend(Matrix4fLogic.makeRotXMatrix(aRadians));
    }

    public void rotateY(float aRadians)
    {
        prepend(Matrix4fLogic.makeRotYMatrix(aRadians));
    }

    public void rotateZ(float aRadians)
    {
        prepend(Matrix4fLogic.makeRotZMatrix(aRadians));
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
        prepend(Matrix4fLogic.makeRotateMatrix(axis));
    }
    
    public void rotate(Matrix3f rot)
    {
        prepend(Matrix4fLogic.makeRotateMatrix(rot));
    }

    public void scale(Tuple3f scale)
    {
        prepend(Matrix4fLogic.makeScaleMatrix(scale.x, scale.y, scale.z));
    }
    
    public void scale(float s)
    {
        prepend(Matrix4fLogic.makeScaleMatrix(s, s, s));
    }
    
    public void scale(float x, float y, float z)
    {
        prepend(Matrix4fLogic.makeScaleMatrix(x, y, z));        
    }

    public void translate(float x, float y, float z)
    {
        prepend(Matrix4fLogic.makeTranslateMatrix(new Point3f(x, y, z)));
    }

    public void translate(Tuple3f scale)
    {
        prepend(Matrix4fLogic.makeTranslateMatrix(scale));
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
        rotate(axis.x, axis.y, axis.z, aRadians);
    }
    
    public void pitch(float aRadians)
    {
        Vector3f axis = getRight();
        rotate(axis.x, axis.y, axis.z, aRadians);
    }
    
    public void roll(float aRadians)
    {
        Vector3f axis = getForward();
        rotate(axis.x, axis.y, axis.z, aRadians);
    }
}
