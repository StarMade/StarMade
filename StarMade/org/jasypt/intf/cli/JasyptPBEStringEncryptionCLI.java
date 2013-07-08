/*   1:    */package org.jasypt.intf.cli;
/*   2:    */
/*   3:    */import java.util.Properties;
/*   4:    */import org.jasypt.intf.service.JasyptStatelessService;
/*   5:    */
/*  44:    */public final class JasyptPBEStringEncryptionCLI
/*  45:    */{
/*  46: 46 */  private static final String[][] VALID_REQUIRED_ARGUMENTS = { { "input" }, { "password" } };
/*  47:    */  
/*  59: 59 */  private static final String[][] VALID_OPTIONAL_ARGUMENTS = { { "verbose" }, { "algorithm" }, { "keyObtentionIterations" }, { "saltGeneratorClassName" }, { "providerName" }, { "providerClassName" }, { "stringOutputType" } };
/*  60:    */  
/*  92:    */  public static void main(String[] args)
/*  93:    */  {
/*  94: 94 */    boolean verbose = CLIUtils.getVerbosity(args);
/*  95:    */    
/*  96:    */    try
/*  97:    */    {
/*  98: 98 */      String applicationName = null;
/*  99: 99 */      String[] arguments = null;
/* 100:100 */      if ((args[0] == null) || (args[0].indexOf("=") != -1)) {
/* 101:101 */        applicationName = JasyptPBEStringEncryptionCLI.class.getName();
/* 102:102 */        arguments = args;
/* 103:    */      } else {
/* 104:104 */        applicationName = args[0];
/* 105:105 */        arguments = new String[args.length - 1];
/* 106:106 */        System.arraycopy(args, 1, arguments, 0, args.length - 1);
/* 107:    */      }
/* 108:    */      
/* 109:109 */      Properties argumentValues = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
/* 110:    */      
/* 114:114 */      CLIUtils.showEnvironment(verbose);
/* 115:    */      
/* 116:116 */      JasyptStatelessService service = new JasyptStatelessService();
/* 117:    */      
/* 118:118 */      String input = argumentValues.getProperty("input");
/* 119:    */      
/* 120:120 */      CLIUtils.showArgumentDescription(argumentValues, verbose);
/* 121:    */      
/* 122:122 */      String result = service.encrypt(input, argumentValues.getProperty("password"), null, null, argumentValues.getProperty("algorithm"), null, null, argumentValues.getProperty("keyObtentionIterations"), null, null, argumentValues.getProperty("saltGeneratorClassName"), null, null, argumentValues.getProperty("providerName"), null, null, argumentValues.getProperty("providerClassName"), null, null, argumentValues.getProperty("stringOutputType"), null, null);
/* 123:    */      
/* 147:147 */      CLIUtils.showOutput(result, verbose);
/* 148:    */    }
/* 149:    */    catch (Throwable t) {
/* 150:150 */      CLIUtils.showError(t, verbose);
/* 151:    */    }
/* 152:    */  }
/* 153:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */