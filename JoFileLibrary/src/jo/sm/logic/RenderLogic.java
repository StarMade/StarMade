package jo.sm.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Point3f;
import javax.vecmath.Point3i;

import jo.sm.data.CubeIterator;
import jo.sm.data.RenderTile;
import jo.sm.data.SparseMatrix;
import jo.sm.ship.data.Block;

public class RenderLogic
{
    public static List<RenderTile> getRender(SparseMatrix<Block> blocks)
    {
        List<RenderTile> polys = new ArrayList<RenderTile>();
        Point3i lower = new Point3i();
        Point3i upper = new Point3i();
        blocks.getBounds(lower, upper);
        getBasicPolys(blocks, upper, lower, polys);
        return polys;
    }

    private static void getBasicPolys(SparseMatrix<Block> blocks,
            Point3i upper, Point3i lower, List<RenderTile> polys)
    {
        for (CubeIterator i = new CubeIterator(lower, upper); i.hasNext(); )
        {
            Point3i p = i.next();
            if (!blocks.contains(p))
                continue;
            if (!blocks.contains(new Point3i(p.x + 1, p.y, p.z)))
            {
                RenderTile rp = new RenderTile();
                rp.setBlock(blocks.get(p));
                rp.setFacing(RenderTile.XP);
                rp.setCenter(new Point3i(p.x + 1, p.y, p.z));
                polys.add(rp);
            }
            if (!blocks.contains(new Point3i(p.x - 1, p.y, p.z)))
            {
                RenderTile rp = new RenderTile();
                rp.setBlock(blocks.get(p));
                rp.setFacing(RenderTile.XM);
                rp.setCenter(new Point3i(p));
                polys.add(rp);
            }
            if (!blocks.contains(new Point3i(p.x, p.y + 1, p.z)))
            {
                RenderTile rp = new RenderTile();
                rp.setBlock(blocks.get(p));
                rp.setFacing(RenderTile.YP);
                rp.setCenter(new Point3i(p.x, p.y + 1, p.z));
                polys.add(rp);
            }
            if (!blocks.contains(new Point3i(p.x, p.y - 1, p.z)))
            {
                RenderTile rp = new RenderTile();
                rp.setBlock(blocks.get(p));
                rp.setFacing(RenderTile.YM);
                rp.setCenter(new Point3i(p));
                polys.add(rp);
            }
            if (!blocks.contains(new Point3i(p.x, p.y, p.z + 1)))
            {
                RenderTile rp = new RenderTile();
                rp.setBlock(blocks.get(p));
                rp.setFacing(RenderTile.ZP);
                rp.setCenter(new Point3i(p.x, p.y, p.z + 1));
                polys.add(rp);
            }
            if (!blocks.contains(new Point3i(p.x, p.y, p.z - 1)))
            {
                RenderTile rp = new RenderTile();
                rp.setBlock(blocks.get(p));
                rp.setFacing(RenderTile.ZM);
                rp.setCenter(new Point3i(p));
                polys.add(rp);
            }
        }
    }
    
    public static void transformAndSort(List<RenderTile> tiles, Matrix4f transform)
    {
        Matrix3f rot = new Matrix3f();
        transform.get(rot);

        boolean[] showing = new boolean[6];
        Point3f xp = new Point3f(1, 0, 0);
        rot.transform(xp);
        showing[RenderTile.XP] = xp.z < 0;
        showing[RenderTile.XM] = !showing[RenderTile.XP];
        Point3f yp = new Point3f(0, 1, 0);
        rot.transform(yp);
        showing[RenderTile.YP] = yp.z < 0;
        showing[RenderTile.YM] = !showing[RenderTile.YP];
        Point3f zp = new Point3f(0, 0, 1);
        rot.transform(zp);
        showing[RenderTile.ZP] = zp.z < 0;
        showing[RenderTile.ZM] = !showing[RenderTile.ZP];
        //System.out.println("Showing +x="+showing[0]+", -x="+showing[1]+", +y="+showing[2]+", -y="+showing[3]+", +z="+showing[4]+", -z="+showing[5]);
        for (RenderTile tile : tiles)
        {
            if (!showing[tile.getFacing()])
            {
                tile.setVisual(null);
                continue;
            }
            Point3i center = tile.getCenter();
            Point3f visual = new Point3f(center.x, center.y, center.z);
            transform.transform(visual);
            tile.setVisual(visual);
        }
        Collections.sort(tiles, new Comparator<RenderTile>(){
            @Override
            public int compare(RenderTile tile1, RenderTile tile2)
            {
                if (tile1.getVisual() == null)
                    if (tile2.getVisual() == null)
                        return 0;
                    else
                        return 1;
                else
                    if (tile2.getVisual() == null)
                        return -1;
                    else
                        return (int)Math.signum(tile2.getVisual().z - tile1.getVisual().z);
            }            
        });
    }
}
