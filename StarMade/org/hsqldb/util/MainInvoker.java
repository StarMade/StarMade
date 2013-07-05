package org.hsqldb.util;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainInvoker
{
  private static String[] emptyStringArray = new String[0];
  public static String LS = System.getProperty("line.separator");
  private static String SYNTAX_MSG = "    java org.hsqldb.util.MainInvoker [package1.Class1 [arg1a arg1b...] \"\"]... \\\n    packageX.ClassX [argXa argXb...]\nOR\n    java org.hsqldb.util.MainInvoker --help\n\nNote that you can only invoke classes in 'named' (non-default) packages.  Delimit multiple classes with empty strings.";

  private static void syntaxFailure()
  {
    System.err.println(SYNTAX_MSG);
    System.exit(2);
  }

  public static void main(String[] paramArrayOfString)
  {
    if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].equals("--help")))
    {
      System.err.println(SYNTAX_MSG);
      System.exit(0);
    }
    ArrayList localArrayList = new ArrayList();
    int i = -1;
    try
    {
      while (true)
      {
        i++;
        if (i >= paramArrayOfString.length)
          break;
        if (paramArrayOfString[i].length() < 1)
        {
          if (localArrayList.size() < 1)
            syntaxFailure();
          invoke((String)localArrayList.remove(0), (String[])localArrayList.toArray(emptyStringArray));
          localArrayList.clear();
        }
        else
        {
          localArrayList.add(paramArrayOfString[i]);
        }
      }
      if (localArrayList.size() < 1)
        syntaxFailure();
      invoke((String)localArrayList.remove(0), (String[])localArrayList.toArray(emptyStringArray));
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      System.exit(1);
    }
  }

  public static void invoke(String paramString, String[] paramArrayOfString)
    throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Class[] arrayOfClass = { emptyStringArray.getClass() };
    Object[] arrayOfObject = { paramArrayOfString == null ? emptyStringArray : paramArrayOfString };
    Class localClass = Class.forName(paramString);
    Method localMethod = localClass.getMethod("main", arrayOfClass);
    localMethod.invoke(null, arrayOfObject);
  }

  static
  {
    if (!LS.equals("\n"))
      SYNTAX_MSG = SYNTAX_MSG.replaceAll("\n", LS);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.util.MainInvoker
 * JD-Core Version:    0.6.2
 */