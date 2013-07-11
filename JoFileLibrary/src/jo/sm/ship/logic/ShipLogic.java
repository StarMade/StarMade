package jo.sm.ship.logic;

import java.util.Map;

import jo.sm.data.Vector3i;
import jo.sm.ship.data.Chunk;
import jo.sm.ship.data.Data;

public class ShipLogic
{
    public static void getBounds(Data datum, Vector3i lower, Vector3i upper)
    {
        boolean first = true;
        for (Chunk c : datum.getChunks())
        {
            Vector3i pos = c.getPosition();
            for (int z = 0; z < 16; z++)
                for (int y = 0; y < 16; y++)
                    for (int x = 0; x < 16; x++)
                    {
                        if (c.getBlocks()[x][y][z].getBlockID() < 0)
                            continue;
                        if (first)
                        {
                            lower.a = pos.a + x;
                            lower.b = pos.b + y;
                            lower.c = pos.c + z;
                            upper.a = pos.a + x;
                            upper.b = pos.b + y;
                            upper.c = pos.c + z;
                            first = false;
                        }
                        else
                        {
                            lower.a = Math.min(lower.a, pos.a + x);
                            lower.b = Math.min(lower.b, pos.b + y);
                            lower.c = Math.min(lower.c, pos.c + z);
                            upper.a = Math.max(upper.a, pos.a + x);
                            upper.b = Math.max(upper.b, pos.b + y);
                            upper.c = Math.max(upper.c, pos.c + z);
                        }
                    }
        }
    }
    
    public static void getBounds(Map<Vector3i,Data> data, Vector3i lower, Vector3i upper)
    {
        boolean first = true;
        for (Vector3i o : data.keySet())
        {
            Vector3i l = new Vector3i();
            Vector3i u = new Vector3i();
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
                lower.a = Math.min(lower.a, l.a);
                lower.b = Math.min(lower.b, l.b);
                lower.c = Math.min(lower.c, l.c);
                upper.a = Math.max(upper.a, u.a);
                upper.b = Math.max(upper.b, u.b);
                upper.c = Math.max(upper.c, u.c);
            }
        }
    }
    
    private static void adjustByBigChunk(Vector3i v, Vector3i mod)
    {
        // looks like no mod is needed
//        v.a += mod.a*16*16;
//        v.b += mod.b*16*16;
//        v.c += mod.c*16*16;
    }
}
