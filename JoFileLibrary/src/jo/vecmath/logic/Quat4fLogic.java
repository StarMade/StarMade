package jo.vecmath.logic;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Tuple3f;

public class Quat4fLogic extends MathUtils
{
    // angles in degrees
    public static Quat4f makeRotateEuler(Tuple3f angles)
    {
        return makeRotateEuler(angles.x, angles.y, angles.z);
    }
    
    // angles in degrees
    public static Quat4f makeRotateEuler(float x, float y, float z)
    {
        return makeRotate(x*DEG_TO_RAD, y*DEG_TO_RAD, z*DEG_TO_RAD);
    }

    // angles in degrees
    public static Quat4f makeRotate(Tuple3f angles)
    {
        return makeRotate(angles.x, angles.y, angles.z);
    }

    // angles in degrees
    public static Quat4f makeRotate(float x, float y, float z)
    {
        Quat4f xRot = makeRotX(x);
        Quat4f yRot = makeRotY(y);
        Quat4f zRot = makeRotZ(z);
        Quat4f m = new Quat4f();
        m.mul(xRot, yRot);
        m.mul(zRot);
        return m;
    }

    public static Quat4f makeRotate(float x, float y, float z, float a)
    {
        return makeRotate(new AxisAngle4f(x, y, z, a));
    }

    public static Quat4f makeRotate(AxisAngle4f axis)
    {
        Quat4f m = new Quat4f();
        m.set(axis);
        return m;
    }

    public static Quat4f makeRotate(Matrix3f rot)
    {
        Quat4f m = new Quat4f();
        m.set(rot);
        m.normalize();
        return m;
    }

    public static Quat4f makeRotX(float radians)
    {
        return makeRotate(1, 0, 0, radians);
    }

    public static Quat4f makeRotY(float radians)
    {
        return makeRotate(0, 1, 0, radians);
    }

    public static Quat4f makeRotZ(float radians)
    {
        return makeRotate(0, 0, 1, radians);
    }

    public static Quat4f makeIdentity()
    {
        Matrix3f m = new Matrix3f();
        m.setIdentity();
        return makeRotate(m);
    }
}
