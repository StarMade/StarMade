package jo.sm.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Path2D;
import java.util.List;

import javax.swing.JPanel;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Point3f;
import javax.vecmath.Point3i;
import javax.vecmath.Vector3f;

import jo.sm.data.RenderTile;
import jo.sm.data.SparseMatrix;
import jo.sm.logic.RenderLogic;
import jo.sm.ship.data.Block;
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
                doMouseDown(ev.getPoint());
            }
            public void mouseReleased(MouseEvent ev)
            {
                doMouseUp(ev.getPoint());
            }
            public void mouseDragged(MouseEvent ev)
            {
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
        for (RenderTile tile : mTiles)
        {
            Point3f corner = tile.getVisual();
            if (corner == null)
                break;
            Path2D p = new Path2D.Float();
            p.moveTo(corner.x, corner.y);
            switch (tile.getFacing())
            {
                case RenderTile.XP:
                case RenderTile.XM:
                    p.lineTo(corner.x + mUnitY.x,        corner.y + mUnitY.y);
                    p.lineTo(corner.x + mUnitY.x + mUnitZ.x, corner.y + mUnitY.y + mUnitZ.y);
                    p.lineTo(corner.x +      + mUnitZ.x, corner.y +      + mUnitZ.y);
                    break;
                case RenderTile.YP:
                case RenderTile.YM:
                    p.lineTo(corner.x + mUnitX.x,        corner.y + mUnitX.y);
                    p.lineTo(corner.x + mUnitX.x + mUnitZ.x, corner.y + mUnitX.y + mUnitZ.y);
                    p.lineTo(corner.x +      + mUnitZ.x, corner.y +      + mUnitZ.y);
                    break;
                case RenderTile.ZP:
                case RenderTile.ZM:
                    p.lineTo(corner.x + mUnitX.x,        corner.y + mUnitX.y);
                    p.lineTo(corner.x + mUnitX.x + mUnitY.x, corner.y + mUnitX.y + mUnitY.y);
                    p.lineTo(corner.x +      + mUnitY.x, corner.y +      + mUnitY.y);
                    break;
            }
            p.lineTo(corner.x, corner.y);
            g2.setPaint(BlockTypeColors.getFillColor(tile.getBlock().getBlockID()));
            g2.fill(p);
            g2.setPaint(BlockTypeColors.getOutlineColor(tile.getBlock().getBlockID()));
            g2.draw(p);
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
}
