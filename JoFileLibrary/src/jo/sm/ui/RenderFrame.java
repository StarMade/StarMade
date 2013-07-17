package jo.sm.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.vecmath.Point3i;

import jo.sm.data.SparseMatrix;
import jo.sm.logic.StarMadeLogic;
import jo.sm.ship.data.Block;
import jo.sm.ship.data.Data;
import jo.sm.ship.logic.DataLogic;
import jo.sm.ship.logic.ShipLogic;
import jo.sm.ui.logic.ShipSpec;
import jo.sm.ui.logic.ShipTreeLogic;

@SuppressWarnings("serial")
public class RenderFrame extends JFrame implements WindowListener
{
    private RenderPanel mClient;

    public RenderFrame()
    {
        super("StarMade Ship Preview");
        // instantiate
        mClient = new RenderPanel();
        JButton openExisting = new JButton("Open...");
        JButton openFile = new JButton("Open File...");
        // layout
        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(new GridLayout(1,6));
        buttonBar.add(openExisting);
        buttonBar.add(openFile);
        getContentPane().add(BorderLayout.NORTH, buttonBar);
        getContentPane().add(BorderLayout.WEST, new EditPanel(mClient));
        getContentPane().add(BorderLayout.CENTER, mClient);
        getContentPane().add(BorderLayout.SOUTH, new BegPanel());
        // link
        this.addWindowListener(this);
        openExisting.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doOpenExisting();
            }            
        });
        openFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doOpenFile();
            }            
        });
        setSize(1024, 768);
    }

    private void doOpenExisting()
    {
        ShipChooser chooser = new ShipChooser(this);
        chooser.setVisible(true);
        ShipSpec spec = chooser.getSelected();
        if (spec == null)
            return;
        SparseMatrix<Block> grid = ShipTreeLogic.loadShip(spec);
        if (grid != null)
            mClient.setGrid(grid);
    }

    private void doOpenFile()
    {
        JFileChooser chooser = new JFileChooser(StarMadeLogic.getInstance().getBaseDir());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Starmade Ship File", "smd2");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
           File smb2 = chooser.getSelectedFile();
           try
           {
               FileInputStream fis = new FileInputStream(smb2);
               Data datum = DataLogic.readFile(fis, true);
               Map<Point3i, Data> data = new HashMap<Point3i, Data>();
               data.put(new Point3i(), datum);
               SparseMatrix<Block> grid = ShipLogic.getBlocks(data);
               mClient.setGrid(grid);
           }
           catch (IOException e)
           {
               e.printStackTrace();
           }
        }
    }

    public void windowClosing(WindowEvent evt)
    {
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }

    public void windowOpened(WindowEvent evt)
    {
    }

    public void windowClosed(WindowEvent evt)
    {
    }

    public void windowIconified(WindowEvent evt)
    {
    }

    public void windowDeiconified(WindowEvent evt)
    {
    }

    public void windowActivated(WindowEvent evt)
    {
    }

    public void windowDeactivated(WindowEvent evt)
    {
    }
    
    private static Properties loadProps()
    {
        Properties p = new Properties();
        File home = new File(System.getProperty("user.home"));
        File props = new File(home, ".josm");
        if (props.exists())
        {
            try
            {
                FileInputStream fis = new FileInputStream(props);
                p.load(fis);
                fis.close();
            }
            catch (Exception e)
            {
                
            }
        }
        return p;
    }
    
    private static void saveProps(Properties p)
    {
        File home = new File(System.getProperty("user.home"));
        File props = new File(home, ".josm");
        try
        {
            FileWriter fos = new FileWriter(props);
            p.store(fos, "StarMade Utils defaults");
            fos.close();
        }
        catch (Exception e)
        {
            
        }
    }
    
    private static void preLoad()
    {
        Properties props = loadProps();
        String home = props.getProperty("starmade.home", "");
        home = JOptionPane.showInputDialog(null, "Enter in the home directory for StarMade", home);
        if (home == null)
            System.exit(0);
        props.put("starmade.home", home);
        saveProps(props);
        StarMadeLogic.setBaseDir(home);
    }

    public static void main(String[] args)
    {
        preLoad();
        RenderFrame f = new RenderFrame();
        f.setVisible(true);
    }
}
