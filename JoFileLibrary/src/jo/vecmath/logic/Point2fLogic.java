package jo.vecmath.logic;

import jo.vecmath.Point2f;

public class Point2fLogic extends Tuple2fLogic
{
    public static Point2f interpolate(Point2f t1, Point2f t2, float alpha)
    {
        Point2f v = new Point2f();
        v.interpolate(t1, t2, alpha);
        return v;
    }
}
