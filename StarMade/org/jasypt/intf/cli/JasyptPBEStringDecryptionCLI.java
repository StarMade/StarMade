/*     */ package org.jasypt.intf.cli;
/*     */ 
/*     */ import java.util.Properties;
/*     */ import org.jasypt.intf.service.JasyptStatelessService;
/*     */ 
/*     */ public final class JasyptPBEStringDecryptionCLI
/*     */ {
/*  47 */   private static final String[][] VALID_REQUIRED_ARGUMENTS = { { "input" }, { "password" } };
/*     */ 
/*  60 */   private static final String[][] VALID_OPTIONAL_ARGUMENTS = { { "verbose" }, { "algorithm" }, { "keyObtentionIterations" }, { "saltGeneratorClassName" }, { "providerName" }, { "providerClassName" }, { "stringOutputType" } };
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  95 */     boolean verbose = CLIUtils.getVerbosity(args);
/*     */     try
/*     */     {
/*  99 */       String applicationName = null;
/* 100 */       String[] arguments = null;
/* 101 */       if ((args[0] == null) || (args[0].indexOf("=") != -1)) {
/* 102 */         applicationName = JasyptPBEStringDecryptionCLI.class.getName();
/* 103 */         arguments = args;
/*     */       } else {
/* 105 */         applicationName = args[0];
/* 106 */         arguments = new String[args.length - 1];
/* 107 */         System.arraycopy(args, 1, arguments, 0, args.length - 1);
/*     */       }
/*     */ 
/* 110 */       Properties argumentValues = CLIUtils.getArgumentValues(applicationName, arguments, VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);
/*     */ 
/* 115 */       CLIUtils.showEnvironment(verbose);
/*     */ 
/* 117 */       JasyptStatelessService service = new JasyptStatelessService();
/*     */ 
/* 119 */       String input = argumentValues.getProperty("input");
/*     */ 
/* 121 */       CLIUtils.showArgumentDescription(argumentValues, verbose);
/*     */ 
/* 123 */       String result = service.decrypt(input, argumentValues.getProperty("password"), null, null, argumentValues.getProperty("algorithm"), null, null, argumentValues.getProperty("keyObtentionIterations"), null, null, argumentValues.getProperty("saltGeneratorClassName"), null, null, argumentValues.getProperty("providerName"), null, null, argumentValues.getProperty("providerClassName"), null, null, argumentValues.getProperty("stringOutputType"), null, null);
/*     */ 
/* 148 */       CLIUtils.showOutput(result, verbose);
/*     */     }
/*     */     catch (Throwable t) {
/* 151 */       CLIUtils.showError(t, verbose);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI
 * JD-Core Version:    0.6.2
 */