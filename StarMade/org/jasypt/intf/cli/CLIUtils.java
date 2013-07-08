package org.jasypt.intf.cli;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.jasypt.commons.CommonUtils;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

final class CLIUtils
{
  static void showEnvironment(boolean verbose)
  {
    if (verbose)
    {
      System.out.println("\n----ENVIRONMENT-----------------\n");
      System.out.println("Runtime: " + System.getProperty("java.vm.vendor") + " " + System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.version") + " ");
      System.out.println("\n");
    }
  }
  
  static void showArgumentDescription(Properties argumentValues, boolean verbose)
  {
    if (verbose)
    {
      System.out.println("\n----ARGUMENTS-------------------\n");
      Iterator entriesIter = argumentValues.entrySet().iterator();
      while (entriesIter.hasNext())
      {
        Map.Entry entry = (Map.Entry)entriesIter.next();
        System.out.println(entry.getKey() + ": " + entry.getValue());
      }
      System.out.println("\n");
    }
  }
  
  static void showOutput(String output, boolean verbose)
  {
    if (verbose)
    {
      System.out.println("\n----OUTPUT----------------------\n");
      System.out.println(output);
      System.out.println("\n");
    }
    else
    {
      System.out.println(output);
    }
  }
  
  static void showError(Throwable local_t, boolean verbose)
  {
    if (verbose)
    {
      System.err.println("\n----ERROR-----------------------\n");
      if ((local_t instanceof EncryptionOperationNotPossibleException)) {
        System.err.println("Operation not possible (Bad input or parameters)");
      } else if (local_t.getMessage() != null) {
        System.err.println(local_t.getMessage());
      } else {
        System.err.println(local_t.getClass().getName());
      }
      System.err.println("\n");
    }
    else
    {
      System.err.print("ERROR: ");
      if ((local_t instanceof EncryptionOperationNotPossibleException)) {
        System.err.println("Operation not possible (Bad input or parameters)");
      } else if (local_t.getMessage() != null) {
        System.err.println(local_t.getMessage());
      } else {
        System.err.println(local_t.getClass().getName());
      }
    }
  }
  
  static boolean getVerbosity(String[] args)
  {
    for (int local_i = 0; local_i < args.length; local_i++)
    {
      String key = CommonUtils.substringBefore(args[local_i], "=");
      String value = CommonUtils.substringAfter(args[local_i], "=");
      if ((!CommonUtils.isEmpty(key)) && (!CommonUtils.isEmpty(value)) && ("verbose".equals(key)))
      {
        Boolean verbosity = CommonUtils.getStandardBooleanValue(value);
        return verbosity != null ? verbosity.booleanValue() : false;
      }
    }
    return true;
  }
  
  static Properties getArgumentValues(String appName, String[] args, String[][] requiredArgNames, String[][] optionalArgNames)
  {
    Set argNames = new HashSet();
    for (int local_i = 0; local_i < requiredArgNames.length; local_i++) {
      argNames.addAll(Arrays.asList(requiredArgNames[local_i]));
    }
    for (int local_i = 0; local_i < optionalArgNames.length; local_i++) {
      argNames.addAll(Arrays.asList(optionalArgNames[local_i]));
    }
    Properties local_i = new Properties();
    for (int local_i1 = 0; local_i1 < args.length; local_i1++)
    {
      String key = CommonUtils.substringBefore(args[local_i1], "=");
      String value = CommonUtils.substringAfter(args[local_i1], "=");
      if ((CommonUtils.isEmpty(key)) || (CommonUtils.isEmpty(value))) {
        throw new IllegalArgumentException("Bad argument: " + args[local_i1]);
      }
      if (argNames.contains(key))
      {
        if ((value.startsWith("\"")) && (value.endsWith("\""))) {
          local_i.setProperty(key, value.substring(1, value.length() - 1));
        } else {
          local_i.setProperty(key, value);
        }
      }
      else {
        throw new IllegalArgumentException("Bad argument: " + args[local_i1]);
      }
    }
    for (int local_i1 = 0; local_i1 < requiredArgNames.length; local_i1++)
    {
      boolean key = false;
      for (int value = 0; value < requiredArgNames[local_i1].length; value++) {
        if (local_i.containsKey(requiredArgNames[local_i1][value])) {
          key = true;
        }
      }
      if (!key) {
        showUsageAndExit(appName, requiredArgNames, optionalArgNames);
      }
    }
    return local_i;
  }
  
  static void showUsageAndExit(String appName, String[][] requiredArgNames, String[][] optionalArgNames)
  {
    System.err.println("\nUSAGE: " + appName + " [ARGUMENTS]\n");
    System.err.println("  * Arguments must apply to format:\n");
    System.err.println("      \"arg1=value1 arg2=value2 arg3=value3 ...\"");
    System.err.println();
    System.err.println("  * Required arguments:\n");
    for (int local_i = 0; local_i < requiredArgNames.length; local_i++)
    {
      System.err.print("      ");
      if (requiredArgNames[local_i].length == 1)
      {
        System.err.print(requiredArgNames[local_i][0]);
      }
      else
      {
        System.err.print("(");
        for (int local_j = 0; local_j < requiredArgNames[local_i].length; local_j++)
        {
          if (local_j > 0) {
            System.err.print(" | ");
          }
          System.err.print(requiredArgNames[local_i][local_j]);
        }
        System.err.print(")");
      }
      System.err.println();
    }
    System.err.println();
    System.err.println("  * Optional arguments:\n");
    for (int local_i = 0; local_i < optionalArgNames.length; local_i++)
    {
      System.err.print("      ");
      if (optionalArgNames[local_i].length == 1)
      {
        System.err.print(optionalArgNames[local_i][0]);
      }
      else
      {
        System.err.print("(");
        for (int local_j = 0; local_j < optionalArgNames[local_i].length; local_j++)
        {
          if (local_j > 0) {
            System.err.print(" | ");
          }
          System.err.print(optionalArgNames[local_i][local_j]);
        }
        System.err.print(")");
      }
      System.err.println();
    }
    System.exit(1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.intf.cli.CLIUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */