/*     */ package org.jasypt.intf.cli;
/*     */ 
/*     */ import java.util.Properties;
/*     */ import org.jasypt.intf.service.JasyptStatelessService;
/*     */ 
/*     */ public final class JasyptPBEStringEncryptionCLI
/*     */ {
/*  46 */   private static final String[][] VALID_REQUIRED_ARGUMENTS = { { "input" }, { "password" } };
/*     */ 
/*  59 */   private static final String[][] VALID_OPTIONAL_ARGUMENTS = { { "verbose" }, { "algorithm" }, { "keyObtentionIterations" }, { "saltGeneratorClassName" }, { "providerName" }, { "providerClassName" }, { "stringOutputType" } };
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  94 */     boolean verbose = CLIUtils.getVerbosity(args);
/*     */     try
/*     */     {
/*  98 */       String applicationName = null;
/*  99 */       String[] arguments = null;
/* 100 */       if ((args[0] == null) || (args[0].indexOf("=") != -1)) {
/* 101 */         applicationName = JasyptPBEStringEncryptionCLI.class.getName();
/* 102 */         arguments = args;
/*     */       } else {
/* 104 */         applicationName = args[0];
/* 105 */         arguments = new String[args.length - 1];
/* 106 */         System.arraycopy(args, 1, arguments, 0, args.length - 1);
/*     */       }
/*     */ 
/* 109 */       Properties argumentValues = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
/*     */ 
/* 114 */       CLIUtils.showEnvironment(verbose);
/*     */ 
/* 116 */       JasyptStatelessService service = new JasyptStatelessService();
/*     */ 
/* 118 */       String input = argumentValues.getProperty("input");
/*     */ 
/* 120 */       CLIUtils.showArgumentDescription(argumentValues, verbose);
/*     */ 
/* 122 */       String result = service.encrypt(input, argumentValues.getProperty("password"), null, null, argumentValues.getProperty("algorithm"), null, null, argumentValues.getProperty("keyObtentionIterations"), null, null, argumentValues.getProperty("saltGeneratorClassName"), null, null, argumentValues.getProperty("providerName"), null, null, argumentValues.getProperty("providerClassName"), null, null, argumentValues.getProperty("stringOutputType"), null, null);
/*     */ 
/* 147 */       CLIUtils.showOutput(result, verbose);
/*     */     }
/*     */     catch (Throwable t) {
/* 150 */       CLIUtils.showError(t, verbose);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI
 * JD-Core Version:    0.6.2
 */