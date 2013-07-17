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
            getCorners(tile, corner, corners);
            ImageIcon icon = BlockTypeColors.getBlockImage(tile.getBlock().getBlockID());
            if ((icon != null) && (tile.getFacing() < RenderTile.XP))
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
    }

    private void getCorners(RenderTile tile, Point3f corner, float[][] corners)
    {
        corners[0][0] = corner.x;
        corners[0][1] = corner.y;
        switch (tile.getFacing())
        {
            case RenderTile.XP:
            case RenderTile.XM:
                corners[1][0] = corner.x + mUnitY.x;
                corners[1][1] = corner.y + mUnitY.y;
                corners[2][0] = corner.x + mUnitY.x + mUnitZ.x;
                corners[2][1] = corner.y + mUnitY.y + mUnitZ.y;
                corners[3][0] = corner.x +      + mUnitZ.x;
                corners[3][1] = corner.y +      + mUnitZ.y;
                break;
            case RenderTile.YP:
            case RenderTile.YM:
                corners[1][0] = corner.x + mUnitZ.x;
                corners[1][1] = corner.y + mUnitZ.y;
                corners[2][0] = corner.x + mUnitZ.x + mUnitX.x;
                corners[2][1] = corner.y + mUnitZ.y + mUnitX.y;
                corners[3][0] = corner.x +      + mUnitX.x;
                corners[3][1] = corner.y +      + mUnitX.y;
                break;
            case RenderTile.ZP:
            case RenderTile.ZM:
                corners[1][0] = corner.x + mUnitX.x;
                corners[1][1] = corner.y + mUnitX.y;
                corners[2][0] = corner.x + mUnitX.x + mUnitY.x;
                corners[2][1] = corner.y + mUnitX.y + mUnitY.y;
                corners[3][0] = corner.x +      + mUnitY.x;
                corners[3][1] = corner.y +      + mUnitY.y;
                break;
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
