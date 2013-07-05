/*    */ package org.dom4j;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ public class DocumentException extends Exception
/*    */ {
/*    */   private Throwable nestedException;
/*    */ 
/*    */   public DocumentException()
/*    */   {
/* 27 */     super("Error occurred in DOM4J application.");
/*    */   }
/*    */ 
/*    */   public DocumentException(String message) {
/* 31 */     super(message);
/*    */   }
/*    */ 
/*    */   public DocumentException(Throwable nestedException) {
/* 35 */     super(nestedException.getMessage());
/* 36 */     this.nestedException = nestedException;
/*    */   }
/*    */ 
/*    */   public DocumentException(String message, Throwable nestedException) {
/* 40 */     super(message);
/* 41 */     this.nestedException = nestedException;
/*    */   }
/*    */ 
/*    */   public Throwable getNestedException() {
/* 45 */     return this.nestedException;
/*    */   }
/*    */ 
/*    */   public String getMessage() {
/* 49 */     if (this.nestedException != null) {
/* 50 */       return super.getMessage() + " Nested exception: " + this.nestedException.getMessage();
/*    */     }
/*    */ 
/* 53 */     return super.getMessage();
/*    */   }
/*    */ 
/*    */   public void printStackTrace()
/*    */   {
/* 58 */     super.printStackTrace();
/*    */ 
/* 60 */     if (this.nestedException != null) {
/* 61 */       System.err.print("Nested exception: ");
/* 62 */       this.nestedException.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void printStackTrace(PrintStream out) {
/* 67 */     super.printStackTrace(out);
/*    */ 
/* 69 */     if (this.nestedException != null) {
/* 70 */       out.println("Nested exception: ");
/* 71 */       this.nestedException.printStackTrace(out);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void printStackTrace(PrintWriter writer) {
/* 76 */     super.printStackTrace(writer);
/*    */ 
/* 78 */     if (this.nestedException != null) {
/* 79 */       writer.println("Nested exception: ");
/* 80 */       this.nestedException.printStackTrace(writer);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.DocumentException
 * JD-Core Version:    0.6.2
 */