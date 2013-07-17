package jo.vecmath.logic;

import jo.vecmath.Tuple2f;

public class Tuple2fLogic extends MathUtils
{
    public static float mag(Tuple2f v)
    {
        return (float)Math.sqrt(v.x*v.x + v.y*v.y);
    }
    
    public static void normalize(Tuple2f v)
    {
        v.scale(1/mag(v));
    }
}
