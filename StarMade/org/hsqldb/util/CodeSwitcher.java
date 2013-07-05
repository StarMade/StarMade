package org.hsqldb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.Vector;

public class CodeSwitcher
{
  private static final String ls = System.getProperty("line.separator", "\n");
  private Vector vList = new Vector();
  private Vector vSwitchOn = new Vector();
  private Vector vSwitchOff = new Vector();
  private Vector vSwitches = new Vector();
  private static final int MAX_LINELENGTH = 82;

  public static void main(String[] paramArrayOfString)
  {
    CodeSwitcher localCodeSwitcher = new CodeSwitcher();
    if (paramArrayOfString.length == 0)
    {
      showUsage();
      return;
    }
    File localFile1 = null;
    File localFile2 = null;
    String str1;
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      str1 = paramArrayOfString[i];
      if (str1.startsWith("+"))
        localCodeSwitcher.vSwitchOn.addElement(str1.substring(1));
      else if (str1.startsWith("--basedir="))
        localFile2 = new File(str1.substring("--basedir=".length()));
      else if (str1.startsWith("--pathlist="))
        localFile1 = new File(str1.substring("--pathlist=".length()));
      else if (str1.startsWith("-"))
        localCodeSwitcher.vSwitchOff.addElement(str1.substring(1));
      else
        localCodeSwitcher.addDir(str1);
    }
    if (localFile2 != null)
      if (localFile1 == null)
      {
        System.err.println("--basedir= setting ignored, since only used for list files");
      }
      else if (!localFile2.isDirectory())
      {
        System.err.println("Skipping listfile since basedir '" + localFile2.getAbsolutePath() + "' is not a directory");
        localFile1 = null;
      }
    if (localFile1 != null)
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new FileReader(localFile1));
        while ((str1 = localBufferedReader.readLine()) != null)
        {
          int j = str1.indexOf(35);
          String str2 = (j > -1 ? str1.substring(0, j) : str1).trim();
          if (str2.length() >= 1)
          {
            File localFile3 = localFile2 == null ? new File(str2) : new File(localFile2, str2);
            if (localFile3.isFile())
              localCodeSwitcher.addDir(localFile3);
            else
              System.err.println("Skipping non-file '" + str2.trim() + "'");
          }
        }
      }
      catch (Exception localException)
      {
        System.err.println("Failed to read pathlist file '" + localFile1.getAbsolutePath() + "'");
      }
    if (localCodeSwitcher.size() < 1)
    {
      printError("No path specified, or no specified paths qualify");
      showUsage();
    }
    localCodeSwitcher.process();
    if ((localCodeSwitcher.vSwitchOff.size() == 0) && (localCodeSwitcher.vSwitchOn.size() == 0))
      localCodeSwitcher.printSwitches();
  }

  public int size()
  {
    return this.vList == null ? 0 : this.vList.size();
  }

  static void showUsage()
  {
    System.out.print("Usage: java CodeSwitcher paths|{--pathlist=listfile} [{+|-}label...] [+][-]\nIf no labels are specified then all used\nlabels in the source code are shown.\nUse +MODE to switch on the things labeld MODE\nUse -MODE to switch off the things labeld MODE\nPath: Any number of path or files may be\nspecified. Use . for the current directory\n(including sub-directories).\nExample: java CodeSwitcher +JAVA2 .\nThis example switches on code labeled JAVA2\nin all *.java files in the current directory\nand all subdirectories.\n");
  }

  void process()
  {
    int i = this.vList.size();
    for (int j = 0; j < i; j++)
    {
      System.out.print(".");
      String str = (String)this.vList.elementAt(j);
      if (!processFile(str))
        System.out.println("in file " + str + " !");
    }
    System.out.println("");
  }

  void printSwitches()
  {
    System.out.println("Used labels:");
    for (int i = 0; i < this.vSwitches.size(); i++)
      System.out.println((String)this.vSwitches.elementAt(i));
  }

  void addDir(String paramString)
  {
    addDir(new File(paramString));
  }

  void addDir(File paramFile)
  {
    if ((paramFile.isFile()) && (paramFile.getName().endsWith(".java")))
    {
      this.vList.addElement(paramFile.getPath());
    }
    else if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      for (int i = 0; i < arrayOfFile.length; i++)
        addDir(arrayOfFile[i]);
    }
  }

  boolean processFile(String paramString)
  {
    File localFile1 = new File(paramString);
    File localFile2 = new File(paramString + ".new");
    int i = 0;
    int j = 0;
    int k = 0;
    try
    {
      Vector localVector1 = getFileLines(localFile1);
      Vector localVector2 = new Vector(localVector1.size());
      for (int m = 0; m < localVector1.size(); m++)
        localVector2.addElement(localVector1.elementAt(m));
      for (m = 0; m < localVector1.size(); m++)
      {
        String str = (String)localVector1.elementAt(m);
        if (str == null)
          break;
        if ((k != 0) && ((str.equals("/*")) || (str.equals("*/"))))
          localVector1.removeElementAt(m--);
        else if (str.startsWith("//#"))
          if (str.startsWith("//#ifdef "))
          {
            if (i != 0)
            {
              printError("'#ifdef' not allowed inside '#ifdef'");
              return false;
            }
            i = 1;
            localObject = str.substring(9);
            if (this.vSwitchOn.indexOf(localObject) != -1)
            {
              k = 1;
              j = 0;
            }
            else if (this.vSwitchOff.indexOf(localObject) != -1)
            {
              k = 1;
              localVector1.insertElementAt("/*", ++m);
              j = 1;
            }
            if (this.vSwitches.indexOf(localObject) == -1)
              this.vSwitches.addElement(localObject);
          }
          else if (str.startsWith("//#ifndef "))
          {
            if (i != 0)
            {
              printError("'#ifndef' not allowed inside '#ifdef'");
              return false;
            }
            i = 1;
            localObject = str.substring(10);
            if (this.vSwitchOff.indexOf(localObject) != -1)
            {
              k = 1;
              j = 0;
            }
            else if (this.vSwitchOn.indexOf(localObject) != -1)
            {
              k = 1;
              localVector1.insertElementAt("/*", ++m);
              j = 1;
            }
            if (this.vSwitches.indexOf(localObject) == -1)
              this.vSwitches.addElement(localObject);
          }
          else if (str.startsWith("//#else"))
          {
            if (i != 1)
            {
              printError("'#else' without '#ifdef'");
              return false;
            }
            i = 2;
            if (k != 0)
              if (j != 0)
              {
                if (localVector1.elementAt(m - 1).equals(""))
                {
                  localVector1.insertElementAt("*/", m - 1);
                  m++;
                }
                else
                {
                  localVector1.insertElementAt("*/", m++);
                }
                j = 0;
              }
              else
              {
                localVector1.insertElementAt("/*", ++m);
                j = 1;
              }
          }
          else if (str.startsWith("//#endif"))
          {
            if (i == 0)
            {
              printError("'#endif' without '#ifdef'");
              return false;
            }
            i = 0;
            if ((k != 0) && (j != 0))
              if (localVector1.elementAt(m - 1).equals(""))
              {
                localVector1.insertElementAt("*/", m - 1);
                m++;
              }
              else
              {
                localVector1.insertElementAt("*/", m++);
              }
            k = 0;
          }
      }
      if (i != 0)
      {
        printError("'#endif' missing");
        return false;
      }
      m = 0;
      for (int n = 0; n < localVector1.size(); n++)
        if (!localVector2.elementAt(n).equals(localVector1.elementAt(n)))
        {
          m = 1;
          break;
        }
      if (m == 0)
        return true;
      writeFileLines(localVector1, localFile2);
      File localFile3 = new File(paramString + ".bak");
      localFile3.delete();
      localFile1.renameTo(localFile3);
      Object localObject = new File(paramString);
      localFile2.renameTo((File)localObject);
      localFile3.delete();
      return true;
    }
    catch (Exception localException)
    {
      printError(localException.toString());
    }
    return false;
  }

  static Vector getFileLines(File paramFile)
    throws IOException
  {
    LineNumberReader localLineNumberReader = new LineNumberReader(new FileReader(paramFile));
    Vector localVector = new Vector();
    while (true)
    {
      String str = localLineNumberReader.readLine();
      if (str == null)
        break;
      localVector.addElement(str);
    }
    localLineNumberReader.close();
    return localVector;
  }

  static void writeFileLines(Vector paramVector, File paramFile)
    throws IOException
  {
    FileWriter localFileWriter = new FileWriter(paramFile);
    for (int i = 0; i < paramVector.size(); i++)
    {
      localFileWriter.write((String)paramVector.elementAt(i));
      localFileWriter.write(ls);
    }
    localFileWriter.flush();
    localFileWriter.close();
  }

  static void printError(String paramString)
  {
    System.out.println("");
    System.out.println("ERROR: " + paramString);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.util.CodeSwitcher
 * JD-Core Version:    0.6.2
 */