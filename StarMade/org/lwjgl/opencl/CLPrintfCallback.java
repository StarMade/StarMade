/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import org.lwjgl.PointerWrapperAbstract;
/*    */ 
/*    */ public abstract class CLPrintfCallback extends PointerWrapperAbstract
/*    */ {
/*    */   protected CLPrintfCallback()
/*    */   {
/* 45 */     super(CallbackUtil.getPrintfCallback());
/*    */   }
/*    */ 
/*    */   protected abstract void handleMessage(String paramString);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLPrintfCallback
 * JD-Core Version:    0.6.2
 */