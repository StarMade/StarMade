package jo.sm.data;

import javax.vecmath.Point3f;
import javax.vecmath.Point3i;

public class RenderTile
{
    public static final int XP = 0;
    public static final int XM = 1;
    public static final int YP = 2;
    public static final int YM = 3;
    public static final int ZP = 4;
    public static final int ZM = 5;

    private int             mFacing;
    private Point3i        mCenter;
    private Point3f        mVisual;

    public int getFacing()
    {
        return mFacing;
    }

    public void setFacing(int facing)
    {
        mFacing = facing;
    }

    public Point3i getCenter()
    {
        return mCenter;
    }

    public void setCenter(Point3i center)
    {
        mCenter = center;
    }

    public Point3f getVisual()
    {
        return mVisual;
    }

    public void setVisual(Point3f visual)
    {
        mVisual = visual;
    }
}
