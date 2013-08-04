package jo.sm.ui.logic;

import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

import jo.sm.data.Entity;
import jo.sm.data.SparseMatrix;
import jo.sm.logic.BlueprintLogic;
import jo.sm.logic.EntityLogic;
import jo.sm.ship.data.Block;
import jo.sm.ship.data.Blueprint;
import jo.sm.ship.logic.ShipLogic;

public class ShipTreeLogic
{
    public static DefaultMutableTreeNode getShipTree()
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        addBlueprint(root, "Blueprints", false);
        addBlueprint(root, "Default Blueprints", true);
        addEntity(root, "Your Ships", "SHIP", "Player");
        addEntity(root, "Other Ships", "SHIP", "MOB_");
        addEntity(root, "Turrets", "SHIP", "AITURRET");
        addEntity(root, "Stations", "SPACESTATION", null);
        addEntity(root, "Shops", "SHOP", null);
        addEntity(root, "Planets", "PLANET", null);
        addEntity(root, "Rocks", "FLOATINGROCK", null);
        return root;
    }

    private static void addBlueprint(DefaultMutableTreeNode root,
            String title, boolean def)
    {
        DefaultMutableTreeNode group = new DefaultMutableTreeNode(title);
        String[] options;
        if (def)
            options = BlueprintLogic.getDefaultBlueprintNames().toArray(new String[0]);
        else
            options = BlueprintLogic.getBlueprintNames().toArray(new String[0]);
        if (options.length == 0)
            return;
        for (String name : options)
        {
            ShipSpec spec = new ShipSpec();
            spec.setType(def ? ShipSpec.DEFAULT_BLUEPRINT : ShipSpec.BLUEPRINT);
            spec.setName(name);
            DefaultMutableTreeNode option = new DefaultMutableTreeNode(spec);
            group.add(option);            
        }
        root.add(group);
    }

    private static void addEntity(DefaultMutableTreeNode root,
            String title, String typeFilter, String nameFilter)
    {
        DefaultMutableTreeNode group = new DefaultMutableTreeNode(title);
        boolean addedAny = false;
        try
        {
            for (Entity e : EntityLogic.getEntities())
            {
                if ((typeFilter != null) && !e.getType().equals(typeFilter))
                    continue;
                if (nameFilter != null)
                {
                    if ("Player".equals(nameFilter))
                    {
                        if (!e.toString().startsWith("Ship "))
                            continue;
                    }
                    else if (!e.getName().startsWith(nameFilter))
                        continue;
                }
                ShipSpec spec = new ShipSpec();
                spec.setType(ShipSpec.ENTITY);
                spec.setName(e.toString());
                spec.setEntity(e);
                DefaultMutableTreeNode option = new DefaultMutableTreeNode(spec);
                group.add(option);
                addedAny = true;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (!addedAny)
            return;
        root.add(group);
    }

    public static SparseMatrix<Block> loadShip(ShipSpec spec)
    {
        try
        {
            if (spec.getType() == ShipSpec.BLUEPRINT)
            {
                Blueprint blueprint = BlueprintLogic.readBlueprint(spec.getName());
                SparseMatrix<Block> grid = ShipLogic.getBlocks(blueprint.getData());
                return grid;
            }
            else if (spec.getType() == ShipSpec.DEFAULT_BLUEPRINT)
            {
                Blueprint blueprint = BlueprintLogic.readDefaultBlueprint(spec.getName());
                SparseMatrix<Block> grid = ShipLogic.getBlocks(blueprint.getData());
                return grid;
            }
            else if (spec.getType() == ShipSpec.ENTITY)
            {
                Entity e = spec.getEntity();
                EntityLogic.readEntityData(e);
                SparseMatrix<Block> grid = ShipLogic.getBlocks(e.getData());
                e.setData(null); // conserve memory
                return grid;
            }
            else
                throw new IllegalArgumentException("Unknown ship type "+spec.getType());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
