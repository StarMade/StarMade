/*     */ package org.jasypt.intf.cli;
/*     */ 
/*     */ import java.util.Properties;
/*     */ import org.jasypt.intf.service.JasyptStatelessService;
/*     */ 
/*     */ public final class JasyptStringDigestCLI
/*     */ {
/*  46 */   private static final String[][] VALID_REQUIRED_ARGUMENTS = { { "input" } };
/*     */ 
/*  56 */   private static final String[][] VALID_OPTIONAL_ARGUMENTS = { { "verbose" }, { "algorithm" }, { "iterations" }, { "saltSizeBytes" }, { "saltGeneratorClassName" }, { "providerName" }, { "providerClassName" }, { "invertPositionOfSaltInMessageBeforeDigesting" }, { "invertPositionOfPlainSaltInEncryptionResults" }, { "useLenientSaltSizeCheck" }, { "unicodeNormalizationIgnored" }, { "stringOutputType" }, { "prefix" }, { "suffix" } };
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 112 */     boolean verbose = CLIUtils.getVerbosity(args);
/*     */     try
/*     */     {
/* 116 */       String applicationName = null;
/* 117 */       String[] arguments = null;
/* 118 */       if ((args[0] == null) || (args[0].indexOf("=") != -1)) {
/* 119 */         applicationName = JasyptStringDigestCLI.class.getName();
/* 120 */         arguments = args;
/*     */       } else {
/* 122 */         applicationName = args[0];
/* 123 */         arguments = new String[args.length - 1];
/* 124 */         System.arraycopy(args, 1, arguments, 0, args.length - 1);
/*     */       }
/*     */ 
/* 127 */       Properties argumentValues = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
/*     */ 
/* 132 */       CLIUtils.showEnvironment(verbose);
/*     */ 
/* 134 */       JasyptStatelessService service = new JasyptStatelessService();
/*     */ 
/* 136 */       String input = argumentValues.getProperty("input");
/*     */ 
/* 138 */       CLIUtils.showArgumentDescription(argumentValues, verbose);
/*     */ 
/* 140 */       String result = service.digest(input, argumentValues.getProperty("algorithm"), null, null, argumentValues.getProperty("iterations"), null, null, argumentValues.getProperty("saltSizeBytes"), null, null, argumentValues.getProperty("saltGeneratorClassName"), null, null, argumentValues.getProperty("providerName"), null, null, argumentValues.getProperty("providerClassName"), null, null, argumentValues.getProperty("invertPositionOfSaltInMessageBeforeDigesting"), null, null, argumentValues.getProperty("invertPositionOfPlainSaltInEncryptionResults"), null, null, argumentValues.getProperty("useLenientSaltSizeCheck"), null, null, argumentValues.getProperty("unicodeNormalizationIgnored"), null, null, argumentValues.getProperty("stringOutputType"), null, null, argumentValues.getProperty("prefix"), null, null, argumentValues.getProperty("suffix"), null, null);
/*     */ 
/* 183 */       CLIUtils.showOutput(result, verbose);
/*     */     }
/*     */     catch (Throwable t) {
/* 186 */       CLIUtils.showError(t, verbose);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.JasyptStringDigestCLI
 * JD-Core Version:    0.6.2
 */