/*     */ package org.jaxen;
/*     */ 
/*     */ public class FunctionCallException extends JaxenException
/*     */ {
/*     */   private static final long serialVersionUID = 7908649612495640943L;
/*     */ 
/*     */   public FunctionCallException(String message)
/*     */   {
/*  74 */     super(message);
/*     */   }
/*     */ 
/*     */   public FunctionCallException(Throwable nestedException)
/*     */   {
/*  83 */     super(nestedException);
/*     */   }
/*     */ 
/*     */   public FunctionCallException(String message, Exception nestedException)
/*     */   {
/*  94 */     super(message, nestedException);
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public Throwable getNestedException()
/*     */   {
/* 108 */     return getCause();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.FunctionCallException
 * JD-Core Version:    0.6.2
 */