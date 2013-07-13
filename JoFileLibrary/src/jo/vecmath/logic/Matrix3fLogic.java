package jo.vecmath.logic;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;

public class Matrix3fLogic extends MathUtils
{
    public static void rotateEuler(Matrix3f m, Tuple3f angles)
    {
        m.mul(makeRotateEulerMatrix(angles), m);
    }

    public static void rotateEuler(Matrix3f m, float x, float y, float z)
    {
        m.mul(makeRotateEulerMatrix(x, y, z), m);
    }

    public static void rotateEulerPre(Matrix3f m, Tuple3f angles)
    {
        m.mul(m, makeRotateEulerMatrix(angles));
    }

    public static void rotateEulerPre(Matrix3f m, float x, float y, float z)
    {
        m.mul(m, makeRotateEulerMatrix(x, y, z));
    }

    // angles in degrees
    public static Matrix3f makeRotateEulerMatrix(Tuple3f angles)
    {
        return makeRotateEulerMatrix(angles.x, angles.y, angles.z);
    }
    
    // angles in degrees
    public static Matrix3f makeRotateEulerMatrix(float x, float y, float z)
    {
        Matrix3f m = new Matrix3f();
        m.setIdentity();
        rotX(m, x*DEG_TO_RAD);
        rotY(m, y*DEG_TO_RAD);
        rotZ(m, z*DEG_TO_RAD);
        return m;
    }
    
    public static void rotate(Matrix3f m, Tuple3f angles)
    {
        m.mul(makeRotateMatrix(angles), m);
    }

    // angles in degrees
    public static Matrix3f makeRotateMatrix(Tuple3f angles)
    {
        return makeRotateMatrix(angles.x, angles.y, angles.z);
    }

    // angles in degrees
    public static Matrix3f makeRotateMatrix(float x, float y, float z)
    {
        Matrix3f m = new Matrix3f();
        m.setIdentity();
        rotX(m, x);
        rotY(m, y);
        rotZ(m, z);
        return m;
    }
    
    public static void rotate(Matrix3f m, float x, float y, float z, float a)
    {
        m.mul(makeRotateMatrix(x, y, z, a), m);
    }

    public static void rotate(Matrix3f m, AxisAngle4f angles)
    {
        m.mul(makeRotateMatrix(angles), m);
    }

    public static Matrix3f makeRotateMatrix(float x, float y, float z, float a)
    {
        return makeRotateMatrix(new AxisAngle4f(x, y, z, a));
    }

    public static Matrix3f makeRotateMatrix(AxisAngle4f axis)
    {
        Matrix3f m = new Matrix3f();
        m.setIdentity();
        m.set(axis);
        return m;
    }

    public static void rotX(Matrix3f m, float radians)
    {
        m.mul(makeRotXMatrix(radians), m);
    }

    public static Matrix3f makeRotXMatrix(float radians)
    {
        Matrix3f m = new Matrix3f();
        m.rotX(radians);
        return m;
    }

    public static void rotY(Matrix3f m, float radians)
    {
        m.mul(makeRotYMatrix(radians), m);
    }

    public static Matrix3f makeRotYMatrix(float radians)
    {
        Matrix3f m = new Matrix3f();
        m.rotY(radians);
        return m;
    }

    public static void rotZ(Matrix3f m, float radians)
    {
        m.mul(makeRotZMatrix(radians), m);
    }

    public static Matrix3f makeRotZMatrix(float radians)
    {
        Matrix3f m = new Matrix3f();
        m.rotZ(radians);
        return m;
    }

    public static void orientateTo(Matrix3f m, Tuple3f lookAt)
    {
        m.mul(makeOrientateToMatrix(lookAt), m);
    }
    
    // return transform that will rotate (1,0,0) to the direction vector given 
    public static Matrix3f makeOrientateToMatrix(Tuple3f lookAt)
    {
        Matrix3f m = new Matrix3f();
        m.setIdentity();
        boolean xZero = epsilonEquals(lookAt.x, 0);
        boolean yZero = epsilonEquals(lookAt.y, 0);
        boolean zZero = epsilonEquals(lookAt.z, 0);
        // evaluate trivial options
        if (xZero)
        {
            if (yZero)
            {
                if (zZero)
                    ; // no op
                else if (lookAt.z < 0)
                    rotY(m, Euler90);
                else
                    rotY(m, -Euler90);
            }
            else if (zZero)
            {
                if (lookAt.y < 0)
                    rotZ(m, -Euler90);
                else
                    rotZ(m, Euler90);                
            }
            else
            {   // YZ plane
                float a = atan2(lookAt.y, lookAt.z);
                rotX(m, a);
                if (Math.signum(lookAt.y) == Math.signum(lookAt.z))
                    rotZ(m, Euler90);
                else
                    rotZ(m, -Euler90);
            }
        }
        else if (yZero)
        {
            if (zZero)
                if (lookAt.x < 0)
                    rotY(m, Euler180);
                else
                    ;
            else
            {   // XZ plane
                float a = atan2(lookAt.z, lookAt.x);
                rotY(m, -a);
            }
        }
        else if (zZero)
        {   // XY plane
            float a = atan2(lookAt.y, lookAt.x);
            rotZ(m, a);
        }
        else
        {
            float xy = (float)Math.sqrt(lookAt.x*lookAt.x + lookAt.y*lookAt.y);
            rotZ(m, atan2(lookAt.y, lookAt.x));
            rotY(m, -atan2(lookAt.z, xy));
        }
        return m;
    }

    public static float[] toFloatArray(Matrix3f m)
    {
        float[] v = new float[9];
        v[ 0] = m.m00;
        v[ 1] = m.m10;
        v[ 2] = m.m20;

        v[ 3] = m.m01;
        v[ 4] = m.m11;
        v[ 5] = m.m21;

        v[ 6] = m.m02;
        v[ 7] = m.m12;
        v[ 8] = m.m22;
        
        return v;
    }
}
