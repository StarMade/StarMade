/*    */ package org.lwjgl;
/*    */ 
/*    */ public class LWJGLException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public LWJGLException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public LWJGLException(String msg)
/*    */   {
/* 62 */     super(msg);
/*    */   }
/*    */ 
/*    */   public LWJGLException(String message, Throwable cause)
/*    */   {
/* 70 */     super(message, cause);
/*    */   }
/*    */ 
/*    */   public LWJGLException(Throwable cause)
/*    */   {
/* 77 */     super(cause);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.LWJGLException
 * JD-Core Version:    0.6.2
 */