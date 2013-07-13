package jo.vecmath.logic;
/*
 * Created on Dec 12, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */


/**
 * @author jjaquinta
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MathUtils
{
    public static final float DEG_TO_RAD = (float)(Math.PI/180);
    public static final float RAD_TO_DEG = (float)(180/Math.PI);
    public static final float Euler90 = 90*DEG_TO_RAD;
    public static final float Euler180 = 180*DEG_TO_RAD;

    public static double EPSILON = 1.0E-14;
    
    public static final float PI = (float)Math.PI; 
    
    public static boolean epsilonEquals(float f1, float f2)
    {
        return Math.abs(f1 - f2) < EPSILON;
    }
    
    public static boolean isZero(float f)
    {
        return epsilonEquals(f, 0);
    }

    public static float atan2(float y, float x)
    {
        return (float)Math.atan2(y, x);
    }

    public static float cos(float a)
    {
        return (float)Math.cos(a);
    }
    
    public static float sin(float a)
    {
        return (float)Math.sin(a);
    }
    
    public static float sqrt(float v)
    {
        return (float)Math.sqrt(v);
    }
    
    public static double interpolate(double v, double srcLow, double srcHigh,
        double targLow, double targHigh)
    {
        if (v == srcLow)
            return targLow;
        if (v == srcHigh)
            return targHigh;
        double pc = (v - srcLow)/(srcHigh - srcLow);
        return (targHigh - targLow)*pc + targLow;
    }
    
    public static float interpolate(float v, float srcLow, float srcHigh,
        float targLow, float targHigh)
    {
        if (v == srcLow)
            return targLow;
        if (v == srcHigh)
            return targHigh;
        float pc = (v - srcLow)/(srcHigh - srcLow);
        return (targHigh - targLow)*pc + targLow;
    }
    
    public static double interpolate(double v, double[] src, double[] target)
    {
        if (v < src[0])
            return target[0];
        if (v > src[src.length - 1])
            return target[target.length - 1];
        for (int i = 0; i < src.length - 1; i++)
            if ((v >= src[i]) && (v <= src[i+1]))
                return interpolate(v, src[i], src[i+1], target[i], target[i+1]);
        return target[0];
    }
    
    public static double interpolate(double idx, double[] vals)
    {
        int low = (int)Math.floor(idx);
        int high = (int)Math.ceil(idx);
        if (low == high)
        {
            if (low < 0)
            {
                double delta = vals[1] - vals[0];
                return vals[0] + low*delta; 
            }
            else if (low >= vals.length)
            {
                double delta = vals[vals.length - 1] - vals[vals.length - 2];
                return vals[vals.length - 1] + (low - vals.length + 1)*delta;
            }
            else
                return vals[low];
        }
        double lowVal;
        double highVal;
        if (low < 0)
        {
            double delta = vals[1] - vals[0];
            lowVal = vals[0] + low*delta; 
        }
        else if (low >= vals.length)
        {
            double delta = vals[vals.length - 1] - vals[vals.length - 2];
            lowVal = vals[vals.length - 1] + (low - vals.length + 1)*delta;
        }
        else
        {
            lowVal = vals[low];
        }
        if (high < 0)
        {
            double delta = vals[1] - vals[0];
            highVal = vals[0] + high*delta; 
        }
        else if (high >= vals.length)
        {
            double delta = vals[vals.length - 1] - vals[vals.length - 2];
            highVal = vals[vals.length - 1] + (high - vals.length + 1)*delta;
        }
        else
        {
            highVal = vals[high];
        }
        return interpolate(idx, low, high, lowVal, highVal);
    }

    public static double interpolateSin(double v, double sLow, double sHigh, double tLow, double tHigh)
    {
        double pc = (v - sLow)/(sHigh - sLow);
        pc = Math.sin(pc*Math.PI/2);
        double targ = (tHigh - tLow)*pc + tLow;
        return targ;
    }
    public static int interpolateSin(int v, int sLow, int sHigh, int tLow, int tHigh)
    {
        return (int)interpolateSin((double)v, (double)sLow, (double)sHigh, (double)tLow, (double)tHigh);
    }
    public static double interpolateCos(double v, double sLow, double sHigh, double tLow, double tHigh)
    {
        double pc = (v - sLow)/(sHigh - sLow);
        pc = 1.0 - Math.cos(pc*Math.PI/2);
        double targ = (tHigh - tLow)*pc + tLow;
        return targ;
    }
    public static int interpolateCos(int v, int sLow, int sHigh, int tLow, int tHigh)
    {
        return (int)interpolateCos((double)v, (double)sLow, (double)sHigh, (double)tLow, (double)tHigh);
    }

    public static double tableLookup(int col1, double v1, int col2, double[][] table)
    {
        int low = 0;
        int high = table.length - 1;
        if (v1 <= table[low][col1])
            return table[low][col2];
        if (v1 >= table[high][col1])
            return table[high][col2];
        while (high - low > 1)
        {
            int mid = (high + low)/2;
            if (v1 < table[mid][col1])
                high = mid;
            else if (v1 > table[mid][col1])
                low = mid;
            else
                return table[mid][col2];
        }
        double pc = (v1 - table[low][col1])/(table[high][col1] - table[low][col1]);
        double ret = (table[high][col2] - table[low][col2])*pc + table[low][col2];
        return ret;
    }
    
    public static double bind(double value, double low, double high)
    {
        if (value < low)
            return low;
        if (value > high)
            return high;
        return value;
    }

    /**
     * Calculate Log base 10.
     * @param v value
     * @return double log
     */
    public static double log10(double v)
    {
        return Math.log(v) / 2.302585092994;
    }
    
    public static double min(double[] range)
    {
        double min = range[0];
        for (int i = 1; i < range.length; i++)
            if (range[i] < min)
                min = range[i];
        return min;
    }
    
    public static double max(double[] range)
    {
        double max = range[0];
        for (int i = 1; i < range.length; i++)
            if (range[i] > max)
                max = range[i];
        return max;
    }

    public static int sgn(double d)
    {
        if (d < 0)
            return -1;
        if (d > 0)
            return 1;
        return 0;
    }
    
    public static double dist(double x1, double y1, double x2, double y2)
    {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public static double dist(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        double dx = x1 - x2;
        double dy = y1 - y2;
        double dz = z1 - z2;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
    
    public static double area(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        double a = dist(x1, y1, x2, y2);
        double b = dist(x2, y2, x3, y3);
        double c = dist(x3, y3, x1, y1);
        double d = .25*Math.sqrt(
                (a + b + c)
                *(b + c - a)
                *(c + a - b)
                *(a + b - c)
                );
        return d;
    }
    
    public static double area(double x1, double y1, double z1, 
            double x2, double y2, double z2,
            double x3, double y3, double z3)
    {
        double a = dist(x1, y1, z1, x2, y2, z2);
        double b = dist(x2, y2, z2, x3, y3, z3);
        double c = dist(x3, y3, z3, x1, y1, z1);
        double d = .25*Math.sqrt(
                (a + b + c)
                *(b + c - a)
                *(c + a - b)
                *(a + b - c)
                );
        return d;
    }   
    
    public static boolean equals(double v1, double v2)
    {
        return Math.abs(v1 - v2) < EPSILON;
    }
    
    public static boolean isColinear(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        // trivial colinear test
        if (equals(y1, y2) && equals(y2, y3))
            return true;
        if (equals(x1, x2) && equals(x2, x3))
            return true;
        // if one slope is infinity, can't be colinear since we've already tested trivial case above
        if (equals(x1, x2) || equals(x2, x3))
            return false;
        // now compare slopes
        double slope1 = (y1 - y2)/(x1 - x2);
        double slope2 = (y1 - y3)/(x1 - x3);
        return equals(slope1, slope2);
    }
    
    // http://www.vb-helper.com/howto_segments_intersect.html
    public static double[] isLineSegmentsIntersect(double l1p1x, double l1p1y, double l1p2x, double l1p2y, 
            double l2p1x, double l2p1y, double l2p2x, double l2p2y)
    {
        double dx = l1p2x - l1p1x;
        double dy = l1p2y - l1p1y;
        double da = l2p2x - l2p1x;
        double db = l2p2y - l2p1y;
        if ((da*dy - db*dx) == 0)
            return null; // The segments are parallel.
        double s = (dx * (l2p1y - l1p1y) + dy * (l1p1x - l2p1x)) / (da * dy - db * dx);
        double t = (da * (l1p1y - l2p1y) + db * (l2p1x - l1p1x)) / (db * dx - da * dy);
        boolean intersect = (s >= 0) && (s <= 1) && (t >= 0) && (t <= 1);
        if (intersect)
            return new double[] { l1p1x + t*dx, l1p1y + t*dy };
        else
            return null;
    }
}
