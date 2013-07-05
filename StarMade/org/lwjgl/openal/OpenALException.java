/*    */ package org.lwjgl.openal;
/*    */ 
/*    */ public class OpenALException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public OpenALException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public OpenALException(int error_code)
/*    */   {
/* 58 */     super("OpenAL error: " + AL10.alGetString(error_code) + " (" + error_code + ")");
/*    */   }
/*    */ 
/*    */   public OpenALException(String message)
/*    */   {
/* 66 */     super(message);
/*    */   }
/*    */ 
/*    */   public OpenALException(String message, Throwable cause)
/*    */   {
/* 75 */     super(message, cause);
/*    */   }
/*    */ 
/*    */   public OpenALException(Throwable cause)
/*    */   {
/* 83 */     super(cause);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.OpenALException
 * JD-Core Version:    0.6.2
 */