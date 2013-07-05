/*    */ package org.schema.schine.network.udp;
/*    */ 
/*    */ public class UDPException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 2679247287365962074L;
/*    */   private Exception ioException;
/*    */ 
/*    */   public UDPException(String paramString)
/*    */   {
/* 12 */     super(paramString);
/*    */   }
/*    */ 
/*    */   public UDPException(String paramString, Exception paramException) {
/* 16 */     super(paramString);
/* 17 */     this.ioException = paramException;
/*    */   }
/*    */ 
/*    */   public Exception getIoException()
/*    */   {
/* 24 */     return this.ioException;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPException
 * JD-Core Version:    0.6.2
 */