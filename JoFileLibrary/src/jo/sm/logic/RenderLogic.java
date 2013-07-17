package jo.sm.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jo.sm.data.BlockTypes;
import jo.sm.data.CubeIterator;
import jo.sm.data.RenderTile;
import jo.sm.data.SparseMatrix;
import jo.sm.ship.data.Block;
import jo.vecmath.Matrix3f;
import jo.vecmath.Matrix4f;
import jo.vecmath.Point3f;
import jo.vecmath.Point3i;

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
        for (int z = lower.z; z <= upper.z; z++)
        {
            System.out.println("Z="+z);
            for (int y = lower.y; y <= upper.y; y++)
            {
                if (y < 10)
                    System.out.print(" ");
                System.out.print(y+": ");
                for (int x = lower.x; x <= upper.x; x++)
                {
                    Block b = blocks.get(x, y, z);
                    /*
                    if (b == null)
                        System.out.print(" ------------------------");
                    else
                        System.out.print(" "+b.getOrientation()+":"+StringUtils.zeroPrefix(Integer.toBinaryString(b.getBitfield()), 24));
                        */
                    if (b == null)
                        System.out.print(" --");
                    else
                        System.out.print(" "+StringUtils.spacePrefix(Integer.toString(b.getOrientation()), 2));
                }
                System.out.println();
            }
        }
        for (CubeIterator i = new CubeIterator(lower, upper); i.hasNext(); )
        {
            Point3i p = i.next();
            if (!blocks.contains(p))
                continue;
            Block b = blocks.get(p);
            if (BlockTypes.isCorner(b.getBlockID()))
                doCorner(blocks, p, polys);
            else if (BlockTypes.isWedge(b.getBlockID()))
                doWedge(blocks, p, polys);
            else
               doCube(blocks, p, polys);
        }
    }
    
    private static void doCorner(SparseMatrix<Block> blocks, Point3i p, List<RenderTile> polys)
    {
    }

    private static void doWedge(SparseMatrix<Block> blocks, Point3i p, List<RenderTile> polys)
    {
        System.out.println("Do wedge with a ori="+blocks.get(p).getOrientation());
        switch (blocks.get(p).getOrientation())
        {
            case 0: // YPZM
                doXMSquare(blocks, p, polys, RenderTile.TRI4);
                doXPSquare(blocks, p, polys, RenderTile.TRI4);
                // no YP face
                doYMSquare(blocks, p, polys, RenderTile.SQUARE);
                doZPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no ZM face
                break;
            case 1: // XMYP
                doXPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no XM face
                // no YP face
              doYMSquare(blocks, p, polys, RenderTile.SQUARE);
                doZMSquare(blocks, p, polys, RenderTile.TRI2);
                doZPSquare(blocks, p, polys, RenderTile.TRI2);
                break;
            case 2: // YPZP
                doXMSquare(blocks, p, polys, RenderTile.TRI1);
                doXPSquare(blocks, p, polys, RenderTile.TRI1);
                // no YP face
                doYMSquare(blocks, p, polys, RenderTile.SQUARE);
                // no ZP face
                doZMSquare(blocks, p, polys, RenderTile.SQUARE);
                break;
            case 3: // XPYP
                // no XP face
                doXMSquare(blocks, p, polys, RenderTile.SQUARE);
                // no YP face
                doYMSquare(blocks, p, polys, RenderTile.SQUARE);
                doZMSquare(blocks, p, polys, RenderTile.TRI1);
                doZPSquare(blocks, p, polys, RenderTile.TRI1);
                break;
            case 4: // YMZM
                doXMSquare(blocks, p, polys, RenderTile.TRI3);
                doXPSquare(blocks, p, polys, RenderTile.TRI3);
                doYPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no YM face
                doZPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no ZM face
                break;
            case 5: // XPYM
                // no XP face
                doXMSquare(blocks, p, polys, RenderTile.SQUARE);
                doYPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no YM face
                doZMSquare(blocks, p, polys, RenderTile.TRI4);
                doZPSquare(blocks, p, polys, RenderTile.TRI4);
                break;
            case 6: // YMZP
                doXMSquare(blocks, p, polys, RenderTile.TRI2);
                doXPSquare(blocks, p, polys, RenderTile.TRI2);
                doYPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no YM face
                // no ZP face
                doZMSquare(blocks, p, polys, RenderTile.SQUARE);
                break;
            case 7: // XMYM
                doXPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no XM face
                doYPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no YM face
                doZMSquare(blocks, p, polys, RenderTile.TRI3);
                doZPSquare(blocks, p, polys, RenderTile.TRI3);
                break;
            case 8: // XPZM
                // no XP face
                doXMSquare(blocks, p, polys, RenderTile.SQUARE);
                doYPSquare(blocks, p, polys, RenderTile.TRI2);
                doYMSquare(blocks, p, polys, RenderTile.TRI2);
                doZPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no ZM face
                break;
            case 10: // XMZM
                doXPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no XM face
                doYPSquare(blocks, p, polys, RenderTile.TRI3);
                doYMSquare(blocks, p, polys, RenderTile.TRI3);
                doZPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no ZM face
                break;
            case 11: // XMZP
                doXPSquare(blocks, p, polys, RenderTile.SQUARE);
                // no XM face
                doYPSquare(blocks, p, polys, RenderTile.TRI4);
                doYMSquare(blocks, p, polys, RenderTile.TRI4);
                // no ZP face
                doZMSquare(blocks, p, polys, RenderTile.SQUARE);
                break;
            case 13: // XPZP
                // no XP face
                doXMSquare(blocks, p, polys, RenderTile.SQUARE);
                doYPSquare(blocks, p, polys, RenderTile.TRI1);
                doYMSquare(blocks, p, polys, RenderTile.TRI1);
                // no ZP face
                doZMSquare(blocks, p, polys, RenderTile.SQUARE);
                break;
        }
    }
    
    private static void doCube(SparseMatrix<Block> blocks, Point3i p, List<RenderTile> polys)
    {
        doXPSquare(blocks, p, polys, RenderTile.SQUARE);
        doXMSquare(blocks, p, polys, RenderTile.SQUARE);
        doYPSquare(blocks, p, polys, RenderTile.SQUARE);
        doYMSquare(blocks, p, polys, RenderTile.SQUARE);
        doZPSquare(blocks, p, polys, RenderTile.SQUARE);
        doZMSquare(blocks, p, polys, RenderTile.SQUARE);
    }

    private static void doZMSquare(SparseMatrix<Block> blocks, Point3i p,
            List<RenderTile> polys, int type)
    {
        if (!blocks.contains(new Point3i(p.x, p.y, p.z - 1)))
        {
            RenderTile rp = new RenderTile();
            rp.setBlock(blocks.get(p));
            rp.setFacing(RenderTile.ZM);
            rp.setCenter(new Point3i(p));
            rp.setType(type);
            polys.add(rp);
        }
    }

    private static void doZPSquare(SparseMatrix<Block> blocks, Point3i p,
            List<RenderTile> polys, int type)
    {
        if (!blocks.contains(new Point3i(p.x, p.y, p.z + 1)))
        {
            RenderTile rp = new RenderTile();
            rp.setBlock(blocks.get(p));
            rp.setFacing(RenderTile.ZP);
            rp.setCenter(new Point3i(p.x, p.y, p.z + 1));
            rp.setType(type);
            polys.add(rp);
        }
    }

    private static void doYMSquare(SparseMatrix<Block> blocks, Point3i p,
            List<RenderTile> polys, int type)
    {
        if (!blocks.contains(new Point3i(p.x, p.y - 1, p.z)))
        {
            RenderTile rp = new RenderTile();
            rp.setBlock(blocks.get(p));
            rp.setFacing(RenderTile.YM);
            rp.setCenter(new Point3i(p));
            rp.setType(type);
            polys.add(rp);
        }
    }

    private static void doYPSquare(SparseMatrix<Block> blocks, Point3i p,
            List<RenderTile> polys, int type)
    {
        if (!blocks.contains(new Point3i(p.x, p.y + 1, p.z)))
        {
            RenderTile rp = new RenderTile();
            rp.setBlock(blocks.get(p));
            rp.setFacing(RenderTile.YP);
            rp.setCenter(new Point3i(p.x, p.y + 1, p.z));
            rp.setType(type);
            polys.add(rp);
        }
    }

    private static void doXMSquare(SparseMatrix<Block> blocks, Point3i p,
            List<RenderTile> polys, int type)
    {
        if (!blocks.contains(new Point3i(p.x - 1, p.y, p.z)))
        {
            RenderTile rp = new RenderTile();
            rp.setBlock(blocks.get(p));
            rp.setFacing(RenderTile.XM);
            rp.setCenter(new Point3i(p));
            rp.setType(type);
            polys.add(rp);
        }
    }

    private static void doXPSquare(SparseMatrix<Block> blocks, Point3i p,
            List<RenderTile> polys, int type)
    {
        if (!blocks.contains(new Point3i(p.x + 1, p.y, p.z)))
        {
            RenderTile rp = new RenderTile();
            rp.setBlock(blocks.get(p));
            rp.setFacing(RenderTile.XP);
            rp.setCenter(new Point3i(p.x + 1, p.y, p.z));
            rp.setType(type);
            polys.add(rp);
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
