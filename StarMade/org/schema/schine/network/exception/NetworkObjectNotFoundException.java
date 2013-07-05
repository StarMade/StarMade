/*    */ package org.schema.schine.network.exception;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class NetworkObjectNotFoundException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 5077844495312584968L;
/*    */ 
/*    */   public NetworkObjectNotFoundException(int paramInt, Class paramClass, String paramString, HashMap paramHashMap)
/*    */   {
/* 14 */     super(paramClass.getSimpleName() + " with id " + paramInt + " not found. updateString: " + paramString + ". available: " + paramHashMap);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.exception.NetworkObjectNotFoundException
 * JD-Core Version:    0.6.2
 */