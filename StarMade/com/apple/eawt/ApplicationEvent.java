/*    */ package com.apple.eawt;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ 
/*    */ public class ApplicationEvent extends EventObject
/*    */ {
/*    */   ApplicationEvent(Object paramObject)
/*    */   {
/*  8 */     super(paramObject);
/*    */   }
/*    */ 
/*    */   ApplicationEvent(Object paramObject, String paramString) {
/* 12 */     super(paramObject);
/*    */   }
/*    */ 
/*    */   public boolean isHandled() {
/* 16 */     return false;
/*    */   }
/*    */   public void setHandled(boolean paramBoolean) {
/*    */   }
/*    */ 
/*    */   public String getFilename() {
/* 22 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.apple.eawt.ApplicationEvent
 * JD-Core Version:    0.6.2
 */