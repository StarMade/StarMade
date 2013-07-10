package jo.sm.ship.logic;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import jo.sm.data.Vector3s;
import jo.sm.logic.IOLogic;
import jo.sm.ship.data.ControllerEntry;
import jo.sm.ship.data.GroupEntry;
import jo.sm.ship.data.Logic;

public class LogicLogic 
{
	public static Logic readFile(InputStream is, boolean close) throws IOException
	{
		DataInputStream dis;
		if (is instanceof DataInputStream)
			dis = (DataInputStream)is;
		else
			dis = new DataInputStream(is);
		Logic logic = new Logic();
		logic.setUnknown1(dis.readInt());
		int numControllers = dis.readInt();
		ControllerEntry[] controllers = new ControllerEntry[numControllers];
		for (int i = 0; i < controllers.length; i++)
		{
		    controllers[i] = new ControllerEntry();
		    controllers[i].setPosition(IOLogic.readVector3s(dis));
		    int numGroups = dis.readInt();
		    GroupEntry[] groups = new GroupEntry[numGroups];
		    for (int j = 0; j < groups.length; j++)
		    {
		        groups[j] = new GroupEntry();
		        groups[j].setBlockID(dis.readShort());
		        int numBlocks = dis.readInt();
		        Vector3s[] blocks = new Vector3s[numBlocks];
		        for (int k = 0; k < blocks.length; k++)
		            blocks[k] = IOLogic.readVector3s(dis);
		        groups[j].setBlocks(blocks);
		    }
		    controllers[i].setGroups(groups);
		}
		logic.setControllers(controllers);
        if (close)
            dis.close();
		return logic;
	}
}
