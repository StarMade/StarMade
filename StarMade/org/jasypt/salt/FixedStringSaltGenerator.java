/*     */ package org.jasypt.salt;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ 
/*     */ public class FixedStringSaltGenerator
/*     */   implements SaltGenerator
/*     */ {
/*     */   private static final String DEFAULT_CHARSET = "UTF-8";
/*  50 */   private String salt = null;
/*  51 */   private String charset = "UTF-8";
/*     */ 
/*  53 */   private byte[] saltBytes = null;
/*     */ 
/*     */   public synchronized void setSalt(String salt)
/*     */   {
/*  70 */     CommonUtils.validateNotNull(salt, "Salt cannot be set null");
/*  71 */     this.salt = salt;
/*     */   }
/*     */ 
/*     */   public synchronized void setCharset(String charset)
/*     */   {
/*  81 */     CommonUtils.validateNotNull(charset, "Charset cannot be set null");
/*  82 */     this.charset = charset;
/*     */   }
/*     */ 
/*     */   public byte[] generateSalt(int lengthBytes)
/*     */   {
/*  93 */     if (this.salt == null) {
/*  94 */       throw new EncryptionInitializationException("Salt has not been set");
/*     */     }
/*     */ 
/*  97 */     if (this.saltBytes == null) {
/*     */       try {
/*  99 */         this.saltBytes = this.salt.getBytes(this.charset);
/*     */       } catch (UnsupportedEncodingException e) {
/* 101 */         throw new EncryptionInitializationException("Invalid charset specified: " + this.charset);
/*     */       }
/*     */     }
/*     */ 
/* 105 */     if (this.saltBytes.length < lengthBytes) {
/* 106 */       throw new EncryptionInitializationException("Requested salt larger than set");
/*     */     }
/*     */ 
/* 109 */     byte[] generatedSalt = new byte[lengthBytes];
/* 110 */     System.arraycopy(this.saltBytes, 0, generatedSalt, 0, lengthBytes);
/* 111 */     return generatedSalt;
/*     */   }
/*     */ 
/*     */   public boolean includePlainSaltInEncryptionResults()
/*     */   {
/* 123 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.salt.FixedStringSaltGenerator
 * JD-Core Version:    0.6.2
 */