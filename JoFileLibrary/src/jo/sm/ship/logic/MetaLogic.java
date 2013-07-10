package jo.sm.ship.logic;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import jo.sm.ship.data.Meta;

public class MetaLogic 
{
	@SuppressWarnings("resource")
    public static Meta readFile(InputStream is, boolean close) throws IOException
	{
		DataInputStream dis;
		if (is instanceof DataInputStream)
			dis = (DataInputStream)is;
		else
			dis = new DataInputStream(is);
		Meta meta = new Meta();

        if (close)
            dis.close();
		return meta;
	}
}
