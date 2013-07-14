package jo.sm.ui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.vecmath.Point3i;

import jo.sm.data.SparseMatrix;
import jo.sm.ship.data.Block;
import jo.sm.ship.data.Data;
import jo.sm.ship.logic.DataLogic;
import jo.sm.ship.logic.ShipLogic;

@SuppressWarnings("serial")
public class RenderApplet extends JApplet
{
	private Map<Point3i,Data>	mShip;
    private RenderPanel mClient;

    public void init()
    {
    	JOptionPane.showMessageDialog(null, "Hello");
    	setNativeLookAndFeel();
        // instantiate
        mClient = new RenderPanel();
        // layout
        getContentPane().add(BorderLayout.CENTER, mClient);
    }
    
    public void start()
    {
        // load
        mShip= new HashMap<Point3i, Data>();
        for (int i = 1; i < 999; i++)
        {
        	String url = getParameter("data"+i);
        	if (url == null)
        	{
                JOptionPane.showMessageDialog(null, "Can't find data"+i);
        		break;
        	}
            JOptionPane.showMessageDialog(null, "Loading "+url);
        	try
        	{
        		URL u = new URL(url);
        		InputStream is = u.openStream();
        		Data datum = DataLogic.readFile(is, true);
        		if (datum != null)
        		{
                    Point3i position = new Point3i(i, i, i);
                    mShip.put(position, datum);
        		}
        	}
        	catch (IOException e)
        	{
        		e.printStackTrace();
        	}
        }
        SparseMatrix<Block> grid = ShipLogic.getBlocks(mShip);
        mClient.setGrid(grid);
    }

    public static void setNativeLookAndFeel() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
          System.out.println("Error setting native LAF: " + e);
        }
      }
}
