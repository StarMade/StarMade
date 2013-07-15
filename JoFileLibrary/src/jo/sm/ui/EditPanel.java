package jo.sm.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.vecmath.Point3i;

import jo.sm.data.BlockTypes;
import jo.sm.data.RenderTile;
import jo.sm.data.SparseMatrix;
import jo.sm.ship.data.Block;

@SuppressWarnings("serial")
public class EditPanel extends JPanel
{
    private short               mCurrentBlockID;
    private boolean             mPainting;

    private RenderPanel         mRenderer;

    private JLabel              mCurrent;
    private JButton             mGrey;
    private JButton             mBlack;
    private JButton             mRed;
    private JButton             mPurple;
    private JButton             mBlue;
    private JButton             mGreen;
    private JButton             mBrown;
    private JButton             mYellow;
    private JButton             mWhite;
    private JButton             mClear;
    private JButton             mAll;

    public EditPanel(RenderPanel renderer)
    {
        mRenderer = renderer;
        mCurrentBlockID = -1;
        // instantiate
        mCurrent = new JLabel("blank");
        mGrey = newButton(BlockTypes.HULL_COLOR_GREY_ID);
        mBlack = newButton(BlockTypes.HULL_COLOR_BLACK_ID);
        mRed = newButton(BlockTypes.HULL_COLOR_RED_ID);
        mPurple = newButton(BlockTypes.HULL_COLOR_PURPLE_ID);
        mBlue = newButton(BlockTypes.HULL_COLOR_BLUE_ID);
        mGreen = newButton(BlockTypes.HULL_COLOR_GREEN_ID);
        mBrown = newButton(BlockTypes.HULL_COLOR_BROWN_ID);
        mYellow = newButton(BlockTypes.HULL_COLOR_YELLOW_ID);
        mWhite = newButton(BlockTypes.HULL_COLOR_WHITE_ID);
        mClear = new JButton("Clear");
        mClear.setToolTipText("Stop painting");
        mAll = new JButton("All");
        mAll.setToolTipText("Set all hulls to current color");
        // layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Paint:"));
        add(mCurrent);
        add(new JLabel("Choose:"));
        add(mGrey);
        add(mBlack);
        add(mRed);
        add(mPurple);
        add(mBlue);
        add(mGreen);
        add(mBrown);
        add(mYellow);
        add(mWhite);
        add(mClear);
        add(mAll);
        // link
        MouseAdapter ma = new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                doMouseClick(e.getX(), e.getY());
            }
            public void mousePressed(MouseEvent ev)
            {
                if (ev.getButton() == MouseEvent.BUTTON3)
                    mPainting = true;
            }
            public void mouseReleased(MouseEvent ev)
            {
                if (ev.getButton() == MouseEvent.BUTTON3)
                    mPainting = false;
            }
            public void mouseDragged(MouseEvent ev)
            {
                if (mPainting)
                    doMouseClick(ev.getX(), ev.getY());
            }
        };
        mRenderer.addMouseListener(ma);
        mRenderer.addMouseMotionListener(ma);
        mClear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                doColorClick(null, (short)-1);
            }});
        mAll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                doColorAll();
            }});
    }

    private JButton newButton(final short blockID)
    {
        ImageIcon rawImage = BlockTypeColors.getBlockImage(blockID);
        Image image = rawImage.getImage().getScaledInstance(32, 32,
                Image.SCALE_DEFAULT);
        final JButton btn = new JButton(new ImageIcon(image));
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                doColorClick(btn.getIcon(), blockID);
            }});
        return btn;
    }
    
    private void doColorClick(Icon color, short blockID)
    {
        mCurrentBlockID = blockID;
        if (mCurrentBlockID == -1)
        {
            mCurrent.setIcon(null);
            mCurrent.setText("blank");
        }
        else
        {
            mCurrent.setIcon(color);
            mCurrent.setText("");
        }
    }
    
    private void doColorAll()
    {
        if (mCurrentBlockID < 0)
            return;
        SparseMatrix<Block> grid = mRenderer.getGrid();
        for (Iterator<Point3i> i = grid.iterator(); i.hasNext(); )
        {
            Point3i coords = i.next();
            Block block = grid.get(coords);
            if (block == null)
                continue;
            if (BlockTypes.isHull(block.getBlockID()))
            {
                block.setBlockID(mCurrentBlockID);
            }
        }
        mRenderer.repaint();
    }

    private void doMouseClick(int x, int y)
    {
        if (mCurrentBlockID < 0)
            return;
        RenderTile tile = mRenderer.getTileAt(x, y);
        if (tile == null)
            return;
        if (BlockTypes.isHull(tile.getBlock().getBlockID()))
        {
            tile.getBlock().setBlockID(mCurrentBlockID);
            mRenderer.repaint();
        }
    }
}
