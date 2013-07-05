/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ public class OpenCLException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public OpenCLException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public OpenCLException(String message)
/*    */   {
/* 43 */     super(message);
/*    */   }
/*    */ 
/*    */   public OpenCLException(String message, Throwable cause) {
/* 47 */     super(message, cause);
/*    */   }
/*    */ 
/*    */   public OpenCLException(Throwable cause) {
/* 51 */     super(cause);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.OpenCLException
 * JD-Core Version:    0.6.2
 */