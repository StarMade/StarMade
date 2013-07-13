package jo.vecmath.logic;

import javax.vecmath.Matrix4f;
import javax.vecmath.Point3f;

public class Point3fLogic extends Tuple3fLogic
{
    public static void rotateBy(Point3f v, float x, float y, float z)
    {
        Matrix4f m = Matrix4fLogic.makeRotateMatrix(x, y, z);
        m.transform(v);
    }

    public static Point3f rotateNew(Point3f vOrig, float x, float y, float z)
    {
        Point3f v = new Point3f(vOrig);
        rotateBy(v, x, y, z);
        return v;
    }

    public static Point3f interpolate(Point3f t1, Point3f t2, float alpha)
    {
        Point3f v = new Point3f();
        v.interpolate(t1, t2, alpha);
        return v;
    }
}
