package jo.sm.ent.cmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jo.sm.ent.data.Tag;
import jo.sm.ent.logic.EntityLogic;
import jo.sm.ent.logic.EntityUtils;

public class EditEntityFile 
{
	private String[] mArgs;
	private File     mTestFile;
	
	public EditEntityFile(String[] argv)
	{
		mArgs = argv;
	}
	
	public void run()
	{
		parseArgs();
		try
		{
			FileInputStream fis = new FileInputStream(mTestFile);
			Tag obj = EntityLogic.readFile(fis, true);
			for (int i = 1; i < mArgs.length; i += 3)
				edit(obj, mArgs[i], mArgs[i+1], mArgs[i+2]);
			FileOutputStream fos = new FileOutputStream(mTestFile);
			EntityLogic.writeFile(obj, fos, true);			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void edit(Tag root, String id, String op, String val) 
	{
		Tag obj = EntityUtils.lookup(root, id);
		if (obj == null)
		{
			System.err.println("Cannot find '"+id+"'");
			return;
		}
		try
		{
			if ("=".equals(op))
				EntityUtils.set(obj, val);
			else if ("+=".equals(op))
				EntityUtils.incr(obj, val);
			else if ("-=".equals(op))
				EntityUtils.decr(obj, val);
			else
			{
				System.err.println("Cannot find '"+id+"'");
				return;
			}
		}
		catch (Exception e)
		{
			System.err.println(e.getLocalizedMessage());
			return;
		}
		EntityUtils.dump(obj, "");
	}

	private void parseArgs()
	{
		if (mArgs.length == 0)
		{
			System.err.println("Arg1 = filename");
			System.err.println("Arg2 = path to object");
			System.err.println("Arg3 = operation (=, +=, -=)");
			System.err.println("Arg4 = value");
			System.exit(0);
		}
		mTestFile = new File(mArgs[0]);
	}
	
	public static void main(String[] argv)
	{
		EditEntityFile app = new EditEntityFile(argv);
		app.run();
	}
}
