/*    */ package org.jasypt.intf.cli;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Set;
/*    */ import org.jasypt.registry.AlgorithmRegistry;
/*    */ 
/*    */ public final class AlgorithmRegistryCLI
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*    */     try
/*    */     {
/* 55 */       Set digestAlgos = AlgorithmRegistry.getAllDigestAlgorithms();
/* 56 */       Set pbeAlgos = AlgorithmRegistry.getAllPBEAlgorithms();
/*    */ 
/* 58 */       System.out.println();
/* 59 */       System.out.println("DIGEST ALGORITHMS:   " + digestAlgos);
/* 60 */       System.out.println();
/* 61 */       System.out.println("PBE ALGORITHMS:      " + pbeAlgos);
/* 62 */       System.out.println();
/*    */     }
/*    */     catch (Throwable t) {
/* 65 */       t.printStackTrace(System.err);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.AlgorithmRegistryCLI
 * JD-Core Version:    0.6.2
 */