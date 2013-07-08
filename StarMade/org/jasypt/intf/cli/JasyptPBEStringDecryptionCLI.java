/*   1:    */package org.jasypt.intf.cli;
/*   2:    */
/*   3:    */import java.util.Properties;
/*   4:    */import org.jasypt.intf.service.JasyptStatelessService;
/*   5:    */
/*  45:    */public final class JasyptPBEStringDecryptionCLI
/*  46:    */{
/*  47: 47 */  private static final String[][] VALID_REQUIRED_ARGUMENTS = { { "input" }, { "password" } };
/*  48:    */  
/*  60: 60 */  private static final String[][] VALID_OPTIONAL_ARGUMENTS = { { "verbose" }, { "algorithm" }, { "keyObtentionIterations" }, { "saltGeneratorClassName" }, { "providerName" }, { "providerClassName" }, { "stringOutputType" } };
/*  61:    */  
/*  93:    */  public static void main(String[] args)
/*  94:    */  {
/*  95: 95 */    boolean verbose = CLIUtils.getVerbosity(args);
/*  96:    */    
/*  97:    */    try
/*  98:    */    {
/*  99: 99 */      String applicationName = null;
/* 100:100 */      String[] arguments = null;
/* 101:101 */      if ((args[0] == null) || (args[0].indexOf("=") != -1)) {
/* 102:102 */        applicationName = JasyptPBEStringDecryptionCLI.class.getName();
/* 103:103 */        arguments = args;
/* 104:    */      } else {
/* 105:105 */        applicationName = args[0];
/* 106:106 */        arguments = new String[args.length - 1];
/* 107:107 */        System.arraycopy(args, 1, arguments, 0, args.length - 1);
/* 108:    */      }
/* 109:    */      
/* 110:110 */      Properties argumentValues = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
/* 111:    */      
/* 115:115 */      CLIUtils.showEnvironment(verbose);
/* 116:    */      
/* 117:117 */      JasyptStatelessService service = new JasyptStatelessService();
/* 118:    */      
/* 119:119 */      String input = argumentValues.getProperty("input");
/* 120:    */      
/* 121:121 */      CLIUtils.showArgumentDescription(argumentValues, verbose);
/* 122:    */      
/* 123:123 */      String result = service.decrypt(input, argumentValues.getProperty("password"), null, null, argumentValues.getProperty("algorithm"), null, null, argumentValues.getProperty("keyObtentionIterations"), null, null, argumentValues.getProperty("saltGeneratorClassName"), null, null, argumentValues.getProperty("providerName"), null, null, argumentValues.getProperty("providerClassName"), null, null, argumentValues.getProperty("stringOutputType"), null, null);
/* 124:    */      
/* 148:148 */      CLIUtils.showOutput(result, verbose);
/* 149:    */    }
/* 150:    */    catch (Throwable t) {
/* 151:151 */      CLIUtils.showError(t, verbose);
/* 152:    */    }
/* 153:    */  }
/* 154:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */