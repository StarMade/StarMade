package jo.vecmath.logic;

import jo.vecmath.AxisAngle4f;
import jo.vecmath.Matrix3f;
import jo.vecmath.Matrix4f;
import jo.vecmath.Point3f;
import jo.vecmath.Tuple3f;
import jo.vecmath.Vector3f;

public class Matrix4fLogic extends MathUtils
{
    public static void rotateEuler(Matrix4f m, Tuple3f angles)
    {
        m.mul(makeRotateEulerMatrix(angles), m);
    }

    public static void rotateEuler(Matrix4f m, float x, float y, float z)
    {
        m.mul(makeRotateEulerMatrix(x, y, z), m);
    }

    public static void rotateEulerPre(Matrix4f m, Tuple3f angles)
    {
        m.mul(m, makeRotateEulerMatrix(angles));
    }

    public static void rotateEulerPre(Matrix4f m, float x, float y, float z)
    {
        m.mul(m, makeRotateEulerMatrix(x, y, z));
    }

    // angles in degrees
    public static Matrix4f makeRotateEulerMatrix(Tuple3f angles)
    {
        return makeRotateEulerMatrix(angles.x, angles.y, angles.z);
    }
    
    // angles in degrees
    public static Matrix4f makeRotateEulerMatrix(float x, float y, float z)
    {
        Matrix4f m = new Matrix4f();
        m.setIdentity();
        rotX(m, x*DEG_TO_RAD);
        rotY(m, y*DEG_TO_RAD);
        rotZ(m, z*DEG_TO_RAD);
        return m;
    }
    
    public static void rotate(Matrix4f m, Tuple3f angles)
    {
        m.mul(makeRotateMatrix(angles), m);
    }

    // angles in degrees
    public static Matrix4f makeRotateMatrix(Tuple3f angles)
    {
        return makeRotateMatrix(angles.x, angles.y, angles.z);
    }

    // angles in degrees
    public static Matrix4f makeRotateMatrix(float x, float y, float z)
    {
        Matrix4f m = new Matrix4f();
        m.setIdentity();
        rotX(m, x);
        rotY(m, y);
        rotZ(m, z);
        return m;
    }
    
    public static void rotate(Matrix4f m, float x, float y, float z, float a)
    {
        m.mul(makeRotateMatrix(x, y, z, a), m);
    }

    public static void rotate(Matrix4f m, AxisAngle4f angles)
    {
        m.mul(makeRotateMatrix(angles), m);
    }

    public static Matrix4f makeRotateMatrix(float x, float y, float z, float a)
    {
        return makeRotateMatrix(new AxisAngle4f(x, y, z, a));
    }

    public static Matrix4f makeRotateMatrix(AxisAngle4f axis)
    {
        Matrix4f m = new Matrix4f();
        m.setIdentity();
        m.set(axis);
        return m;
    }

    public static Matrix4f makeRotateMatrix(Matrix3f rot)
    {
        Matrix4f m = new Matrix4f();
        m.m00 = rot.m00;
        m.m01 = rot.m01;
        m.m02 = rot.m02;
        m.m03 = 0f;

        m.m10 = rot.m10;
        m.m11 = rot.m11;
        m.m12 = rot.m12;
        m.m13 = 0f;

        m.m20 = rot.m20;
        m.m21 = rot.m21;
        m.m22 = rot.m22;
        m.m23 = 0f;

        m.m30 = 0.0f;
        m.m31 = 0.0f;
        m.m32 = 0.0f;
        m.m33 = 1.0f;
        return m;
    }

    public static void rotX(Matrix4f m, float radians)
    {
        m.mul(makeRotXMatrix(radians), m);
    }

    public static Matrix4f makeRotXMatrix(float radians)
    {
        Matrix4f m = new Matrix4f();
        m.rotX(radians);
        return m;
    }

    public static void rotY(Matrix4f m, float radians)
    {
        m.mul(makeRotYMatrix(radians), m);
    }

    public static Matrix4f makeRotYMatrix(float radians)
    {
        Matrix4f m = new Matrix4f();
        m.rotY(radians);
        return m;
    }

    public static void rotZ(Matrix4f m, float radians)
    {
        m.mul(makeRotZMatrix(radians), m);
    }

    public static Matrix4f makeRotZMatrix(float radians)
    {
        Matrix4f m = new Matrix4f();
        m.rotZ(radians);
        return m;
    }

    public static void scale(Matrix4f m, float scale)
    {
        scale(m, new Point3f(scale, scale, scale));
    }

    public static void scale(Matrix4f m, Tuple3f scale)
    {
        m.mul(makeScaleMatrix(scale), m);
    }

    public static Matrix4f makeScaleMatrix(Tuple3f scale)
    {
        return makeScaleMatrix(scale.x, scale.y, scale.z);
    }
    
    public static Matrix4f makeScaleMatrix(float s)
    {
        return makeScaleMatrix(s, s, s);
    }
    
    public static Matrix4f makeScaleMatrix(float x, float y, float z)
    {
        Matrix4f m = new Matrix4f();
        m.m00 = x;
        m.m01 = (float) 0.0;
        m.m02 = (float) 0.0;
        m.m03 = (float) 0.0;

        m.m10 = (float) 0.0;
        m.m11 = y;
        m.m12 = (float) 0.0;
        m.m13 = (float) 0.0;

        m.m20 = (float) 0.0;
        m.m21 = (float) 0.0;
        m.m22 = z;
        m.m23 = (float) 0.0;

        m.m30 = (float) 0.0;
        m.m31 = (float) 0.0;
        m.m32 = (float) 0.0;
        m.m33 = (float) 1.0;
        return m;
    }

    public static void translate(Matrix4f m, float x, float y, float z)
    {
        translate(m, new Point3f(x, y, z));
    }

    public static void translate(Matrix4f m, Tuple3f scale)
    {
        m.mul(makeTranslateMatrix(scale), m);
    }

    public static Matrix4f makeTranslateMatrix(Tuple3f translate)
    {
        Matrix4f m = new Matrix4f();
        m.m00 = (float) 1.0;
        m.m01 = (float) 0.0;
        m.m02 = (float) 0.0;
        m.m03 = translate.x; 

        m.m10 = (float) 0.0;
        m.m11 = (float) 1.0;
        m.m12 = (float) 0.0;
        m.m13 = translate.y;

        m.m20 = (float) 0.0;
        m.m21 = (float) 0.0;
        m.m22 = (float) 1.0;
        m.m23 = translate.z;

        m.m30 = (float) 0.0;
        m.m31 = (float) 0.0;
        m.m32 = (float) 0.0;
        m.m33 = (float) 1.0;
        return m;
    }

    public static void orientateTo(Matrix4f m, Tuple3f lookAt)
    {
        m.mul(makeOrientateToMatrix(lookAt), m);
    }
    
    // return transform that will rotate (1,0,0) to the direction vector given 
    public static Matrix4f makeOrientateToMatrix(Tuple3f lookAt)
    {
        Matrix4f m = new Matrix4f();
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

    public static float[] toFloatArray(Matrix4f m)
    {
        float[] v = new float[16];
        v[ 0] = m.m00;
        v[ 1] = m.m10;
        v[ 2] = m.m20;
        v[ 3] = m.m30;

        v[ 4] = m.m01;
        v[ 5] = m.m11;
        v[ 6] = m.m21;
        v[ 7] = m.m31;

        v[ 8] = m.m02;
        v[ 9] = m.m12;
        v[10] = m.m22;
        v[11] = m.m32;

        v[12] = m.m03;
        v[13] = m.m13;
        v[14] = m.m23;
        v[15] = m.m33;
        
        return v;
    }

    public static void lookAt(Matrix4f transform, Point3f standHere, Point3f lookAtThis)
    {
        // assume +z is the direction we are looking
        Vector3f zaxis = new Vector3f(lookAtThis);
        zaxis.sub(standHere);
        zaxis.normalize();
        Vector3f provisionalUp;
        // if we're not looking along +y make that up, otherwise make it +z
        if (!MathUtils.equals(zaxis.x, 0) || !MathUtils.equals(zaxis.z, 0))
            provisionalUp = new Vector3f(0, 1, 0);
        else
            provisionalUp = new Vector3f(0, 0, 1);
        Vector3f xaxis = new Vector3f();
        xaxis.cross(zaxis, provisionalUp); // +x is left
        xaxis.normalize();
        Vector3f yaxis = new Vector3f();
        yaxis.cross(xaxis, zaxis); // +y is the real up
        yaxis.normalize();
        
        transform.m00 = xaxis.x;
        transform.m01 = yaxis.x;
        transform.m02 = zaxis.x;
        transform.m03 = standHere.x;
        transform.m10 = xaxis.y;
        transform.m11 = yaxis.y;
        transform.m12 = zaxis.y;
        transform.m13 = standHere.y;
        transform.m20 = xaxis.z;
        transform.m21 = yaxis.z;
        transform.m22 = zaxis.z;
        transform.m23 = standHere.z;
        transform.m30 = 0;
        transform.m31 = 0;
        transform.m32 = 0;
        transform.m33 = 1;
    }
}
