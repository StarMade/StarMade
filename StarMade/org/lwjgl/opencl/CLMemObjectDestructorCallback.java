/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import org.lwjgl.PointerWrapperAbstract;
/*    */ 
/*    */ public abstract class CLMemObjectDestructorCallback extends PointerWrapperAbstract
/*    */ {
/*    */   protected CLMemObjectDestructorCallback()
/*    */   {
/* 44 */     super(CallbackUtil.getMemObjectDestructorCallback());
/*    */   }
/*    */ 
/*    */   protected abstract void handleMessage(long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLMemObjectDestructorCallback
 * JD-Core Version:    0.6.2
 */