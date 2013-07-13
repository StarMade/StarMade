package jo.sm.ship.logic;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.vecmath.Vector3f;

import jo.sm.ship.data.BlockEntry;
import jo.sm.ship.data.Header;

public class HeaderLogic 
{
	@SuppressWarnings("resource")
    public static Header readFile(InputStream is, boolean close) throws IOException
	{
		DataInputStream dis;
		if (is instanceof DataInputStream)
			dis = (DataInputStream)is;
		else
			dis = new DataInputStream(is);
		Header header = new Header();
		header.setUnknown1(dis.readInt());
        header.setUnknown2(dis.readInt());
        header.setUpperBound(new Vector3f(dis.readFloat(),
                        dis.readFloat(),
                        dis.readFloat()));
        header.setLowerBound(new Vector3f(dis.readFloat(),
                dis.readFloat(),
                dis.readFloat()));
        int manifestLen = dis.readInt();
        BlockEntry[] manifest = new BlockEntry[manifestLen];
        for (int i = 0; i < manifest.length; i++)
        {
            manifest[i] = new BlockEntry();
            manifest[i].setBlockID(dis.readShort());
            manifest[i].setBlockQuantity(dis.readInt());
        }
        header.setManifest(manifest);
        if (close)
            dis.close();
		return header;
	}
}
