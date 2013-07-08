/*   1:    */package org.jasypt.intf.cli;
/*   2:    */
/*   3:    */import java.util.Properties;
/*   4:    */import org.jasypt.intf.service.JasyptStatelessService;
/*   5:    */
/*  44:    */public final class JasyptStringDigestCLI
/*  45:    */{
/*  46: 46 */  private static final String[][] VALID_REQUIRED_ARGUMENTS = { { "input" } };
/*  47:    */  
/*  56: 56 */  private static final String[][] VALID_OPTIONAL_ARGUMENTS = { { "verbose" }, { "algorithm" }, { "iterations" }, { "saltSizeBytes" }, { "saltGeneratorClassName" }, { "providerName" }, { "providerClassName" }, { "invertPositionOfSaltInMessageBeforeDigesting" }, { "invertPositionOfPlainSaltInEncryptionResults" }, { "useLenientSaltSizeCheck" }, { "unicodeNormalizationIgnored" }, { "stringOutputType" }, { "prefix" }, { "suffix" } };
/*  57:    */  
/* 110:    */  public static void main(String[] args)
/* 111:    */  {
/* 112:112 */    boolean verbose = CLIUtils.getVerbosity(args);
/* 113:    */    
/* 114:    */    try
/* 115:    */    {
/* 116:116 */      String applicationName = null;
/* 117:117 */      String[] arguments = null;
/* 118:118 */      if ((args[0] == null) || (args[0].indexOf("=") != -1)) {
/* 119:119 */        applicationName = JasyptStringDigestCLI.class.getName();
/* 120:120 */        arguments = args;
/* 121:    */      } else {
/* 122:122 */        applicationName = args[0];
/* 123:123 */        arguments = new String[args.length - 1];
/* 124:124 */        System.arraycopy(args, 1, arguments, 0, args.length - 1);
/* 125:    */      }
/* 126:    */      
/* 127:127 */      Properties argumentValues = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
/* 128:    */      
/* 132:132 */      CLIUtils.showEnvironment(verbose);
/* 133:    */      
/* 134:134 */      JasyptStatelessService service = new JasyptStatelessService();
/* 135:    */      
/* 136:136 */      String input = argumentValues.getProperty("input");
/* 137:    */      
/* 138:138 */      CLIUtils.showArgumentDescription(argumentValues, verbose);
/* 139:    */      
/* 140:140 */      String result = service.digest(input, argumentValues.getProperty("algorithm"), null, null, argumentValues.getProperty("iterations"), null, null, argumentValues.getProperty("saltSizeBytes"), null, null, argumentValues.getProperty("saltGeneratorClassName"), null, null, argumentValues.getProperty("providerName"), null, null, argumentValues.getProperty("providerClassName"), null, null, argumentValues.getProperty("invertPositionOfSaltInMessageBeforeDigesting"), null, null, argumentValues.getProperty("invertPositionOfPlainSaltInEncryptionResults"), null, null, argumentValues.getProperty("useLenientSaltSizeCheck"), null, null, argumentValues.getProperty("unicodeNormalizationIgnored"), null, null, argumentValues.getProperty("stringOutputType"), null, null, argumentValues.getProperty("prefix"), null, null, argumentValues.getProperty("suffix"), null, null);
/* 141:    */      
/* 183:183 */      CLIUtils.showOutput(result, verbose);
/* 184:    */    }
/* 185:    */    catch (Throwable t) {
/* 186:186 */      CLIUtils.showError(t, verbose);
/* 187:    */    }
/* 188:    */  }
/* 189:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.JasyptStringDigestCLI
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */