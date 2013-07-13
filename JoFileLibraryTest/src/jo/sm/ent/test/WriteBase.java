package jo.sm.ent.test;

import java.io.ByteArrayOutputStream;
import java.io.File;

import jo.sm.ent.data.Tag;
import jo.sm.ent.logic.TagLogic;
import jo.util.utils.io.FileUtils;
import jo.util.utils.obj.ByteUtils;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;

public class WriteBase extends ReadBase
{
    @Before
    public void setUp() throws Exception
    {
        super.setUp();
    }
    
    protected Tag testFile(File f)
    {
        Tag entity = super.testFile(f);
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            TagLogic.writeFile(entity, baos, true);
            byte[] output = baos.toByteArray();
            byte[] input = FileUtils.readFile(f.toString());
            System.out.println("INPUT:");
            System.out.println(ByteUtils.toStringDump(input));
            System.out.println("OUTPUT:");
            System.out.println(ByteUtils.toStringDump(output));
            for (int i = 0; i < output.length; i++)
            {
            	if (input[i] != output[i])
            	{
            		System.out.println("Differs at byte #"+i);
            	}
            }
            Assert.assertEquals("Write length mismatch", f.length(), output.length);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("Cannot write "+f.toString());
        }
        return entity;
    }

    @After
    public void tearDown() throws Exception
    {
        super.tearDown();
    }
}
