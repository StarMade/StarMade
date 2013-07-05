/*     */ package org.jaxen.saxpath;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ public class SAXPathException extends Exception
/*     */ {
/*     */   private static final long serialVersionUID = 4826444568928720706L;
/*  66 */   private static double javaVersion = 1.4D;
/*     */   private Throwable cause;
/* 113 */   private boolean causeSet = false;
/*     */ 
/*     */   public SAXPathException(String message)
/*     */   {
/*  86 */     super(message);
/*     */   }
/*     */ 
/*     */   public SAXPathException(Throwable cause)
/*     */   {
/*  95 */     super(cause.getMessage());
/*  96 */     initCause(cause);
/*     */   }
/*     */ 
/*     */   public SAXPathException(String message, Throwable cause)
/*     */   {
/* 107 */     super(message);
/* 108 */     initCause(cause);
/*     */   }
/*     */ 
/*     */   public Throwable getCause()
/*     */   {
/* 123 */     return this.cause;
/*     */   }
/*     */ 
/*     */   public Throwable initCause(Throwable cause)
/*     */   {
/* 137 */     if (this.causeSet) throw new IllegalStateException("Cause cannot be reset");
/* 138 */     if (cause == this) throw new IllegalArgumentException("Exception cannot be its own cause");
/* 139 */     this.causeSet = true;
/* 140 */     this.cause = cause;
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */   public void printStackTrace(PrintStream s)
/*     */   {
/* 151 */     super.printStackTrace(s);
/* 152 */     if ((javaVersion < 1.4D) && (getCause() != null)) {
/* 153 */       s.print("Caused by: ");
/* 154 */       getCause().printStackTrace(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void printStackTrace(PrintWriter s)
/*     */   {
/* 165 */     super.printStackTrace(s);
/* 166 */     if ((javaVersion < 1.4D) && (getCause() != null)) {
/* 167 */       s.print("Caused by: ");
/* 168 */       getCause().printStackTrace(s);
/*     */     }
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
/*     */     catch (Exception ex)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.SAXPathException
 * JD-Core Version:    0.6.2
 */