package org.jasypt.intf.cli;

import java.util.Properties;
import org.jasypt.intf.service.JasyptStatelessService;

public final class JasyptPBEStringDecryptionCLI
{
  private static final String[][] VALID_REQUIRED_ARGUMENTS = { { "input" }, { "password" } };
  private static final String[][] VALID_OPTIONAL_ARGUMENTS = { { "verbose" }, { "algorithm" }, { "keyObtentionIterations" }, { "saltGeneratorClassName" }, { "providerName" }, { "providerClassName" }, { "stringOutputType" } };
  
  public static void main(String[] args)
  {
    boolean verbose = CLIUtils.getVerbosity(args);
    try
    {
      String applicationName = null;
      String[] arguments = null;
      if ((args[0] == null) || (args[0].indexOf("=") != -1))
      {
        applicationName = JasyptPBEStringDecryptionCLI.class.getName();
        arguments = args;
      }
      else
      {
        applicationName = args[0];
        arguments = new String[args.length - 1];
        System.arraycopy(args, 1, arguments, 0, args.length - 1);
      }
      Properties argumentValues = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
      CLIUtils.showEnvironment(verbose);
      JasyptStatelessService service = new JasyptStatelessService();
      String input = argumentValues.getProperty("input");
      CLIUtils.showArgumentDescription(argumentValues, verbose);
      String result = service.decrypt(input, argumentValues.getProperty("password"), null, null, argumentValues.getProperty("algorithm"), null, null, argumentValues.getProperty("keyObtentionIterations"), null, null, argumentValues.getProperty("saltGeneratorClassName"), null, null, argumentValues.getProperty("providerName"), null, null, argumentValues.getProperty("providerClassName"), null, null, argumentValues.getProperty("stringOutputType"), null, null);
      CLIUtils.showOutput(result, verbose);
    }
    catch (Throwable applicationName)
    {
      CLIUtils.showError(applicationName, verbose);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */