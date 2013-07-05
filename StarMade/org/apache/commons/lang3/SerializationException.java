/*    */ package org.apache.commons.lang3;
/*    */ 
/*    */ public class SerializationException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 4029025366392702726L;
/*    */ 
/*    */   public SerializationException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SerializationException(String msg)
/*    */   {
/* 52 */     super(msg);
/*    */   }
/*    */ 
/*    */   public SerializationException(Throwable cause)
/*    */   {
/* 63 */     super(cause);
/*    */   }
/*    */ 
/*    */   public SerializationException(String msg, Throwable cause)
/*    */   {
/* 75 */     super(msg, cause);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.SerializationException
 * JD-Core Version:    0.6.2
 */