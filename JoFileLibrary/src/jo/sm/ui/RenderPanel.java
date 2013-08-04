package jo.sm.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jo.sm.data.RenderTile;
import jo.sm.data.SparseMatrix;
import jo.sm.logic.RenderLogic;
import jo.sm.ship.data.Block;
import jo.vecmath.Matrix3f;
import jo.vecmath.Matrix4f;
import jo.vecmath.Point3f;
import jo.vecmath.Point3i;
import jo.vecmath.Vector3f;
import jo.vecmath.logic.Matrix4fLogic;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel
{
    private static final float  PIXEL_TO_RADIANS = (1f/3.14159f/16f);
    private static final float  ROLL_SCALE = 1.1f;
    
    private Point               mMouseDownAt;
    
    private SparseMatrix<Block> mGrid;
    private List<RenderTile>    mTiles;
    private Matrix4f            mTransform;
    private Vector3f            mPreTranslate;
    private float               mScale;
    private float               mRotX;
    private float               mRotY;
    private Vector3f            mPostTranslate;
    
    private Point3f             mUnitX;
    private Point3f             mUnitY;
    private Point3f             mUnitZ;
    
    public RenderPanel()
    {
        mTransform = new Matrix4f();
        mPreTranslate = new Vector3f();
        mScale = 1f;
        mRotX = 0;
        mRotY = 0;
        mPostTranslate = new Vector3f();
        MouseAdapter ma =  new MouseAdapter(){
            public void mousePressed(MouseEvent ev)
            {
                if (ev.getButton() == MouseEvent.BUTTON1)
                    doMouseDown(ev.getPoint());
            }
            public void mouseReleased(MouseEvent ev)
            {
                if (ev.getButton() == MouseEvent.BUTTON1)
                    doMouseUp(ev.getPoint());
            }
            public void mouseDragged(MouseEvent ev)
            {
                if (mMouseDownAt != null)
                    doMouseMove(ev.getPoint());
            }
            public void mouseWheelMoved(MouseWheelEvent e)
            {
                doMouseWheel(e.getWheelRotation());
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);
        addMouseWheelListener(ma);
    }
    
    private void updateTransform()
    {
        Dimension s = getSize();
        mPostTranslate.x = s.width/2;
        mPostTranslate.y = s.height/2;
        
        mTransform.setIdentity();
        //System.out.println("After identity=\n"+mTransform);
        Matrix4fLogic.translate(mTransform, mPreTranslate);
        //System.out.println("After preTrans=\n"+mTransform);
        Matrix4fLogic.scale(mTransform, mScale);
        //System.out.println("After scale=\n"+mTransform);
        Matrix4fLogic.rotX(mTransform, mRotX);
        //System.out.println("After rotX=\n"+mTransform);
        Matrix4fLogic.rotY(mTransform, mRotY);
        //System.out.println("After rotY=\n"+mTransform);
        Matrix4fLogic.translate(mTransform, mPostTranslate);
        //System.out.println("After postTrans=\n"+mTransform);
        
        Matrix3f rot = new Matrix3f();
        mTransform.get(rot);
        mUnitX = new Point3f(mScale, 0, 0);
        rot.transform(mUnitX);
        mUnitY = new Point3f(0, mScale, 0);
        rot.transform(mUnitY);
        mUnitZ = new Point3f(0, 0, mScale);
        rot.transform(mUnitZ);
        //System.out.println("UnitX="+mUnitX);
        //System.out.println("UnitY="+mUnitY);
        //System.out.println("UnitZ="+mUnitZ);
        if (mTiles != null)
            RenderLogic.transformAndSort(mTiles, mTransform);
        repaint();
    }
    
    private void doMouseDown(Point p)
    {
        mMouseDownAt = p;
    }
    
    private void doMouseMove(Point p)
    {
        int dx = p.x - mMouseDownAt.x;
        int dy = p.y - mMouseDownAt.y;
        mMouseDownAt = p;
        mRotX += dy*PIXEL_TO_RADIANS;
        mRotY += dx*PIXEL_TO_RADIANS;
        updateTransform();
    }
    
    private void doMouseUp(Point p)
    {
        doMouseMove(p);
        mMouseDownAt = null;
    }

    private void doMouseWheel(int roll)
    {
        if (roll > 0)
        {
            while (roll-- > 0)
                mScale /= ROLL_SCALE;
        }
        else if (roll < 0)
        {
            while (roll++ < 0)
                mScale *= ROLL_SCALE;
        }
        updateTransform();
    }
    
    public void paint(Graphics g)
    {
        if (mTiles == null)
            return;
        Dimension s = getSize();
        g.setColor(Color.black);
        g.fillRect(0, 0, s.width, s.height);
        Graphics2D g2 = (Graphics2D)g;
        float[][] corners = new float[4][2];
        for (RenderTile tile : mTiles)
        {
            Point3f corner = tile.getVisual();
            if (corner == null)
                break;
            if (!getCorners(tile, corner, corners))
                continue;
            if (tile.getType() == RenderTile.SQUARE)
                renderSquare(g2, corners, tile, BlockTypeColors.getBlockImage(tile.getBlock().getBlockID()));
            else if ((tile.getType() >= RenderTile.TRI1) && (tile.getType() <= RenderTile.TRI4))
                renderTriangle(g2, corners, tile, BlockTypeColors.getWedgeImage(tile.getBlock().getBlockID()));
            else if ((tile.getType() == RenderTile.RECTANGLE))
                renderSquare(g2, corners, tile, BlockTypeColors.getBlockImage(tile.getBlock().getBlockID()));
        }
    }

    private void renderTriangle(Graphics2D g2, float[][] corners,
            RenderTile tile, ImageIcon icon)
    {
        //System.out.println("Render triangle "+tile.getType());
        int pCenter = (tile.getType() - RenderTile.TRI1);
        int pLeft = (pCenter + 1)%4;
        int pRight = (pCenter + 3)%4;
        if (icon != null)
        {
            float m00 = (corners[pRight][0] - corners[pCenter][0])/64f;
            float m10 = (corners[pRight][1] - corners[pCenter][1])/64f;
            float m01 = (corners[pLeft][0] - corners[pCenter][0])/64f;
            float m11 = (corners[pLeft][1] - corners[pCenter][1])/64f;
            float m02 = corners[pCenter][0];
            float m12 = corners[pCenter][1];
            AffineTransform t = new AffineTransform(m00, m10, m01, m11, m02, m12);
            g2.drawImage(icon.getImage(), t, null);
        }
        else
        {
            Path2D p = new Path2D.Float();
            p.moveTo(corners[pCenter][0], corners[pCenter][1]);
            p.lineTo(corners[pLeft][0], corners[pLeft][1]);
            p.lineTo(corners[pRight][0], corners[pRight][1]);
            p.lineTo(corners[pCenter][0], corners[pCenter][1]);
            g2.setPaint(BlockTypeColors.getFillColor(tile.getBlock().getBlockID()));
            g2.fill(p);
            g2.setPaint(BlockTypeColors.getOutlineColor(tile.getBlock().getBlockID()));
            g2.draw(p);
        }
    }

    private void renderSquare(Graphics2D g2, float[][] corners,
            RenderTile tile, ImageIcon icon)
    {
        if (icon != null)
        {
            float m00 = (corners[1][0] - corners[0][0])/64f;
            float m10 = (corners[1][1] - corners[0][1])/64f;
            float m01 = (corners[3][0] - corners[0][0])/64f;
            float m11 = (corners[3][1] - corners[0][1])/64f;
            float m02 = corners[0][0];
            float m12 = corners[0][1];
            AffineTransform t = new AffineTransform(m00, m10, m01, m11, m02, m12);
            g2.drawImage(icon.getImage(), t, null);
        }
        else
        {
            Path2D p = new Path2D.Float();
            p.moveTo(corners[0][0], corners[0][1]);
            p.lineTo(corners[1][0], corners[1][1]);
            p.lineTo(corners[2][0], corners[2][1]);
            p.lineTo(corners[3][0], corners[3][1]);
            p.lineTo(corners[0][0], corners[0][1]);
            g2.setPaint(BlockTypeColors.getFillColor(tile.getBlock().getBlockID()));
            g2.fill(p);
            g2.setPaint(BlockTypeColors.getOutlineColor(tile.getBlock().getBlockID()));
            g2.draw(p);
        }
    }

    private boolean getCorners(RenderTile tile, Point3f corner, float[][] corners)
    {
        switch (tile.getFacing())
        {
            case RenderTile.XP:
            case RenderTile.XM:
                setCorner(corners, corner, 0, false, false, false);
                setCorner(corners, corner, 1, false, true , false);
                setCorner(corners, corner, 2, false, true , true );
                setCorner(corners, corner, 3, false, false, true );
                break;
            case RenderTile.YP:
            case RenderTile.YM:
                setCorner(corners, corner, 0, false, false, false);
                setCorner(corners, corner, 1, false, false, true );
                setCorner(corners, corner, 2, true , false, true );
                setCorner(corners, corner, 3, true , false, false);
                break;
            case RenderTile.ZP:
            case RenderTile.ZM:
                setCorner(corners, corner, 0, false, false, false);
                setCorner(corners, corner, 1, true , false, false);
                setCorner(corners, corner, 2, true , true , false);
                setCorner(corners, corner, 3, false, true , false);
                break;
            case RenderTile.XPYP:
            case RenderTile.XMYM:
                setDiagonalCorners(corners, corner, false, true , false, false, true , true );
                break;
            case RenderTile.XPYM:
            case RenderTile.XMYP:
                setDiagonalCorners(corners, corner, true , true , false, true , true , true );
                break;
                case RenderTile.YPZM:
              case RenderTile.YMZP:
                  setDiagonalCorners(corners, corner, false, false, false, true , false, false);
                  break;
              case RenderTile.YPZP:
              case RenderTile.YMZM:
                  setDiagonalCorners(corners, corner, false, false, true , true , false, true );
                  break;
              case RenderTile.ZPXP:
              case RenderTile.ZMXM:
                  setDiagonalCorners(corners, corner, false, false, true , true , false, false);
                  break;
              case RenderTile.ZPXM:
              case RenderTile.ZMXP:
                  setDiagonalCorners(corners, corner, true, true, true , true , false, true);
                  break;
            default:
                return false;
        }
        return true;
    }
    
    private void setDiagonalCorners(float[][] corners, Point3f corner, 
            boolean x0, boolean y0, boolean z0, 
            boolean x1, boolean y1, boolean z1)
    {
        boolean x2 = (x0 == x1) ? !x1 : x1;
        boolean x3 = (x1 == x2) ? !x2 : x2;
        boolean y2 = (y0 == y1) ? !y1 : y1;
        boolean y3 = (y1 == y2) ? !y2 : y2;
        boolean z2 = (z0 == z1) ? !z1 : z1;
        boolean z3 = (z1 == z2) ? !z2 : z2;
        setCorner(corners, corner, 0, x0, y0, z0);
        setCorner(corners, corner, 1, x1, y1, z1);
        setCorner(corners, corner, 2, x2, y2, z2);
        setCorner(corners, corner, 3, x3, y3, z3);
    }
    
    private void setCorner(float[][] corners, Point3f corner, int off, boolean x, boolean y, boolean z)
    {
        corners[off][0] = corner.x;
        corners[off][1] = corner.y;
        if (x)
        {
            corners[off][0] += mUnitX.x;
            corners[off][1] += mUnitX.y;
        }
        if (y)
        {
            corners[off][0] += mUnitY.x;
            corners[off][1] += mUnitY.y;
        }
        if (z)
        {
            corners[off][0] += mUnitZ.x;
            corners[off][1] += mUnitZ.y;
        }
    }
    
    public List<RenderTile> getTiles()
    {
        return mTiles;
    }

    public void setTiles(List<RenderTile> tiles)
    {
        mTiles = tiles;
        updateTransform();
    }

    public SparseMatrix<Block> getGrid()
    {
        return mGrid;
    }

    public void setGrid(SparseMatrix<Block> grid)
    {
        mGrid = grid;
        Point3i lower = new Point3i();
        Point3i upper = new Point3i();
        mGrid.getBounds(lower, upper);
        mPreTranslate.x = -(lower.x + upper.x)/2;
        mPreTranslate.y = -(lower.y + upper.y)/2;
        mPreTranslate.z = -(lower.z + upper.z)/2;
        float maxModel = Math.max(Math.max(upper.x - lower.x, upper.y - lower.y), upper.z - lower.z);
        Dimension s = getSize();
        float maxScreen = Math.max(s.width, s.height);
        mScale = maxScreen/maxModel/2f;
        System.out.println("Scale="+mScale+", preTrans="+mPreTranslate);
        //mTransform.setTranslation(new Vector3f(s.width/2f, s.height/2f, 0));
        mTiles = RenderLogic.getRender(grid);
        updateTransform();
    }
    
    public RenderTile getTileAt(double x, double y)
    {
        float[][] corners = new float[4][2];
        for (int i = mTiles.size() - 1; i >= 0; i--)
        {
            RenderTile tile = mTiles.get(i);
            Point3f corner = tile.getVisual();
            if (corner == null)
                continue;
            getCorners(tile, corner, corners);
            Path2D p = new Path2D.Float();
            p.moveTo(corners[0][0], corners[0][1]);
            p.lineTo(corners[1][0], corners[1][1]);
            p.lineTo(corners[2][0], corners[2][1]);
            p.lineTo(corners[3][0], corners[3][1]);
            p.lineTo(corners[0][0], corners[0][1]);
            if (p.contains(x, y))
                return tile;
        }
        return null;
    }
}
