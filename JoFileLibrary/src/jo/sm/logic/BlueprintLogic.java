package jo.sm.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jo.sm.data.StarMade;
import jo.sm.ship.data.Blueprint;
import jo.sm.ship.logic.DataLogic;
import jo.sm.ship.logic.HeaderLogic;
import jo.sm.ship.logic.LogicLogic;
import jo.sm.ship.logic.MetaLogic;

public class BlueprintLogic
{
    public static List<String> getBlueprintNames()
    {
        StarMade sm = StarMadeLogic.getInstance();
        if (sm.getBlueprints() == null)
        {
            sm.setBlueprints(new ArrayList<String>());
            File blueprintsDir = new File(sm.getBaseDir(), "blueprints");
            scanBlueprintsDir(blueprintsDir, sm.getBlueprints());
        }
        return sm.getBlueprints();
    }

    private static void scanBlueprintsDir(File blueprintsDir, List<String> blueprints)
    {
        for (File f : blueprintsDir.listFiles())
        {
            if (isValidBlueprintDir(f))
                blueprints.add(f.getName());
        }
    }
    
    public static List<String> getDefaultBlueprintNames()
    {
        StarMade sm = StarMadeLogic.getInstance();
        if (sm.getDefaultBlueprints() == null)
        {
            sm.setDefaultBlueprints(new ArrayList<String>());
            File blueprintsDir = new File(sm.getBaseDir(), "blueprints-default");
            scanBlueprintsDir(blueprintsDir, sm.getDefaultBlueprints());
        }
        return sm.getDefaultBlueprints();
    }
    
    private static boolean isValidBlueprintDir(File dir)
    {
        File header = new File(dir, "header.smbph");
        return header.exists();
    }

    public static Blueprint readBlueprint(String name) throws IOException
    {
        File blueprintsDir = new File(StarMadeLogic.getInstance().getBaseDir(), "blueprints");
        File blueprintDir = new File(blueprintsDir, name);
        return readBlueprint(blueprintDir);
    }
    public static Blueprint readDefaultBlueprint(String name) throws IOException
    {
        File blueprintsDir = new File(StarMadeLogic.getInstance().getBaseDir(), "blueprints-default");
        File blueprintDir = new File(blueprintsDir, name);
        return readBlueprint(blueprintDir);
    }
    public static Blueprint readBlueprint(File dir) throws IOException
    {
        Blueprint bp = new Blueprint();
        bp.setName(dir.getName());
        File header = new File(dir, "header.smbph");
        InputStream headerIS = new FileInputStream(header);
        bp.setHeader(HeaderLogic.readFile(headerIS, true));
        File logic = new File(dir, "logic.smbpl");
        InputStream logicIS = new FileInputStream(logic);
        bp.setLogic(LogicLogic.readFile(logicIS, true));
        File meta = new File(dir, "meta.smbpm");
        InputStream metaIS = new FileInputStream(meta);
        bp.setMeta(MetaLogic.readFile(metaIS, true));
        File dataDir = new File(dir, "DATA");
        bp.setData(DataLogic.readFiles(dataDir, bp.getName()));
        return bp;
    }
}
