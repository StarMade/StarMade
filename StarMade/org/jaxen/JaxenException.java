/*     */ package org.jaxen;
/*     */ 
/*     */ import org.jaxen.saxpath.SAXPathException;
/*     */ 
/*     */ public class JaxenException extends SAXPathException
/*     */ {
/*     */   private static final long serialVersionUID = 7132891439526672639L;
/*  66 */   static double javaVersion = 1.4D;
/*     */ 
/*     */   public JaxenException(String message)
/*     */   {
/*  87 */     super(message);
/*     */   }
/*     */ 
/*     */   public JaxenException(Throwable rootCause)
/*     */   {
/*  97 */     super(rootCause);
/*     */   }
/*     */ 
/*     */   public JaxenException(String message, Throwable nestedException)
/*     */   {
/* 108 */     super(message, nestedException);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  70 */       String versionString = System.getProperty("java.version");
/*  71 */       versionString = versionString.substring(0, 3);
/*  72 */       javaVersion = Double.valueOf(versionString).doubleValue();
/*     */     }
/*     */     catch (RuntimeException ex)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.JaxenException
 * JD-Core Version:    0.6.2
 */