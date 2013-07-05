/*     */ package org.jasypt.encryption.pbe;
/*     */ 
/*     */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*     */ 
/*     */ final class NumberUtils
/*     */ {
/*     */   static byte[] byteArrayFromInt(int number)
/*     */   {
/*  39 */     byte b0 = (byte)(0xFF & number);
/*  40 */     byte b1 = (byte)(0xFF & number >> 8);
/*  41 */     byte b2 = (byte)(0xFF & number >> 16);
/*  42 */     byte b3 = (byte)(0xFF & number >> 24);
/*  43 */     return new byte[] { b3, b2, b1, b0 };
/*     */   }
/*     */ 
/*     */   static int intFromByteArray(byte[] byteArray)
/*     */   {
/*  49 */     if ((byteArray == null) || (byteArray.length == 0)) {
/*  50 */       throw new IllegalArgumentException("Cannot convert an empty array into an int");
/*     */     }
/*     */ 
/*  53 */     int result = 0xFF & byteArray[0];
/*  54 */     for (int i = 1; i < byteArray.length; i++) {
/*  55 */       result = result << 8 | 0xFF & byteArray[i];
/*     */     }
/*     */ 
/*  58 */     return result;
/*     */   }
/*     */ 
/*     */   static byte[] processBigIntegerEncryptedByteArray(byte[] byteArray, int signum)
/*     */   {
/*  66 */     if (byteArray.length > 4)
/*     */     {
/*  68 */       int initialSize = byteArray.length;
/*     */ 
/*  70 */       byte[] encryptedMessageExpectedSizeBytes = new byte[4];
/*  71 */       System.arraycopy(byteArray, initialSize - 4, encryptedMessageExpectedSizeBytes, 0, 4);
/*     */ 
/*  73 */       byte[] processedByteArray = new byte[initialSize - 4];
/*  74 */       System.arraycopy(byteArray, 0, processedByteArray, 0, initialSize - 4);
/*     */ 
/*  76 */       int expectedSize = intFromByteArray(encryptedMessageExpectedSizeBytes);
/*     */ 
/*  78 */       if ((expectedSize < 0) || (expectedSize > maxSafeSizeInBytes())) {
/*  79 */         throw new EncryptionOperationNotPossibleException();
/*     */       }
/*     */ 
/*  85 */       if (processedByteArray.length != expectedSize)
/*     */       {
/*  90 */         int sizeDifference = expectedSize - processedByteArray.length;
/*     */ 
/*  93 */         byte[] paddedProcessedByteArray = new byte[expectedSize];
/*  94 */         for (int i = 0; i < sizeDifference; i++) {
/*  95 */           paddedProcessedByteArray[i] = (signum >= 0 ? 0 : -1);
/*     */         }
/*     */ 
/* 101 */         System.arraycopy(processedByteArray, 0, paddedProcessedByteArray, sizeDifference, processedByteArray.length);
/*     */ 
/* 103 */         return paddedProcessedByteArray;
/*     */       }
/*     */ 
/* 107 */       return processedByteArray;
/*     */     }
/*     */ 
/* 111 */     return (byte[])byteArray.clone();
/*     */   }
/*     */ 
/*     */   private static int maxSafeSizeInBytes()
/*     */   {
/* 127 */     long max = Runtime.getRuntime().maxMemory();
/* 128 */     long free = Runtime.getRuntime().freeMemory();
/* 129 */     long total = Runtime.getRuntime().totalMemory();
/* 130 */     return (int)((free + (max - total)) / 2L);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.NumberUtils
 * JD-Core Version:    0.6.2
 */