package jo.sm.ent.cmd;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import jo.sm.logic.StarMadeLogic;
import jo.sm.ui.BlockTypeColors;

public class DumpBlockToColor 
{
    private static final String TEXTURE_PREFIX = "textureId=\"";
    private static final String TYPE_PREFIX = "type=\"";
    
	private String[] mArgs;
	
	public DumpBlockToColor(String[] argv)
	{
		mArgs = argv;
	}
	
	public void run()
	{
		parseArgs();
		try
		{
		    File blockConfig = new File(StarMadeLogic.getInstance().getBaseDir(), "data/config/BlockConfig.xml");
		    BufferedReader rdr = new BufferedReader(new FileReader(blockConfig));
		    for (;;)
		    {
		        String inbuf = rdr.readLine();
		        if (inbuf == null)
		            break;
		        int off = inbuf.indexOf(TEXTURE_PREFIX);
		        if (off < 0)
		            continue;
		        inbuf = inbuf.substring(off + TEXTURE_PREFIX.length());
		        off = inbuf.indexOf("\"");
		        if (off < 0)
		            continue;
		        short texture = Short.parseShort(inbuf.substring(0, off));
		        off = inbuf.indexOf(TYPE_PREFIX);
		        if (off < 0)
		            continue;
		        inbuf = inbuf.substring(off + TYPE_PREFIX.length());
		        off = inbuf.indexOf("\"");
                if (off < 0)
                    continue;
                String type = inbuf.substring(0, off);
                ImageIcon icon = BlockTypeColors.getBlockImage(texture);
                if (icon == null)
                {
                    //System.out.println("No icon for "+texture);
                    continue;
                }
                BufferedImage image = (BufferedImage)icon.getImage();
                int r = 0;
                int g = 0;
                int b = 0;
                for (int y = 0; y < 64; y++)
                    for (int x = 0; x < 64; x++)
                    {
                        int rgb = image.getRGB(x, y);
                        if (type.indexOf("HULL_COLOR_RED_ID") >= 0)
                        {
                            System.out.print(Integer.toHexString(rgb)+"  ");
                            if (x == 63)
                                System.out.println();
                        }
                        r += (rgb>>0)&0xff;
                        g += (rgb>>8)&0xff;
                        b += (rgb>>16)&0xff;
                    }
                r /= (64*64);
                g /= (64*64);
                b /= (64*64);
		        System.out.println("BLOCK_FILL.put(BlockTypes."+type+", new Color("+r+", "+g+", "+b+"));");
		    }
		    rdr.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void parseArgs()
	{
		if (mArgs.length == 0)
		{
			System.err.println("Arg1 = starmade dir");
			System.exit(0);
		}
		StarMadeLogic.setBaseDir(mArgs[0]);
	}
	
	public static void main(String[] argv)
	{
		DumpBlockToColor app = new DumpBlockToColor(argv);
		app.run();
	}
}
