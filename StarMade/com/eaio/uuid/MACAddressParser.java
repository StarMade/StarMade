/*     */ package com.eaio.uuid;
/*     */ 
/*     */ class MACAddressParser
/*     */ {
/*     */   static String parse(String in)
/*     */   {
/*  58 */     String out = in;
/*     */ 
/*  62 */     int hexStart = out.indexOf("0x");
/*  63 */     if ((hexStart != -1) && (out.indexOf("ETHER") != -1)) {
/*  64 */       int hexEnd = out.indexOf(' ', hexStart);
/*  65 */       if (hexEnd > hexStart + 2) {
/*  66 */         out = out.substring(hexStart, hexEnd);
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*  72 */       int octets = 0;
/*     */ 
/*  75 */       if (out.indexOf('-') > -1) {
/*  76 */         out = out.replace('-', ':');
/*     */       }
/*     */ 
/*  79 */       int lastIndex = out.lastIndexOf(':');
/*     */ 
/*  81 */       if (lastIndex > out.length() - 2) {
/*  82 */         out = null;
/*     */       }
/*     */       else
/*     */       {
/*  86 */         int end = Math.min(out.length(), lastIndex + 3);
/*     */ 
/*  88 */         octets++;
/*  89 */         int old = lastIndex;
/*  90 */         while ((octets != 5) && (lastIndex != -1) && (lastIndex > 1)) {
/*  91 */           lastIndex = out.lastIndexOf(':', --lastIndex);
/*  92 */           if ((old - lastIndex == 3) || (old - lastIndex == 2)) {
/*  93 */             octets++;
/*  94 */             old = lastIndex;
/*     */           }
/*     */         }
/*     */ 
/*  98 */         if ((octets == 5) && (lastIndex > 1)) {
/*  99 */           out = out.substring(lastIndex - 2, end).trim();
/*     */         }
/*     */         else {
/* 102 */           out = null;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 109 */     if ((out != null) && (out.startsWith("0x"))) {
/* 110 */       out = out.substring(2);
/*     */     }
/*     */ 
/* 113 */     return out;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.MACAddressParser
 * JD-Core Version:    0.6.2
 */