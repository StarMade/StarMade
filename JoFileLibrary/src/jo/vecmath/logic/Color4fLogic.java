package jo.vecmath.logic;

import jo.vecmath.Color4f;

public class Color4fLogic extends Tuple4fLogic
{

    public static Color4f interpolate(float v, float low, float high, Color4f lowColor, Color4f highColor)
    {
        return new Color4f(
                interpolate(v, low, high, lowColor.x, highColor.x),
                interpolate(v, low, high, lowColor.y, highColor.y),
                interpolate(v, low, high, lowColor.z, highColor.z),
                interpolate(v, low, high, lowColor.w, highColor.w)
                );
    }
}
