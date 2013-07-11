package jo.sm.data;

import java.util.HashMap;
import java.util.Map;

public class SparseMatrix<T>
{
    private Map<Integer,Map<Integer,Map<Integer,T>>> mMatrix;
    
    public SparseMatrix()
    {
        mMatrix = new HashMap<Integer, Map<Integer,Map<Integer,T>>>();
    }
    
    public void set(int x, int y, int z, T val)
    {
        Map<Integer,Map<Integer,T>> xrow = mMatrix.get(x);
        if (xrow == null)
        {
            xrow = new HashMap<Integer, Map<Integer,T>>();
            mMatrix.put(x, xrow);
        }
        Map<Integer,T> yrow = xrow.get(y);
        if (yrow == null)
        {
            yrow = new HashMap<Integer, T>();
            xrow.put(y,  yrow);
        }
        if (val == null)
            yrow.remove(z);
        else
            yrow.put(z,  val);
    }
    
    public T get(int x, int y, int z)
    {
        Map<Integer,Map<Integer,T>> xrow = mMatrix.get(x);
        if (xrow == null)
            return null;
        Map<Integer,T> yrow = xrow.get(y);
        if (yrow == null)
            return null;
        return yrow.get(z);        
    }
    
    public void set(Vector3i v, T val)
    {
        set(v.a, v.b, v.c, val);
    }
    
    public T get(Vector3i v)
    {
        return get(v.a, v.b, v.c);
    }
    
    public void getBounds(Vector3i lower, Vector3i upper)
    {
        for (Integer x : mMatrix.keySet())
        {
            lower.a = Math.min(lower.a, x);
            upper.a = Math.max(upper.a, x);
            Map<Integer,Map<Integer,T>> xrow = mMatrix.get(x);
            for (Integer y : xrow.keySet())
            {
                lower.b = Math.min(lower.b, y);
                upper.b = Math.max(upper.b, y);
                Map<Integer,T> yrow = xrow.get(y);
                for (Integer z : yrow.keySet())
                {
                    lower.c = Math.min(lower.c, z);
                    upper.c = Math.max(upper.c, z);
                }
            }
        }
    }
}
