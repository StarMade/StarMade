/*    */ package org.schema.schine.network.exception;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.ServerSocket;
/*    */ 
/*    */ public class ServerPortNotAvailableException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 906116778403491118L;
/*    */   private boolean instanceRunning;
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 15 */     paramArrayOfString = null;
/*    */     try {
/* 17 */       paramArrayOfString = new ServerSocket(4242);
/*    */     }
/*    */     catch (IOException localIOException1) {
/*    */     }
/*    */     while (true)
/*    */       try {
/* 23 */         paramArrayOfString.accept();
/*    */       }
/*    */       catch (IOException localIOException2)
/*    */       {
/* 27 */         localIOException2.printStackTrace();
/*    */       }
/*    */   }
/*    */ 
/*    */   public ServerPortNotAvailableException(String paramString)
/*    */   {
/* 34 */     super(paramString);
/*    */   }
/*    */ 
/*    */   public boolean isInstanceRunning()
/*    */   {
/* 41 */     return this.instanceRunning;
/*    */   }
/*    */ 
/*    */   public void setInstanceRunning(boolean paramBoolean)
/*    */   {
/* 48 */     this.instanceRunning = paramBoolean;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.exception.ServerPortNotAvailableException
 * JD-Core Version:    0.6.2
 */