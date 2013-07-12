package jo.sm.ui;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import jo.sm.data.SparseMatrix;
import jo.sm.logic.BlueprintLogic;
import jo.sm.logic.StarMadeLogic;
import jo.sm.ship.data.Block;
import jo.sm.ship.data.Blueprint;
import jo.sm.ship.logic.ShipLogic;

@SuppressWarnings("serial")
public class RenderFrame extends JFrame implements WindowListener
{
    private RenderPanel mClient;
    
    public RenderFrame()
    {
        super("StarMade Ship Preview");
        mClient = new RenderPanel();
        getContentPane().add(BorderLayout.CENTER, mClient);
        this.addWindowListener(this);
        setSize(1024, 768);
    }
    
    public void windowClosing(WindowEvent evt) {
        this.setVisible(false);
        this.dispose();
      }
      
      public void windowOpened(WindowEvent evt) {}  
      public void windowClosed(WindowEvent evt) {}
      public void windowIconified(WindowEvent evt) {}
      public void windowDeiconified(WindowEvent evt) {}
      public void windowActivated(WindowEvent evt) {}
      public void windowDeactivated(WindowEvent evt) {}
    
    public static void main(String[] args)
    {
        RenderFrame f = new RenderFrame();
        f.setVisible(true);
        
        String baseDir = System.getProperty("sm.basedir");
        if (baseDir == null)
            baseDir = "/Users/jojaquinta/Downloads/StarMade";
        StarMadeLogic.setBaseDir(baseDir);
        try
        {
            String blueprintName = BlueprintLogic.getBlueprintNames().get(0);
            Blueprint blueprint = BlueprintLogic.readBlueprint(blueprintName);
            SparseMatrix<Block> grid = ShipLogic.getBlocks(blueprint.getData());
            f.mClient.setGrid(grid);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
