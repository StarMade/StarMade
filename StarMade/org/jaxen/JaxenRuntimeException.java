/*     */ package org.jaxen;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ public class JaxenRuntimeException extends RuntimeException
/*     */ {
/*     */   private static final long serialVersionUID = -930309761511911193L;
/*     */   private Throwable cause;
/*  66 */   private boolean causeSet = false;
/*     */ 
/*     */   public JaxenRuntimeException(Throwable cause)
/*     */   {
/*  76 */     super(cause.getMessage());
/*  77 */     initCause(cause);
/*     */   }
/*     */ 
/*     */   public JaxenRuntimeException(String message)
/*     */   {
/*  86 */     super(message);
/*     */   }
/*     */ 
/*     */   public Throwable getCause()
/*     */   {
/*  97 */     return this.cause;
/*     */   }
/*     */ 
/*     */   public Throwable initCause(Throwable cause)
/*     */   {
/* 111 */     if (this.causeSet) throw new IllegalStateException("Cause cannot be reset");
/* 112 */     if (cause == this) throw new IllegalArgumentException("Exception cannot be its own cause");
/* 113 */     this.causeSet = true;
/* 114 */     this.cause = cause;
/* 115 */     return this;
/*     */   }
/*     */ 
/*     */   public void printStackTrace(PrintStream s)
/*     */   {
/* 125 */     super.printStackTrace(s);
/* 126 */     if ((JaxenException.javaVersion < 1.4D) && (getCause() != null)) {
/* 127 */       s.print("Caused by: ");
/* 128 */       getCause().printStackTrace(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void printStackTrace(PrintWriter s)
/*     */   {
/* 139 */     super.printStackTrace(s);
/* 140 */     if ((JaxenException.javaVersion < 1.4D) && (getCause() != null)) {
/* 141 */       s.print("Caused by: ");
/* 142 */       getCause().printStackTrace(s);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.JaxenRuntimeException
 * JD-Core Version:    0.6.2
 */