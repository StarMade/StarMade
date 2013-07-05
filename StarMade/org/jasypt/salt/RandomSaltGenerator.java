/*     */ package org.jasypt.salt;
/*     */ 
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ 
/*     */ public class RandomSaltGenerator
/*     */   implements SaltGenerator
/*     */ {
/*     */   public static final String DEFAULT_SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
/*     */   private final SecureRandom random;
/*     */ 
/*     */   public RandomSaltGenerator()
/*     */   {
/*  62 */     this("SHA1PRNG");
/*     */   }
/*     */ 
/*     */   public RandomSaltGenerator(String secureRandomAlgorithm)
/*     */   {
/*     */     try
/*     */     {
/*  76 */       this.random = SecureRandom.getInstance(secureRandomAlgorithm);
/*  77 */       this.random.setSeed(System.currentTimeMillis());
/*     */     } catch (NoSuchAlgorithmException e) {
/*  79 */       throw new EncryptionInitializationException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] generateSalt(int lengthBytes)
/*     */   {
/*  91 */     byte[] salt = new byte[lengthBytes];
/*  92 */     synchronized (this.random) {
/*  93 */       this.random.nextBytes(salt);
/*     */     }
/*  95 */     return salt;
/*     */   }
/*     */ 
/*     */   public boolean includePlainSaltInEncryptionResults()
/*     */   {
/* 107 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.salt.RandomSaltGenerator
 * JD-Core Version:    0.6.2
 */