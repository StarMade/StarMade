package jo.sm.ent.cmd;


public class Main 
{
	private String[] mArgs;
	private String	 mOp;
	private String[] mOpArgs;
	
	public Main(String[] argv)
	{
		mArgs = argv;
	}
	
	public void run()
	{
		parseArgs();
		if ("dump".equals(mOp))
			DumpEntityFile.main(mOpArgs);
		else if ("edit".equals(mOp))
			EditEntityFile.main(mOpArgs);
		else
			System.err.println("Unknown command '"+mOp+"'. Try 'dump' or 'edit'.");
	}

	private void parseArgs()
	{
		if (mArgs.length == 0)
		{
			System.err.println("Try:");
			System.err.println("dump file.ent [id]");
			System.err.println("edit file.ent id (=|+=|-=) val");
			System.exit(0);
		}
		mOp = mArgs[0];
		mOpArgs = new String[mArgs.length - 1];
		System.arraycopy(mArgs, 1, mOpArgs, 0, mOpArgs.length);
	}
	
	public static void main(String[] argv)
	{
		Main app = new Main(argv);
		app.run();
	}
}
