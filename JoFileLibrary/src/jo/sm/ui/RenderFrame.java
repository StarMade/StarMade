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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jo.sm.data.Entity;
import jo.sm.data.SparseMatrix;
import jo.sm.logic.BlueprintLogic;
import jo.sm.logic.EntityLogic;
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
        // instantiate
        mClient = new RenderPanel();
        JButton loadBlueprint = new JButton("Blueprint");
        JButton loadDefaultBlueprint = new JButton("Default Blueprint");
        JButton loadPlayerShip = new JButton("Player Ship");
        JButton loadOtherShip = new JButton("Other Ship");
        JButton loadTurret = new JButton("Turret");
        JButton loadSpaceStation = new JButton("Station");
        JButton loadShop = new JButton("Shop");
        JButton loadPlanet = new JButton("Planet");
        JButton loadRock = new JButton("Rock");
        // layout
        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(new GridLayout(1,8));
        buttonBar.add(loadBlueprint);
        buttonBar.add(loadDefaultBlueprint);
        buttonBar.add(loadPlayerShip);
        buttonBar.add(loadOtherShip);
        buttonBar.add(loadTurret);
        buttonBar.add(loadSpaceStation);
        buttonBar.add(loadShop);
        buttonBar.add(loadPlanet);
        buttonBar.add(loadRock);
        getContentPane().add(BorderLayout.NORTH, buttonBar);
        getContentPane().add(BorderLayout.WEST, new EditPanel(mClient));
        getContentPane().add(BorderLayout.CENTER, mClient);
        getContentPane().add(BorderLayout.SOUTH, new BegPanel());
        // link
        this.addWindowListener(this);
        loadBlueprint.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doLoadBlueprint(false);
            }            
        });
        loadDefaultBlueprint.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doLoadBlueprint(true);
            }            
        });
        loadPlayerShip.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doLoadEntity("SHIP", "Player");
            }            
        });
        loadOtherShip.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doLoadEntity("SHIP", "MOB_");
            }            
        });
        loadTurret.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doLoadEntity("SHIP", "AITURRET");
            }            
        });
        loadSpaceStation.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doLoadEntity("SPACESTATION", null);
            }            
        });
        loadShop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doLoadEntity("SHOP", null);
            }            
        });
        loadPlanet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doLoadEntity("PLANET", null);
            }            
        });
        loadRock.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doLoadEntity("FLOATINGROCK", null);
            }            
        });
        setSize(1024, 768);
    }

    protected void doLoadBlueprint(boolean def)
    {
        String[] options;
        if (def)
            options = BlueprintLogic.getDefaultBlueprintNames().toArray(new String[0]);
        else
            options = BlueprintLogic.getBlueprintNames().toArray(new String[0]);
        int choice = JOptionPane.showOptionDialog(this, "Pick blueprint to view", "Starmade Utils", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice < 0)
            return;
        try
        {
            Blueprint blueprint;
            if (def)
                blueprint = BlueprintLogic.readDefaultBlueprint(options[choice]);
            else
                blueprint = BlueprintLogic.readBlueprint(options[choice]);
            SparseMatrix<Block> grid = ShipLogic.getBlocks(blueprint.getData());
            mClient.setGrid(grid);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void doLoadEntity(String typeFilter, String nameFilter)
    {
        try
        {
            List<Entity> entities = new ArrayList<Entity>();
            entities.addAll(EntityLogic.getEntities());
            for (Iterator<Entity> i = entities.iterator(); i.hasNext(); )
            {
                Entity e = i.next();
                System.out.println(e.getType()+" - "+e.getName());
                if ((typeFilter != null) && !e.getType().equals(typeFilter))
                {
                    i.remove();
                    continue;
                }
                if (nameFilter != null)
                {
                    if ("Player".equals(nameFilter))
                    {
                        if (!e.toString().startsWith("Ship "))
                        {
                            i.remove();
                            continue;
                        }
                    }
                    else if (!e.getName().startsWith(nameFilter))
                    {
                        i.remove();
                        continue;
                    }
                }
                System.out.println(e.getType()+" ("+e.getName()+")");
            }
            if (entities.size() == 0)
                return;
            int choice = JOptionPane.showOptionDialog(this, "Pick object to view", "Starmade Utils", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, entities.toArray(), entities.get(0));
            if (choice < 0)
                return;
            Entity e = entities.get(choice);
            EntityLogic.readEntityData(e);
            SparseMatrix<Block> grid = ShipLogic.getBlocks(e.getData());
            e.setData(null); // conserve memory
            mClient.setGrid(grid);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected void doLoadShop()
    {
        // TODO Auto-generated method stub
        
    }

    protected void doLoadPlanet()
    {
        // TODO Auto-generated method stub
        
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
