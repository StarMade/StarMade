package jo.sm.ship.logic;

import java.util.Map;

import jo.sm.data.CubeIterator;
import jo.sm.data.SparseMatrix;
import jo.sm.ship.data.Block;
import jo.sm.ship.data.Chunk;
import jo.sm.ship.data.Data;
import jo.vecmath.Point3i;

public class ShipLogic
{
    public static void getBounds(Data datum, Point3i lower, Point3i upper)
    {
        boolean first = true;
        for (Chunk c : datum.getChunks())
        {
            Point3i pos = c.getPosition();
            for (CubeIterator i = new CubeIterator(new Point3i(0,0,0), new Point3i(15,15,15)); i.hasNext(); )
            {
                Point3i xyz = i.next();
                if (c.getBlocks()[xyz.x][xyz.y][xyz.z].getBlockID() <= 0)
                    continue;
                if (first)
                {
                    lower.add(pos, xyz);
                    upper.add(pos, xyz);
                    first = false;
                }
                else
                {
                    lower.x = Math.min(lower.x, pos.x + xyz.x);
                    lower.y = Math.min(lower.y, pos.y + xyz.y);
                    lower.z = Math.min(lower.z, pos.z + xyz.z);
                    upper.x = Math.max(upper.x, pos.x + xyz.x);
                    upper.y = Math.max(upper.y, pos.y + xyz.y);
                    upper.z = Math.max(upper.z, pos.z + xyz.z);
                }
            }
        }
    }
    
    public static void getBounds(Map<Point3i,Data> data, Point3i lower, Point3i upper)
    {
        boolean first = true;
        for (Point3i o : data.keySet())
        {
            Point3i l = new Point3i();
            Point3i u = new Point3i();
            Data datum = data.get(o);
            getBounds(datum, l, u);
            adjustByBigChunk(l, o);
            adjustByBigChunk(u, o);
            if (first)
            {
                lower.set(l);
                upper.set(u);
                first = false;
            }
            else
            {
                lower.x = Math.min(lower.x, l.x);
                lower.y = Math.min(lower.y, l.y);
                lower.z = Math.min(lower.z, l.z);
                upper.x = Math.max(upper.x, u.x);
                upper.y = Math.max(upper.y, u.y);
                upper.z = Math.max(upper.z, u.z);
            }
        }
    }
    
    public static SparseMatrix<Block> getBlocks(Map<Point3i, Data> data)
    {
        SparseMatrix<Block> blocks = new SparseMatrix<Block>();
        for (Data datum : data.values())
            for (Chunk c : datum.getChunks())
            {
                Point3i p = c.getPosition();
                for (CubeIterator i = new CubeIterator(new Point3i(0,0,0), new Point3i(15,15,15)); i.hasNext(); )
                {
                    Point3i xyz = i.next();
                    Block b = c.getBlocks()[xyz.x][xyz.y][xyz.z];
                    if (b.getBlockID() > 0)
                        blocks.set(p.x + xyz.x, p.y + xyz.y, p.z + xyz.z, b);
                }
            }
        return blocks;
    }
    
    private static void adjustByBigChunk(Point3i v, Point3i mod)
    {
        // looks like no mod is needed
//        v.a += mod.a*16*16;
//        v.b += mod.b*16*16;
//        v.c += mod.c*16*16;
    }
}
