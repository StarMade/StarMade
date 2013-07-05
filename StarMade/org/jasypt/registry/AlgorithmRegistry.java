/*    */ package org.jasypt.registry;
/*    */ 
/*    */ import java.security.Security;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ public final class AlgorithmRegistry
/*    */ {
/*    */   public static Set getAllDigestAlgorithms()
/*    */   {
/* 61 */     List algos = new ArrayList(Security.getAlgorithms("MessageDigest"));
/* 62 */     Collections.sort(algos);
/* 63 */     return Collections.unmodifiableSet(new LinkedHashSet(algos));
/*    */   }
/*    */ 
/*    */   public static Set getAllPBEAlgorithms()
/*    */   {
/* 81 */     List algos = new ArrayList(Security.getAlgorithms("Cipher"));
/* 82 */     Collections.sort(algos);
/* 83 */     LinkedHashSet pbeAlgos = new LinkedHashSet();
/* 84 */     Iterator algosIter = algos.iterator();
/* 85 */     while (algosIter.hasNext()) {
/* 86 */       String algo = (String)algosIter.next();
/* 87 */       if ((algo != null) && (algo.startsWith("PBE"))) {
/* 88 */         pbeAlgos.add(algo);
/*    */       }
/*    */     }
/* 91 */     return Collections.unmodifiableSet(pbeAlgos);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.registry.AlgorithmRegistry
 * JD-Core Version:    0.6.2
 */