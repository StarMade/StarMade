/*     */ package org.jasypt.intf.cli;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*     */ 
/*     */ final class CLIUtils
/*     */ {
/*     */   static void showEnvironment(boolean verbose)
/*     */   {
/*  45 */     if (verbose) {
/*  46 */       System.out.println("\n----ENVIRONMENT-----------------\n");
/*  47 */       System.out.println("Runtime: " + System.getProperty("java.vm.vendor") + " " + System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.version") + " ");
/*     */ 
/*  51 */       System.out.println("\n");
/*     */     }
/*     */   }
/*     */ 
/*     */   static void showArgumentDescription(Properties argumentValues, boolean verbose)
/*     */   {
/*  62 */     if (verbose) {
/*  63 */       System.out.println("\n----ARGUMENTS-------------------\n");
/*  64 */       Iterator entriesIter = argumentValues.entrySet().iterator();
/*  65 */       while (entriesIter.hasNext()) {
/*  66 */         Map.Entry entry = (Map.Entry)entriesIter.next();
/*  67 */         System.out.println(entry.getKey() + ": " + entry.getValue());
/*     */       }
/*     */ 
/*  70 */       System.out.println("\n");
/*     */     }
/*     */   }
/*     */ 
/*     */   static void showOutput(String output, boolean verbose)
/*     */   {
/*  81 */     if (verbose) {
/*  82 */       System.out.println("\n----OUTPUT----------------------\n");
/*  83 */       System.out.println(output);
/*  84 */       System.out.println("\n");
/*     */     } else {
/*  86 */       System.out.println(output);
/*     */     }
/*     */   }
/*     */ 
/*     */   static void showError(Throwable t, boolean verbose)
/*     */   {
/*  97 */     if (verbose)
/*     */     {
/*  99 */       System.err.println("\n----ERROR-----------------------\n");
/* 100 */       if ((t instanceof EncryptionOperationNotPossibleException)) {
/* 101 */         System.err.println("Operation not possible (Bad input or parameters)");
/*     */       }
/* 104 */       else if (t.getMessage() != null)
/* 105 */         System.err.println(t.getMessage());
/*     */       else {
/* 107 */         System.err.println(t.getClass().getName());
/*     */       }
/*     */ 
/* 110 */       System.err.println("\n");
/*     */     }
/*     */     else
/*     */     {
/* 114 */       System.err.print("ERROR: ");
/* 115 */       if ((t instanceof EncryptionOperationNotPossibleException)) {
/* 116 */         System.err.println("Operation not possible (Bad input or parameters)");
/*     */       }
/* 119 */       else if (t.getMessage() != null)
/* 120 */         System.err.println(t.getMessage());
/*     */       else
/* 122 */         System.err.println(t.getClass().getName());
/*     */     }
/*     */   }
/*     */ 
/*     */   static boolean getVerbosity(String[] args)
/*     */   {
/* 135 */     for (int i = 0; i < args.length; i++) {
/* 136 */       String key = CommonUtils.substringBefore(args[i], "=");
/* 137 */       String value = CommonUtils.substringAfter(args[i], "=");
/* 138 */       if ((!CommonUtils.isEmpty(key)) && (!CommonUtils.isEmpty(value)))
/*     */       {
/* 141 */         if ("verbose".equals(key)) {
/* 142 */           Boolean verbosity = CommonUtils.getStandardBooleanValue(value);
/*     */ 
/* 144 */           return verbosity != null ? verbosity.booleanValue() : false;
/*     */         }
/*     */       }
/*     */     }
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */   static Properties getArgumentValues(String appName, String[] args, String[][] requiredArgNames, String[][] optionalArgNames)
/*     */   {
/* 157 */     Set argNames = new HashSet();
/* 158 */     for (int i = 0; i < requiredArgNames.length; i++) {
/* 159 */       argNames.addAll(Arrays.asList(requiredArgNames[i]));
/*     */     }
/* 161 */     for (int i = 0; i < optionalArgNames.length; i++) {
/* 162 */       argNames.addAll(Arrays.asList(optionalArgNames[i]));
/*     */     }
/*     */ 
/* 165 */     Properties argumentValues = new Properties();
/* 166 */     for (int i = 0; i < args.length; i++) {
/* 167 */       String key = CommonUtils.substringBefore(args[i], "=");
/* 168 */       String value = CommonUtils.substringAfter(args[i], "=");
/* 169 */       if ((CommonUtils.isEmpty(key)) || (CommonUtils.isEmpty(value))) {
/* 170 */         throw new IllegalArgumentException("Bad argument: " + args[i]);
/*     */       }
/* 172 */       if (argNames.contains(key)) {
/* 173 */         if ((value.startsWith("\"")) && (value.endsWith("\""))) {
/* 174 */           argumentValues.setProperty(key, value.substring(1, value.length() - 1));
/*     */         }
/*     */         else
/*     */         {
/* 178 */           argumentValues.setProperty(key, value);
/*     */         }
/*     */       }
/* 181 */       else throw new IllegalArgumentException("Bad argument: " + args[i]);
/*     */ 
/*     */     }
/*     */ 
/* 186 */     for (int i = 0; i < requiredArgNames.length; i++) {
/* 187 */       boolean found = false;
/* 188 */       for (int j = 0; j < requiredArgNames[i].length; j++) {
/* 189 */         if (argumentValues.containsKey(requiredArgNames[i][j])) {
/* 190 */           found = true;
/*     */         }
/*     */       }
/* 193 */       if (!found) {
/* 194 */         showUsageAndExit(appName, requiredArgNames, optionalArgNames);
/*     */       }
/*     */     }
/*     */ 
/* 198 */     return argumentValues;
/*     */   }
/*     */ 
/*     */   static void showUsageAndExit(String appName, String[][] requiredArgNames, String[][] optionalArgNames)
/*     */   {
/* 209 */     System.err.println("\nUSAGE: " + appName + " [ARGUMENTS]\n");
/* 210 */     System.err.println("  * Arguments must apply to format:\n");
/* 211 */     System.err.println("      \"arg1=value1 arg2=value2 arg3=value3 ...\"");
/*     */ 
/* 213 */     System.err.println();
/* 214 */     System.err.println("  * Required arguments:\n");
/* 215 */     for (int i = 0; i < requiredArgNames.length; i++) {
/* 216 */       System.err.print("      ");
/* 217 */       if (requiredArgNames[i].length == 1) {
/* 218 */         System.err.print(requiredArgNames[i][0]);
/*     */       } else {
/* 220 */         System.err.print("(");
/* 221 */         for (int j = 0; j < requiredArgNames[i].length; j++) {
/* 222 */           if (j > 0) {
/* 223 */             System.err.print(" | ");
/*     */           }
/* 225 */           System.err.print(requiredArgNames[i][j]);
/*     */         }
/* 227 */         System.err.print(")");
/*     */       }
/* 229 */       System.err.println();
/*     */     }
/* 231 */     System.err.println();
/* 232 */     System.err.println("  * Optional arguments:\n");
/* 233 */     for (int i = 0; i < optionalArgNames.length; i++) {
/* 234 */       System.err.print("      ");
/* 235 */       if (optionalArgNames[i].length == 1) {
/* 236 */         System.err.print(optionalArgNames[i][0]);
/*     */       } else {
/* 238 */         System.err.print("(");
/* 239 */         for (int j = 0; j < optionalArgNames[i].length; j++) {
/* 240 */           if (j > 0) {
/* 241 */             System.err.print(" | ");
/*     */           }
/* 243 */           System.err.print(optionalArgNames[i][j]);
/*     */         }
/* 245 */         System.err.print(")");
/*     */       }
/* 247 */       System.err.println();
/*     */     }
/* 249 */     System.exit(1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.CLIUtils
 * JD-Core Version:    0.6.2
 */